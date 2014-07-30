package genesis.block.aquatic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockCharnia extends BlockGenesisAquaticPlant
{
	public BlockCharnia()
	{
		super(Material.water);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block b = world.getBlock(x, y + 1, z);
	}
	
	@Override
	protected void dropPlantIfCannotStay(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			Block b = world.getBlock(x, y + 1, z);
		}
	}
}