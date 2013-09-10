package genesis.genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.common.Genesis;
import genesis.genesis.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Blocks {
	
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
		TreeBlocks.registerBlocks();
	}
}
