package genesis.block.trees;

import genesis.block.BlockGenesis;
import genesis.client.renderer.BlockBjuviaConeRenderer;
import genesis.common.GenesisTabs;
import genesis.item.GenesisModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author Arbiter
 */
public class BlockBjuviaCone extends BlockGenesis {
    public BlockBjuviaCone() {
        super(Material.wood);
        setHardness(0.2F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        setCreativeTab(null);
        setTickRandomly(true);
        setBlockBounds(0.3f, 0.0f, 0.3f, 0.7f, 0.8f, 0.7f);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockBjuviaConeRenderer.renderID;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return meta == 2 ? 3 : 0;
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return GenesisModItems.bjuvia_seeds;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(10) == 0 && canBlockStay(world, x, y, z)) {
            int oldMeta =  world.getBlockMetadata(x, y, z);
            int newMeta = world.getBlockMetadata(x, y, z) + 1;
            if (newMeta > 2) {
                newMeta = 2;
            }
            if (oldMeta != newMeta) {
                world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, 0, 2);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return !world.isAirBlock(x, y - 1, z) && world.getBlock(x, y, z) == GenesisTreeBlocks.logs[GenesisTreeBlocks.TreeType.BJUVIA.getGroup()];
    }
}