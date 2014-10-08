package genesis.block.aquatic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.Genesis;
import genesis.lib.Names;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * @author Arbiter
 */
public class BlockGenesisAlgae extends BlockGenesisAquaticPlant {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockGenesisAlgae() {
        super(Material.water);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        int size = Names.Plants.ALGAE_TYPES.size();
        for (int i = 0; i < size; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[Names.Plants.ALGAE_TYPES.size()];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon(Genesis.ASSETS + Names.Plants.ALGAE_TYPES.get(i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }
}