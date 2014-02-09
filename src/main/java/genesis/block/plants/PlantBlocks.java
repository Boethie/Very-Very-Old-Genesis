package genesis.block.plants;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.lib.Names;

public class PlantBlocks {

	public static final String COOK_NAME = "cooksonia";
	public static final String BARA_NAME = "baragwanathia";
	public static final String SCIA_NAME = "sciadophyton";
	public static final String PSILO_NAME = "psilophyton";

	public static final ArrayList<String> flowerTypes = new ArrayList() {
		{
			add(COOK_NAME);
			add(BARA_NAME);
			add(SCIA_NAME);
			add(PSILO_NAME);
		}
	};

	public static BlockGenesisFlowerPot flowerPot;
	public static BlockCalamitesPlant calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	public static BlockGenesisFlower flower;
	public static BlockGenesisCrop zingiberopsis;

	public static void init() {
		flowerPot = (BlockGenesisFlowerPot) new BlockGenesisFlowerPot(IDs.blockFlowerPotID).setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

		calamitesPlant = (BlockCalamitesPlant) new BlockCalamitesPlant(IDs.blockCalamitesPlantID).setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage) new BlockCalamitesStorage(IDs.blockCalamitesBlockID).setBlockTextureName("calamites_block").setBlockName(Names.blockCalamites);

		flower = (BlockGenesisFlower) new BlockGenesisFlower(IDs.blockFlowerID).setBlockName(Names.blockFlower);

		zingiberopsis = (BlockGenesisCrop) new BlockGenesisCrop(IDs.blockZingiberopsisID, IDs.itemRhizomeID, Blocks.farmland, 8, 4).setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");

	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites);

		GameRegistry.registerBlock(flower, ItemBlockGenesisPlant.class, Names.blockFlower);

		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), "CCC", "CCC", "CCC", 'C', calamitesPlant);

		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), "C", 'C', calamitesBlock);
	}

}
