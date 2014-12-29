package genesis.block.plants.aquatic;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
import genesis.lib.Names;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockGenesisCoral extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockGenesisCoral()
	{
		super(Material.rock);
		this.setHardness(0.75F);
		this.setResistance(8.5F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(GenesisTabs.tabGenesisDecoration);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		int size = Names.Plants.CORAL_TYPES.size();
		for (int i = 0; i < size; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[Names.Plants.CORAL_TYPES.size()];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = register.registerIcon(Genesis.ASSETS + Names.Plants.CORAL_TYPES.get(i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta)
	{
		return icons[meta];
	}
}