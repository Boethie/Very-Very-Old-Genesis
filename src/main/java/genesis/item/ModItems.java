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
	
	public static ItemGenesis meteoricIronIngot;
	public static ItemsToolSet meteoricIronTools;
	public static ItemsSuitOfArmor meteoricIronArmor;
	
	public static ItemGenesis garnet;
	
	public static ItemGenesis malachite;
	
	public static ItemGenesis chalcopyriteIngot;
	public static ItemsToolSet chalcopyriteTools;
	//public static ItemsSuitOfArmor chalcopyriteArmor;
	
	// Seeds/Foods
	public static ItemGenesisSeed rhyzome;
	
	public static void init() {
		// Tools/Armors
		graniteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemGraniteMaterial.toUpperCase(), 0, 181, 4.0F, 1.0F, 5),
				Names.itemGraniteMaterial, ModBlocks.granite, Items.stick);
		
		zircon = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		zirconTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemZirconMaterial.toUpperCase(), 2, 625, 7, 2, 12),
				Names.itemZirconMaterial, zircon, Items.stick);
		zirconArmor = new ItemsSuitOfArmor(
					EnumHelper.addArmorMaterial(Names.itemZirconMaterial.toUpperCase(), 25, new int[] {2, 7, 6, 2}, 9),
					Names.itemZirconMaterial, zircon);
		
		quartz = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
		
		olivine = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");
		olivineTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemOlivineMaterial.toUpperCase(), 3, 500, 7, 3, 14),
				Names.itemOlivineMaterial, olivine, Items.stick);
		olivineArmor = new ItemsSuitOfArmor(
					EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
					Names.itemOlivineMaterial, olivine);
		
		meteoricIronIngot = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemMeteoricIronIngot).setTextureName("iron_meteoric_ingot");
		meteoricIronTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemMeteoricIronMaterial.toUpperCase(), 2, 366, 4, 1, 25),
				Names.itemMeteoricIronMaterial, meteoricIronIngot, Items.stick);
		meteoricIronArmor = new ItemsSuitOfArmor(
					EnumHelper.addArmorMaterial(Names.itemMeteoricIronMaterial.toUpperCase(), 18, new int[] {2, 6, 5, 2}, 25),
					Names.itemMeteoricIronMaterial, meteoricIronIngot);
		
		garnet = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
		
		malachite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
		
		chalcopyriteIngot = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemChalcopyriteIngot).setTextureName("chalcopyrite_ingot");
		chalcopyriteTools = new ItemsToolSet(
				EnumHelper.addToolMaterial(Names.itemChalcopyriteIngotMaterial.toUpperCase(), 2, 145, 5, 2, 12),
				Names.itemChalcopyriteIngotMaterial, chalcopyriteIngot, Items.stick);
		
		/*Waiting for texture*/
		//olivineArmor = new ItemsSuitOfArmor(
		//		EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 23, new int[] {3, 7, 5, 2}, 10),
		
		// Seeds/Foods
		rhyzome = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.zingiberopsis, 2, 1)
				.setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");
	}
	
	public static void registerItems() {
		Recipes.registerRecipes();
		ItemSets.registerAllRecipes();
	}
}
