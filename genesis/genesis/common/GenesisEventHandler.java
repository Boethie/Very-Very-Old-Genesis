package genesis.genesis.common;

import genesis.genesis.block.BlockGenesisSapling;
import genesis.genesis.block.Blocks;
import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

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
				BlockGenesisSapling sapling = (BlockGenesisSapling)block;
				sapling.growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}


}
