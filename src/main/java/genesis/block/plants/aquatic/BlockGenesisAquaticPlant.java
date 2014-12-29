package genesis.block.plants.aquatic;

import genesis.Genesis;
import genesis.block.plants.IPlantRenderSpecials;
import genesis.client.renderer.block.BlockGenesisPlantRenderer;
import genesis.lib.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * @author Arbiter
 */
public abstract class BlockGenesisAquaticPlant extends Block implements IPlantRenderSpecials
{
	public BlockGenesisAquaticPlant(Material material)
	{
		super(material);
		this.setResistance(0.0F);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeCloth);
		this. setCreativeTab(GenesisTabs.tabGenesisDecoration);
	}

	@Override
	public int getRenderType()
	{
		return BlockGenesisPlantRenderer.renderID;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	//TODO: Do this in json
	/*
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
*/
	
	@Override
	public boolean canSustainPlant(IBlockAccess parWorld, BlockPos parBlockPos, EnumFacing parDirection, IPlantable parPlantable)
	{
		boolean flag = false;

		int x = parBlockPos.getX();
		int y = parBlockPos.getY();
		int z = parBlockPos.getZ();

		Block[] blocks = new Block[] {
				parWorld.getBlockState(parBlockPos.offsetEast()).getBlock(),
				parWorld.getBlockState(parBlockPos.offsetWest()).getBlock(),
				parWorld.getBlockState(parBlockPos.offsetUp()).getBlock(),
				parWorld.getBlockState(parBlockPos.offsetSouth()).getBlock(),
				parWorld.getBlockState(parBlockPos.offsetNorth()).getBlock()
		};

		int index = 0, waterCount = 0;
		for (Block b : blocks)
		{
			if (index == 2)
			{
				continue;
			}
			else if (b == null)
			{
				flag = false;
				break;
			}
			else if (b == Blocks.water)
			{
				waterCount++;
			}
			else if (b instanceof BlockGenesisAquaticPlant)
			{
				waterCount++;
			}

			index++;
		}

		if (waterCount >= 1 && ((blocks[2] == Blocks.water) || (blocks[2] instanceof BlockCharniaTop)))
		{
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean canPlaceBlockAt(World parWorld, BlockPos parBlockPos)
	{
		return this.canPlaceBlockOn(parWorld.getBlockState(parBlockPos.offsetDown()).getBlock()) && this.canSustainPlant(parWorld, parBlockPos, EnumFacing.DOWN, null);
	}

	protected boolean canPlaceBlockOn(Block block)
	{
		boolean flag = false;
		final Block[] blockList = new Block[]{Blocks.dirt, Blocks.gravel, Blocks.sand, Blocks.clay};
		if (block.getMaterial() == Material.rock)
		{
			flag = true;
		}

		for (Block b : blockList)
		{
			if (block == b)
			{
				flag = true;
				break;
			}
		}

		return flag;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World p_149668_1_, BlockPos parBlockPos, IBlockState parBlockState)
	{
		return null;
	}

	protected void dropPlantIfCannotStay(World parWorld, BlockPos parBlockPos)
	{
		if (!this.canPlaceBlockAt(parWorld, parBlockPos))
		{
			this.dropBlockAsItem(parWorld, parBlockPos, parWorld.getBlockState(parBlockPos), 2);
			parWorld.setBlockToAir(parBlockPos);
		}
	}

	@Override
	public void onBlockAdded(World parWorld, BlockPos parBlockPos, IBlockState parBlockState)
	{
		super.onBlockAdded(parWorld, parBlockPos, parBlockState);
		this.dropPlantIfCannotStay(parWorld, parBlockPos);
	}

	@Override
	public void onNeighborBlockChange(World parWorld, BlockPos parBlockPos, IBlockState parBlockState, Block parNeighbor)
	{
		super.onNeighborBlockChange(parWorld, parBlockPos, parBlockState, parNeighbor);
		this.dropPlantIfCannotStay(parWorld, parBlockPos);
	}

	@Override
	public double randomPos(IBlockAccess world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return 0.4;
	}

	@Override
	public double randomYPos(IBlockAccess world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return 0.2;
	}

	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side)
	{
		// TODO Auto-generated method stub
		return false;
	}

	//TODO: MAKE THE BLOCK JSON FILES INSTEAD!
	/*@Override
	public Block setBlockTextureName(String textureName)
	{
		return super.setBlockTextureName(Genesis.ASSETS + textureName);
	}*/
}