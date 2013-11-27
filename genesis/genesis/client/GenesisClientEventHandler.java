package genesis.genesis.client;

import genesis.genesis.block.plants.BlockCalamitesPlant;
import genesis.genesis.block.plants.BlockCalamitesPlant.CalamitesProperties;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.registry.BlockProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GenesisClientEventHandler {
	
	private static final List<Class> blockExclude = new ArrayList() {{
		add(BlockProxy.class);
	}};
	
	private static final List<Class> tileEntityExclude = new ArrayList() {{
	}};
	
	private static void addAllFromArrayReverse(ArrayList list, Object[] array)
	{
		int i = 0;
		
		for (Object obj : array)
		{
			list.add(i++, obj);
		}
	}
	
	private static String getClassHierarchy(Object startingInstance, List<Class> excludeClasses)
	{
		String classHierarchy = "";
		Class currentClass = startingInstance.getClass();
		
		ArrayList<Class> interfaces = new ArrayList();
		
		do
		{
			if (!excludeClasses.contains(currentClass))
			{
				if (classHierarchy != "")
					classHierarchy += "->";
				
				classHierarchy += currentClass.getSimpleName();
			}
			
			addAllFromArrayReverse(interfaces, currentClass.getInterfaces());
			
			currentClass = currentClass.getSuperclass();
		} while (!currentClass.equals(Object.class));
		
		for (Class clazz : excludeClasses)
		{
			interfaces.remove(clazz);
		}
		
		if (interfaces.size() > 0)
		{
			classHierarchy += " (";
			boolean start = true;
			
			for (Class iClass : interfaces)
			{
				if (!start)
					classHierarchy += ", ";
				
				classHierarchy += iClass.getSimpleName();
				
				start = false;
			}
			
			classHierarchy += ")";
		}
		
		return classHierarchy;
	}
	
	@ForgeSubscribe
	public void addDebugText(RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = ClientProxy.getMC();
		
		if (mc.gameSettings.showDebugInfo)
		{
			ArrayList<String> left = event.left;
			
			left.add(null);
			
			MovingObjectPosition lookPos = mc.renderViewEntity.rayTrace(100, ClientTickHandler.partialTick);
			float length = 100;
	        Vec3 start = mc.renderViewEntity.getPosition(ClientTickHandler.partialTick);
	        start = start.addVector(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);
	        Vec3 look = mc.renderViewEntity.getLook(ClientTickHandler.partialTick);
	        Vec3 end = start.addVector(look.xCoord * length, look.yCoord * length, look.zCoord * length);
	        lookPos = mc.renderViewEntity.worldObj.clip(start, end);
			
			if (lookPos != null && lookPos.typeOfHit == EnumMovingObjectType.TILE)
			{
				int hitX = lookPos.blockX;
				int hitY = lookPos.blockY;
				int hitZ = lookPos.blockZ;
				
				int blockID = mc.theWorld.getBlockId(hitX, hitY, hitZ);
				int metadata = mc.theWorld.getBlockMetadata(hitX, hitY, hitZ);
				Material mat = mc.theWorld.getBlockMaterial(hitX, hitY, hitZ);
				
				Block block = Block.blocksList[blockID];
				String blockHier = getClassHierarchy(block, blockExclude);
				
				String locName = null;
				
				String extraStuff = "";
				
				Item blockItem = Item.itemsList[blockID];
				
				if (blockItem instanceof ItemBlock)
				{
					ItemBlock itemBlock = (ItemBlock)blockItem;
					ItemStack pickStack = block.getPickBlock(lookPos, mc.theWorld, hitX, hitY, hitZ);
					
					locName = itemBlock.getItemDisplayName(pickStack);
				}
				
				if (block instanceof BlockCalamitesPlant)
				{
					CalamitesProperties props = ((BlockCalamitesPlant)block).getProperties(mc.theWorld, hitX, hitY, hitZ);
					extraStuff = String.format("height: %d, position: %d, eggs: %b, top: %b",
							props.height, props.position, props.hasEggs, props.top);
				}
				
				TileEntity tileEntity = mc.theWorld.getBlockTileEntity(hitX, hitY, hitZ);
				String entityHier = null;
				
				if (tileEntity != null)
				{
					entityHier = getClassHierarchy(tileEntity, tileEntityExclude);
				}
				
				int lightOpac = mc.theWorld.getBlockLightOpacity(hitX, hitY, hitZ);
				
				ForgeDirection hitDir = ForgeDirection.getOrientation(lookPos.sideHit);
				//String 
				
				boolean solidOnSide = block.isBlockSolidOnSide(mc.theWorld, hitX, hitY, hitZ, hitDir);
				boolean opaque = block.isOpaqueCube();
				
				if (opaque)
				{
					hitX += hitDir.offsetX;
					hitY += hitDir.offsetY;
					hitZ += hitDir.offsetZ;
				}
				
				int lightVal = mc.theWorld.getBlockLightValue(hitX, hitY, hitZ);
				int fullLightVal = mc.theWorld.getFullBlockLightValue(hitX, hitY, hitZ);

				left.add("Block info:");
				left.add(String.format("bid: %d:%d, bname: %s, mat: %s",
						blockID, metadata, locName == null ? "N/A" : "\"" + locName + "\"",
						mat.getClass().getSimpleName()));
				left.add(String.format("sld: %b, opq: %b, side: %s ",
						solidOnSide, opaque, hitDir));
				left.add(String.format("bhier: %s",
						blockHier));
				left.add(String.format("light (%s): %d (full: %d), opac: %d",
						opaque ? "on" : "in", lightVal, fullLightVal, lightOpac));
				
				if (tileEntity != null)
				{
					left.add(String.format("tehier: %s",
							entityHier));
				}
				
				left.add(extraStuff);
			}
			else
			{
				left.add("Block info:");
				left.add("N/A");
			}
		}
	}
	
}
