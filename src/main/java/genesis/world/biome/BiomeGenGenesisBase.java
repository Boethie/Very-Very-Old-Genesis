package genesis.world.biome;

import genesis.managers.GenesisModBlocks;
import genesis.world.gen.BiomeDecoratorGenesis;
import genesis.world.gen.feature.WorldGenMinableSurface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenGenesisBase extends BiomeGenBase {
    BiomeDecoratorGenesis theBiomeDecorator;

    public BiomeGenGenesisBase(int par1) {
        super(par1);
        this.topBlock = GenesisModBlocks.moss;
        this.fillerBlock = Blocks.dirt;
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorGenesis();
        this.theBiomeDecorator.dirtPerChunk = 10;
    }

    public void decorate(World world, Random rand, int x, int z) {
        this.theBiomeDecorator.decorateChunk(world, rand, this, x, z);
        
        for(int c = this.theBiomeDecorator.dirtPerChunk; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
        	int j5 = z + rand.nextInt(16) + 8;
    		int l3 = world.getTopSolidOrLiquidBlock(j2, j5);
        	(new WorldGenMinableSurface(Blocks.dirt, 0, 20, GenesisModBlocks.moss)).generate(world, rand, j2, l3, j5);
    	}
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_) {
        this.topBlock = GenesisModBlocks.moss;

        //if (Math.round(p_150573_7_ * 10) % 3 == 0) this.topBlock = Blocks.dirt;

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    /**
     * takes temperature, returns color
     */
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return 0x317A00;
    }

    public int getWaterColorMultiplier() {
        return 0xA36F1C;
    }
}
