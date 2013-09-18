package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemGenesisSword extends ItemSword {
	
	protected String materialName;
	
	public ItemGenesisSword(int itemID, EnumToolMaterial material, String materialName) {
		super(itemID, material);
		
		this.materialName = materialName;
		setUnlocalizedName(Names.itemSword + materialName);
		setCreativeTab(Genesis.tabGenesis);
	}
	
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_sword");
    }
    
}
