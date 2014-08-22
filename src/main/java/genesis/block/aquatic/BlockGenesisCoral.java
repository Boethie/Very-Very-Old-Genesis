package genesis.block.aquatic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.lib.PlantMetadata;
import net.minecraft.block.Block;
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
public class BlockGenesisCoral extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockGenesisCoral() {
        super(Material.water);
        setHardness(0.75F);
        setResistance(8.5F);
        setStepSound(soundTypeStone);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        int size = PlantMetadata.coralTypes.size();
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
        icons = new IIcon[PlantMetadata.coralTypes.size()];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon(Genesis.ASSETS + PlantMetadata.coralTypes.get(i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }
}