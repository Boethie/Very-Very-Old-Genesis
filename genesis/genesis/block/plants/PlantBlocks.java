package genesis.genesis.block.plants;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class PlantBlocks {

	public static BlockGenesisFlowerPot flowerPot;
	
	public static BlockCalamitesPlant calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	
	public static BlockGenesisPlant neuropterisPlant;
	
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
	}

	public static void registerBlocks()
	{
		GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites);
		
		GameRegistry.registerBlock(neuropterisPlant, Names.blockNeuropterisPlant);
		
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
