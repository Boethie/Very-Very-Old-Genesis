package genesis.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.block.ModBlocks;
import genesis.world.gen.BiomeDecoratorGenesis;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class BiomeGenGenesisBase extends BiomeGenBase {
    BiomeDecoratorGenesis theBiomeDecorator;

    public BiomeGenGenesisBase(int par1) {
        super(par1);
        this.topBlock = ModBlocks.moss;
        this.fillerBlock = Blocks.dirt;
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorGenesis();
    }

    public void decorate(World world, Random rand, int x, int z) {
        this.theBiomeDecorator.decorateChunk(world, rand, this, x, z);
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_) {
        this.topBlock = ModBlocks.moss;

        if (Math.round(p_150573_7_ * 10) % 3 == 0) this.topBlock = Blocks.dirt;

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
