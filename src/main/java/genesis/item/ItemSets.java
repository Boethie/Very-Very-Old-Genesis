package genesis.item;

import genesis.block.gui.PolissoirRecipes;
import genesis.block.trees.TreeBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemSets {

    public static void registerAllRecipes() {
        ItemsToolSet.registerAllCraftingRecipes();
    }

    public static ItemStack getStackFromObject(Object obj) {
        if (obj instanceof Item)
            return new ItemStack((Item) obj);
        else if (obj instanceof Block)
            return new ItemStack((Block) obj);
        else if (obj instanceof ItemStack)
            return (ItemStack) obj;

        return null;
    }

    public static void registerRecipeWithDefault(Object craftingObj, IRecipeWithDefault iRecipeDef) {
        if (craftingObj == null)
            return;

        int recipeCount = iRecipeDef.getRecipeCount();
        ItemStack[][] recipes = new ItemStack[recipeCount][];
        int[] recipeWidths = new int[recipeCount];

        if (craftingObj instanceof Object[][]) {
            Object[][] recipeObjs = (Object[][]) craftingObj;

            int recipe = 0;

            for (Object[] aObj : recipeObjs) {
                ItemStack[] items = new ItemStack[aObj.length];
                int item = 0;

                for (Object obj : aObj) {
                    if (obj instanceof Integer)
                        recipeWidths[recipe] = (Integer) obj;
                    else
                        items[item] = getStackFromObject(obj);

                    item++;
                }

                recipes[recipe] = items;
                recipe++;
            }
        } else {
            ItemStack craftStack = getStackFromObject(craftingObj);

            if (craftStack != null)
                for (int i = 0; i < recipeCount; i++) {
                    recipes[i] = iRecipeDef.getDefaultRecipe(i, craftStack);
                    recipeWidths[i] = iRecipeDef.getRecipeWidth(i);
                }
            else
                recipes = null;
        }

        if (recipes != null) {
            @SuppressWarnings("unchecked")
            List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

            int recipe = 0;

            for (ItemStack[] items : recipes) {
                int width = recipeWidths[recipe];
                ItemStack output = iRecipeDef.getOutput(recipe);

                ShapedRecipes shaped = new ShapedRecipes(width, (int) Math.ceil((double) items.length / width), items, output);
                recipeList.add(shaped);

                recipe++;
            }
        }
    }

    public enum ToolType {
        AXE, HOE, KNIFE, PICKAXE, SPADE;
    }

    public enum ToolQuality {
        CRUDE("toolquality.crude"),
        CHIPPED("toolquality.chipped"),
        POLISHED("toolquality.polished"),
        SHARPENED("toolquality.sharpened");

        public String localizeableString;

        ToolQuality(String str) {
            localizeableString = str;
        }

        public String toString() {
            return this.localizeableString;
        }
    }

    public static class ItemsToolSet implements IRecipeWithDefault {
        private static final ArrayList<ItemsToolSet> sets = new ArrayList<ItemsToolSet>();
        public ItemGenesisKnife knifeCrude, knifeChipped, knifePolished, knifeSharpened;
        public ItemGenesisPickaxe pickaxeCrude, pickaxeChipped, pickaxePolished, pickaxeSharpened;
        public ItemGenesisAxe axeCrude, axeChipped, axePolished, axeSharpened;
        public ItemGenesisSpade spadeCrude, spadeChipped, spadePolished, spadeSharpened;
        public ItemGenesisHoe hoeCrude, hoeChipped, hoePolished, hoeSharpened;
        public Object craftingObj, craftingHandleObj;

        public ItemsToolSet(String materialName, Object crafting, Object craftingHandle, Object[] material1, Object[] material2, Object[] material3, Object[] material4) {
            this(materialName, crafting, craftingHandle, material1, material2, material3, material4, true, true, true, true, true);
        }

        public ItemsToolSet(String materialName, Object crafting, Object craftingHandle, Object[] material1, Object[] material2, Object[] material3, Object[] material4, boolean enableKnife, boolean enablePickaxe, boolean enableAxe, boolean enableSpade, boolean enableHoe) {
            String material = materialName.toUpperCase() + "_";
            Item.ToolMaterial toolMaterial1 = EnumHelper.addToolMaterial(material + "CRUDE", (Integer) material1[0], (Integer) material1[1], (Float) material1[2], (Float) material1[3], (Integer) material1[4]);
            Item.ToolMaterial toolMaterial2 = EnumHelper.addToolMaterial(material + "CHIPPED", (Integer) material2[0], (Integer) material2[1], (Float) material2[2], (Float) material2[3], (Integer) material2[4]);
            Item.ToolMaterial toolMaterial3 = EnumHelper.addToolMaterial(material + "POLISHED", (Integer) material3[0], (Integer) material3[1], (Float) material3[2], (Float) material3[3], (Integer) material3[4]);
            Item.ToolMaterial toolMaterial4 = EnumHelper.addToolMaterial(material + "SHARPENED", (Integer) material4[0], (Integer) material4[1], (Float) material4[2], (Float) material4[3], (Integer) material4[4]);

            String[] strings = materialName.split("_");
            String unlocalizedName = "";
            if (strings.length > 0) {
                for (int i = 0; i < strings.length; i++) {
                    if (i > 0) strings[i] = WordUtils.capitalize(strings[i]);
                    unlocalizedName += strings[i];
                }
            }

            if (enableKnife) {
                knifeCrude = new ItemGenesisKnife(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                knifeChipped = new ItemGenesisKnife(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                knifePolished = new ItemGenesisKnife(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.POLISHED);
                knifeSharpened = new ItemGenesisKnife(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.SHARPENED);

                if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                    knifeCrude.setUnlocalizedName(unlocalizedName);
                    knifeChipped.setUnlocalizedName(unlocalizedName);
                    knifePolished.setUnlocalizedName(unlocalizedName);
                    knifeSharpened.setUnlocalizedName(unlocalizedName);
                }
            }

            if (enablePickaxe) {
                pickaxeCrude = new ItemGenesisPickaxe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                pickaxeChipped = new ItemGenesisPickaxe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                pickaxePolished = new ItemGenesisPickaxe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.POLISHED);
                pickaxeSharpened = new ItemGenesisPickaxe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.SHARPENED);

                if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                    pickaxeCrude.setUnlocalizedName(unlocalizedName);
                    pickaxeChipped.setUnlocalizedName(unlocalizedName);
                    pickaxePolished.setUnlocalizedName(unlocalizedName);
                    pickaxeSharpened.setUnlocalizedName(unlocalizedName);
                }
            }

            if (enableAxe) {
                axeCrude = new ItemGenesisAxe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                axeChipped = new ItemGenesisAxe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                axePolished = new ItemGenesisAxe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.POLISHED);
                axeSharpened = new ItemGenesisAxe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.SHARPENED);

                if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                    axeCrude.setUnlocalizedName(unlocalizedName);
                    axeChipped.setUnlocalizedName(unlocalizedName);
                    axePolished.setUnlocalizedName(unlocalizedName);
                    axeSharpened.setUnlocalizedName(unlocalizedName);
                }
            }

            if (enableSpade) {
                spadeCrude = new ItemGenesisSpade(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                spadeChipped = new ItemGenesisSpade(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                spadePolished = new ItemGenesisSpade(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.POLISHED);
                spadeSharpened = new ItemGenesisSpade(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.SHARPENED);

                if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                    spadeCrude.setUnlocalizedName(unlocalizedName);
                    spadeChipped.setUnlocalizedName(unlocalizedName);
                    spadePolished.setUnlocalizedName(unlocalizedName);
                    spadeSharpened.setUnlocalizedName(unlocalizedName);
                }
            }

            if (enableHoe) {
                hoeCrude = new ItemGenesisHoe(toolMaterial1, materialName + "_crude_chipped", ToolQuality.CRUDE);
                hoeChipped = new ItemGenesisHoe(toolMaterial2, materialName + "_crude_chipped", ToolQuality.CHIPPED);
                hoePolished = new ItemGenesisHoe(toolMaterial3, materialName + "_sharpened_polished", ToolQuality.POLISHED);
                hoeSharpened = new ItemGenesisHoe(toolMaterial4, materialName + "_sharpened_polished", ToolQuality.SHARPENED);

                if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                    hoeCrude.setUnlocalizedName(unlocalizedName);
                    hoeChipped.setUnlocalizedName(unlocalizedName);
                    hoePolished.setUnlocalizedName(unlocalizedName);
                    hoeSharpened.setUnlocalizedName(unlocalizedName);
                }
            }

            setupHierarchy();

            craftingObj = crafting;
            craftingHandleObj = craftingHandle;

            sets.add(this);
        }

        public static void registerAllCraftingRecipes() {
            for (ItemsToolSet set : sets)
                set.registerRecipes();
        }

        private void setupHierarchy() {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(axeCrude), new ItemStack(axeChipped));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(axeChipped), new ItemStack(axePolished));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(axePolished), new ItemStack(axeSharpened));

            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(hoeCrude), new ItemStack(hoeChipped));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(hoeChipped), new ItemStack(hoePolished));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(hoePolished), new ItemStack(hoeSharpened));

            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(knifeCrude), new ItemStack(knifeChipped));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(knifeChipped), new ItemStack(knifePolished));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(knifePolished), new ItemStack(knifeSharpened));

            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(pickaxeCrude), new ItemStack(pickaxeChipped));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(pickaxeChipped), new ItemStack(pickaxePolished));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(pickaxePolished), new ItemStack(pickaxeSharpened));

            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(spadeCrude), new ItemStack(spadeChipped));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(spadeChipped), new ItemStack(spadePolished));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(spadePolished), new ItemStack(spadeSharpened));
        }

        public void registerRecipes() {
            for (Block block : TreeBlocks.blocksLogs) {
                craftingHandleObj = new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE);
                registerRecipeWithDefault(craftingObj, this);
            }
        }

        @Override
        public int getRecipeCount() {
            return 5;
        }

        @Override
        public int getRecipeWidth(int recipe) {
            switch (recipe) {
                //case 0: // Sword
                case 0: // knife
                case 3: // Spade
                    return 1;
                case 2: // Axe
                case 4: // Hoe
                    return 2;
                default:
                    return 3;
            }
        }

        @Override
        public ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack) {
            ItemStack handle = getStackFromObject(craftingHandleObj);

            switch (recipe) {
                /*case 0: // Sword
                    return new ItemStack[]
                    {
                    craftStack, craftStack, handle
                    };*/
                case 0: // knife
                    return new ItemStack[]
                            {
                                    craftStack, handle
                            };
                case 1: // Pickaxe
                    return new ItemStack[]
                            {
                                    craftStack, craftStack, craftStack, null, handle, null, null, handle, null
                            };
                case 2: // Axe
                    return new ItemStack[]
                            {
                                    craftStack, craftStack, craftStack, handle, null, handle
                            };
                case 3: // Spade
                    return new ItemStack[]
                            {
                                    craftStack, handle, handle
                            };
                case 4: // Hoe
                    return new ItemStack[]
                            {
                                    craftStack, craftStack, null, handle, null, handle
                            };
                default:
                    return null;
            }
        }

        @Override
        public ItemStack getOutput(int recipe) {
            Item item = null;

            switch (recipe) {
                case 0:
                    item = knifeCrude;
                    break;
                case 1:
                    item = pickaxeCrude;
                    break;
                case 2:
                    item = axeCrude;
                    break;
                case 3:
                    item = spadeCrude;
                    break;
                case 4:
                    item = hoeCrude;
                    break;
            }

            if (item == null) {
                return null;
            } else {
                return new ItemStack(item);
            }
        }
    }
}
