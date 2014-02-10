package genesis.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisTree extends ItemBlockWithMetadata {

	public ItemBlockGenesisTree(Block block) {
		super(block, block);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		Block block = Block.getBlockFromItem(stack.getItem());
		IItemBlockWithSubNames tree = (IItemBlockWithSubNames) block;
		
		return block.getUnlocalizedName() + tree.getSubName(stack.getItemDamage());
    }
	
	public int getMetadata(int damage) {
          return damage & 3;
    }
}
