package genesis.block.plants;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockSphenophyllumTop extends BlockGenesisPlantTop {
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;

    public BlockSphenophyllumTop() {
        super(Material.plants);
    }

    protected void updateBlock(World world, int x, int y, int z) {
        Block b = world.getBlock(x, y - 1, z);
        if (b instanceof BlockSphenophyllumBase) {
            int meta = world.getBlockMetadata(x, y - 1, z) - 5;
            if (meta >= 0) {
                world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            }
        } else {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block below = world.getBlock(x, y - 1, z);
        if (below instanceof BlockSphenophyllumBase) {
            below.dropBlockAsItem(world, x, y - 1, z, meta + 5, 0);
            world.setBlockToAir(x, y - 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[3];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon(getTextureName() + "_stage_" + (i + 5) + "_top");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < 3; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}