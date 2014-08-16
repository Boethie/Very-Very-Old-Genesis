package genesis.world.gen.feature;

import genesis.block.BlockAndMeta;
import genesis.block.trees.TreeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public abstract class WorldGenTreeBase extends WorldGenAbstractTree {

    protected BlockAndMeta leaves;
    protected BlockAndMeta wood;
    protected int type;
    protected int minHeight;
    protected int maxHeight;
    protected boolean notifyFlag;
    protected World world;
    protected Random random;

    // this array is the 8 directions of x and y, used for palm trees.
    protected int[][] directions = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    /**
     * Constructor - sets up tree variables
     *
     * @param leaves block and metadata for leaves blocks.
     * @param wood   block and metadata for wood blocks.
     * @param notify whether or not to notify blocks of the tree being grown.
     *               Generally false for world generation, true for saplings.
     */
    public WorldGenTreeBase(BlockAndMeta wood, BlockAndMeta leaves, boolean notify) {
        super(notify);
        this.leaves = leaves;
        this.wood = wood;
        notifyFlag = notify;
    }

    @Override
    public abstract boolean generate(World world, Random random, int locX, int locY, int locZ);

    // UTILITY GENERATORS - LEAVES, BRANCHES, TRUNKS

    // generates a circular disk of leaves around a coordinate block, only
    // overwriting air blocks.
    protected void generateLeafLayerCircle(World world, Random random, double radius, int xo, int zo, int h) {
        for (int x = (int) Math.ceil(xo - radius); x <= (int) Math.ceil(xo + radius); x++)
            for (int z = (int) Math.ceil(zo - radius); z <= (int) Math.ceil(zo + radius); z++) {
                double xfr = z - zo;
                double zfr = x - xo;

                if (xfr * xfr + zfr * zfr <= radius * radius)
                    setBlockInWorld(x, h, z, leaves.block, leaves.metadata);
            }
    }

    // generates a circular disk of leaves around a coordinate block, only
    // overwriting air blocks.
    // noise means the outer block has a 50% chance of generating
    protected void generateLeafLayerCircleNoise(World world, Random random, double radius, int xo, int zo, int h) {
        for (int x = (int) Math.ceil(xo - radius); x <= (int) Math.ceil(xo + radius); x++)
            for (int z = (int) Math.ceil(zo - radius); z <= (int) Math.ceil(zo + radius); z++) {
                double xfr = z - zo;
                double zfr = x - xo;

                if (xfr * xfr + zfr * zfr <= radius * radius)
                    if (xfr * xfr + zfr * zfr <= (radius - 1) * (radius - 1) || random.nextInt(2) == 0)
                        setBlockInWorld(x, h, z, leaves.block, leaves.metadata);
            }
    }

    // generates a circular disk of wood around a coordinate block, only
    // overwriting air and leaf blocks.
    protected void generateWoodLayerCircle(World world, Random random, double radius, int xo, int zo, int h) {
        for (int x = (int) Math.ceil(xo - radius); x <= (int) Math.ceil(xo + radius); x++)
            for (int z = (int) Math.ceil(zo - radius); z <= (int) Math.ceil(zo + radius); z++) {
                double xfr = z - zo;
                double zfr = x - xo;

                if (xfr * xfr + zfr * zfr <= radius * radius)
                    setBlockInWorld(x, h, z, wood.block, wood.metadata);
            }
    }

    // generate a branch, can be any direction
    // startHeight is absolute, not relative to the tree.
    // dir = direction: 0 = north (+z) 1 = east (+x) 2 = south 3 = west
    protected int[] generateStraightBranch(World world, Random random, int length, int locX, int locY, int locZ, int dir) {
        int direction = -1;
        if (dir < 2)
            direction = 1;
        if (dir % 2 == 0) {
            // generates branch
            for (int i = 1; i <= length; i++)
                setBlockInWorld(locX + i * direction, locY + i, locZ, wood.block, TreeBlocks.getLogMetadataForDirection(wood.metadata, ForgeDirection.NORTH));
            return new int[]{locX + length * direction, locY + length, locZ};
        } else {
            for (int i = 1; i <= length; i++)
                setBlockInWorld(locX, locY + i, locZ + i * direction, wood.block, TreeBlocks.getLogMetadataForDirection(wood.metadata, ForgeDirection.EAST));
            return new int[]{locX, locY + length, locZ + length * direction};
        }
    }

    // same as GenerateStraightBranch but downward (for Arau tree)
    protected int[] generateStraightBranchDown(World world, Random random, int length, int locX, int locY, int locZ, int dir) {
        int direction = -1;
        if (dir < 2)
            direction = 1;
        if (dir % 2 == 0) {
            // generates branch
            for (int i = 1; i <= length; i++)
                setBlockInWorld(locX + i * direction, locY - i, locZ, wood.block, TreeBlocks.getLogMetadataForDirection(wood.metadata, ForgeDirection.NORTH));
            return new int[]{locX + length * direction, locY - length, locZ};
        } else {
            for (int i = 1; i <= length; i++)
                setBlockInWorld(locX, locY - i, locZ + i * direction, wood.block, TreeBlocks.getLogMetadataForDirection(wood.metadata, ForgeDirection.EAST));
            return new int[]{locX, locY - length, locZ + length * direction};
        }
    }

    protected void setBlockInWorld(int x, int y, int z, Block block, int metadata) {
        try {
            if (block == wood.block && (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isReplaceable() || world.getBlock(x, y, z).isLeaves(world, x, y, z))) {
                if (notifyFlag)
                    world.setBlock(x, y, z, block, metadata, 3);
                else
                    world.setBlock(x, y, z, block, metadata, 2);
            } else if (block == leaves.block && world.isAirBlock(x, y, z))
                if (notifyFlag)
                    world.setBlock(x, y, z, block, metadata, 3);
                else
                    world.setBlock(x, y, z, block, metadata, 2);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Already decorating!!"))
                System.out.println("Error: Tree block couldn't generate!");
        }
    }

    protected boolean isCubeClear(int x, int y, int z, int radius, int height) {
        for (int i = x - radius; i <= x + radius; i++)
            for (int j = z - radius; j <= z + radius; j++)
                for (int k = y; k <= y + height; k++)
                    if (!(world.getBlock(i, k, j) == Blocks.air || world.getBlock(i, k, j).isLeaves(world, i, k, j)))
                        return false;

        return true;
    }

    protected boolean isCubeClearWater(int x, int y, int z, int radius, int height) {
        for (int i = x - radius; i <= x + radius; i++)
            for (int j = z - radius; j <= z + radius; j++)
                for (int k = y; k <= y + height; k++)
                    if (!(world.getBlock(i, k, j) == Blocks.air || world.getBlock(i, k, j).getMaterial() == Material.water || world.getBlock(i, k, j).isLeaves(world, i, k, j)))
                        return false;

        return true;
    }

    // finds top block for the given x,z position (excluding leaves)
    protected int findTopBlock(int x, int z) {
        int y = 256;
        for (boolean var6 = false; (world.getBlock(x, y, z) == Blocks.air || world.getBlock(x, y, z).isLeaves(world, x, y, z)) && y > 0; --y)
            ;
        return y;
    }
}
