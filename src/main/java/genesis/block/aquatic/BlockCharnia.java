package genesis.block.aquatic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Arbiter
 */
public class BlockCharnia extends BlockGenesisAquaticPlant {
    public BlockCharnia() {
        super(Material.water);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block b = world.getBlock(x, y + 1, z);
        if (b instanceof BlockCharniaTop) {
            world.setBlockToAir(x, y + 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    protected void dropPlantIfCannotStay(World world, int x, int y, int z) {
        if (!canBlockStay(world, x, y, z)) {
            Block b = world.getBlock(x, y + 1, z);
            if (b instanceof BlockCharniaTop) {
                world.setBlockToAir(x, y + 1, z);
            }
        }
        super.dropPlantIfCannotStay(world, x, y, z);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        world.setBlock(x, y + 1, z, GenesisAquaticBlocks.charnia_top, 0, 2);
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        Block b = world.getBlock(x, y + 1, z);
        if ((b == null) || (!(b instanceof BlockCharniaTop))) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y + 2, z) == Blocks.water && super.canPlaceBlockAt(world, x, y, z);
    }
}