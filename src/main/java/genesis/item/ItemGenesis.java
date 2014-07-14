package genesis.item;

import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenesis extends Item {

	public ItemGenesis() {
		super();
		setCreativeTab(GenesisTabs.tabGenesisMaterials);
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
