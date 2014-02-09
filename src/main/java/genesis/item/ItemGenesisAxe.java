package genesis.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;
import genesis.lib.Names;

public class ItemGenesisAxe extends ItemAxe {
	
	protected String materialName;

	public ItemGenesisAxe(Item.ToolMaterial material, String materialName) {
		super(material);
		
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
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_axe");
    }
}
