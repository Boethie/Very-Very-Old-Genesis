package genesis.block.trees;

import genesis.block.plants.BlockGenesisFlowerPot;
import genesis.helper.LogHelper;
import genesis.lib.Names;
import genesis.world.gen.feature.WorldGenTreeAraucarioxylon;
import genesis.world.gen.feature.WorldGenTreeBase;
import genesis.world.gen.feature.WorldGenTreeCordaites;
import genesis.world.gen.feature.WorldGenTreeLepidodendron;
import genesis.world.gen.feature.WorldGenTreePsaronius;
import genesis.world.gen.feature.WorldGenTreeSigillaria;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenesisTreeBlocks {
    public static Block bjuvia_cone;
    public static Block[] logs;
    public static Block[] saplings;
    public static Block[] leaves;
    public static Block[] rottenLogs;
    private static int numGroups;
    //private static ArrayList<WorldGenTreeBase> treeGenerators;

    private static final HashMap<TreeType, WorldGenTreeBase> treeGenerators = new HashMap();
    
    public static void init() {
        bjuvia_cone = new BlockBjuviaCone().setBlockName(Names.blockBjuviaCone).setBlockTextureName("bjuvia_cone").setCreativeTab(null);

        numGroups = TreeType.values().length;

        logs = new Block[numGroups];
        saplings = new Block[numGroups];
        leaves = new Block[numGroups];
        rottenLogs = new Block[numGroups];

        for (int group = 0; group < numGroups; group++) {
            logs[group] = new BlockGenesisLog(group).setBlockName(Names.blockLogGenesis);
            saplings[group] = new BlockGenesisSapling(group).setBlockName(Names.blockSaplingGenesis);
            leaves[group] = new BlockGenesisLeaves(group).setBlockName(Names.blockLeavesGenesis);
            rottenLogs[group] = new BlockRottenLog(group).setBlockName(Names.blockRottenLogGenesis);
        }
    }

    public static void register() {
        GameRegistry.registerBlock(bjuvia_cone, Names.Registry.blockBjuviaCone);

        /*registerBlocks(logs, Names.Registry.blockLogGenesis, "logWood");
        registerBlocks(saplings, Names.Registry.blockSaplingGenesis, "treeSapling");
        registerBlocks(leaves, Names.Registry.blockLeavesGenesis, "treeLeaves");
        registerBlocks(rottenLogs, Names.Registry.blockRottenLogGenesis);*/
        
        for(TreeType tree : TreeType.values()){
        	int i = tree.ordinal();
        	String s = tree.name().toLowerCase();
        	GameRegistry.registerBlock(logs[i], Names.Registry.blockLogGenesis + "_" + s);
        	GameRegistry.registerBlock(saplings[i], Names.Registry.blockSaplingGenesis + "_" + s);
        	GameRegistry.registerBlock(leaves[i], Names.Registry.blockLeavesGenesis + "_" + s);
        	GameRegistry.registerBlock(rottenLogs[i], Names.Registry.blockRottenLogGenesis + "_" + s);
        }
        
        /*for(int i = 0; i < logs.length; i++){
        	TreeType type = TreeType.
        	GameRegistry.registerBlock(logs[i], "genesisLog"+i);
        }
        
        for(int i = 0; i < saplings.length; i++){
        	GameRegistry.registerBlock(saplings[i], "genesisSappling"+i);
        }*/

        for (TreeType type : TreeType.values()) {
            BlockGenesisFlowerPot.tryRegisterPlant(new ItemStack(saplings[type.ordinal()], 1, 0));
        }

        treeGenerators.put(TreeType.SIGILLARIA, new WorldGenTreeSigillaria(8, 3, true));
        treeGenerators.put(TreeType.LEPIDODENDRON, new WorldGenTreeLepidodendron(10, 5, true));
        treeGenerators.put(TreeType.CORDAITES, new WorldGenTreeCordaites(15, 5, true));
        treeGenerators.put(TreeType.PSARONIUS, new WorldGenTreePsaronius(5, 4, true));
        treeGenerators.put(TreeType.ARAUCARIOXYLON, new WorldGenTreeAraucarioxylon(20, 7, true));
        
        /*treeGenerators.add(null); // there are no world gens for the archeopteris, bjuvia, etc. yet, so it is set to null
        treeGenerators.add(new WorldGenTreeSigillaria(8, 3, true));
        treeGenerators.add(new WorldGenTreeLepidodendron(10, 5, true));
        treeGenerators.add(new WorldGenTreeCordaites(15, 5, true));
        treeGenerators.add(new WorldGenTreePsaronius(5, 4, true));
        treeGenerators.add(null);
        treeGenerators.add(null);
        treeGenerators.add(new WorldGenTreeAraucarioxylon(20, 7, true));*/
        
        for (int group = 0; group < numGroups; group++) {
            Blocks.fire.setFireInfo(logs[group], 5, 5);
            Blocks.fire.setFireInfo(rottenLogs[group], 10, 10);
            Blocks.fire.setFireInfo(leaves[group], 30, 60);
        }
    }

    private static void registerBlocks(Block[] blocks, String name) {
        registerBlocks(blocks, name, null);
    }

    private static void registerBlocks(Block[] blocks, String name, String oreDictName) {
    	for(TreeType tree : TreeType.values()){
    		Block block = blocks[tree.ordinal()];
        	GameRegistry.registerBlock(block, name + "_" + tree.name().toLowerCase());
        	
        	if(oreDictName != null && !oreDictName.isEmpty()){
        		OreDictionary.registerOre(oreDictName, block);
        	}
        }
    }

    public static ItemStack getItemStackForType(TreeBlockType type, TreeType tree) {
    	Block block = getBlockForType(type,tree);
    	return block == null ? null : new ItemStack(block);
    }
    
    public static Block getBlockForType(TreeBlockType type, TreeType tree){
    	switch (type) {
        case LOG: return logs[tree.ordinal()];
        case LEAVES: return leaves[tree.ordinal()];
        case SAPLING: return saplings[tree.ordinal()];
        case ROTTEN_LOG: return rottenLogs[tree.ordinal()];
        default: return null;
        }
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
        return getTreeGenerator(TreeType.valueOf(name.toUpperCase()));
    }
    
    public static WorldGenTreeBase getTreeGenerator(int id) {
        return getTreeGenerator(TreeType.get(id));
    }
    
    public static WorldGenTreeBase getTreeGenerator(TreeType type){
    	return treeGenerators.get(type);
    }

    public enum TreeType {
        ARCHAEOPTERIS,
        SIGILLARIA,
        LEPIDODENDRON,
        CORDAITES,
        PSARONIUS,
        BJUVIA,
        GLOSSOPTERIS,
        ARAUCARIOXYLON,
        VOLTZIA,
        DRYOPHYLLUM,
        CREDNERIA;

        private TreeType(){}

        /*public String getName() {
            return Names.Plants.TREE_TYPES.get(ordinal());
        }*/
        
        public static TreeType get(int id){
        	try{
        		return values()[id];
        	}catch(ArrayIndexOutOfBoundsException e){
        		LogHelper.log(Level.FATAL, "Trying to get tree type for invalid id: " + id);
        		return null;
        	}
        }

        public boolean equals(TreeType... types) {
            for (TreeType type : types) {
                if (equals(type)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static enum TreeBlockType {
        LOG,
        LEAVES,
        SAPLING,
        ROTTEN_LOG
    }
}
