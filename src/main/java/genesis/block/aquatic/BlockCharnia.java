package genesis.block.aquatic;

import genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockCharnia extends BlockGenesisAquaticPlant
{
	@SideOnly(Side.CLIENT)
	private IIcon icon;
	
	public BlockCharnia()
	{
		super(Material.water);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block b = world.getBlock(x, y + 1, z);
		if (b instanceof BlockCharniaTop)
		{
			world.setBlockToAir(x, y + 1, z);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	protected void dropPlantIfCannotStay(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			Block b = world.getBlock(x, y + 1, z);
			if (b instanceof BlockCharniaTop)
			{
				world.setBlockToAir(x, y + 1, z);
			}
		}
		super.dropPlantIfCannotStay(world, x, y, z);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.setBlock(x, y + 1, z, AquaticBlocks.charniaTop, 0, 2);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		Block b = world.getBlock(x, y + 1, z);
		if ((b == null) || (!(b instanceof BlockCharniaTop)))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icon = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
	}
}