package genesis.world.biome;

import genesis.helper.GenesisWorldHelper;
import genesis.managers.GenesisModBlocks;
import genesis.world.gen.feature.WorldGenBoulder;
import genesis.world.gen.feature.WorldGenTreeAraucarioxylon;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenGenesisAuxForest extends BiomeGenGenesisBase
{
	public BiomeGenGenesisAuxForest(int par1)
	{
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 5;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		return new WorldGenTreeAraucarioxylon(10, 15, true);
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		for (int i = 0; i < 4; i++)
		{
			int varX = x + rand.nextInt(16);
			int varZ = z + rand.nextInt(16);
			int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
			if (world.isAirBlock(varX, varY, varZ))
			{
				world.setBlock(varX, varY, varZ, GenesisModBlocks.mossy_granite, 0, 2);
                world.setBlock(varX, varY - 1, varZ, GenesisModBlocks.mossy_granite, 0, 2);
			}
		}
		
		super.decorate(world, rand, x, z);
		
		int i1 = x + rand.nextInt(16) + 8;
        int j1 = z + rand.nextInt(16) + 8;
        BlockPos blockPos=new BlockPos(i1,0,j1);
        int k1 = world.getHorizon(blockPos).getY();
        new WorldGenBoulder(GenesisModBlocks.mossy_granite, 0).generate(world, rand, i1, k1, j1);
	}
}