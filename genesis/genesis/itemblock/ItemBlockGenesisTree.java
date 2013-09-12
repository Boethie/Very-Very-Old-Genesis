package genesis.genesis.itemblock;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.IBlockGenesisTrees;
import genesis.genesis.block.trees.TreeBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisTree extends ItemBlockWithMetadata {

	public ItemBlockGenesisTree(int itemID, Block block) {
		super(itemID, block);
	}
	
	public String getUnlocalizedName(ItemStack stack)
    {
		Block block = Block.blocksList[stack.itemID];
		IBlockGenesisTrees tree = (IBlockGenesisTrees)block;
		
		return block.getUnlocalizedName() + TreeBlocks.woodTypes[(stack.getItemDamage() & 3) + (tree.getBlockSet() * 4)];
    }
	
	public int getMetadata(int damage)
    {
          return damage & 3;
    }

}
