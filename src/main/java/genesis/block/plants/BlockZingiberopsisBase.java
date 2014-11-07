package genesis.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.managers.GenesisModBlocks;
import genesis.managers.GenesisModItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author Arbiter
 */
public class BlockZingiberopsisBase extends BlockGenesisCrop implements IGrowable {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockZingiberopsisBase() {
        super(GenesisModItems.zingiberopsis_rhizome, GenesisModItems.zingiberopsis_rhizome, Blocks.farmland, 8, 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[stages];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon(getTextureName() + "_stage_" + i + (i >= 5 ? "_bottom" : ""));
        }
    }

    @Override
    public boolean canPlaceBlockOn(Block block) {
        return block == soilBlock || block == GenesisModBlocks.moss;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float meta = world.getBlockMetadata(x, y, z);
        if (meta > 5) {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else if (meta == 0) {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
        } else {
            float f = 2*(1.0F/(6.0F - meta));
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F + f, 1.0F);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
        int meta = world.getBlockMetadata(x, y, z);
        if (meta >= 5) {
            checkAndUpdateTop(world, x, y, z, meta);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random par2Random, int par3) {
        return meta == 3 ? func_149865_P() : func_149866_i();
    }

    @Override
    public int quantityDropped(int par1, int par2, Random par3) {
        return par1 == stages - 1 ? droppedAmountMax : 1;
    }

    protected void checkAndUpdateTop(World world, int x, int y, int z, int meta) {
        if (meta >= 5) {
            if (world.getBlock(x, y + 1, z) == Blocks.air) {
                world.setBlock(x, y + 1, z, GenesisPlantBlocks.zingiberopsis_top, meta - 5, 2);
            } else if (world.getBlock(x, y + 1, z) instanceof BlockZingiberopsisTop) {
                world.setBlockMetadataWithNotify(x, y + 1, z, meta - 5, 2);
            }
        }
    }

    @Override
    public boolean isTall() {
        return true;
    }

    @Override
    protected Item func_149865_P() {
        return GenesisModItems.zingiberopsis_rhizome;
    }

    @Override
    protected Item func_149866_i() {
        return GenesisModItems.zingiberopsis_rhizome;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block b = world.getBlock(x, y + 1, z);
        if (b instanceof BlockZingiberopsisTop) {
            world.setBlockToAir(x, y + 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    /**
     * Overridden to provide custom code when grown using bonemeal.
     */
    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(random, 2, 5);
        if (meta >= stages) {
            meta = stages - 1;
        }
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        checkAndUpdateTop(world, x, y, z, meta);
    }
}