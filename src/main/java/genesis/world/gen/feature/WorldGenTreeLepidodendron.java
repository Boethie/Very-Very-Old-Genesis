package genesis.world.gen.feature;

import genesis.block.BlockAndMeta;
import genesis.block.trees.BlockGenesisSapling;
import genesis.block.trees.GenesisTreeBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class WorldGenTreeLepidodendron extends WorldGenTreeBase {

    /**
     * Constructor
     *
     * @param minH   minimum height of tree trunk
     * @param maxH   max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *               Generally false for world generation, true for saplings.
     */
    public WorldGenTreeLepidodendron(int minH, int maxH, boolean notify) {
        super(new BlockAndMeta(GenesisTreeBlocks.logs[0], 2), new BlockAndMeta(GenesisTreeBlocks.leaves[0], 2), notify);
        minHeight = minH;
        maxHeight = maxH;
    }

    @Override
    public boolean generate(World world, Random random, int locX, int locY, int locZ) {
        this.world = world;
        this.random = random;

        // finds top block for the given x,z position (excluding leaves and
        // grass)
        for (boolean var6 = false; world.getBlock(locX, locY, locZ) == Blocks.air || world.getBlock(locX, locY, locZ).isLeaves(world, locX, locY, locZ) && locY > 0; --locY) {
            ;
        }
        // locY is now the highest solid terrain block

        Block soil = world.getBlock(locX, locY, locZ);
        if (soil == null || !soil.canSustainPlant(world, locX, locY, locZ, ForgeDirection.UP, (BlockGenesisSapling) GenesisTreeBlocks.saplings[0])) {
            return false;
        }
        if (!isCubeClear(locX, locY + 2, locZ, 1, 15)) {
            return false;
        }

        // generates the trunk
        locY++;
        int treeHeight = minHeight + random.nextInt(maxHeight);

        // Generate trunk
        for (int i = 0; i < treeHeight; i++) {
            setBlockInWorld(locX, locY + i, locZ, wood.block, wood.metadata);
        }
        // Generate leaves
        int currentHeight = locY + treeHeight - 3;
        generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight);
        generateLeafLayerCircle(world, random, 3.5, locX, locZ, currentHeight + 1);
        generateLeafLayerCircle(world, random, 2.5, locX, locZ, currentHeight + 2);
        generateLeafLayerCircle(world, random, 1.5, locX, locZ, currentHeight + 3);
        setBlockInWorld(locX, currentHeight + 4, locZ, leaves.block, leaves.metadata);

        return true;
    }
}
