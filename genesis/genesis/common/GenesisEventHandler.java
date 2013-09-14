package genesis.genesis.common;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.Blocks;
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
	
	private static String getClassHierarchy(Object startingInstance)
	{
        String classHierarchy = "";
        Class currentClass = startingInstance.getClass();
        
        do
        {
        	if (classHierarchy != "")
        		classHierarchy += "->";
        	
        	classHierarchy += currentClass.getSimpleName();
        	
        	currentClass = currentClass.getSuperclass();
        } while (!currentClass.equals(Object.class));
        
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
	            String blockHier = getClassHierarchy(block);
	            
	            ItemBlock itemBlock = (ItemBlock)Item.itemsList[blockID];
	            ItemStack pickStack = block.getPickBlock(lookPos, mc.theWorld, hitX, hitY, hitZ);
	            String locName = itemBlock.getItemDisplayName(pickStack);
	            
	            TileEntity tileEntity = mc.theWorld.getBlockTileEntity(hitX, hitY, hitZ);
	            String entityHier = null;
	            
	            if (tileEntity != null)
	            {
	            	entityHier = getClassHierarchy(tileEntity);
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
	            left.add(String.format("bid: %d:%d, bname: \"%s\"", blockID, metadata, locName));
	            left.add(String.format("sld: %b, opq: %b, side: %s", solidOnSide, opaque, hitDir));
	            left.add(String.format("bhier: %s", blockHier));
	            left.add(String.format("light (%s): %d (full: %d), opac: %d", opaque ? "on" : "in", lightVal, fullLightVal, lightOpac));
	            
	            if (tileEntity != null)
	            {
	            	left.add(String.format("tehier: %s", entityHier));
	            }
	        }
	        else
	        {
	            left.add("Block info:");
	            left.add("N/A");
	        }
		}
	}
	
}
