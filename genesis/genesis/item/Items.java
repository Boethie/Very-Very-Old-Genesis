package genesis.genesis.item;

import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import genesis.genesis.block.Blocks;
import genesis.genesis.item.ItemSets.ItemsSuitOfArmor;
import genesis.genesis.item.ItemSets.ItemsToolSet;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;

public class Items {

	public static ItemsToolSet graniteTools;
	
	public static ItemGenesis zircon;
	public static ItemsToolSet zirconTools;
	public static ItemsSuitOfArmor zirconArmor;
	
	public static ItemGenesis quartz;
	
	public static ItemGenesis olivine;
	public static ItemsToolSet olivineTools;
	public static ItemsSuitOfArmor olivineArmor;
	
	public static ItemGenesis meteoricIronIngot;
	public static ItemsToolSet meteoricIronTools;
	public static ItemsSuitOfArmor meteoricIronArmor;
	
	public static ItemGenesis garnet;
	
	public static ItemGenesis malachite;
	
	public static ItemGenesis chalcopyriteIngot;
	public static ItemsToolSet chalcopyriteTools;
	//public static ItemsSuitOfArmor chalcopyriteArmor;
	
	public static void init() {
		graniteTools = new ItemsToolSet(IDs.itemsGraniteToolsStartID,
				EnumHelper.addToolMaterial(Names.itemGraniteMaterial.toUpperCase(), 1, 181, 4.0F, 1.0F, 5),
				Names.itemGraniteMaterial, Blocks.granite, Item.stick);
		
		zircon = (ItemGenesis) new ItemGenesis(IDs.itemZirconID)
				.setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		zirconTools = new ItemsToolSet(IDs.itemsZirconToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemZirconMaterial.toUpperCase(), 2, 625, 7, 2, 12),
				Names.itemZirconMaterial, zircon, Item.stick);
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsZirconArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemZirconMaterial.toUpperCase(), 25, new int[] {2, 7, 6, 2}, 9),
					Names.itemZirconMaterial, zircon);
		
		quartz = (ItemGenesis) new ItemGenesis(IDs.itemQuartzID)
				.setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
		
		olivine = (ItemGenesis) new ItemGenesis(IDs.itemOlivineID)
				.setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");
		olivineTools = new ItemsToolSet(IDs.itemsOlivineToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemOlivineMaterial.toUpperCase(), 3, 500, 7, 3, 14),
				Names.itemOlivineMaterial, olivine, Item.stick);
		olivineArmor = new ItemsSuitOfArmor(IDs.itemsOlivineArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
					Names.itemOlivineMaterial, olivine);
		
		meteoricIronIngot = (ItemGenesis) new ItemGenesis(IDs.itemMeteoricIronIngotID)
				.setUnlocalizedName(Names.itemMeteoricIronIngot).setTextureName("meteoricIron_ingot");
		meteoricIronTools = new ItemsToolSet(IDs.itemsMeteoricIronToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemMeteoricIronMaterial.toUpperCase(), 2, 366, 4, 1, 25),
				Names.itemMeteoricIronMaterial, meteoricIronIngot, Item.stick);
		meteoricIronArmor = new ItemsSuitOfArmor(IDs.itemsMeteoricIronArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemMeteoricIronMaterial.toUpperCase(), 18, new int[] {2, 6, 5, 2}, 25),
					Names.itemMeteoricIronMaterial, meteoricIronIngot);
		
		garnet = (ItemGenesis) new ItemGenesis(IDs.itemGarnetID)
				.setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
		
		malachite = (ItemGenesis) new ItemGenesis(IDs.itemMalachiteID)
				.setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
		
		chalcopyriteIngot = (ItemGenesis) new ItemGenesis(IDs.itemChalcopyriteIngotID)
			.setUnlocalizedName(Names.itemChalcopyriteIngot).setTextureName("chalcopyrite_ingot");
		chalcopyriteTools = new ItemsToolSet(IDs.itemsChalcopyriteToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemChalcopyriteIngotMaterial.toUpperCase(), 2, 145, 5, 2, 12),
				Names.itemChalcopyriteIngotMaterial, chalcopyriteIngot, Item.stick);
		/**Waiting for texture*/
		//olivineArmor = new ItemsSuitOfArmor(IDs.itemsOlivineArmorStartID,
		//		EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
	}
	
	public static void registerItems() {
		Recipes.registerRecipes();
		ItemSets.registerAllRecipes();
	}
}
