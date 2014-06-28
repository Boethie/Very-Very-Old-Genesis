package genesis.world.gen;

import genesis.block.ModBlocks;
import genesis.world.gen.feature.WorldGenMinableGenesis;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorGenesis
{
    /** The world the BiomeDecorator is currently decorating */
    public World currentWorld;
    /** The Biome Decorator's random number generator. */
    public Random randomGenerator;
    /** The X-coordinate of the chunk currently being decorated */
    public int chunk_X;
    /** The Z-coordinate of the chunk currently being decorated */
    public int chunk_Z;
    /** The clay generator. */
   /** The dirt generator. */
    public WorldGenerator dirtGen;
    public WorldGenerator gravelGen;
    public WorldGenerator quartzGen;
    public WorldGenerator malachiteGen;
    public WorldGenerator hematiteGen;
    public WorldGenerator zirconGen;
    public WorldGenerator garnetGen;
    public WorldGenerator olivineGen;
    public WorldGenFlowers yellowFlowerGen;
    /** Field that holds mushroomBrown WorldGenFlowers */
    public WorldGenerator mushroomBrownGen;
    /** Field that holds mushroomRed WorldGenFlowers */
    public WorldGenerator mushroomRedGen;
    /**
     * The number of trees to attempt to generate per chunk. Up to 10 in forests, none in deserts.
     */
    public int treesPerChunk;
    /**
     * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
     * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
     */
    public int mushroomsPerChunk;
    /** True if decorator should generate surface lava & water */
    public boolean generateLakes;

    public BiomeDecoratorGenesis()
    {
        this.dirtGen = new WorldGenMinableGenesis(Blocks.dirt, 32);
        this.gravelGen = new WorldGenMinableGenesis(Blocks.gravel, 32);
        this.quartzGen = new WorldGenMinableGenesis(ModBlocks.quartzGraniteOre, 6, ModBlocks.quartzite);
        this.malachiteGen = new WorldGenMinableGenesis(ModBlocks.malachiteOre, 3);
        this.hematiteGen = new WorldGenMinableGenesis(ModBlocks.hematiteOre, 10);
        this.zirconGen = new WorldGenMinableGenesis(ModBlocks.zirconOre, 8);
        this.garnetGen = new WorldGenMinableGenesis(ModBlocks.garnetOre, 8, ModBlocks.fauxAmphibolite);
        this.olivineGen = new WorldGenMinableGenesis(ModBlocks.olivineOre, 10, ModBlocks.komatiite);
        this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
        this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
        this.generateLakes = true;
    }

    public void decorateChunk(World p_150512_1_, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = p_150512_1_;
            this.randomGenerator = p_150512_2_;
            this.chunk_X = p_150512_4_;
            this.chunk_Z = p_150512_5_;
            this.genDecorations(p_150512_3_);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    protected void genDecorations(BiomeGenBase p_150513_1_)
    {
        this.generateOres();
        int i;
        int j;
        int k;

        i = this.treesPerChunk;

        if (this.randomGenerator.nextInt(10) == 0)
        {
            ++i;
        }

        int l;
        int i1;

        for (j = 0; j < i; ++j)
        {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.currentWorld.getHeightValue(k, l);
            WorldGenAbstractTree worldgenabstracttree = p_150513_1_.func_150567_a(this.randomGenerator);
            worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

            if (worldgenabstracttree.generate(this.currentWorld, this.randomGenerator, k, i1, l))
            {
                worldgenabstracttree.func_150524_b(this.currentWorld, this.randomGenerator, k, i1, l);
            }
        }

        for (j = 0; j < this.mushroomsPerChunk; ++j)
        {
            if (this.randomGenerator.nextInt(4) == 0)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                i1 = this.currentWorld.getHeightValue(k, l);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
            }

            if (this.randomGenerator.nextInt(8) == 0)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
            }
        }

        if (this.randomGenerator.nextInt(4) == 0)
        {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

        if (this.randomGenerator.nextInt(8) == 0)
        {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

        if (this.generateLakes)
        {
            for (j = 0; j < 50; ++j)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
                i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }

            for (j = 0; j < 20; ++j)
            {
                k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
                i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }
        }
    }

    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int l = 0; l < par1; ++l)
        {
            int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
            int j1 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
        }
    }

    /**
     * Generates ores in the current chunk
     */
    protected void generateOres()
    {
        /*this.genStandardOre1(20, this.dirtGen, 0, 256);
        this.genStandardOre1(10, this.gravelGen, 0, 256);*/
        this.genStandardOre1(20, this.quartzGen, 24, 128);
        this.genStandardOre1(12, this.malachiteGen, 0, 96);
        this.genStandardOre1(12, this.hematiteGen, 0, 64);
        this.genStandardOre1(8, this.zirconGen, 0, 64);
        this.genStandardOre1(4, this.garnetGen, 24, 48);
        this.genStandardOre1(1, this.olivineGen, 0, 16);
    }

    private int nextInt(int i) {
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
	}
}