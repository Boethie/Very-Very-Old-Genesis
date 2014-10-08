package genesis.world.biome;

import genesis.managers.GenesisModBlocks;
import genesis.block.aquatic.GenesisAquaticBlocks;
import genesis.helper.GenesisWorldHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenGenesisRiver extends BiomeGenGenesisBase {
    public BiomeGenGenesisRiver(int par1) {
        super(par1);
        this.topBlock = GenesisModBlocks.limestone;
        this.fillerBlock = GenesisModBlocks.limestone;
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_) {
        /*this.topBlock = ModBlocks.limestone;
        this.fillerBlock = ModBlocks.limestone;

        if(p_150573_7_ > 1.45D)
        {
            this.topBlock = ModBlocks.shale;
            this.fillerBlock = ModBlocks.shale;
        }*/

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
    
    public void decorate(World world, Random rand, int x, int z) {
        super.decorate(world, rand, x, z);
        
        for (int a = 0; a < 5; a++) {
            int varX = x + rand.nextInt(16);
            int varZ = z + rand.nextInt(16);
            int varY = GenesisWorldHelper.getTopBlockAfterType(world, varX, varZ, Blocks.water, Blocks.flowing_water);
            if(GenesisAquaticBlocks.algae.canBlockStay(world, varX, varY, varZ)) world.setBlock(varX, varY, varZ, GenesisAquaticBlocks.algae, 1, 2);
        }
    }
}
