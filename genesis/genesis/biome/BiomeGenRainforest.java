package genesis.genesis.biome;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.TreeBlocks;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRainforest extends BiomeGenBase
{
    public BiomeGenRainforest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = (BiomeDecorator)new BiomeDecoratorGenesis(this);
        
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;
        
        this.topBlock = (byte) Blocks.moss.blockID;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	int a = par1Random.nextInt(3);
        return (WorldGenerator)TreeBlocks.getTreeGenerator(TreeBlocks.woodTypes.get(a));
    }
}
