package genesis.genesis.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.block.trees.TreeBlocks.TreeType;

public class BiomeGenRainforest extends BiomeGenBase {
    
	public BiomeGenRainforest(int id) {
        super(id);
        this.theBiomeDecorator = (BiomeDecorator) new BiomeDecoratorGenesis(this);
        
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;
        
        this.topBlock = (byte) Blocks.moss.blockID;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random random) {
    	int a = random.nextInt(3);
        return (WorldGenerator) TreeBlocks.getTreeGenerator(TreeType.values()[a].getName());
    }
}
