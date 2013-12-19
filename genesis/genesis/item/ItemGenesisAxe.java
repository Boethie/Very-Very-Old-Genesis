package genesis.genesis.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;

public class ItemGenesisAxe extends ItemAxe {
	
	protected String materialName;

	public ItemGenesisAxe(int itemID, EnumToolMaterial material, String materialName) {
		super(itemID, material);
		
		this.materialName = materialName;
		setUnlocalizedName(Names.itemAxe + materialName);
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
    public void registerIcons(IconRegister register) {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_axe");
    }
}
