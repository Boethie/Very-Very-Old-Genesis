package genesis.genesis.block.plants;

import java.util.Random;

import net.minecraft.world.World;

public class BlockNeuropterisPlant extends BlockGenesisPlant {
	
	int fiddleheadBit = 8;
	
	protected BlockNeuropterisPlant(int id) {
		super(id);
		
		setStackable(2);
		
		this.maxAge -= 8;
	}
	
	protected int setHasFiddlehead(int metadata, boolean has)
	{
		return (metadata & this.maxAge) | (has ? fiddleheadBit : 0);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		
		if (getVerticalPosition(world, x, y, z) >= 2)
		{
			int metadata = world.getBlockMetadata(x, y, z);
			int age = getAge(metadata);
			
			if (age >= this.maxAge)
			{
				world.setBlockMetadataWithNotify(x, y, z, setHasFiddlehead(metadata, false), 3);
			}
		}
	}
	
}
