package genesis.item;


import com.google.common.collect.Multimap;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.lib.Names;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import genesis.item.ItemsToolSet.ToolQuality;
import genesis.lib.GenesisTabs;
import genesis.Genesis;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemGenesisSpear extends Item
{
    private final Item.ToolMaterial material;
    private final ToolQuality quality;
    protected String materialName;
    private float weaponDamage;

    public ItemGenesisSpear(Item.ToolMaterial toolMaterial, String nameOfMaterial, ToolQuality toolQuality){
        material = toolMaterial;
        materialName = nameOfMaterial;
        maxStackSize = 1;
        weaponDamage = 4.0F + toolMaterial.getDamageVsEntity();
        quality = toolQuality;
        GameRegistry.registerItem(this, Names.itemSpear + toolMaterial.name().toLowerCase() + "." + toolQuality.toString().toLowerCase());
        setUnlocalizedName(toolMaterial.name().toLowerCase().split("_")[0]);
        setMaxDamage(toolMaterial.getMaxUses() / 2);
        if (toolQuality == ItemsToolSet.ToolQuality.values()[0]){
            setCreativeTab(GenesisTabs.tabGenesisCombat);
        } else {
            setCreativeTab(null);
        }

    }

    @Override
    public Item setUnlocalizedName(String unlocName) { return super.setUnlocalizedName(Names.itemSpear + unlocName); }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName + "_spear");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity1, EntityLivingBase entity2) {
        stack.damageItem(1, entity2);
        return true;
    }

    @Override
    public int getItemEnchantability() { return material.getEnchantability(); }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return material.func_150995_f() == stack2.getItem() || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) weaponDamage, 0));
        return multimap;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }
}
