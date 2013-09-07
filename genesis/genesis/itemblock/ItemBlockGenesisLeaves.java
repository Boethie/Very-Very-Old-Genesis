package genesis.genesis.itemblock;

import genesis.genesis.block.BlockGenesisLeaves;
import genesis.genesis.block.BlockGenesisSapling;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisLeaves extends ItemBlockWithMetadata{

	public ItemBlockGenesisLeaves(int par1, Block par2Block) {
		super(par1, par2Block);
	}
	public String getUnlocalizedName(ItemStack itemstack)
    {
		BlockGenesisLeaves block = (BlockGenesisLeaves) Block.blocksList[itemstack.itemID];
		return "blockLeaves" + block.gwoodType[(itemstack.getItemDamage() & 3) + (block.leavesCat * 4)];
    }
	public int getMetadata(int par1)
    {
          return par1 & 3;
    }

}
