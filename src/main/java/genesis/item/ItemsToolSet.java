package genesis.item;

import genesis.block.gui.PolissoirRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class ItemsToolSet implements IRecipeWithDefault {
    public Item crude_knife, chipped_knife, polished_knife, sharpened_knife;
    public Item crude_pickaxe, chipped_pickaxe, polished_pickaxe, sharpened_pickaxe;
    public Item crude_axe, chipped_axe, polished_axe, sharpened_axe;
    public Item crude_spade, chipped_spade, polished_spade, sharpened_spade;
    public Item crude_hoe, chipped_hoe, polished_hoe, sharpened_hoe;
    public Object craftingHandleObj;

    public ItemsToolSet(String materialName, Object[] material1, Object[] material2, Object[] material3, Object[] material4) {
        this(materialName, material1, material2, material3, material4, true, true, true, true, true);
    }

    public ItemsToolSet(String materialName, Object[] material1, Object[] material2, Object[] material3, Object[] material4, boolean enableKnife, boolean enablePickaxe, boolean enableAxe, boolean enableSpade, boolean enableHoe) {
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

        String crudeName = materialName + "_crude_chipped";
        String polishedName = materialName + "_polished_sharpened";

        if (enableKnife) {
            crude_knife = new ItemGenesisKnife(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_knife = new ItemGenesisKnife(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_knife = new ItemGenesisKnife(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_knife = new ItemGenesisKnife(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_knife.setUnlocalizedName(unlocalizedName);
                chipped_knife.setUnlocalizedName(unlocalizedName);
                polished_knife.setUnlocalizedName(unlocalizedName);
                sharpened_knife.setUnlocalizedName(unlocalizedName);
            }
        }

        if (enablePickaxe) {
            crude_pickaxe = new ItemGenesisPickaxe(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_pickaxe = new ItemGenesisPickaxe(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_pickaxe = new ItemGenesisPickaxe(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_pickaxe = new ItemGenesisPickaxe(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_pickaxe.setUnlocalizedName(unlocalizedName);
                chipped_pickaxe.setUnlocalizedName(unlocalizedName);
                polished_pickaxe.setUnlocalizedName(unlocalizedName);
                sharpened_pickaxe.setUnlocalizedName(unlocalizedName);
            }
        }

        if (enableAxe) {
            crude_axe = new ItemGenesisAxe(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_axe = new ItemGenesisAxe(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_axe = new ItemGenesisAxe(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_axe = new ItemGenesisAxe(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_axe.setUnlocalizedName(unlocalizedName);
                chipped_axe.setUnlocalizedName(unlocalizedName);
                polished_axe.setUnlocalizedName(unlocalizedName);
                sharpened_axe.setUnlocalizedName(unlocalizedName);
            }
        }

        if (enableSpade) {
            crude_spade = new ItemGenesisSpade(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_spade = new ItemGenesisSpade(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_spade = new ItemGenesisSpade(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_spade = new ItemGenesisSpade(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_spade.setUnlocalizedName(unlocalizedName);
                chipped_spade.setUnlocalizedName(unlocalizedName);
                polished_spade.setUnlocalizedName(unlocalizedName);
                sharpened_spade.setUnlocalizedName(unlocalizedName);
            }
        }

        if (enableHoe) {
            crude_hoe = new ItemGenesisHoe(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_hoe = new ItemGenesisHoe(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_hoe = new ItemGenesisHoe(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_hoe = new ItemGenesisHoe(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_hoe.setUnlocalizedName(unlocalizedName);
                chipped_hoe.setUnlocalizedName(unlocalizedName);
                polished_hoe.setUnlocalizedName(unlocalizedName);
                sharpened_hoe.setUnlocalizedName(unlocalizedName);
            }
        }

        setupHierarchy();
    }

    private void setupHierarchy() {
        PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_axe), new ItemStack(chipped_axe));
        PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_axe), new ItemStack(polished_axe));
        PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_axe), new ItemStack(sharpened_axe));

        PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_hoe), new ItemStack(chipped_hoe));
        PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_hoe), new ItemStack(polished_hoe));
        PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_hoe), new ItemStack(sharpened_hoe));

        PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_knife), new ItemStack(chipped_knife));
        PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_knife), new ItemStack(polished_knife));
        PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_knife), new ItemStack(sharpened_knife));

        PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_pickaxe), new ItemStack(chipped_pickaxe));
        PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_pickaxe), new ItemStack(polished_pickaxe));
        PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_pickaxe), new ItemStack(sharpened_pickaxe));

        PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_spade), new ItemStack(chipped_spade));
        PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_spade), new ItemStack(polished_spade));
        PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_spade), new ItemStack(sharpened_spade));
    }

    public void registerRecipes(Object crafting, Object craftingHandle) {
        /*for (Block block : GenesisTreeBlocks.blocksLogs) {
            craftingHandleObj = new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE);
            registerRecipeWithDefault(crafting, this);
        }*/
        craftingHandleObj = craftingHandle;
        registerRecipeWithDefault(crafting, this);
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
                return new ItemStack[] {craftStack, craftStack, handle};*/
            case 0: // knife
                return new ItemStack[]{craftStack, handle};
            case 1: // Pickaxe
                return new ItemStack[]{craftStack, craftStack, craftStack, null, handle, null, null, handle, null};
            case 2: // Axe
                return new ItemStack[]{craftStack, craftStack, craftStack, handle, null, handle};
            case 3: // Spade
                return new ItemStack[]{craftStack, handle, handle};
            case 4: // Hoe
                return new ItemStack[]{craftStack, craftStack, null, handle, null, handle};
            default:
                return null;
        }
    }

    @Override
    public ItemStack getOutput(int recipe) {
        Item item = null;

        switch (recipe) {
            case 0:
                item = crude_knife;
                break;
            case 1:
                item = crude_pickaxe;
                break;
            case 2:
                item = crude_axe;
                break;
            case 3:
                item = crude_spade;
                break;
            case 4:
                item = crude_hoe;
                break;
        }

        if (item == null) {
            return null;
        } else {
            return new ItemStack(item);
        }
    }

    private ItemStack getStackFromObject(Object obj) {
        if (obj instanceof Item)
            return new ItemStack((Item) obj);
        else if (obj instanceof Block)
            return new ItemStack((Block) obj);
        else if (obj instanceof ItemStack)
            return (ItemStack) obj;

        return null;
    }

    public void registerRecipeWithDefault(Object craftingObj, IRecipeWithDefault iRecipeDef) {
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

    public static enum ToolQuality {
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
}
