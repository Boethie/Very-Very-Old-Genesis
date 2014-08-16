package genesis.block.gui;

import genesis.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PolissoirRecipes {
    private static final PolissoirRecipes INSTANCE = new PolissoirRecipes();
    public final ItemStack chippedUpgradeItem = new ItemStack(ModItems.brownishFlintPebble);
    public final ItemStack polishedUpgradeItem = new ItemStack(Items.flint); // TODO
    public final ItemStack sharpenedUpgradeItem = new ItemStack(Items.iron_ingot); // TODO
    public final int chippedUpgradeTime = 100;
    public final int polishedUpgradeTime = 200;
    public final int sharpenedUpgradeTime = 240;
    private Map<ItemStack, ItemStack> chippedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> polishedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> sharpenedRecipes = new HashMap<ItemStack, ItemStack>();

    private PolissoirRecipes() {
    }

    public static PolissoirRecipes instance() {
        return INSTANCE;
    }

    public void addChippedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) chippedRecipes.put(input, result);
    }

    public void addPolishedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) polishedRecipes.put(input, result);
    }

    public void addSharpenedRecipe(ItemStack input, ItemStack result) {
        if (input != null && result != null) sharpenedRecipes.put(input, result);
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
        if (hasChippedRecipe(input)) return chippedUpgradeItem;
        if (hasPolishedRecipe(input)) return polishedUpgradeItem;
        if (hasSharpenedRecipe(input)) return sharpenedUpgradeItem;
        return null;
    }

    public int getUpgradeTime(ItemStack input) {
        if (hasChippedRecipe(input)) return chippedUpgradeTime;
        if (hasPolishedRecipe(input)) return polishedUpgradeTime;
        if (hasSharpenedRecipe(input)) return sharpenedUpgradeTime;
        return 0;
    }

    public boolean isUpgradeItem(ItemStack itemStack) {
        return itemStack != null && (isStackEqual(itemStack, chippedUpgradeItem) || isStackEqual(itemStack, polishedUpgradeItem) || isStackEqual(itemStack, sharpenedUpgradeItem));
    }

    private boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
        return stack1.isItemStackDamageable() && stack2.isItemStackDamageable() ? stack1.getItem() == stack2.getItem() : stack1.isItemEqual(stack2);
    }
}
