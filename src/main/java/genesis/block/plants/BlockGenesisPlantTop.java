package genesis.block.plants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * A class to represent a block that can only appear on the top of another block (preferably a plant).
 * An abstract, pre-defined class is used to minimize the amount of code needed as all top blocks will
 * behave in the way as defined in this class. <br /><br />
 * The benefit of inheritance however, is that any of these methods can be overridden, which opens up 
 * many more options. 
 * @author Arbiter
 *
 */
public abstract class BlockGenesisPlantTop extends Block
{
	public BlockGenesisPlantTop()
	{
		super(Material.plants);
		disableStats();
		setTickRandomly(true);
		setHardness(0.0f);
		setResistance(0.0f);
		setStepSound(soundTypeGrass);
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
	
	protected abstract void updateBlock(World world, int x, int y, int z);
	
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
}