package genesis.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import net.minecraftforge.common.util.EnumHelper;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.item.ItemSets.ItemsToolSet;
import genesis.lib.Names;

public class ModItems {

	// Tools/Armors
	public static ItemsToolSet graniteTools;
	
	public static ItemGenesis zircon;
	public static ItemGenesis quartz;	
	public static ItemGenesis olivine;	
	public static ItemGenesis garnet;	
	public static ItemGenesis malachite;	
	public static ItemGenesis hematite;
	public static ItemGenesis biotite;
	
	public static ItemGenesis sphenoFiber;
	
	// Seeds/Foods
	public static ItemGenesisSeed rhizome;
	public static ItemGenesisSeed sphenoSpore;
	
	public static void init() {
		// Tools/Armors
		graniteTools = new ItemsToolSet(EnumHelper.addToolMaterial(Names.itemGraniteMaterial.toUpperCase(), 0, 181, 4.0F, 1.0F, 5),
				Names.itemGraniteMaterial, ModBlocks.granite, Items.stick, true, true, true, true, true);
		
		zircon = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemZircon).setTextureName("zircon");		
		quartz = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");		
		olivine = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");		
		garnet = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");		
		malachite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");		
		hematite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemHematite).setTextureName("hematite");		
		biotite = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemBiotite).setTextureName("biotite");
		
		sphenoFiber = (ItemGenesis) new ItemGenesis().setUnlocalizedName(Names.itemSphenoFiber).setTextureName("sphenophyllum_fiber");
		
		// Seeds/Foods
		rhizome = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.zingiberopsis, 1, 0.5f)
				.setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");
		sphenoSpore = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.sphenophyllum, 1, 1.0f)
				.setUnlocalizedName(Names.itemSphenoSpore).setTextureName("sphenophyllum_spores");
	}
	
	public static void registerItems() {
		Recipes.registerRecipes();
		ItemSets.registerAllRecipes();
	}
}
