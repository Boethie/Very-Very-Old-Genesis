package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemGenesisPickaxe extends ItemPickaxe {
	
	protected String materialName;
	
	public ItemGenesisPickaxe(int itemID, EnumToolMaterial material, String materialName) {
		super(itemID, material);
		
		this.materialName = materialName;
		setUnlocalizedName(Names.itemPickaxe + materialName);
		setCreativeTab(Genesis.tabGenesis);
	}
	
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_pickaxe");
    }
    
}
