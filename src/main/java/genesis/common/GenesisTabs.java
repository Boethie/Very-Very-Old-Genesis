package genesis.common;

import genesis.block.GenesisModBlocks;
import genesis.block.trees.GenesisTreeBlocks;
import genesis.block.trees.GenesisTreeBlocks.TreeBlockType;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.item.GenesisModItems;
import genesis.lib.Names;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GenesisTabs {
    public static final CreativeTabs tabGenesis = new CreativeTabs(Names.tabBlock) {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(GenesisModBlocks.moss);
        }

        @Override
        public Item getTabIconItem() {
            return getIconItemStack().getItem();
        }
    };

    public static final CreativeTabs tabGenesisDecoration = new CreativeTabs(Names.tabDecorations) {
        @Override
        public ItemStack getIconItemStack() {
            return GenesisTreeBlocks.getBlockForType(TreeBlockType.SAPLING, TreeType.SIGILLARIA.getName()).getStack();
        }

        @Override
        public Item getTabIconItem() {
            return getIconItemStack().getItem();
        }
    };

    public static final CreativeTabs tabGenesisTools = new CreativeTabs(Names.tabTools) {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(GenesisModItems.granite_tools.chipped_axe);
        }

        @Override
        public Item getTabIconItem() {
            return getIconItemStack().getItem();
        }
    };

    public static final CreativeTabs tabGenesisFood = new CreativeTabs(Names.tabFood) {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(GenesisModItems.cooked_eryops);
        }

        @Override
        public Item getTabIconItem() {
            return getIconItemStack().getItem();
        }
    };

    public static final CreativeTabs tabGenesisMaterials = new CreativeTabs(Names.tabMaterials) {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(GenesisModItems.manganese);
        }

        @Override
        public Item getTabIconItem() {
            return getIconItemStack().getItem();
        }
    };
}
