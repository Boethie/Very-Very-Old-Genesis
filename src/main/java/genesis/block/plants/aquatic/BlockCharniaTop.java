package genesis.block.plants.aquatic;

import genesis.block.plants.BlockGenesisPlantTop;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * @author Arbiter
 */
public class BlockCharniaTop extends BlockGenesisPlantTop
{
	public BlockCharniaTop()
	{
		super(Material.water);
		this.setCreativeTab(null);
	}

	@Override
	protected void updateBlock(World world, BlockPos parBlockPos)
	{
		IBlockState block = world.getBlockState(parBlockPos.offsetDown());

		if (block.getBlock() instanceof BlockCharnia)
		{
			ERROR
			//TODO: FIX THIS!!
			/*int meta = world.getBlockMetadata(x, y - 1, z);
			 * world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			 */
		}
		else
		{
			world.setBlockToAir(parBlockPos);
		}

		IBlockState block1 = world.getBlockState(parBlockPos.offsetUp());
		if (block1 == null || block1 != Blocks.water)
		{
			world.setBlockToAir(parBlockPos);
		}
	}

	@Override
	public void breakBlock(World world, BlockPos parBlockPos, IBlockState parBlockState)
	{
		IBlockState block = world.getBlockState(parBlockPos.offsetDown());

		if (block instanceof BlockCharnia)
		{
			world.setBlockToAir(parBlockPos.offsetDown());
		}
		
		super.breakBlock(world, parBlockPos, parBlockState);
	}
}