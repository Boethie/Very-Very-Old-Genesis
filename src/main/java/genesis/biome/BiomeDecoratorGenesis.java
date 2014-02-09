package genesis.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorGenesis extends BiomeDecorator {

	public int calamitesPerChunk;

	public BiomeDecoratorGenesis() {
		super();
	}

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		if (currentWorld != null)
			throw new RuntimeException("Already decorating!!");
		else {
			currentWorld = par1World;
			randomGenerator = par2Random;
			chunk_X = par3;
			chunk_Z = par4;
			super.decorate();
			this.decorate();
			currentWorld = null;
			randomGenerator = null;
		}
	}

	protected void decorate() {
		int j;

		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);

		for (j = 0; doGen && j < calamitesPerChunk; ++j) {
			int k = chunk_X + randomGenerator.nextInt(16) + 8;
			int l = chunk_Z + randomGenerator.nextInt(16) + 8;
			int i1 = randomGenerator.nextInt(128);
			reedGen.generate(currentWorld, randomGenerator, k, i1, l);
		}
	}
}
