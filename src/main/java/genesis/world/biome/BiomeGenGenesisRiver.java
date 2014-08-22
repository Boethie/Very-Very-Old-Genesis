package genesis.world.biome;

import genesis.block.GenesisModBlocks;
import net.minecraft.block.Block;
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
}
