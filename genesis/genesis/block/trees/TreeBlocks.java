package genesis.genesis.block.trees;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TreeBlocks {

	public static final String SIGIL_NAME = "sigillaria";
	public static final String LEPID_NAME = "lepidodendron";
	public static final String CORD_NAME = "cordaites";
	
	public static final ArrayList<String> woodTypes = new ArrayList() {{
		add(SIGIL_NAME);
		add(LEPID_NAME);
		add(CORD_NAME);
	}};
	public static final int woodTypeCount = woodTypes.size();
	
	public static final int setSize = 4;
	
	public static Block[] blocksLogs = new Block[IDs.TREE_BLOCK_COUNT];
	public static Block[] blocksSaplings = new Block[IDs.TREE_BLOCK_COUNT];
	public static Block[] blocksLeaves = new Block[IDs.TREE_BLOCK_COUNT];
	
	public static void init()
	{
		for (int set = 0; set < IDs.TREE_BLOCK_COUNT; set++)
		{
			blocksLogs[set] = new BlockGenesisLog(IDs.blockLogID.getID(set), set)
					.setUnlocalizedName(Names.blockLogGenesis);
			
			blocksSaplings[set] = new BlockGenesisSapling(IDs.blockSaplingID.getID(set), set)
					.setUnlocalizedName(Names.blockSaplingGenesis);
			
			blocksLeaves[set] = new BlockGenesisLeaves(IDs.blockLeavesID.getID(set), set)
					.setUnlocalizedName(Names.blockLeavesGenesis);
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
			GameRegistry.registerBlock(blocksLogs[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLogGenesis + set);
			
			GameRegistry.registerBlock(blocksSaplings[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockSaplingGenesis + set);
			
			GameRegistry.registerBlock(blocksLeaves[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLeavesGenesis + set);
		}
	}

	public static enum TreeBlockType {
		LOG,
		LEAVES,
		SAPLING;
	}
	
	public static class BlockAndMetadata
	{
		int blockID;
		int metadata;
		
		public BlockAndMetadata(int blockID, int metadata)
		{
			this.blockID = blockID;
			this.metadata = metadata;
		}

		public ItemStack getStack() {
			return new ItemStack(blockID, 1, Block.blocksList[blockID].damageDropped(metadata));
		}
	}
	
	public static BlockAndMetadata getBlockForType(TreeBlockType type, String name)
	{
		int index = woodTypes.indexOf(name);
		int set = index / setSize;
		int metadata = index % setSize;
		Block block = null;
		
		switch (type)
		{
		case LOG:
			block = blocksLogs[set];
			break;
		case LEAVES:
			block = blocksLeaves[set];
			break;
		case SAPLING:
			block = blocksSaplings[set];
			break;
		}
		
		if (block != null)
			return new BlockAndMetadata(block.blockID, metadata);
		
		return null;
	}
	
}
