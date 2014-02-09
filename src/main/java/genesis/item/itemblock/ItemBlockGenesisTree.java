package genesis.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisTree extends ItemBlockWithMetadata {

	public ItemBlockGenesisTree(int itemID, Block block) {
		super(itemID, block);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		Block block = Block.blocksList[stack.itemID];
		IItemBlockWithSubNames tree = (IItemBlockWithSubNames) block;
		
		return block.getUnlocalizedName() + tree.getSubName(stack.getItemDamage());
    }
	
	public int getMetadata(int damage) {
          return damage & 3;
    }
}
