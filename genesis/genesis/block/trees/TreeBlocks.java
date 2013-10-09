package genesis.genesis.block.trees;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import genesis.genesis.world.WorldGenTreeBase;
import genesis.genesis.world.WorldGenTreeCordaites;
import genesis.genesis.world.WorldGenTreeLepidodendron;
import genesis.genesis.world.WorldGenTreeSigillaria;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

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
	private static ArrayList<WorldGenTreeBase> treeGenerators = new ArrayList(woodTypeCount);
	
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
	
	public static void registerBlocks()
	{
		for (int set = 0; set < IDs.TREE_BLOCK_COUNT; set++)
		{
			GameRegistry.registerBlock(blocksLogs[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLogGenesis + set);
			
			GameRegistry.registerBlock(blocksSaplings[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockSaplingGenesis + set);
			
			GameRegistry.registerBlock(blocksLeaves[set], ItemBlockGenesisTree.class, Genesis.MOD_ID + "." + Names.blockLeavesGenesis + set);
		}
		
		treeGenerators.add(new WorldGenTreeSigillaria(7, 3, true));
		treeGenerators.add(new WorldGenTreeLepidodendron(9, 5, true));
		treeGenerators.add(new WorldGenTreeCordaites(15, 5, true));
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
	
	public static int getLogMetadataForDirection(int logMetadata, ForgeDirection direction)
	{
		int directionBits = 0;
		
		switch (direction)
		{
		case NORTH:
		case SOUTH:
			directionBits = 1;
			break;
		case EAST:
		case WEST:
			directionBits = 2;
			break;
		case UNKNOWN:
			directionBits = 3;
			break;
		default:
			break;
		}
		
		return (logMetadata & 3) | directionBits;
	}
	
	public static WorldGenTreeBase getTreeGenerator(String treeName)
	{
		return treeGenerators.get(woodTypes.indexOf(treeName));
	}
	
}
