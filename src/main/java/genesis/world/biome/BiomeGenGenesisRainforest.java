package genesis.world.biome;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.lib.GenesisWorldHelper;
import genesis.world.gen.feature.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenGenesisRainforest extends BiomeGenGenesisBase {
    public BiomeGenGenesisRainforest(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 10;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        int treeType = rand.nextInt(20);
        return treeType < 14 ? new WorldGenTreeLepidodendron(10, 5, true) :
                treeType < 17 ? new WorldGenTreeSigillaria(8, 3, true) :
                        treeType < 19 ? new WorldGenTreeCordaites(15, 5, true) :
                                new WorldGenTreePsaronius(5, 4, true);
    }

    public void decorate(World world, Random rand, int x, int z) {
        for (int a = 0; a < 4; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, ModBlocks.moss, Blocks.dirt);
            if (world.isAirBlock(varX, varY, varZ)) {
                world.setBlock(varX, varY, varZ, ModBlocks.graniteMossy, 0, 2);
                world.setBlock(varX, varY - 1, varZ, ModBlocks.graniteMossy, 0, 2);
            }
        }

        super.decorate(world, rand, x, z);

        if (rand.nextInt(10) == 0) {
            for (int a = 0; a < 10; a++) {
                int varX = x + rand.nextInt(16);
                int varZ = z + rand.nextInt(16);
                int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, ModBlocks.moss, Blocks.dirt);
                if (world.isAirBlock(varX, varY, varZ)) {
                    if (world.isAirBlock(varX, varY + 1, varZ)) {
                        world.setBlock(varX, varY, varZ, PlantBlocks.zingiberopsis, 7, 2);
                        world.setBlock(varX, varY + 1, varZ, PlantBlocks.zingTop, 2, 2);
                    } else world.setBlock(varX, varY, varZ, PlantBlocks.zingiberopsis, 0, 2);
                }
            }
        }

        int i1 = x + rand.nextInt(16) + 8;
        int j1 = z + rand.nextInt(16) + 8;
        int k1 = world.getHeightValue(i1, j1);
        new WorldGenBoulder(ModBlocks.graniteMossy, 0).generate(world, rand, i1, k1, j1);

        for (int a = 0; a < 30; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, ModBlocks.moss, Blocks.dirt);
            if (world.isAirBlock(varX, varY, varZ)) world.setBlock(varX, varY, varZ, PlantBlocks.ferns, 0, 2);
        }

    	/*int varX = x + rand.nextInt(16);
		int varZ = z + rand.nextInt(16);
		int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, ModBlocks.moss, Blocks.dirt);
		if(world.isAirBlock(varX, varY, varZ)) 
		{
			world.setBlock(varX, varY, varZ, TreeBlocks.blocksLeaves[3], 0, 2);
		}*/
    }
}
