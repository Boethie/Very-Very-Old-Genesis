package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemGenesis extends Item {

	public ItemGenesis(int itemID) {
		super(itemID);
		
        setCreativeTab(Genesis.tabGenesis);
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getIconString());
    }

}
