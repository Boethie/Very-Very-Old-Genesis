package genesis.block.plants;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.common.Genesis;
import genesis.item.ModItems;
import genesis.item.itemblock.ItemBlockAsteroxylon;
import genesis.item.itemblock.ItemBlockGenesisFern;
import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.lib.Names;

public class PlantBlocks {

	public static final String COOK_NAME = "cooksonia", BARA_NAME = "baragwanathia", 
			SCIA_NAME = "sciadophyton", PSILO_NAME = "psilophyton", NOTHIA_NAME = "nothia";
	
	public static final String ZYGO_NAME = "zygopteris", MATON_NAME = "matonidium", 
			ASTRA_NAME = "astralopteris", RUFF_NAME = "ruffordia";

	public static final ArrayList<String> plantTypes = new ArrayList() {
		{
			add(COOK_NAME);
			add(BARA_NAME);
			add(SCIA_NAME);
			add(PSILO_NAME);
			add(NOTHIA_NAME);
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
	public static BlockGenesisTerrestrialPlant plants;
	public static BlockZingiberopsisBase zingiberopsis;
	public static BlockZingiberopsisTop zingTop;
	public static BlockGenesisFern ferns;
	public static BlockAsteroxylon asteroxylon;
	public static BlockAsteroxylonTop asterTop;
	
	public static void init() {
		flowerPot = (BlockGenesisFlowerPot) new BlockGenesisFlowerPot().setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

		// calamitesPlant = new BlockCalamitesPlant().setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage) new BlockCalamitesStorage().setBlockTextureName("calamites_block").setBlockName("storage." + Names.blockCalamitesPlant);

		plants = (BlockGenesisTerrestrialPlant) new BlockGenesisTerrestrialPlant().setBlockName(Names.blockPlant);

		zingiberopsis = (BlockZingiberopsisBase) new BlockZingiberopsisBase().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		zingTop = (BlockZingiberopsisTop) new BlockZingiberopsisTop().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		ferns = (BlockGenesisFern) new BlockGenesisFern().setBlockName(Names.blockFern);
		
		asteroxylon = (BlockAsteroxylon) new BlockAsteroxylon().setBlockName(Names.blockAsteroxylon);
		
		asterTop = (BlockAsteroxylonTop) new BlockAsteroxylonTop().setBlockName(Names.blockAsteroxylon + ".top").setBlockTextureName(Genesis.MOD_ID + ":asteroxylon_top");
	}

	public static void registerBlocks() {
		// GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, "storage." + Names.blockCalamitesPlant);

		GameRegistry.registerBlock(plants, ItemBlockGenesisPlant.class, Names.blockPlant);
		
		GameRegistry.registerBlock(zingiberopsis, Names.blockZingiberopsis);
		
		GameRegistry.registerBlock(zingTop, Names.blockZingiberopsis + ".top");
		
		GameRegistry.registerBlock(ferns, ItemBlockGenesisFern.class, Names.blockFern);
		
		GameRegistry.registerBlock(asteroxylon, ItemBlockAsteroxylon.class, Names.blockAsteroxylon);
		
		GameRegistry.registerBlock(asterTop, Names.blockAsteroxylon + ".top");

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), "CCC", "CCC", "CCC", 'C', calamitesPlant);

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), "C", 'C', calamitesBlock);
	}

}
