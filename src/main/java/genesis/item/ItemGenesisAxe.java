package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.ItemSets.ToolQuality;
import genesis.lib.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemGenesisAxe extends ItemAxe {

    protected String materialName;
    private ToolQuality quality;

    public ItemGenesisAxe(Item.ToolMaterial material, String materialName, ToolQuality quality) {
        super(material);

        this.materialName = materialName;
        this.quality = quality;
        GameRegistry.registerItem(this, Names.itemAxe + "." + material.name().toLowerCase() + quality.toString().toLowerCase());
        setUnlocalizedName(material.name().toLowerCase().split("_")[0]);
        if(quality == ToolQuality.values()[0]) {
            setCreativeTab(GenesisTabs.tabGenesisTools);
        }
    }

    @Override
    public Item setUnlocalizedName(String unlocName) {
        return super.setUnlocalizedName(Names.itemAxe + unlocName);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_axe");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }
}
