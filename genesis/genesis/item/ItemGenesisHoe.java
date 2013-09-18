package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemGenesisHoe extends ItemHoe {
	
	protected String materialName;
	
	public ItemGenesisHoe(int itemID, EnumToolMaterial material, String materialName) {
		super(itemID, material);

		this.materialName = materialName;
		setUnlocalizedName(Names.itemHoe + materialName);
		setCreativeTab(Genesis.tabGenesis);
	}
	
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_hoe");
    }
    
}
