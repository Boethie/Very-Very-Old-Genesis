package genesis.genesis.block.plants;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class PlantBlocks {
	
	public static BlockCalamitesPlant calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	
	public static void init()
	{
		calamitesPlant = (BlockCalamitesPlant)new BlockCalamitesPlant(IDs.blockCalamitesPlantID).setTextureName("calamites")
				.setUnlocalizedName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage)new BlockCalamitesStorage(IDs.blockCalamitesBlockID).setTextureName("calamites_block")
				.setUnlocalizedName(Names.blockCalamites);
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, Names.blockCalamites);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), 
			"CC",
			"CC",
			'C', calamitesPlant);
		
		CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 4), 
			"C",
			'C', calamitesBlock);
	}

}
