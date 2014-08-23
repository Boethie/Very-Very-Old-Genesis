package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.ItemsToolSet.ToolQuality;
import genesis.lib.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemGenesisPickaxe extends ItemPickaxe {

    protected String materialName;
    private ToolQuality quality;

    public ItemGenesisPickaxe(Item.ToolMaterial material, String materialName, ToolQuality quality) {
        super(material);

        this.materialName = materialName;
        this.quality = quality;
        GameRegistry.registerItem(this, Names.itemPickaxe + "." + material.name().toLowerCase() + quality.toString().toLowerCase());
        setUnlocalizedName(material.name().toLowerCase().split("_")[0]);
        if (quality == ItemsToolSet.ToolQuality.values()[0]) {
            setCreativeTab(GenesisTabs.tabGenesisTools);
        } else {
            setCreativeTab(null);
        }
    }

    @Override
    public Item setUnlocalizedName(String unlocName) {
        return super.setUnlocalizedName(Names.itemPickaxe + unlocName);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName + "_pickaxe");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }
}
