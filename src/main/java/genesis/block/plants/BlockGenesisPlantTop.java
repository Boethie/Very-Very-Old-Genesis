package genesis.block.plants;

import genesis.client.renderer.block.BlockGenesisPlantRenderer;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * A class to represent a block that can only appear on the top of another block (preferably a plant).
 * An abstract, pre-defined class is used to minimize the amount of code needed as all top blocks will
 * behave in the way as defined in this class. <br /><br />
 * The benefit of inheritance however, is that any of these methods can be overridden, which opens up
 * many more options.
 *
 * @author Arbiter
 */
public abstract class BlockGenesisPlantTop extends Block implements IPlantRenderSpecials
{
	public BlockGenesisPlantTop(Material material)
	{
		super(material);
		disableStats();
		setTickRandomly(true);
		setHardness(0.0f);
		setResistance(0.0f);
		setStepSound(soundTypeGrass);
		setCreativeTab(null);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	//TODO: JSON!
	/*
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	*/

	@Override
	public int getRenderType()
	{
		return BlockGenesisPlantRenderer.renderID;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos parBlockPos)
	{
		IBlockState block = world.getBlockState(parBlockPos.offsetDown());
		return block != null ? block.getBlock().getPickBlock(target, world, parBlockPos.offsetDown()) : null;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World parWorld, BlockPos parBlockPos, IBlockState parBlockState)
	{
		return null;
	}

	protected abstract void updateBlock(World world, BlockPos parBlockPos);

	@Override
	public void updateTick(World world, BlockPos parBlockPos, IBlockState parBlockState, Random random)
	{
		super.updateTick(world, parBlockPos, parBlockState, random);
		this.updateBlock(world, parBlockPos);
	}

	@Override
	public void onNeighborBlockChange(World oarWorld, BlockPos parBlockPos, IBlockState parBlockState, Block parNeighbor)
	{
		super.onNeighborBlockChange(oarWorld, parBlockPos, parBlockState, parNeighbor);
		this.updateBlock(oarWorld, parBlockPos);
	}

	@Override
	public Item getItemDropped(IBlockState parBlockState, Random par2, int par3)
	{
		return null;
	}

	@Override
	public double randomPos(IBlockAccess world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double randomYPos(IBlockAccess world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side)
	{
		// TODO Auto-generated method stub
		return false;
	}

	//TODO: BLOCK JSON
	/*
	@Override
	public Block setBlockTextureName(String textureName)
	{
		return super.setBlockTextureName(Genesis.ASSETS + textureName);
	}
	 */
}