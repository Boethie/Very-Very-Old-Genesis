package genesis.genesis.block.trees;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.common.Genesis;
import genesis.genesis.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TreeBlocks {
	
	public static final String[] woodTypes = new String[] {"sigillaria", "lepidodendron"};
	
	public static Block[] blockLogGenesis = new Block[IDs.TREE_BLOCK_COUNT];
	public static Block[] blockSaplingGenesis = new Block[IDs.TREE_BLOCK_COUNT];
	public static Block[] blockLeavesGenesis = new Block[IDs.TREE_BLOCK_COUNT];
	
	public static void init()
	{
		for (int set = 0; set < IDs.TREE_BLOCK_COUNT; set++)
		{
			blockLogGenesis[set] = new BlockGenesisLog(IDs.blockLogID.getID(set), set)
					.setUnlocalizedName(Names.blockLogGenesis_unloc);
			
			blockSaplingGenesis[set] = new BlockGenesisSapling(IDs.blockSaplingID.getID(set), set)
					.setUnlocalizedName(Names.blockSaplingGenesis_unloc);
			
			blockLeavesGenesis[set] = new BlockGenesisLeaves(IDs.blockLeavesID.getID(set), set)
					.setUnlocalizedName(Names.blockLeavesGenesis_unloc);
		}
	}
	
	public static void addNames()
	{
		/*LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 0), Names.blockLogAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 1), Names.blockLogBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 2), Names.blockLogCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 3), Names.blockLogFig_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 0), Names.blockLogLepidodendron_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 1), Names.blockLogSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 2), Names.blockLogTempskya_name);
		
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 0), Names.blockSaplingAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 1), Names.blockSaplingBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 2), Names.blockSaplingCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 3), Names.blockSaplingFig_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 0), Names.blockSaplingLepidodendron_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 1), Names.blockSaplingSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 2), Names.blockSaplingTempskya_name);
		
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 0), Names.blockLeavesAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 1), Names.blockLeavesBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 2), Names.blockLeavesCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 3), Names.blockLeavesFig_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 0), Names.blockLeavesLepdodendron_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 1), Names.blockLeavesSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 2), Names.blockLeavesTempskya_name);*/
	}
	
	public static void registerBlocks()
	{
		for (int set = 0; set < IDs.TREE_BLOCK_COUNT; set++)
		{
			GameRegistry.registerBlock(blockLogGenesis[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLogGenesis_unloc + set);
			
			GameRegistry.registerBlock(blockSaplingGenesis[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockSaplingGenesis_unloc + set);
			
			GameRegistry.registerBlock(blockLeavesGenesis[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLeavesGenesis_unloc + set);
		}
	}
}
