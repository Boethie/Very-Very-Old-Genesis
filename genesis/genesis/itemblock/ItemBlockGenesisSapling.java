package genesis.genesis.itemblock;

import genesis.genesis.block.BlockGenesisSapling;
import genesis.genesis.block.BlockGenesisLog;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisSapling extends ItemBlockWithMetadata{

	public ItemBlockGenesisSapling(int par1, Block par2Block) {
		super(par1, par2Block);
	}
	public String getUnlocalizedName(ItemStack itemstack)
    {
		BlockGenesisSapling block = (BlockGenesisSapling) Block.blocksList[itemstack.itemID];
		return "blockSapling" + block.gwoodType[(itemstack.getItemDamage() & 3) + (block.SapCat * 4)];
    }
	public int getMetadata(int par1)
    {
          return par1 & 3;
    }

}
