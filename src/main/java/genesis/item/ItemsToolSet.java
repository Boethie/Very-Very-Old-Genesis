package genesis.item;

import genesis.lib.PolissoirRecipes;
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
    public Item crude_shovel, chipped_shovel, polished_shovel, sharpened_shovel;
    public Item crude_hoe, chipped_hoe, polished_hoe, sharpened_hoe;
    public Item crude_spear, chipped_spear, polished_spear, sharpened_spear;
    public boolean knifeEnabled, pickaxeEnabled, axeEnabled, shovelEnabled, hoeEnabled, spearEnabled;
    public Object craftingHandleObj;

    public ItemsToolSet(String materialName, Object[] baseValues) {
        this(materialName, baseValues, true, true, true, true, true, true);
    }

    public ItemsToolSet(String materialName, Object[] baseValues, boolean enableKnife, boolean enablePickaxe, boolean enableAxe, boolean enableShovel, boolean enableHoe, boolean enableSpear) {
        this(materialName, baseValues, null, null, null, enableKnife, enablePickaxe, enableAxe, enableShovel, enableHoe, enableSpear);
    }

    public ItemsToolSet(String materialName, Object[] material1, Object[] material2, Object[] material3, Object[] material4) {
        this(materialName, material1, material2, material3, material4, true, true, true, true, true, true);
    }

    public ItemsToolSet(String materialName, Object[] material1, Object[] material2, Object[] material3, Object[] material4, boolean enableKnife, boolean enablePickaxe, boolean enableAxe, boolean enableShovel, boolean enableHoe, boolean enableSpear) {
        knifeEnabled = enableKnife;
        pickaxeEnabled = enablePickaxe;
        axeEnabled = enableAxe;
        shovelEnabled = enableShovel;
        hoeEnabled = enableHoe;
        spearEnabled = enableSpear;

        String material = materialName.toUpperCase() + "_";
        Item.ToolMaterial toolMaterial1 = addMaterial(material + "CRUDE", material1, null);
        Item.ToolMaterial toolMaterial2 = addMaterial(material + "CHIPPED", material1, material2, new Object[]{15, 1.2F});
        Item.ToolMaterial toolMaterial3 = addMaterial(material + "POLISHED", material1, material3, new Object[]{40, 3.2F});
        Item.ToolMaterial toolMaterial4 = addMaterial(material + "SHARPENED", material1, material4, new Object[]{50, 4.0F});

        String[] strings = materialName.split("_");
        String unlocalizedName = "";
        if (strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                if (i > 0) {
                    strings[i] = WordUtils.capitalize(strings[i]);
                }
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

        if (enableShovel) {
            crude_shovel = new ItemGenesisSpade(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_shovel = new ItemGenesisSpade(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_shovel = new ItemGenesisSpade(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_shovel = new ItemGenesisSpade(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_shovel.setUnlocalizedName(unlocalizedName);
                chipped_shovel.setUnlocalizedName(unlocalizedName);
                polished_shovel.setUnlocalizedName(unlocalizedName);
                sharpened_shovel.setUnlocalizedName(unlocalizedName);
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

        if (enableSpear) {
            crude_spear = new ItemGenesisSpear(toolMaterial1, crudeName, ToolQuality.CRUDE).setTextureName(crudeName);
            chipped_spear = new ItemGenesisSpear(toolMaterial2, crudeName, ToolQuality.CHIPPED).setTextureName(crudeName);
            polished_spear = new ItemGenesisSpear(toolMaterial3, polishedName, ToolQuality.POLISHED).setTextureName(polishedName);
            sharpened_spear = new ItemGenesisSpear(toolMaterial4, polishedName, ToolQuality.SHARPENED).setTextureName(polishedName);

            if (!StringUtils.isNullOrEmpty(unlocalizedName)) {
                crude_spear.setUnlocalizedName(unlocalizedName);
                chipped_spear.setUnlocalizedName(unlocalizedName);
                polished_spear.setUnlocalizedName(unlocalizedName);
                sharpened_spear.setUnlocalizedName(unlocalizedName);
            }
        }

        setupHierarchy();
    }

    private Item.ToolMaterial addMaterial(String name, Object[] baseValues, Object[] values, Object[] modifiers) {
        return values != null ? addMaterial(name, values, modifiers) : addMaterial(name, baseValues, modifiers);
    }

    private Item.ToolMaterial addMaterial(String name, Object[] values, Object[] modifiers) {
        int harvestLevel = (Integer) values[0];
        int maxUses = (Integer) values[1];
        float efficiency = (Float) values[2];
        float damage = (Float) values[3];
        int enchantability = (Integer) values[4];
        if (modifiers != null) {
            if (modifiers.length == 2) {
                addModifier(maxUses, modifiers[0]);
                addModifier(efficiency, modifiers[1]);
            } else {
                addModifier(harvestLevel, modifiers[0]);
                addModifier(maxUses, modifiers[1]);
                addModifier(efficiency, modifiers[2]);
                addModifier(damage, modifiers[3]);
                addModifier(enchantability, modifiers[4]);
            }
        }
        return EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
    }

    private int addModifier(int value, Object modifier) {
        return modifier != null ? value + (Integer) modifier : value;
    }

    private float addModifier(float value, Object modifier) {
        return modifier != null ? value + (Float) modifier : value;
    }

    private void setupHierarchy() {
        if (knifeEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_knife), new ItemStack(chipped_knife));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_knife), new ItemStack(polished_knife));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_knife), new ItemStack(sharpened_knife));
        }

        if (pickaxeEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_pickaxe), new ItemStack(chipped_pickaxe));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_pickaxe), new ItemStack(polished_pickaxe));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_pickaxe), new ItemStack(sharpened_pickaxe));
        }

        if (axeEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_axe), new ItemStack(chipped_axe));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_axe), new ItemStack(polished_axe));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_axe), new ItemStack(sharpened_axe));
        }

        if (shovelEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_shovel), new ItemStack(chipped_shovel));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_shovel), new ItemStack(polished_shovel));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_shovel), new ItemStack(sharpened_shovel));
        }

        if (hoeEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_hoe), new ItemStack(chipped_hoe));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_hoe), new ItemStack(polished_hoe));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_hoe), new ItemStack(sharpened_hoe));
        }

        if (spearEnabled) {
            PolissoirRecipes.instance().addChippedRecipe(new ItemStack(crude_spear), new ItemStack(chipped_spear));
            PolissoirRecipes.instance().addPolishedRecipe(new ItemStack(chipped_spear), new ItemStack(polished_spear));
            PolissoirRecipes.instance().addSharpenedRecipe(new ItemStack(polished_spear), new ItemStack(sharpened_hoe));
        }
    }

    public void registerRecipes(Object craftingItem, Object craftingHandle) {
        craftingHandleObj = craftingHandle;
        registerRecipeWithDefault(craftingItem, this);
    }

    @Override
    public int getRecipeCount() {
        return 5;
    }

    @Override
    public int getRecipeWidth(int recipe) {
        int width = 0;

        switch (recipe) {
        case 0:
            if (!knifeEnabled) {
                break;
            }
            width = 1;
        case 1:
            if (!pickaxeEnabled) {
                break;
            }
            width = 3;
        case 2:
            if (!axeEnabled) {
                break;
            }
            width = 2;
        case 3:
            if (!shovelEnabled) {
                break;
            }
            width = 1;
        case 4:
            if (!hoeEnabled) {
                break;
            }
            width = 2;
        }
        return width;
    }

    @Override
    public ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack) {
        ItemStack handle = getStackFromObject(craftingHandleObj);
        ItemStack[] items = null;

        switch (recipe) {
        case 0:
            if (knifeEnabled) {
                items = new ItemStack[]{craftStack, handle};
            }
            break;
        case 1:
            if (pickaxeEnabled) {
                items = new ItemStack[]{craftStack, craftStack, craftStack, null, handle, null, null, handle, null};
            }
            break;
        case 2:
            if (axeEnabled) {
                items = new ItemStack[]{craftStack, craftStack, craftStack, handle, null, handle};
            }
            break;
        case 3:
            if (shovelEnabled) {
                items = new ItemStack[]{craftStack, handle, handle};
            }
            break;
        case 4:
            if (hoeEnabled) {
                items = new ItemStack[]{craftStack, craftStack, null, handle, null, handle};
            }
            break;
        }

        /* Add Case for Spear */

        return items;
    }

    @Override
    public ItemStack getOutput(int recipe) {
        Item item = null;

        switch (recipe) {
        case 0:
            if (knifeEnabled) {
                item = crude_knife;
            }
            break;
        case 1:
            if (pickaxeEnabled) {
                item = crude_pickaxe;
            }
            break;
        case 2:
            if (axeEnabled) {
                item = crude_axe;
            }
            break;
        case 3:
            if (shovelEnabled) {
                item = crude_shovel;
            }
            break;
        case 4:
            if (hoeEnabled) {
                item = crude_hoe;
            }
            break;

        /* Add Case for Spear */

        }

        if (item == null) {
            return null;
        } else {
            return new ItemStack(item);
        }
    }

    private static ItemStack getStackFromObject(Object obj) {
        if (obj instanceof Item) {
            return new ItemStack((Item) obj);
        } else if (obj instanceof Block) {
            return new ItemStack((Block) obj);
        } else if (obj instanceof ItemStack) {
            return (ItemStack) obj;
        }

        return null;
    }

    public static void registerRecipeWithDefault(Object craftingObj, IRecipeWithDefault iRecipeDef) {
        if (craftingObj == null) {
            return;
        }

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
                    if (obj instanceof Integer) {
                        recipeWidths[recipe] = (Integer) obj;
                    } else {
                        items[item] = getStackFromObject(obj);
                    }

                    item++;
                }

                recipes[recipe] = items;
                recipe++;
            }
        } else {
            ItemStack craftStack = getStackFromObject(craftingObj);

            if (craftStack != null) {
                for (int i = 0; i < recipeCount; i++) {
                    recipes[i] = iRecipeDef.getDefaultRecipe(i, craftStack);
                    recipeWidths[i] = iRecipeDef.getRecipeWidth(i);
                }
            } else {
                recipes = null;
            }
        }

        if (recipes != null) {
            @SuppressWarnings("unchecked") List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

            int recipe = 0;

            for (ItemStack[] items : recipes) {
                if (items != null) {
                    int width = recipeWidths[recipe];
                    ItemStack output = iRecipeDef.getOutput(recipe);

                    ShapedRecipes shaped = new ShapedRecipes(width, (int) Math.ceil((double) items.length / width), items, output);
                    recipeList.add(shaped);

                    recipe++;
                }
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
            return localizeableString;
        }
    }
}
