package genesis.common;

import genesis.block.BlockMoss;
import genesis.block.trees.BlockGenesisSapling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
	
	@SubscribeEvent
	public void onPlayerUseHoe(UseHoeEvent event)
	{
		World world = event.world;
		Block b = world.getBlock(event.x, event.y, event.z);
		if(b instanceof BlockMoss)
		{
			world.setBlock(event.x, event.y, event.z, Blocks.farmland);
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		World world = event.entityPlayer.worldObj;
		Block block = world.getBlock(event.x, event.y, event.z);
		Item itemInHand = event.entityPlayer.getCurrentEquippedItem().getItem();
		if(itemInHand != null)
		{
			Block blockInHand = Block.getBlockFromItem(itemInHand);
			if(event.face == 1 && block instanceof BlockMoss && blockInHand instanceof BlockBush && event.action == Action.RIGHT_CLICK_BLOCK)
			{
				world.setBlock(event.x, event.y + 1, event.z, blockInHand);
			}
		}
	}
}
