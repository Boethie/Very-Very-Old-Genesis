package genesis.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorGenesis extends BiomeDecorator {

	public int calamitesPerChunk;
	
	@Override
	public void decorateChunk(World world, Random random, BiomeGenBase biomeGen, int x, int y) {
		if (currentWorld != null)
			throw new RuntimeException("Already decorating!!");
		else {
			currentWorld = world;
			randomGenerator = random;
			chunk_X = x;
			chunk_Z = y;
			
			super.genDecorations(biomeGen);
			genDecorations(biomeGen);
			
			currentWorld = null;
			randomGenerator = null;
		}
	}

	@Override
	protected void genDecorations(BiomeGenBase biomeGen) {
		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);

		for (int j = 0; doGen && j < calamitesPerChunk; ++j) {
			int k = chunk_X + randomGenerator.nextInt(16) + 8;
			int l = chunk_Z + randomGenerator.nextInt(16) + 8;
			int i1 = randomGenerator.nextInt(128);
			reedGen.generate(currentWorld, randomGenerator, k, i1, l);
		}
	}
}
