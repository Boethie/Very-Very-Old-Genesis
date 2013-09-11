package genesis.genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class Blocks {

	public static Block granite = new BlockGranite(IDs.blockGraniteID).setTextureName("granite")
			.setUnlocalizedName(Names.blockGranite_unloc);
	public static Block moss = new BlockMoss(IDs.blockMossID).setTextureName("moss")
			.setUnlocalizedName(Names.blockGranite_unloc);
	public static Block graniteMossy = new BlockGranite(IDs.blockGraniteMossyID).setTextureName("granite_mossy")
			.setUnlocalizedName(Names.blockGraniteMossy_unloc);
	
	public static void init()
	{
		TreeBlocks.init();
	}
	
	public static void addNames()
	{
		TreeBlocks.addNames();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(granite, ItemBlockWithMetadata.class, Genesis.modid + ".granite");
		GameRegistry.registerBlock(moss, ItemBlockWithMetadata.class, Genesis.modid + ".moss");
		GameRegistry.registerBlock(graniteMossy, ItemBlockWithMetadata.class, Genesis.modid + ".graniteMossy");
		
		TreeBlocks.registerBlocks();
	}
}
