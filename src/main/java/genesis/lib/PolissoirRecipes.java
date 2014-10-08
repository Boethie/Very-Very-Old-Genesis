package genesis.lib;

import genesis.block.plants.GenesisPlantBlocks;
import genesis.managers.GenesisModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PolissoirRecipes {
    private static final PolissoirRecipes INSTANCE = new PolissoirRecipes();
    // 20 ticks = 1 second
    /** 15 seconds */
    public final int chippedUpgradeTime = 5 * 20;
    /** 1 minute 50 seconds */
    public final int polishedUpgradeTime = 15 * 20;
    /** 2 minutes 30 seconds */
    public final int sharpenedUpgradeTime = 75 * 20;
    private Map<ItemStack, ItemStack> chippedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> polishedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> sharpenedRecipes = new HashMap<ItemStack, ItemStack>();

    private PolissoirRecipes() {
    }

    public static PolissoirRecipes instance() {
        return INSTANCE;
    }

    public void addChippedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) {
            chippedRecipes.put(input, result);
        }
    }

    public void addPolishedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) {
            polishedRecipes.put(input, result);
        }
    }

    public void addSharpenedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) {
            sharpenedRecipes.put(input, result);
        }
    }

    public boolean hasChippedRecipe(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : chippedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPolishedRecipe(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : polishedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasSharpenedRecipe(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : sharpenedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ItemStack getResult(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : chippedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return chippedRecipes.get(recipeInput);
                }
            }
            for (ItemStack recipeInput : polishedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return polishedRecipes.get(recipeInput);
                }
            }
            for (ItemStack recipeInput : sharpenedRecipes.keySet()) {
                if (isStackEqual(input, recipeInput)) {
                    return sharpenedRecipes.get(recipeInput);
                }
            }
        }
        return null;
    }

    public ItemStack getRequirement(ItemStack input) {
        if (hasChippedRecipe(input)) {
            return getChippedUpgradeItem();
        }
        if (hasPolishedRecipe(input)) {
            return getPolishedUpgradeItem();
        }
        if (hasSharpenedRecipe(input)) {
            return getSharpenedUpgradeItem();
        }
        return null;
    }

    public int getUpgradeTime(ItemStack input) {
        if (hasChippedRecipe(input)) {
            return chippedUpgradeTime;
        }
        if (hasPolishedRecipe(input)) {
            return polishedUpgradeTime;
        }
        if (hasSharpenedRecipe(input)) {
            return sharpenedUpgradeTime;
        }
        return 0;
    }

    public boolean isUpgradeItem(ItemStack itemStack) {
        return itemStack != null && (isStackEqual(itemStack, getChippedUpgradeItem()) || isStackEqual(itemStack, getPolishedUpgradeItem()) || isStackEqual(itemStack, getSharpenedUpgradeItem()));
    }

    public ItemStack getChippedUpgradeItem() {
        return new ItemStack(GenesisModItems.brownish_flint_pebble);
    }

    public ItemStack getPolishedUpgradeItem() {
        return new ItemStack(GenesisPlantBlocks.calamites);
    }

    public ItemStack getSharpenedUpgradeItem() {
        return new ItemStack(Items.iron_ingot); // TODO
    }

    private boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
        return stack1.isItemStackDamageable() && stack2.isItemStackDamageable() ? stack1.getItem() == stack2.getItem() : stack1.isItemEqual(stack2);
    }
}
