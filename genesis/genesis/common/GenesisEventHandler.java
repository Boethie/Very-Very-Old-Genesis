package genesis.genesis.common;

import java.util.ArrayList;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.BlockGenesisSapling;
import genesis.genesis.client.ClientTickHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class GenesisEventHandler {
	
	Minecraft mc;
	
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
	            int lightOpac = mc.theWorld.getBlockLightOpacity(hitX, hitY, hitZ);
	            
	            ForgeDirection hitDir = ForgeDirection.getOrientation(lookPos.sideHit);
	
	            hitX += hitDir.offsetX;
	            hitY += hitDir.offsetY;
	            hitZ += hitDir.offsetZ;
	            
	            int lightVal = mc.theWorld.getBlockLightValue(hitX, hitY, hitZ);
	            int fullLightVal = mc.theWorld.getFullBlockLightValue(hitX, hitY, hitZ);
	            
	            left.add(String.format("bi: %d, lv: %d, flv: %d, lo: %d", blockID, lightVal, fullLightVal, lightOpac));
	        }
	        else
	        {
	            left.add(String.format("NO BLOCK"));
	        }
		}
	}
	
}
