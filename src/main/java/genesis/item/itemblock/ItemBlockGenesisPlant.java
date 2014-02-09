package genesis.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

import genesis.block.plants.PlantBlocks;

public class ItemBlockGenesisPlant extends ItemBlockWithMetadata {

	public ItemBlockGenesisPlant(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		Block block = Block.getBlockFromItem(stack.getItem());
		return block.getUnlocalizedName() + "." + PlantBlocks.flowerTypes.get(getMetadata(stack.getItemDamage()));
    }
	
	@Override
	public int getMetadata(int damage) {
          return damage & 15;
    }

}
