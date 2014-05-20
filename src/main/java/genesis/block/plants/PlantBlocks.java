package genesis.block.plants;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.item.ModItems;
import genesis.item.itemblock.ItemBlockGenesisFern;
import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.lib.Names;

public class PlantBlocks {

	public static final String COOK_NAME = "cooksonia", BARA_NAME = "baragwanathia", 
			SCIA_NAME = "sciadophyton", PSILO_NAME = "psilophyton";
	
	public static final String ZYGO_NAME = "zygopteris", MATON_NAME = "matonidium", 
			ASTRA_NAME = "astralopteris", RUFF_NAME = "ruffordia";

	public static final ArrayList<String> flowerTypes = new ArrayList() {
		{
			add(COOK_NAME);
			add(BARA_NAME);
			add(SCIA_NAME);
			add(PSILO_NAME);
		}
	};
	
	public static final ArrayList<String> fernTypes = new ArrayList() {
		{
			add(ZYGO_NAME);
			add(MATON_NAME);
			add(ASTRA_NAME);
			add(RUFF_NAME);
		}
	};

	public static BlockGenesisFlowerPot flowerPot;
	public static Block calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	public static BlockGenesisFlower flower;
	public static BlockGenesisCrop zingiberopsis;
	public static BlockGenesisFern ferns;
	
	public static void init() {
		flowerPot = (BlockGenesisFlowerPot) new BlockGenesisFlowerPot().setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

		// calamitesPlant = new BlockCalamitesPlant().setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage) new BlockCalamitesStorage().setBlockTextureName("calamites_block").setBlockName(Names.blockCalamites);

		flower = (BlockGenesisFlower) new BlockGenesisFlower().setBlockName(Names.blockFlower);

		zingiberopsis = (BlockGenesisCrop) new BlockGenesisCrop(ModItems.rhizome, ModItems.rhizome, Blocks.farmland, 8, 4).setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		ferns = (BlockGenesisFern) new BlockGenesisFern().setBlockName(Names.blockFern);
	}

	public static void registerBlocks() {
		// GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites);

		GameRegistry.registerBlock(flower, ItemBlockGenesisPlant.class, Names.blockFlower);
		
		GameRegistry.registerBlock(zingiberopsis, Names.blockZingiberopsis);
		
		GameRegistry.registerBlock(ferns, ItemBlockGenesisFern.class, Names.blockFern);

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), "CCC", "CCC", "CCC", 'C', calamitesPlant);

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), "C", 'C', calamitesBlock);
	}

}
