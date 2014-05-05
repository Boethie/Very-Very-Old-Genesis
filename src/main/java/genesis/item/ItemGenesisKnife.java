package genesis.item;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;
import genesis.lib.Names;

public class ItemGenesisKnife extends Item {

	private float weaponDamage;
	private final Item.ToolMaterial toolMaterial;
	
	protected String materialName;
	
	public ItemGenesisKnife(Item.ToolMaterial toolMaterial, String materialName) {
		super();
		
		this.toolMaterial = toolMaterial;
		this.materialName = materialName;
		maxStackSize = 1;
		weaponDamage = 3.0F + toolMaterial.getDamageVsEntity();
		
		setUnlocalizedName(Names.itemKnife + materialName);
		setMaxDamage(toolMaterial.getMaxUses() / 2);
		setCreativeTab(Genesis.tabGenesis);
	}
	
	public void registerItem(String name) {
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocName) {
		registerItem(unlocName);
		return super.setUnlocalizedName(unlocName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_knife");
	}
	
	@Override
	// public float getStrVsBlock(ItemStack stack, Block block) {
	public float func_150893_a(ItemStack stack, Block block) {
		if (block == Blocks.web)
			return 15.0F;
		else {
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
		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D)
			stack.damageItem(2, entity);
		
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
		return toolMaterial.getEnchantability();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
		// toolMaterial.getToolCraftingItem()
		return toolMaterial.func_150995_f() == stack2.getItem() ? true : super.getIsRepairable(stack1, stack2);
	}
	
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) weaponDamage, 0));
		return multimap;
	}
	
	public String getToolMaterialName() {
		return toolMaterial.toString();
	}
}
