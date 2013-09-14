package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemGenesisArmor extends ItemArmor {
	
	protected static final String[] TYPE_NAMES = {"helmet", "chestplate", "leggings", "boots"};
	
	protected String materialName;
	
    public ItemGenesisArmor(int itemID, EnumArmorMaterial armorMaterial, int armorType, String materialName) {
		super(itemID, armorMaterial, 0, armorType);
		
		setCreativeTab(Genesis.tabGenesis);
		
		String matNameCap = materialName.substring(0, 1).toUpperCase() + materialName.substring(1);
		setUnlocalizedName(Names.mod_unloc + TYPE_NAMES[this.armorType] + matNameCap);
		
		this.materialName = materialName;
    }
    
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
	    this.itemIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.materialName + "_" + TYPE_NAMES[this.armorType]);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		if (slot == 2)
			return Genesis.MOD_ID + ":textures/models/armor/" + materialName + "_overlay.png";
		
		return Genesis.MOD_ID + ":textures/models/armor/" + materialName + "_skin.png";
	}
	
}
