package genesis.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.managers.GenesisModBlocks;
import genesis.managers.GenesisModItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Arbiter
 */
public class BlockSphenophyllumBase extends BlockGenesisCrop implements IGrowable {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockSphenophyllumBase() {
        super(GenesisModItems.sphenophyllum_spores, GenesisModItems.sphenophyllum_fiber, Blocks.dirt, 8, 2);
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
        return block == soilBlock || block == GenesisModBlocks.moss || block == Blocks.grass;
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
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
        int meta = world.getBlockMetadata(x, y, z);
        if (meta >= 5) {
            checkAndUpdateTop(world, x, y, z, meta);
        }
    }

    protected void checkAndUpdateTop(World world, int x, int y, int z, int meta) {
        if (meta >= 5) {
            if (world.getBlock(x, y + 1, z) == Blocks.air) {
                world.setBlock(x, y + 1, z, GenesisPlantBlocks.sphenophyllum_top, meta - 5, 2);
            } else if (world.getBlock(x, y + 1, z) instanceof BlockSphenophyllumTop) {
                world.setBlockMetadataWithNotify(x, y + 1, z, meta - 5, 2);
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_) != Blocks.farmland && super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
    }

    @Override
    public boolean isTall() {
        return true;
    }

    @Override
    public int quantityDropped(int par1, int par2, Random random) {
        return par1 == stages - 1 ? random.nextInt(3) : 0;
    }

    @Override
    protected Item func_149866_i() {
        return GenesisModItems.sphenophyllum_spores;
    }

    @Override
    protected Item func_149865_P() {
        return GenesisModItems.sphenophyllum_fiber;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block b = world.getBlock(x, y + 1, z);
        if (b instanceof BlockSphenophyllumTop) {
            world.setBlockToAir(x, y + 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        int count = world.rand.nextInt(3) + 1;
        if (meta == 7) {
            for (int i = 0; i < count + fortune; i++) {
                list.add(new ItemStack(func_149866_i(), 1, 0));
            }
            list.add(new ItemStack(func_149865_P(), 1, 0));
        }
        return list;
    }

    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(random, 2, 5);
        if (meta >= stages) {
            meta = stages - 1;
        }
        Block b = world.getBlock(x, y + 1, z);
        if (!world.isAirBlock(x, y + 1, z) && b.getMaterial() != Material.plants) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        } else if (world.isAirBlock(x, y + 1, z) || !world.isAirBlock(x, y + 1, z) && b.getMaterial() == Material.plants) {
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            checkAndUpdateTop(world, x, y, z, meta);
        }
    }
}