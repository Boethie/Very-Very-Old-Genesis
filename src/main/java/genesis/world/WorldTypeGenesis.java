package genesis.world;

import genesis.world.gen.layer.GenLayerBiomeEdgeGenesis;
import genesis.world.gen.layer.GenLayerBiomeGenesis;
import net.minecraft.world.*;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.*;

public class WorldTypeGenesis extends WorldType
{   		
	public WorldTypeGenesis(String str)
    {
        super(str);        
    }

	@Override
    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerGenesis(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderGenesis(world, world.getSeed());
    }

    /**
     * Creates the GenLayerBiome used for generating the world
     * 
     * @param worldSeed The world seed
     * @param parentLayer The parent layer to feed into any layer you return
     * @return A GenLayer that will return ints representing the Biomes to be generated, see GenLayerBiome
     */
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeGenesis(200L, parentLayer);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdgeGenesis(1000L, ret);
        return ret;
    }
}