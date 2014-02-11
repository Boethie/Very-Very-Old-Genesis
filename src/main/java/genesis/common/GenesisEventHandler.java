package genesis.common;

import net.minecraft.block.Block;

import net.minecraftforge.event.entity.player.BonemealEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import genesis.block.trees.BlockGenesisSapling;

public class GenesisEventHandler {

	@SubscribeEvent
	public void bonemealUsed(BonemealEvent event) {
		if (!event.world.isRemote) {
			Block block = event.world.getBlock(event.x, event.y, event.z);;

			if (block instanceof BlockGenesisSapling) {
				if (event.world.rand.nextFloat() < 0.45) {
					BlockGenesisSapling sapling = (BlockGenesisSapling) block;
					
					//sapling.markOrGrowMarked(event.world, event.x, event.y, event.z, event.world.rand);
					sapling.func_149879_c(event.world, event.x, event.y, event.z, event.world.rand);
				}

				event.setResult(Result.ALLOW);
			}
		}
	}

}
