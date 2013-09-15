package genesis.genesis.common;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.BlockProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.plants.BlockCalamitesPlant;
import genesis.genesis.block.plants.BlockCalamitesPlant.CalamitesProperties;
import genesis.genesis.block.trees.BlockGenesisSapling;
import genesis.genesis.client.ClientTickHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class GenesisEventHandler {
	
	@SideOnly(Side.CLIENT)
	Minecraft mc;
	
	@SideOnly(Side.CLIENT)
	private static final List<Class> blockExclude = new ArrayList() {{
		add(BlockProxy.class);
	}};
	
	@SideOnly(Side.CLIENT)
	private static final List<Class> tileEntityExclude = new ArrayList() {{
	}};
	
	@SideOnly(Side.CLIENT)
	private void getMC()
	{
		if (mc == null)
			mc = Minecraft.getMinecraft();
	}
	
	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event)
	{
		if (!event.world.isRemote)
		{
			int blockID = event.world.getBlockId(event.X, event.Y, event.Z);
			Block block = Block.blocksList[blockID];
			
			if (block instanceof BlockGenesisSapling)
			{
				BlockGenesisSapling sapling = (BlockGenesisSapling)block;
				sapling.growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}
	
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
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void addDebugText(RenderGameOverlayEvent.Text event)
	{
		getMC();
		
		if (mc.gameSettings.showDebugInfo)
		{
			ArrayList<String> left = event.left;
			
			left.add(null);
			
			MovingObjectPosition lookPos = mc.renderViewEntity.rayTrace(100, ClientTickHandler.partialTick);
			
			if (lookPos != null && lookPos.typeOfHit == EnumMovingObjectType.TILE)
			{
				int hitX = lookPos.blockX;
				int hitY = lookPos.blockY;
				int hitZ = lookPos.blockZ;
				
				int blockID = mc.theWorld.getBlockId(hitX, hitY, hitZ);
				int metadata = mc.theWorld.getBlockMetadata(hitX, hitY, hitZ);
				
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
				left.add(String.format("bid: %d:%d, bname: %s",
						blockID, metadata, locName == null ? "N/A" : "\"" + locName + "\""));
				left.add(String.format("sld: %b, opq: %b, side: %s",
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
