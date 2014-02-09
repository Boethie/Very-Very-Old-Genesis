package genesis.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;

public class ItemGenesis extends Item {

	public ItemGenesis() {
		super();
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
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getIconString());
	}
}
