package genesis.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import net.minecraftforge.common.util.EnumHelper;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.item.ItemSets.ItemsSuitOfArmor;
import genesis.item.ItemSets.ItemsToolSet;
import genesis.lib.Names;

public class ModItems {

	// Tools/Armors
	public static ItemsToolSet graniteTools;
	
	public static ItemGenesis zircon;
	public static ItemsToolSet zirconTools;
	public static ItemsSuitOfArmor zirconArmor;
	
	public static ItemGenesis quartz;
	
	public static ItemGenesis olivine;
	public static ItemsToolSet olivineTools;
	public static ItemsSuitOfArmor olivineArmor;
	
	public static ItemsToolSet meteoricIronTools;
	public static ItemsSuitOfArmor meteoricIronArmor;
	
	public static ItemGenesis garnet;
	
	public static ItemGenesis malachite;
	
	public static ItemGenesis hematite;
	
	// Seeds/Foods
	public static ItemGenesisSeed rhizome;
	
	public static void init() {
		// Tools/Armors
		graniteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemGraniteMaterial.toUpperCase(), 0, 181, 4.0F, 1.0F, 5),
				Names.itemGraniteMaterial, ModBlocks.granite, Items.stick,
				false, true, true, true, true, true);
		
		zircon = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		zirconTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemZirconMaterial.toUpperCase(), 2, 625, 7, 2, 12),
				Names.itemZirconMaterial, zircon, Items.stick,
				false, true, true, true, true, true);
		//zirconArmor = new ItemsSuitOfArmor(
		//			EnumHelper.addArmorMaterial(Names.itemZirconMaterial.toUpperCase(), 25, new int[] {2, 7, 6, 2}, 9),
		//			Names.itemZirconMaterial, zircon);
		
		quartz = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
		
		olivine = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");
		olivineTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemOlivineMaterial.toUpperCase(), 3, 500, 7, 3, 14),
				Names.itemOlivineMaterial, olivine, Items.stick,
				false, true, true, true, true, true);
		//olivineArmor = new ItemsSuitOfArmor(
		//			EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
		//			Names.itemOlivineMaterial, olivine);
		
		//meteoricIronArmor = new ItemsSuitOfArmor(
		//			EnumHelper.addArmorMaterial(Names.itemMeteoricIronMaterial.toUpperCase(), 18, new int[] {2, 6, 5, 2}, 25),
		//			Names.itemMeteoricIronMaterial, meteoricIronIngot);
		
		garnet = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
		
		malachite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
		
		hematite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemHematite).setTextureName("hematite");
		
		/*Waiting for texture*/
		//olivineArmor = new ItemsSuitOfArmor(
		//		EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
		
		// Seeds/Foods
		rhizome = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.zingiberopsis, 2, 1)
				.setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");
	}
	
	public static void registerItems() {
		Recipes.registerRecipes();
		ItemSets.registerAllRecipes();
	}
}
