package genesis.block.trees;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.BlockAndMeta;
import genesis.block.BlockGenesisStairs;
import genesis.block.plants.BlockGenesisFlowerPot;
import genesis.common.Genesis;
import genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.lib.Names;
import genesis.world.gen.feature.WorldGenTreeAraucarioxylon;
import genesis.world.gen.feature.WorldGenTreeBase;
import genesis.world.gen.feature.WorldGenTreeCordaites;
import genesis.world.gen.feature.WorldGenTreeLepidodendron;
import genesis.world.gen.feature.WorldGenTreePsaronius;
import genesis.world.gen.feature.WorldGenTreeSigillaria;

public class TreeBlocks {
	public enum TreeType {
		SIGILLARIA("sigillaria"),
		LEPIDODENDRON("lepidodendron"),
		CORDAITES("cordaites"),
		ARAUCARIOXYLON("araucarioxylon"),
		PSARONIUS("psaronius"),
		ARCHEOPTERIS("archeopteris"),
		BJUVIA("bjuvia"),
		GLOSSOPTERIS("glossopteris");
		
		public static final int GROUP_SIZE = 4;
		
		private final String name;
		private int group;
		private int metadata;
		
		TreeType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public int getGroup() {
			return group;
		}
		
		public int getMetadata() {
			return metadata;
		}
		
		private void setGroup(int group) {
			this.group = group;
		}
		
		private void setMetadata(int metadata) {
			this.metadata = metadata;
		}
		
		protected static void init() {
			for (TreeType type : values()) {
				type.setGroup(type.ordinal() / GROUP_SIZE);
				type.setMetadata(type.ordinal() % GROUP_SIZE);
			}
		}
		
		public static int getNumGroups() {
			init();
			return values()[values().length - 1].group + 1;
		}
	}

	private static int numGroups;
	
	private static ArrayList<WorldGenTreeBase> treeGenerators;
	
	public static Block[] blocksLogs;
	public static Block[] blocksSaplings;
	public static Block[] blocksLeaves;
	public static Block[] blocksRottenLogs;
	
	public static void init() {
		numGroups = TreeType.getNumGroups();
		
		treeGenerators = new ArrayList<WorldGenTreeBase>(numGroups);
		
		blocksLogs = new Block[numGroups];
		blocksSaplings = new Block[numGroups];
		blocksLeaves = new Block[numGroups];
		blocksRottenLogs = new Block[numGroups];
		
		for (int group = 0; group < numGroups; group++) {			
			blocksLogs[group] = new BlockGenesisLog(group)
					.setBlockName(Names.blockLogGenesis);
			
			blocksSaplings[group] = new BlockGenesisSapling(group)
					.setBlockName(Names.blockSaplingGenesis);
			
			blocksLeaves[group] = new BlockGenesisLeaves(group)
					.setBlockName(Names.blockLeavesGenesis);
			
			blocksRottenLogs[group] = new BlockRottenLog(group)
				.setBlockName(Names.blockRottenLogGenesis);
		}
	}
	
	public static void registerBlocks() {
		for (int group = 0; group < numGroups; group++) {
			Genesis.proxy.registerBlock(blocksLogs[group], Names.blockLogGenesis + group, ItemBlockGenesisTree.class);
			Genesis.proxy.registerBlock(blocksSaplings[group], Names.blockSaplingGenesis + group, ItemBlockGenesisTree.class);
			Genesis.proxy.registerBlock(blocksLeaves[group], Names.blockLeavesGenesis + group, ItemBlockGenesisTree.class);
			Genesis.proxy.registerBlock(blocksRottenLogs[group], Names.blockRottenLogGenesis + group, ItemBlockGenesisTree.class);
			
			GameRegistry.addSmelting(blocksLogs[group], new ItemStack(Items.coal, 1, 1), 0.15F);
			
			OreDictionary.registerOre("logWood", new ItemStack(blocksLogs[group], 1, OreDictionary.WILDCARD_VALUE));
		}
		
		for (TreeType type : TreeType.values()) {			
			BlockGenesisFlowerPot.tryRegisterPlant(new ItemStack(blocksSaplings[type.getGroup()], 1, type.getMetadata()));
		}
		
		treeGenerators.add(new WorldGenTreeSigillaria(8, 3, true));
		treeGenerators.add(new WorldGenTreeLepidodendron(10, 5, true));
		treeGenerators.add(new WorldGenTreeCordaites(15, 5, true));
		treeGenerators.add(new WorldGenTreeAraucarioxylon(20, 7, true));
		treeGenerators.add(new WorldGenTreePsaronius(5, 4, true));
	}

	public static enum TreeBlockType {
		LOG,
		LEAVES,
		SAPLING,
		ROTTEN_LOG;
	}
	
	public static BlockAndMeta getBlockForType(TreeBlockType type, String name) {
		TreeType treeType = TreeType.valueOf(name.toUpperCase());
		int group = treeType.getGroup();
		Block block;
		
		switch (type) {
		case LOG:
			block = blocksLogs[group];
			break;
		case LEAVES:
			block = blocksLeaves[group];
			break;
		case SAPLING:
			block = blocksSaplings[group];
			break;
		case ROTTEN_LOG:
			block = blocksRottenLogs[group];
			break;
		default:
			return null;
		}
		
		return new BlockAndMeta(block, treeType.getMetadata());
	}
	
	public static int getLogMetadataForDirection(int metadata, ForgeDirection direction) {
		int directionBits;
		
		switch (direction) {
		case NORTH:
		case SOUTH:
			directionBits = 4;
			break;
		case EAST:
		case WEST:
			directionBits = 8;
			break;
		case UNKNOWN:
			directionBits = 12;
			break;
		default:
			directionBits = 0;
			break;
		}
		
		return (metadata & 3) | directionBits;
	}
	
	public static WorldGenTreeBase getTreeGenerator(String name) {
		return treeGenerators.get(TreeType.valueOf(name.toUpperCase()).ordinal());
	}
}
