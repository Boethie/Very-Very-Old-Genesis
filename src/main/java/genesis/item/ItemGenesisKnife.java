package genesis.item;

import genesis.Genesis;
import genesis.item.ItemsToolSet.ToolQuality;
import genesis.lib.GenesisTabs;
import genesis.lib.Names;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenesisKnife extends Item {

    private final Item.ToolMaterial material;
    private final ToolQuality quality;
    protected String materialName;
    private float weaponDamage;

    public ItemGenesisKnife(Item.ToolMaterial toolMaterial, String nameOfMaterial, ToolQuality toolQuality) {
        material = toolMaterial;
        materialName = nameOfMaterial;
        maxStackSize = 1;
        weaponDamage = 3.0F + toolMaterial.getDamageVsEntity();
        quality = toolQuality;
        GameRegistry.registerItem(this, Names.itemKnife + toolMaterial.name().toLowerCase() + "." + toolQuality.toString().toLowerCase());
        setUnlocalizedName(toolMaterial.name().toLowerCase().split("_")[0]);
        setMaxDamage(toolMaterial.getMaxUses() / 2);
        if (toolQuality == ItemsToolSet.ToolQuality.values()[0]) {
            setCreativeTab(GenesisTabs.tabGenesisTools);
        } else {
            setCreativeTab(null);
        }
    }

    @Override
    public Item setUnlocalizedName(String unlocName) {
        return super.setUnlocalizedName(Names.itemKnife + unlocName);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName + "_knife");
    }

    @Override
    // public float getStrVsBlock(ItemStack stack, Block block) {
    public float func_150893_a(ItemStack stack, Block block) {
        if (block == Blocks.web) {
            return 15.0F;
        } else {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity1, EntityLivingBase entity2) {
        stack.damageItem(1, entity2);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
        if ((double) block.getBlockHardness(world, x, y, z) != 0.0D) {
            stack.damageItem(2, entity);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.block;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer entity) {
        entity.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return block == Blocks.web;
    }

    @Override
    public int getItemEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        // material.getToolCraftingItem()
        return material.func_150995_f() == stack2.getItem() || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) weaponDamage, 0));
        return multimap;
    }

    public String getToolMaterialName() {
        return material.toString();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
        return Items.shears.itemInteractionForEntity(itemstack, player, entity);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        return Items.shears.onBlockStartBreak(itemstack, x, y, z, player);
    }
}
