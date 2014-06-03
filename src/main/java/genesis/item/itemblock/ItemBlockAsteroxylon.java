package genesis.item.itemblock;

import genesis.block.plants.BlockAsteroxylon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Arbiter
 *
 */
public class ItemBlockAsteroxylon extends ItemBlockWithMetadata
{
	public ItemBlockAsteroxylon(Block block)
	{
		super(block, block);
		setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		Block b = Block.getBlockFromItem(item.getItem());
		return b.getUnlocalizedName();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		return ((BlockAsteroxylon)this.field_150939_a).getRenderColor(stack.getItemDamage());
	}
}