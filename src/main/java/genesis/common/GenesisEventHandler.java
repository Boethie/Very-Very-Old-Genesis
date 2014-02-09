package genesis.common;

import net.minecraft.block.Block;

import net.minecraftforge.event.entity.player.BonemealEvent;

import genesis.block.trees.BlockGenesisSapling;

public class GenesisEventHandler {
	
	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event)
	{
		if (!event.world.isRemote)
		{
			int blockID = event.world.getBlockId(event.X, event.Y, event.Z);
			Block block = Block.blocksList[blockID];
			
			if (block instanceof BlockGenesisSapling)
			{
				if (event.world.rand.nextFloat() < 0.45)
				{
					BlockGenesisSapling sapling = (BlockGenesisSapling)block;
					sapling.markOrGrowMarked(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
				
				event.setResult(Result.ALLOW);
			}
		}
	}
	
}
