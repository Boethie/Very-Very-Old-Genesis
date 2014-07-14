package genesis.common;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.block.trees.TreeBlocks;
import genesis.block.trees.TreeBlocks.TreeBlockType;
import genesis.block.trees.TreeBlocks.TreeType;
import genesis.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GenesisTabs
{
    public static final CreativeTabs tabGenesis = new CreativeTabs("tabGenesisBlock") 
    {
        @Override
        public ItemStack getIconItemStack() 
        {
            return new ItemStack(ModBlocks.moss);
        }

        @Override
        public Item getTabIconItem() 
        {
            return getIconItemStack().getItem();
        }
    };
    
    public static final CreativeTabs tabGenesisDecoration = new CreativeTabs("tabGenesisDecoration")
    {
        @Override
        public ItemStack getIconItemStack() 
        {
            return TreeBlocks.getBlockForType(TreeBlockType.SAPLING, TreeType.SIGILLARIA.getName()).getStack();
        }
        
        @Override
        public Item getTabIconItem()
        {
            return getIconItemStack().getItem();
        }
    };
    
    public static final CreativeTabs tabGenesisTools = new CreativeTabs("tabGenesisTools")
    {
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(ModItems.graniteTools.axeChipped);
        }
        
        @Override
        public Item getTabIconItem()
        {
            return getIconItemStack().getItem();
        }
    };
    
    public static final CreativeTabs tabGenesisFood = new CreativeTabs("tabGenesisFood")
    {
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(ModItems.cookedEryops);
        }
        
        @Override
        public Item getTabIconItem()
        {
            return getIconItemStack().getItem();
        }
    };
    
    public static final CreativeTabs tabGenesisMaterials = new CreativeTabs("tabGenesisMaterials")
    {
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(ModItems.biotite);
        }
        
        @Override
        public Item getTabIconItem()
        {
            return getIconItemStack().getItem();
        }
    };
}
