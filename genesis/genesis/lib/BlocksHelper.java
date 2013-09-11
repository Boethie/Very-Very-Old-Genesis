package genesis.genesis.lib;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.TreeBlocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlocksHelper {
	
	public static void addTreeSubBlocksToCreative(int blockID, CreativeTabs creativeTabs, List itemList,
			int blockSet)
    {
		int start = blockSet * 4;
		int end = Math.min((blockSet + 1) * 4, TreeBlocks.woodTypes.length);
		
		for(int i = start; i < end; i++)
		{
			itemList.add(new ItemStack(blockID, 1, i - start));
		}
    }
	
}
