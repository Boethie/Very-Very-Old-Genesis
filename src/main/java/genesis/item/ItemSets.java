package genesis.item;

import genesis.block.trees.TreeBlocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSets
{

    public enum ToolType
    {
        AXE, HOE, KNIFE, PICKAXE, SPADE;
    }

    public enum ToolQuality
    {
        CRUDE("toolquality.crude"), CHIPPED("toolquality.chipped"), SHARPENED(
                "toolquality.sharpened"), POLISHED("toolquality.polished");

        public String localizeableString;

        ToolQuality(String str)
        {
            localizeableString = str;
        }

        public String toString()
        {
            return this.localizeableString;
        }
    }

    public static void registerAllRecipes()
    {
        ItemsToolSet.registerAllCraftingRecipes();
    }

    public static ItemStack getStackFromObject(Object obj)
    {
        if (obj instanceof Item)
            return new ItemStack((Item) obj);
        else if (obj instanceof Block)
            return new ItemStack((Block) obj);
        else if (obj instanceof ItemStack)
            return (ItemStack) obj;

        return null;
    }

    public static void registerRecipeWithDefault(Object craftingObj, IRecipeWithDefault iRecipeDef)
    {
        if (craftingObj == null)
            return;

        int recipeCount = iRecipeDef.getRecipeCount();
        ItemStack[][] recipes = new ItemStack[recipeCount][];
        int[] recipeWidths = new int[recipeCount];

        if (craftingObj instanceof Object[][])
        {
            Object[][] recipeObjs = (Object[][]) craftingObj;

            int recipe = 0;

            for (Object[] aObj : recipeObjs)
            {
                ItemStack[] items = new ItemStack[aObj.length];
                int item = 0;

                for (Object obj : aObj)
                {
                    if (obj instanceof Integer)
                        recipeWidths[recipe] = (Integer) obj;
                    else
                        items[item] = getStackFromObject(obj);

                    item++;
                }

                recipes[recipe] = items;
                recipe++;
            }
        }
        else
        {
            ItemStack craftStack = getStackFromObject(craftingObj);

            if (craftStack != null)
                for (int i = 0; i < recipeCount; i++)
                {
                    recipes[i] = iRecipeDef.getDefaultRecipe(i, craftStack);
                    recipeWidths[i] = iRecipeDef.getRecipeWidth(i);
                }
            else
                recipes = null;
        }

        if (recipes != null)
        {
            @SuppressWarnings("unchecked")
            List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

            int recipe = 0;

            for (ItemStack[] items : recipes)
            {
                int width = recipeWidths[recipe];
                ItemStack output = iRecipeDef.getOutput(recipe);

                ShapedRecipes shaped = new ShapedRecipes(width, (int) Math.ceil((double) items.length / width), items, output);
                recipeList.add(shaped);

                recipe++;
            }
        }
    }

    public static class ItemsToolSet implements IRecipeWithDefault
    {
        private static final ArrayList<ItemsToolSet> sets = new ArrayList<ItemsToolSet>();

        public static void registerAllCraftingRecipes()
        {
            for (ItemsToolSet set : sets)
                set.registerRecipes();
        }

        public ItemGenesisKnife knifeCrude;
        public ItemGenesisPickaxe pickaxeCrude;
        public ItemGenesisAxe axeCrude;
        public ItemGenesisSpade spadeCrude;
        public ItemGenesisHoe hoeCrude;

        public ItemGenesisKnife knifeChipped;
        public ItemGenesisPickaxe pickaxeChipped;
        public ItemGenesisAxe axeChipped;
        public ItemGenesisSpade spadeChipped;
        public ItemGenesisHoe hoeChipped;

        public ItemGenesisKnife knifeSharpened;
        public ItemGenesisPickaxe pickaxeSharpened;
        public ItemGenesisAxe axeSharpened;
        public ItemGenesisSpade spadeSharpened;
        public ItemGenesisHoe hoeSharpened;

        public ItemGenesisKnife knifePolished;
        public ItemGenesisPickaxe pickaxePolished;
        public ItemGenesisAxe axePolished;
        public ItemGenesisSpade spadePolished;
        public ItemGenesisHoe hoePolished;

        public Object craftingObj;
        public Object craftingHandleObj;

        public ItemsToolSet(Item.ToolMaterial toolMaterial1, Item.ToolMaterial toolMaterial2, Item.ToolMaterial toolMaterial3, Item.ToolMaterial toolMaterial4, String materialName, Object crafting, Object craftingHandle)
        {
            this(toolMaterial1, toolMaterial2, toolMaterial3, toolMaterial4, materialName, crafting, craftingHandle, true, true, true, true, true);
        }

        public ItemsToolSet(Item.ToolMaterial toolMaterial1, Item.ToolMaterial toolMaterial2, Item.ToolMaterial toolMaterial3, Item.ToolMaterial toolMaterial4, String materialName, Object crafting, Object craftingHandle, boolean enableKnife, boolean enablePickaxe, boolean enableAxe, boolean enableSpade, boolean enableHoe)
        {

            if (enableKnife)
            {
                knifeCrude = new ItemGenesisKnife(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                knifeChipped = new ItemGenesisKnife(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                knifeSharpened = new ItemGenesisKnife(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.SHARPENED);
                knifePolished = new ItemGenesisKnife(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.POLISHED);
            }

            if (enablePickaxe)
            {
                pickaxeCrude = new ItemGenesisPickaxe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                pickaxeChipped = new ItemGenesisPickaxe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                pickaxeSharpened = new ItemGenesisPickaxe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.SHARPENED);
                pickaxePolished = new ItemGenesisPickaxe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.POLISHED);
            }
            if (enableAxe)
            {
                axeCrude = new ItemGenesisAxe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                axeChipped = new ItemGenesisAxe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                axeSharpened = new ItemGenesisAxe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.SHARPENED);
                axePolished = new ItemGenesisAxe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.POLISHED);
            }
            if (enableSpade)
            {
                spadeCrude = new ItemGenesisSpade(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                spadeChipped = new ItemGenesisSpade(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                spadeSharpened = new ItemGenesisSpade(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.SHARPENED);
                spadePolished = new ItemGenesisSpade(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.POLISHED);
            }
            if (enableHoe)
            {
                hoeCrude = new ItemGenesisHoe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                hoeChipped = new ItemGenesisHoe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                hoeSharpened = new ItemGenesisHoe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.SHARPENED);
                hoePolished = new ItemGenesisHoe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.POLISHED);

            }
            setupHierarchy();

            craftingObj = crafting;
            craftingHandleObj = craftingHandle;

            sets.add(this);
        }

        private void setupHierarchy()
        {

            axeCrude.setNextTier(axeChipped);
            axeChipped.setNextTier(axeSharpened);
            axeSharpened.setNextTier(axePolished);
            axePolished.setNextTier(null);

            hoeCrude.setNextTier(hoeChipped);
            hoeChipped.setNextTier(hoeSharpened);
            hoeSharpened.setNextTier(hoePolished);
            hoePolished.setNextTier(null);

            knifeCrude.setNextTier(knifeChipped);
            knifeChipped.setNextTier(knifeSharpened);
            knifeSharpened.setNextTier(knifePolished);
            knifePolished.setNextTier(null);

            pickaxeCrude.setNextTier(pickaxeChipped);
            pickaxeChipped.setNextTier(pickaxeSharpened);
            pickaxeChipped.setNextTier(pickaxePolished);
            pickaxePolished.setNextTier(null);

            spadeCrude.setNextTier(spadeChipped);
            spadeChipped.setNextTier(spadeSharpened);
            spadeSharpened.setNextTier(spadePolished);
            spadePolished.setNextTier(null);
        }

        public void registerRecipes()
        {
        	for(Block block:TreeBlocks.blocksLogs){
        		craftingHandleObj=new ItemStack(block,1,OreDictionary.WILDCARD_VALUE);
        		registerRecipeWithDefault(craftingObj, this);
        	}
        }

        @Override
        public int getRecipeCount()
        {
            return 6;
        }

        @Override
        public int getRecipeWidth(int recipe)
        {
            switch (recipe)
            {
                case 0: // Sword
                case 1: // knife
                case 4: // Spade
                    return 1;
                case 3: // Axe
                case 5: // Hoe
                    return 2;
                default:
                    return 3;
            }
        }

        @Override
        public ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack)
        {
            ItemStack handle = getStackFromObject(craftingHandleObj);

            switch (recipe)
            {
                case 0: // Sword
                    return new ItemStack[]
                    {
                    craftStack, craftStack, handle
                    };
                case 1: // knife
                    return new ItemStack[]
                    {
                    craftStack, handle
                    };
                case 2: // Pickaxe
                    return new ItemStack[]
                    {
                    craftStack, craftStack, craftStack, null, handle, null, null, handle, null
                    };
                case 3: // Axe
                    return new ItemStack[]
                    {
                    craftStack, craftStack, craftStack, handle, null, handle
                    };
                case 4: // Spade
                    return new ItemStack[]
                    {
                    craftStack, handle, handle
                    };
                case 5: // Hoe
                    return new ItemStack[]
                    {
                    craftStack, craftStack, null, handle, null, handle
                    };
                default:
                    return null;
            }
        }

        @Override
        public ItemStack getOutput(int recipe)
        {
            Item item = null;

            switch (recipe)
            {
                case 1:
                    item = knifeCrude;
                    break;
                case 2:
                    item = pickaxeCrude;
                    break;
                case 3:
                    item = axeCrude;
                    break;
                case 4:
                    item = spadeCrude;
                    break;
                case 5:
                    item = hoeCrude;
                    break;
            }

            if (item == null)
            {
                return null;
            }
            else
            {
                return new ItemStack(item);
            }
        }
    }
}
