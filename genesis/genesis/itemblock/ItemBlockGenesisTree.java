package genesis.genesis.itemblock;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.IBlockGenesisTrees;
import genesis.genesis.block.TreeBlocks;
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
		
		return tree.getBlockTypeName() + TreeBlocks.woodTypes[(stack.getItemDamage() & 3) + (tree.getBlockSet() * 4)];
    }
	
	public int getMetadata(int par1)
    {
          return par1 & 3;
    }

}
