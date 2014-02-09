package genesis.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorGenesis extends BiomeDecorator
{

	public int calamitesPerChunk;
	
	public BiomeDecoratorGenesis(BiomeGenBase par1BiomeGenBase) {
		super(par1BiomeGenBase);
	}
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = par1World;
            this.randomGenerator = par2Random;
            this.chunk_X = par3;
            this.chunk_Z = par4;
            super.decorate();
            this.decorate();
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
	
	
	protected void decorate(){
		int j;

        boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
        
        for (j = 0; doGen && j < this.calamitesPerChunk; ++j)
        {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int i1 = this.randomGenerator.nextInt(128);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }
	}
}
