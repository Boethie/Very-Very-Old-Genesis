package genesis.block.aquatic;

import genesis.block.plants.BlockGenesisPlantTop;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Arbiter
 */
public class BlockCharniaTop extends BlockGenesisPlantTop {
    public BlockCharniaTop() {
        super(Material.water);
        setCreativeTab(null);
    }

    @Override
    protected void updateBlock(World world, int x, int y, int z) {
        Block b = world.getBlock(x, y - 1, z);
        if (b != null && b instanceof BlockCharnia) {
            int meta = world.getBlockMetadata(x, y - 1, z);
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        } else {
            world.setBlockToAir(x, y, z);
        }
        Block b1 = world.getBlock(x, y + 1, z);
        if (b1 == null || b1 != Blocks.water) {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block below = world.getBlock(x, y - 1, z);
        if (below != null && below instanceof BlockCharnia) {
            world.setBlockToAir(x, y - 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
}