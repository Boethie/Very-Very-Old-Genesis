package genesis.item;

import java.util.List;

import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.ItemSets.ToolQuality;
import genesis.lib.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenesisHoe extends ItemHoe {
	
	protected String materialName;
	private ItemGenesisHoe nextTier;
	private ToolQuality quality;
	
	public ItemGenesisHoe(Item.ToolMaterial material, String materialName, ToolQuality quality) {
		super(material);

		this.materialName = materialName;
		this.quality = quality;
		setUnlocalizedName(Names.itemHoe + materialName + "." + quality.toString().toLowerCase());
		setCreativeTab(GenesisTabs.tabGenesisTools);
	}
	
	public void registerItem(String name) {
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocName) {
		registerItem(unlocName);
		return super.setUnlocalizedName(Names.itemHoe + this.theToolMaterial.name().toLowerCase().split("_")[0]);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + materialName + "_hoe");
    }
	
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_)
    {
        list.add(StatCollector.translateToLocal("tooltip.toolquality") + ": " + StatCollector.translateToLocal(quality.localizeableString));
    }
}
