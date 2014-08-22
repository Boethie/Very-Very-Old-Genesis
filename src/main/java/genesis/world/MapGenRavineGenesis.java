package genesis.world;

import genesis.block.BlockGenesisRock;
import genesis.block.GenesisModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenRavineGenesis extends MapGenRavine {
    /**
     * Digs out the current block, default implementation removes stone, filler, and top block
     * Sets the block to lava if y is less then 10, and air other wise.
     * If setting to air, it also checks to see if we've broken the surface and if so
     * tries to make the floor the biome's top block
     *
     * @param data     Block data array
     * @param index    Pre-calculated index into block data
     * @param x        local X position
     * @param y        local Y position
     * @param z        local Z position
     * @param chunkX   Chunk X position
     * @param chunkZ   Chunk Y position
     * @param foundTop True if we've encountered the biome's top block. Ideally if we've broken the surface.
     */
    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        Block top = biome.topBlock;
        Block filler = biome.fillerBlock;
        Block block = data[index];

        if (block instanceof BlockGenesisRock || block == filler || block == top || block == Blocks.dirt || block == GenesisModBlocks.moss) {
            if (y < 9) {
                data[index] = Blocks.flowing_lava;
            } else {
                data[index] = null;

                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }
}
