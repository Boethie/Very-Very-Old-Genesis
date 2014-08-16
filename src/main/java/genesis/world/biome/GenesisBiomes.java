package genesis.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class GenesisBiomes {
    public static final BiomeGenBase.Height heightDefault = new BiomeGenBase.Height(0.1F, 0.2F);
    public static final BiomeGenBase.Height heightShallowWaters = new BiomeGenBase.Height(-0.6F, 0.0F);
    public static final BiomeGenBase.Height heightOceans = new BiomeGenBase.Height(-1.0F, 0.1F);
    public static final BiomeGenBase.Height heightDeepOceans = new BiomeGenBase.Height(-1.8F, 0.1F);
    public static final BiomeGenBase.Height heightLowPlains = new BiomeGenBase.Height(0.125F, 0.05F);
    public static final BiomeGenBase.Height heightMidPlains = new BiomeGenBase.Height(0.2F, 0.2F);
    public static final BiomeGenBase.Height heightLowHills = new BiomeGenBase.Height(0.45F, 0.3F);
    public static final BiomeGenBase.Height heightHighPlateaus = new BiomeGenBase.Height(1.5F, 0.025F);
    public static final BiomeGenBase.Height heightMidHills = new BiomeGenBase.Height(1.0F, 0.5F);
    public static final BiomeGenBase.Height heightShores = new BiomeGenBase.Height(0.0F, 0.025F);
    public static final BiomeGenBase.Height heightRockyWaters = new BiomeGenBase.Height(0.1F, 0.8F);
    public static final BiomeGenBase.Height heightLowIslands = new BiomeGenBase.Height(0.2F, 0.3F);
    public static final BiomeGenBase.Height heightPartiallySubmerged = new BiomeGenBase.Height(-0.2F, 0.1F);
    public static final BiomeGenBase.Height heightHighHills = new BiomeGenBase.Height(2.5F, 0.5F);
    public static final BiomeGenBase.Height heightMidPlateaus = new BiomeGenBase.Height(1.5F, 0.025F);
    public static final BiomeGenBase.Height heightSeaPlateaus = new BiomeGenBase.Height(0.0F, 0.025F);
    public static final BiomeGenBase.Height heightLowPlateaus = new BiomeGenBase.Height(0.5F, 0.025F);
    public static final BiomeGenBase.Height heightHighPlains = new BiomeGenBase.Height(0.5F, 0.1F);
    public static final BiomeGenBase.Height heightPSPlateaus = new BiomeGenBase.Height(-0.2F, 0.05F);
    public static int rainforestId;
    public static BiomeGenBase rainforest;
    public static int riverId;
    public static BiomeGenBase river;
    public static int rainforestSwampId;
    public static BiomeGenBase rainforestSwamp;

    public static void config() {
        File configFile = new File("config/Genesis/Biomes.cfg");
        Configuration config = new Configuration(configFile);
        config.load();

        rainforestId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rainforest", 120).getInt();
        riverId = config.get(config.CATEGORY_GENERAL, "Biome ID of River (Genesis)", 121).getInt();
        rainforestSwampId = config.get(config.CATEGORY_GENERAL, "Biome ID of Rainforest Swamp", 122).getInt();

        config.save();
    }

    public static void init() {
        rainforest = (new BiomeGenGenesisRainforest(rainforestId)).setColor(9286496).setTemperatureRainfall(0.9F, 0.8F).setHeight(heightLowHills).setBiomeName("Rainforest");
        river = (new BiomeGenGenesisRiver(riverId)).setColor(255).setTemperatureRainfall(0.7F, 1.0F).setHeight(heightShallowWaters).setBiomeName("River");
        rainforestSwamp = (new BiomeGenGenesisRainforestSwamp(rainforestSwampId)).setColor(9286496).setTemperatureRainfall(0.7F, 1.0F).setHeight(heightPSPlateaus).setBiomeName("Rainforest Swamp");
    }
}
