package genesis.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import genesis.block.ModBlocks;
import genesis.block.trees.TreeBlocks;
import genesis.block.trees.TreeBlocks.TreeType;

public class BiomeGenRainforest extends BiomeGenBase {

	public BiomeGenRainforest(int id) {
		super(id);
		theBiomeDecorator = new BiomeDecoratorGenesis();
		theBiomeDecorator.treesPerChunk = 10;
		theBiomeDecorator.grassPerChunk = 2;
		topBlock = (Block) ModBlocks.moss;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		int a = random.nextInt(3);
		return TreeBlocks.getTreeGenerator(TreeType.values()[a].getName());
	}
}
