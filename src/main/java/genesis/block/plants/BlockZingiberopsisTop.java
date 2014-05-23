package genesis.block.plants;

import genesis.common.Genesis;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockZingiberopsisTop extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockZingiberopsisTop()
	{
		super(Material.plants);
		disableStats();
		setTickRandomly(true);
		setHardness(0.0f);
		setResistance(0.0f);
		setCreativeTab((CreativeTabs)null);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	protected void updateBlock(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		if (b instanceof BlockZingiberopsisBase)
		{
			int meta = world.getBlockMetadata(x, y - 1, z) - 5;
			if (meta >= 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
		else
		{
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		updateBlock(world, x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		updateBlock(world, x, y, z);
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2, int par3)
	{
		return null;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block down = world.getBlock(x, y - 1, z);
		if (down instanceof BlockZingiberopsisBase)
		{
			((BlockZingiberopsisBase)down).dropBlockAsItem(world, x, y - 1, z, world.getBlockMetadata(x, y, z) + 5, 0);
			world.setBlockToAir(x, y - 1, z);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[3];
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_stage_" + (i + 5) + "_top");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta)
	{
		if (meta >= 3)
		{
			meta = 2;
		}
		return icons[meta];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 0; i < 3; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
}