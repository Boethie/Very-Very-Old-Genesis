package genesis.world.biome;

import genesis.block.plants.GenesisPlantBlocks;
import genesis.helper.GenesisWorldHelper;
import genesis.managers.GenesisModBlocks;
import genesis.world.gen.feature.WorldGenBoulder;
import genesis.world.gen.feature.WorldGenTreeCordaites;
import genesis.world.gen.feature.WorldGenTreeLepidodendron;
import genesis.world.gen.feature.WorldGenTreePsaronius;
import genesis.world.gen.feature.WorldGenTreeSigillaria;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenGenesisRainforest extends BiomeGenGenesisBase {
    public BiomeGenGenesisRainforest(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 10;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        int treeType = rand.nextInt(20);
        return treeType < 14 ? new WorldGenTreeLepidodendron(10, 5, true) : treeType < 17 ? new WorldGenTreeSigillaria(8, 3, true) : treeType < 19 ? new WorldGenTreeCordaites(15, 5, true) : new WorldGenTreePsaronius(5, 4, true);
    }

    public void decorate(World world, Random rand, int x, int z) {
        for (int a = 0; a < 4; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
            if (world.isAirBlock(varX, varY, varZ)) {
                world.setBlock(varX, varY, varZ, GenesisModBlocks.mossy_granite, 0, 2);
                world.setBlock(varX, varY - 1, varZ, GenesisModBlocks.mossy_granite, 0, 2);
            }
        }

        super.decorate(world, rand, x, z);

        if (rand.nextInt(20) == 0) {
            for (int a = 0; a < 5; a++) {
                int varX = x + rand.nextInt(16);
                int varZ = z + rand.nextInt(16);
                int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
                if (world.isAirBlock(varX, varY, varZ)) {
                    if (world.isAirBlock(varX, varY + 1, varZ)) {
                        world.setBlock(varX, varY, varZ, GenesisPlantBlocks.zingiberopsis, 7, 2);
                        world.setBlock(varX, varY + 1, varZ, GenesisPlantBlocks.zingiberopsis_top, 2, 2);
                    } else {
                        world.setBlock(varX, varY, varZ, GenesisPlantBlocks.zingiberopsis, 0, 2);
                    }
                }
            }
        }

        if (rand.nextInt(10) == 0) {
            for (int a = 0; a < 5; a++) {
            	int varX = x + rand.nextInt(16);
                int varZ = z + rand.nextInt(16);
                int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
                if (world.isAirBlock(varX, varY, varZ)) {
                    if (world.isAirBlock(varX, varY + 1, varZ)) {
                        int stage = rand.nextInt(8);
                    	world.setBlock(varX, varY, varZ, GenesisPlantBlocks.sphenophyllum, stage, 2);
                        if(stage >= 5) world.setBlock(varX, varY + 1, varZ, GenesisPlantBlocks.sphenophyllum_top, stage - 5, 2);
                    } else {
                        world.setBlock(varX, varY, varZ, GenesisPlantBlocks.sphenophyllum, rand.nextInt(5), 2);
                    }
                }
            }
        }

        int i1 = x + rand.nextInt(16) + 8;
        int j1 = z + rand.nextInt(16) + 8;
        int k1 = world.getHeightValue(i1, j1);
        new WorldGenBoulder(GenesisModBlocks.mossy_granite, 0).generate(world, rand, i1, k1, j1);

        for (int a = 0; a < 75; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
            if (world.isAirBlock(varX, varY, varZ)) {
                world.setBlock(varX, varY, varZ, GenesisPlantBlocks.ferns, 0, 2);
            }
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
