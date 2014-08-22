package genesis.lib;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Collections;

public class GenesisWorldHelper {
    public static int getTopBlockOfType(World world, int x, int z, Block... blocks) {
        ArrayList<Block> blockArray = new ArrayList<Block>();
        Collections.addAll(blockArray, blocks);
        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        int k = chunk.getTopFilledSegment() + 15;
        x &= 15;

        for (z &= 15; k > 0; --k) {
            Block block = chunk.getBlock(x, k, z);

            if (blockArray.contains(block)) {
                return k + 1;
            }
        }

        return -1;
    }
}
