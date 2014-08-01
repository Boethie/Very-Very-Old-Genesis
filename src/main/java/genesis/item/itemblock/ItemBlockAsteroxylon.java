package genesis.item.itemblock;

import genesis.block.plants.BlockAsteroxylon;
import genesis.block.plants.BlockGenesisPlantTop;
import genesis.block.plants.PlantBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		Block b = Block.getBlockFromItem(item.getItem());
		return b.getUnlocalizedName() + (getMetadata(item.getItemDamage()) == 1 ? ".tall" : "");
	}
	
	
	public IIcon getIconFromDamage(int meta)
	{
		return meta==0?PlantBlocks.asteroxylon.icons[0]:PlantBlocks.asterTop.getIcon(0,0);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		return ((BlockAsteroxylon)this.field_150939_a).getRenderColor(stack.getItemDamage());
	}
}