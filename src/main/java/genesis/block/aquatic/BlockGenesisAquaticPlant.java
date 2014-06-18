package genesis.block.aquatic;

import genesis.block.ModBlocks;
import genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockGenesisAquaticPlant extends Block
{
	public BlockGenesisAquaticPlant(Material material)
	{
		super(material);
		setResistance(0.0F);
		setHardness(0.6F);
		setStepSound(soundTypeCloth);
		setCreativeTab(Genesis.tabGenesis);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
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
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		boolean flag = false;
		Block[] blocks = new Block[] {
				world.getBlock(x + 1, y, z),
				world.getBlock(x - 1, y, z),
				world.getBlock(x, y + 1, z),
				world.getBlock(x, y, z + 1),
				world.getBlock(x, y, z - 1)
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
			else if (b == AquaticBlocks.sponge || b == AquaticBlocks.algae)
			{
				waterCount++;
			}
			index++;
		}
		if (waterCount >= 1 && blocks[2] == Blocks.water)
		{
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canBlockStay(world, x, y, z);
	}
	
	protected boolean canPlaceBlockOn(Block block)
	{
		boolean flag = false;
		final Block[] blockList = new Block[] {Blocks.dirt, Blocks.gravel, Blocks.sand,
				Blocks.clay, AquaticBlocks.coral, ModBlocks.granite, Blocks.stone,
				ModBlocks.limestone, ModBlocks.gneiss, ModBlocks.quartzite, ModBlocks.rhyolite,
				ModBlocks.shale};
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
			return canPlaceBlockOn(world.getBlock(x, y - 1, z)) &&
					canSustainPlant(world, x, y, z, ForgeDirection.DOWN, null);
	}
	
	protected void dropSpongeIfCannotStay(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (!canBlockStay(world, x, y, z))
		{
			dropSpongeIfCannotStay(world, x, y, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		if (!canBlockStay(world, x, y, z))
		{
			dropSpongeIfCannotStay(world, x, y, z);
		}
	}
}