package genesis.genesis.block.plants;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class PlantBlocks {
	
	public static Block calamitesPlant;
	public static Block calamitesBlock;
	
	public static void init()
	{
		calamitesPlant = new BlockCalamitesPlant(IDs.blockCalamitesPlantID).setTextureName("calamites")
				.setUnlocalizedName(Names.blockCalamitesPlant_unloc);
		calamitesBlock = new BlockCalamitesStorage(IDs.blockCalamitesBlockID).setTextureName("calamites_block")
				.setUnlocalizedName(Names.blockCalamites_unloc);
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant_unloc);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites_unloc);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), 
			"CC",
			"CC",
			'C', calamitesPlant);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 4), 
			"C",
			'C', calamitesBlock);
	}

}
