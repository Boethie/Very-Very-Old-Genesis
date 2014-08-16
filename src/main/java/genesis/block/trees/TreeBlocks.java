package genesis.block.trees;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.BlockAndMeta;
import genesis.block.plants.BlockGenesisFlowerPot;
import genesis.common.Genesis;
import genesis.item.itemblock.ItemBlockGenesisTree;
import genesis.lib.Names;
import genesis.world.gen.feature.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class TreeBlocks {
    public static Block[] blocksLogs;
    public static Block[] blocksSaplings;
    public static Block[] blocksLeaves;
    public static Block[] blocksRottenLogs;
    public static BlockBjuviaCone bjuviaCone;
    private static int numGroups;
    private static ArrayList<WorldGenTreeBase> treeGenerators;

    public static void init() {
        numGroups = TreeType.getNumGroups();

        treeGenerators = new ArrayList<WorldGenTreeBase>(numGroups);

        blocksLogs = new Block[numGroups];
        blocksSaplings = new Block[numGroups];
        blocksLeaves = new Block[numGroups];
        blocksRottenLogs = new Block[numGroups];
        bjuviaCone = (BlockBjuviaCone) new BlockBjuviaCone().setBlockName(Names.blockBjuviaCone);

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

        GameRegistry.registerBlock(bjuviaCone, Names.blockBjuviaCone);

        treeGenerators.add(null); // there are no world gens for the archeopteris, bjuvia, etc. yet, so it is set to null
        treeGenerators.add(new WorldGenTreeSigillaria(8, 3, true));
        treeGenerators.add(new WorldGenTreeLepidodendron(10, 5, true));
        treeGenerators.add(new WorldGenTreeCordaites(15, 5, true));
        treeGenerators.add(new WorldGenTreePsaronius(5, 4, true));
        treeGenerators.add(null);
        treeGenerators.add(null);
        treeGenerators.add(new WorldGenTreeAraucarioxylon(20, 7, true));
        Blocks.fire.setFireInfo(blocksLogs[0], 5, 5);
        Blocks.fire.setFireInfo(blocksRottenLogs[0], 10, 10);
        Blocks.fire.setFireInfo(blocksLeaves[0], 30, 60);
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

    public enum TreeType {
        ARCHAEOPTERIS("archaeopteris"),
        SIGILLARIA("sigillaria"),
        LEPIDODENDRON("lepidodendron"),
        CORDAITES("cordaites"),
        PSARONIUS("psaronius"),
        BJUVIA("bjuvia"),
        GLOSSOPTERIS("glossopteris"),
        ARAUCARIOXYLON("araucarioxylon");

        public static final int GROUP_SIZE = 4;

        private final String name;
        private int group;
        private int metadata;

        TreeType(String name) {
            this.name = name;
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
            return name;
        }

        public int getGroup() {
            return group;
        }

        private void setGroup(int group) {
            this.group = group;
        }

        public int getMetadata() {
            return metadata;
        }

        private void setMetadata(int metadata) {
            this.metadata = metadata;
        }
    }

    public static enum TreeBlockType {
        LOG,
        LEAVES,
        SAPLING,
        ROTTEN_LOG;
    }
}
