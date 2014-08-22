package genesis.block.plants;

import genesis.client.renderer.BlockGenesisPlantRenderer;
import genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * A class to represent a block that can only appear on the top of another block (preferably a plant).
 * An abstract, pre-defined class is used to minimize the amount of code needed as all top blocks will
 * behave in the way as defined in this class. <br /><br />
 * The benefit of inheritance however, is that any of these methods can be overridden, which opens up
 * many more options.
 *
 * @author Arbiter
 */
public abstract class BlockGenesisPlantTop extends Block implements IPlantRenderSpecials {
    public BlockGenesisPlantTop(Material material) {
        super(material);
        disableStats();
        setTickRandomly(true);
        setHardness(0.0f);
        setResistance(0.0f);
        setStepSound(soundTypeGrass);
        setCreativeTab(null);
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
        return BlockGenesisPlantRenderer.renderID;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Block block = world.getBlock(x, y - 1, z);
        return block != null ? block.getPickBlock(target, world, x, y - 1, z) : null;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    protected abstract void updateBlock(World world, int x, int y, int z);

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
        updateBlock(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        updateBlock(world, x, y, z);
    }

    @Override
    public Item getItemDropped(int par1, Random par2, int par3) {
        return null;
    }

    @Override
    public double randomPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double randomYPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z,
                                    int side) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }
}