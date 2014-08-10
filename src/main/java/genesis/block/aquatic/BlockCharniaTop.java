package genesis.block.aquatic;

import genesis.block.plants.BlockGenesisPlantTop;
import genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockCharniaTop extends BlockGenesisPlantTop
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	
	public BlockCharniaTop()
	{
		super(Material.water);
		setCreativeTab((CreativeTabs)null);
	}
	
	@Override
	protected void updateBlock(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		if (b != null && b instanceof BlockCharnia)
		{
			int meta = world.getBlockMetadata(x, y - 1, z);
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
		else
		{
			world.setBlockToAir(x, y, z);
		}
		Block b1 = world.getBlock(x, y + 1, z);
		if (b1 == null || b1 != Blocks.water)
		{
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return topIcon;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block below = world.getBlock(x, y - 1, z);
		if (below != null && below instanceof BlockCharnia)
		{
			world.setBlockToAir(x, y - 1, z);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		topIcon = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
	}
}