package genesis.block.plants.aquatic;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author Arbiter
 */
public class BlockCharnia extends BlockGenesisAquaticPlant
{
	public BlockCharnia()
	{
		super(Material.water);
	}

	@Override
	public void breakBlock(World world, BlockPos parBlockPos, IBlockState parBlockState)
	{
		IBlockState block = world.getBlockState(parBlockPos.offsetUp());
		if (block.getBlock() instanceof BlockCharniaTop)
		{
			world.setBlockToAir(parBlockPos.offsetUp());
		}

		super.breakBlock(world, parBlockPos, parBlockState);
	}

	@Override
	protected void dropPlantIfCannotStay(World parWorld, BlockPos parBlockPos)
	{
		if (!this.canPlaceBlockAt(parWorld, parBlockPos))
		{
			IBlockState block = parWorld.getBlockState(parBlockPos.offsetDown());
			if (block.getBlock() instanceof BlockCharniaTop)
			{
				parWorld.setBlockToAir(parBlockPos.add(0, 1, 0));
			}
		}

		super.dropPlantIfCannotStay(parWorld, parBlockPos);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos parBlockPos, EnumFacing parFace, float hitX, float hitY, float hitZ, int meta, EntityLivingBase parPlacer)
	{
		world.setBlockState(parBlockPos.offsetUp(), GenesisAquaticBlocks.charnia_top.getDefaultState(), 2);
		return super.onBlockPlaced(world, parBlockPos, parFace, hitX, hitY, hitZ, meta, parPlacer);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos parBlockPos)
	{
		return world.getBlockState(parBlockPos.add(0, 2, 0)) == Blocks.water && super.canPlaceBlockAt(world, parBlockPos);
	}
}