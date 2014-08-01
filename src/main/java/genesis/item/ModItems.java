package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.util.EnumHelper;
import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.common.Genesis;
import genesis.item.ItemSets.ItemsToolSet;
import genesis.lib.Names;

public class ModItems {

	// Tools
	public static ItemsToolSet graniteTools;
	public static ItemsToolSet rhyoliteTools;
	public static ItemsToolSet quartziteTools;
	public static ItemsToolSet limestoneTools;
	public static ItemsToolSet flintTools;
	
	// Crafting
	public static ItemGenesis quartz;
	public static ItemGenesis biotite;
	public static ItemGenesis zircon;
	public static ItemGenesis garnet;
	public static ItemGenesis hematite;
	public static ItemGenesis malachite;	
	public static ItemGenesis olivine;
	
	public static ItemGenesis sphenoFiber;
	public static ItemGenesis bjuviaSeeds;
	
	public static ItemGenesis ceratitesShell;
	public static ItemGenesis arthropleuraChitin;
	public static ItemGenesis tyrannosaurusTooth;
	public static ItemGenesis velociraptorClaw;
	public static ItemGenesis archeopterixFeather;
	
	// Seeds
	public static ItemGenesisSeed rhizome;
	public static ItemGenesisSeed sphenoSpores;
	
	// Foods
	public static ItemGenesisFood rawEryops;
	public static ItemGenesisFood cookedEryops;
	
	public static ItemGenesisFood rawAphthoroblattina;
	public static ItemGenesisFood cookedAphthoroblattina;
	
	public static ItemGenesisFood rawCeratites;
	public static ItemGenesisFood cookedCeratites;
	
	public static ItemGenesisFood rawMixosaurusFillet;
	public static ItemGenesisFood cookedMixosaurusFillet;
	
	public static ItemGenesisFood rawArcheopterixThigh;
	public static ItemGenesisFood cookedArcheopterixThigh;
	
	public static void init() {
		// Tools
	    graniteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemGraniteCrudeMaterial.toUpperCase(), 1, 375, 4.0F, 1.0F, 5),
				EnumHelper.addToolMaterial(Names.itemGraniteChippedMaterial.toUpperCase(), 1, 390, 4.6F, 1.0F, 5),
				EnumHelper.addToolMaterial(Names.itemGranitePolishedMaterial.toUpperCase(), 1, 415, 4.9F, 1.0F, 5),
				EnumHelper.addToolMaterial(Names.itemGraniteSharpenedMaterial.toUpperCase(), 1, 425, 5.2F, 1.0F, 5), Names.itemGraniteMaterial, ModBlocks.granite, Items.stick);
		
	    rhyoliteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemRhyoliteCrudeMaterial.toUpperCase(), 1, 375, 4.0F, 1.0F, 5),
	            EnumHelper.addToolMaterial(Names.itemRhyoliteChippedMaterial.toUpperCase(), 1, 390, 4.6F, 1.0F, 5),
	            EnumHelper.addToolMaterial(Names.itemRhyolitePolishedMaterial.toUpperCase(), 1, 415, 4.9F, 1.0F, 5),
	            EnumHelper.addToolMaterial(Names.itemRhyoliteSharpenedMaterial.toUpperCase(), 1, 425, 5.2F, 1.0F, 5),Names.itemRhyoliteMaterial, ModBlocks.rhyolite, Items.stick);
		
	    quartziteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemQuartziteCrudeMaterial.toUpperCase(), 1, 375, 4.0F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemQuartziteChippedMaterial.toUpperCase(), 1, 452, 4.6F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemQuartzitePolishedMaterial.toUpperCase(), 1, 477, 4.9F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemQuartziteSharpenedMaterial.toUpperCase(), 1, 487, 5.2F, 1.0F, 5), Names.itemQuartziteMaterial, ModBlocks.quartzite, Items.stick);
	    
	    limestoneTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemLimestoneCrudeMaterial.toUpperCase(), 1, 187, 4.0F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemLimestoneChippedMaterial.toUpperCase(), 1, 202, 4.6F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemLimestonePolishedMaterial.toUpperCase(), 1, 247, 4.9F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemLimestoneSharpenedMaterial.toUpperCase(), 1, 237, 5.2F, 1.0F, 5)
                , Names.itemLimestoneMaterial, ModBlocks.limestone, Items.stick);
	    
	    flintTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemFlintCrudeMaterial.toUpperCase(), 1, 353, 4.0F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemFlintChippedMaterial.toUpperCase(), 1, 368, 4.6F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemFlintPolishedMaterial.toUpperCase(), 1, 493, 4.9F, 1.0F, 5),
                EnumHelper.addToolMaterial(Names.itemFlintSharpenedMaterial.toUpperCase(), 1, 403, 5.2F, 1.0F, 5), Names.itemFlintMaterial, Items.flint, Items.stick);
		
		// Crafting
		quartz = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");	
		biotite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemBiotite).setTextureName("biotite");
		zircon = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		garnet = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
		hematite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemHematite).setTextureName("hematite");
		malachite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
		olivine = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");		
		
		sphenoFiber = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemSphenoFiber).setTextureName("sphenophyllum_fiber");
		bjuviaSeeds = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemBjuviaSeeds).setTextureName("seeds_bjuvia");
		
		ceratitesShell = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemCeratitesShell).setTextureName("ceratites_shell");
		arthropleuraChitin = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemArthopleuraChitin).setTextureName("arthropleura_chitin");
		tyrannosaurusTooth = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemTyrannosaurasTooth).setTextureName("tyrannosaurus_tooth");
		velociraptorClaw = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemVelociraptorClaw).setTextureName("velociraptor_claw");
		archeopterixFeather = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemArcheopterixFeather).setTextureName("archeopterix_feather");
		
		// Seeds
		rhizome = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.zingiberopsis, 1, 0.5f,true)
				.setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");
		sphenoSpores = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.sphenophyllum,false)
				.setUnlocalizedName(Names.itemSphenoSpores).setTextureName("sphenophyllum_spores");
		
		// Foods
		rawEryops = (ItemGenesisFood) new ItemGenesisFood(3, 1.8F)
				.setUnlocalizedName(Names.itemRawEryops).setTextureName("eryops_raw_leg");
		cookedEryops = (ItemGenesisFood) new ItemGenesisFood(8, 12.8F)
				.setUnlocalizedName(Names.itemCookedEryops).setTextureName("eryops_cooked_leg");
		
		rawAphthoroblattina = (ItemGenesisFood) new ItemGenesisFood(1, 0.6F)
				.setUnlocalizedName(Names.itemRawAphthoroblattina).setTextureName("aphthoroblattina_raw");
		cookedAphthoroblattina = (ItemGenesisFood) new ItemGenesisFood(2, 1.2F)
				.setUnlocalizedName(Names.itemCookedAphthoroblattina).setTextureName("aphthoroblattina_cooked");
		
		rawCeratites = (ItemGenesisFood) new ItemGenesisFood(1, 1.2F)
				.setUnlocalizedName(Names.itemRawCeratites).setTextureName("ceratites_raw");
		cookedCeratites = (ItemGenesisFood) new ItemGenesisFood(5, 6.0F)
				.setUnlocalizedName(Names.itemCookedCeratites).setTextureName("ceratites_cooked");
		
		rawMixosaurusFillet = (ItemGenesisFood) new ItemGenesisFood(1, 1.2F)
				.setUnlocalizedName(Names.itemRawMixosaurus).setTextureName("mixosaurus_fillet_raw");
		cookedMixosaurusFillet = (ItemGenesisFood) new ItemGenesisFood(5, 6.0F)
				.setUnlocalizedName(Names.itemCookedMixosaurus).setTextureName("mixosaurus_fillet_cooked");
		
		rawArcheopterixThigh = (ItemGenesisFood) new ItemGenesisFood(2, 1.2F)
				.setUnlocalizedName(Names.itemRawArcheopterix).setTextureName("archeopterix_thigh_raw");
		cookedArcheopterixThigh = (ItemGenesisFood) new ItemGenesisFood(6, 7.2F)
				.setUnlocalizedName(Names.itemCookedArcheopterix).setTextureName("archeopterix_thigh_cooked");
	}
	
	public static void registerItems() {
		Recipes.registerRecipes();
		ItemSets.registerAllRecipes();
	}
}
