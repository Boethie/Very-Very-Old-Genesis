package genesis.genesis.block.plants;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class PlantBlocks {
	
	public static final String COOK_NAME = "cooksonia";
	public static final String BARA_NAME = "baragwanathia";
	public static final String SCIA_NAME = "sciadophyton";
	public static final String PSILO_NAME = "psilophyton";
	
	public static final ArrayList<String> flowerTypes = new ArrayList() {{
		add(COOK_NAME);
		add(BARA_NAME);
		add(SCIA_NAME);
		add(PSILO_NAME);
	}};

	public static BlockGenesisFlowerPot flowerPot;
	
	public static BlockCalamitesPlant calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	
	public static BlockGenesisPlant neuropterisPlant;
	
	public static BlockGenesisFlower flower;
	
	public static void init()
	{
		flowerPot = (BlockGenesisFlowerPot)new BlockGenesisFlowerPot(IDs.blockFlowerPotID).setTextureName("flower_pot")
				.setUnlocalizedName(Names.blockFlowerPot);
		
		calamitesPlant = (BlockCalamitesPlant)new BlockCalamitesPlant(IDs.blockCalamitesPlantID).setTextureName("calamites")
				.setUnlocalizedName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage)new BlockCalamitesStorage(IDs.blockCalamitesBlockID).setTextureName("calamites_block")
				.setUnlocalizedName(Names.blockCalamites);
		
		neuropterisPlant = (BlockGenesisPlant)new BlockNeuropterisPlant(IDs.blockNeuropterisPlantID).setTextureName("neuropteris")
				.setUnlocalizedName(Names.blockNeuropterisPlant);
		
		flower = (BlockGenesisFlower)new BlockGenesisFlower(IDs.blockFlowerID).setUnlocalizedName(Names.blockFlower);
	}

	public static void registerBlocks()
	{
		GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites);
		
		GameRegistry.registerBlock(neuropterisPlant, Names.blockNeuropterisPlant);
		
		GameRegistry.registerBlock(flower, ItemBlockGenesisPlant.class, Names.blockFlower);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), 
			"CCC",
			"CCC",
			"CCC",
			'C', calamitesPlant);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), 
			"C",
			'C', calamitesBlock);
	}
	
}
