package genesis.genesis.item;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Names;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;

public class ItemGenesisSpade extends ItemSpade {
	
	protected String materialName;
	
	public ItemGenesisSpade(int itemID, EnumToolMaterial material, String materialName) {
		super(itemID, material);

		this.materialName = materialName;
		setUnlocalizedName(Names.itemSpade + materialName);
		setCreativeTab(Genesis.tabGenesis);
	}
	
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_shovel");
    }
    
}
