package genesis.genesis.common;

import genesis.genesis.block.BlockGenesisSapling;
import genesis.genesis.lib.Blocks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class GenesisEventHandler {
	
	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event)
	{
		if(!event.world.isRemote)
		{
			if(event.world.getBlockId(event.X, event.Y, event.Z) == Blocks.blockSaplingGenesis1.blockID)
			{
				((BlockGenesisSapling)Blocks.blockSaplingGenesis1).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}


}
