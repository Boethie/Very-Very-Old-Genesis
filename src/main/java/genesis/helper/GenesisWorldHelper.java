package genesis.helper;

import java.util.ArrayList;
import java.util.Collections;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

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
	
	public static int getTopBlockNotOfType(World world, int x, int z, Block... blocks) {
        ArrayList<Block> blockArray = new ArrayList<Block>();
        Collections.addAll(blockArray, blocks);
        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        int k = chunk.getTopFilledSegment() + 15;
        x &= 15;

        for (z &= 15; k > 0; --k) {
            Block block = chunk.getBlock(x, k, z);

            if (!blockArray.contains(block) && block != Blocks.air) {
                return k + 1;
            }
        }

        return -1;
    }
	
	public static int getTopBlockAfterType(World world, int x, int z, Block... blocks) {
        boolean hasReachedType = false;
		ArrayList<Block> blockArray = new ArrayList<Block>();
        Collections.addAll(blockArray, blocks);
        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        int k = chunk.getTopFilledSegment() + 15;
        x &= 15;

        for (z &= 15; k > 0; --k) {
            Block block = chunk.getBlock(x, k, z);

            if (blockArray.contains(block)) hasReachedType = true;
            if (!blockArray.contains(block) && block != Blocks.air && hasReachedType) {
                return k + 1;
            }
        }

        return -1;
    }
}
