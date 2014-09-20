package genesis.block.trees;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.BlockAndMeta;
import genesis.block.plants.BlockGenesisFlowerPot;
import genesis.item.Recipes;
import genesis.item.itemblock.IItemBlockWithSubNames;
import genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.lib.Names;
import genesis.lib.PlantMetadata;
import genesis.world.gen.feature.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class GenesisTreeBlocks {
    public static Block bjuvia_cone;
    public static Block[] logs;
    public static Block[] saplings;
    public static Block[] leaves;
    public static Block[] rotten_logs;
    private static int numGroups;
    private static ArrayList<WorldGenTreeBase> treeGenerators;

    public static void initiate() {
        bjuvia_cone = new BlockBjuviaCone().setBlockName(Names.blockBjuviaCone).setBlockTextureName("bjuvia_cone");

        numGroups = TreeType.getNumGroups();

        treeGenerators = new ArrayList<WorldGenTreeBase>(numGroups);

        logs = new Block[numGroups];
        saplings = new Block[numGroups];
        leaves = new Block[numGroups];
        rotten_logs = new Block[numGroups];

        for (int group = 0; group < numGroups; group++) {
            logs[group] = new BlockGenesisLog(group).setBlockName(Names.blockLogGenesis);
            saplings[group] = new BlockGenesisSapling(group).setBlockName(Names.blockSaplingGenesis);
            leaves[group] = new BlockGenesisLeaves(group).setBlockName(Names.blockLeavesGenesis);
            rotten_logs[group] = new BlockRottenLog(group).setBlockName(Names.blockRottenLogGenesis);
        }
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(bjuvia_cone, Names.Registry.blockBjuviaCone);

        registerBlocks(logs, Names.Registry.blockLogGenesis, "logWood");
        registerBlocks(saplings, Names.Registry.blockSaplingGenesis, "treeSapling");
        registerBlocks(leaves, Names.Registry.blockLeavesGenesis, "treeLeaves");
        registerBlocks(rotten_logs, Names.Registry.blockRottenLogGenesis);

        for (TreeType type : TreeType.values()) {
            BlockGenesisFlowerPot.tryRegisterPlant(new ItemStack(saplings[type.getGroup()], 1, type.getMetadata()));
        }

        treeGenerators.add(null); // there are no world gens for the archeopteris, bjuvia, etc. yet, so it is set to null
        treeGenerators.add(new WorldGenTreeSigillaria(8, 3, true));
        treeGenerators.add(new WorldGenTreeLepidodendron(10, 5, true));
        treeGenerators.add(new WorldGenTreeCordaites(15, 5, true));
        treeGenerators.add(new WorldGenTreePsaronius(5, 4, true));
        treeGenerators.add(null);
        treeGenerators.add(null);
        treeGenerators.add(new WorldGenTreeAraucarioxylon(20, 7, true));
        for (int group = 0; group < numGroups; group++) {
            Blocks.fire.setFireInfo(logs[group], 5, 5);
            Blocks.fire.setFireInfo(rotten_logs[group], 10, 10);
            Blocks.fire.setFireInfo(leaves[group], 30, 60);
        }
    }

    private static void registerBlocks(Block[] blocks, String name) {
        registerBlocks(blocks, name, null);
    }

    private static void registerBlocks(Block[] blocks, String name, String oreDictName) {
        for (int group = 0; group < numGroups; group++) {
            GameRegistry.registerBlock(blocks[group], ItemBlockGenesisTree.class, name + group);

            if (!StringUtils.isNullOrEmpty(oreDictName)) {
                OreDictionary.registerOre(oreDictName, new ItemStack(blocks[group], 1, OreDictionary.WILDCARD_VALUE));
            }
        }
    }

    public static BlockAndMeta getBlockForType(TreeBlockType type, String name) {
        TreeType treeType = TreeType.valueOf(name.toUpperCase());
        int group = treeType.getGroup();
        Block block;

        switch (type) {
        case LOG:
            block = logs[group];
            break;
        case LEAVES:
            block = leaves[group];
            break;
        case SAPLING:
            block = saplings[group];
            break;
        case ROTTEN_LOG:
            block = rotten_logs[group];
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

        public static final int GROUP_SIZE = 4;

        private int group;
        private int metadata;

        TreeType() {
            setGroup(ordinal() / GROUP_SIZE);
            setMetadata(ordinal() % GROUP_SIZE);
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

        public String getName() {
            return PlantMetadata.treeTypes.get(ordinal());
        }

        public int getGroup() {
            return group;
        }

        private void setGroup(int treeGroup) {
            group = treeGroup;
        }

        public int getMetadata() {
            return metadata;
        }

        private void setMetadata(int treeMetadata) {
            metadata = treeMetadata;
        }

        public boolean equals(TreeType... types) {
            for (TreeType type : types) {
                if (equals(type)) {
                    return true;
                }
            }
            return false;
        }

        public static TreeType valueOf(IItemBlockWithSubNames block, int metadata) {
            return valueOf(block.getSubName(metadata).toUpperCase());
        }
    }

    public static enum TreeBlockType {
        LOG,
        LEAVES,
        SAPLING,
        ROTTEN_LOG
    }
}
