package genesis.genesis.itemblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.genesis.block.BlockGenesisLog;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockGenesisLog extends ItemBlock{

	public ItemBlockGenesisLog(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	public String getUnlocalizedName(ItemStack itemstack)
    {
		BlockGenesisLog block = (BlockGenesisLog) Block.blocksList[itemstack.itemID];
		return "blockLog" + BlockGenesisLog.gwoodType[(itemstack.getItemDamage() & 3) + (block.LogCat * 4)];
    }
	public int getMetadata(int par1)
    {
          return par1 & 3;
    }

}
