package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.ItemsToolSet.ToolQuality;
import genesis.lib.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemGenesisHoe extends ItemHoe {

    protected String materialName;
    private ToolQuality quality;

    public ItemGenesisHoe(Item.ToolMaterial material, String nameOfMaterial, ToolQuality toolQuality) {
        super(material);
        materialName = nameOfMaterial;
        quality = toolQuality;
        GameRegistry.registerItem(this, Names.itemHoe + "." + material.name().toLowerCase() + toolQuality.toString().toLowerCase());
        setUnlocalizedName(material.name().toLowerCase().split("_")[0]);
        if (toolQuality == ItemsToolSet.ToolQuality.values()[0]) {
            setCreativeTab(GenesisTabs.tabGenesisTools);
        } else {
            setCreativeTab(null);
        }
    }

    @Override
    public Item setUnlocalizedName(String unlocName) {
        return super.setUnlocalizedName(Names.itemHoe + unlocName);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName + "_hoe");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }
}
