package genesis.world.biome;

import genesis.block.GenesisModBlocks;
import genesis.block.plants.GenesisPlantBlocks;
import genesis.lib.GenesisWorldHelper;
import genesis.world.gen.feature.WorldGenTreeCordaites;
import genesis.world.gen.feature.WorldGenTreeLepidodendron;
import genesis.world.gen.feature.WorldGenTreePsaronius;
import genesis.world.gen.feature.WorldGenTreeSigillaria;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenGenesisRainforestSwamp extends BiomeGenGenesisBase {
    public BiomeGenGenesisRainforestSwamp(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 7;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        int treeType = rand.nextInt(20);
        return treeType < 6 ? new WorldGenTreeLepidodendron(7, 5, true) : treeType < 13 ? new WorldGenTreeSigillaria(5, 3, true) : treeType < 18 ? new WorldGenTreeCordaites(12, 5, true) : new WorldGenTreePsaronius(5, 3, true);
    }

    public void decorate(World world, Random rand, int x, int z) {
        super.decorate(world, rand, x, z);

        for (int a = 0; a < 30; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockOfType(world, varX, varZ, GenesisModBlocks.moss, Blocks.dirt);
            if (world.isAirBlock(varX, varY, varZ)) {
                world.setBlock(varX, varY, varZ, GenesisPlantBlocks.asteroxylon, 0, 2);
            }
        }
    }
}
