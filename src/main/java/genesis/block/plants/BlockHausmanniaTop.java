package genesis.block.plants;

import genesis.Genesis;
import genesis.client.renderer.block.BlockHausmanniaRenderer;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockHausmanniaTop extends BlockGenesisPlantTop implements IShearable {
    @SideOnly(Side.CLIENT)
    private IIcon plantIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    public BlockHausmanniaTop() {
        super(Material.plants);
        setCreativeTab(null);
    }

    @Override
    public int getRenderType() {
        return BlockHausmanniaRenderer.renderID;
    }

    @Override
    protected void updateBlock(World world, int x, int y, int z) {
        Block b = world.getBlock(x, y - 1, z);
        if (b != null && b instanceof BlockGenesisFern) {
            int meta = world.getBlockMetadata(x, y - 1, z);
            if (meta != 4) {
                world.setBlockToAir(x, y, z);
            }
        } else {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizerGrass.getGrassColor(0.5d, 1.0d);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return plantIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block below = world.getBlock(x, y - 1, z);
        if (below != null && below instanceof BlockGenesisFern) {
            world.setBlockToAir(x, y - 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(GenesisPlantBlocks.ferns, 2, 4));
        return list;
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        plantIcon = register.registerIcon(getTextureName() + "_bottom");
        topIcon = register.registerIcon(getTextureName() + "_top");
    }

    public IIcon getTopIcon() {
        return topIcon;
    }
}