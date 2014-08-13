package genesis.block.gui;

import genesis.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PolissoirRecipes {
    private static final PolissoirRecipes INSTANCE = new PolissoirRecipes();

    private Map<ItemStack, ItemStack> chippedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> polishedRecipes = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, ItemStack> sharpenedRecipes = new HashMap<ItemStack, ItemStack>();

    public final ItemStack chippedUpgradeItem = new ItemStack(ModItems.brownishFlintPebble);
    public final ItemStack polishedUpgradeItem = new ItemStack(Items.flint); // TODO
    public final ItemStack sharpenedUpgradeItem = new ItemStack(Items.iron_ingot); // TODO

    public final int chippedUpgradeTime = 100;
    public final int polishedUpgradeTime = 200;
    public final int sharpenedUpgradeTime = 240;

    public static PolissoirRecipes instance() {
        return INSTANCE;
    }

    private PolissoirRecipes() {
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

    public ItemStack getResult(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : chippedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return chippedRecipes.get(recipeInput);
                }
            }
            for (ItemStack recipeInput : polishedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return polishedRecipes.get(recipeInput);
                }
            }
            for (ItemStack recipeInput : sharpenedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return sharpenedRecipes.get(recipeInput);
                }
            }
        }
        return null;
    }

    public ItemStack getRequirement(ItemStack input) {
        if (input != null) {
            for (ItemStack recipeInput : chippedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return chippedUpgradeItem;
                }
            }
            for (ItemStack recipeInput : polishedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return polishedUpgradeItem;
                }
            }
            for (ItemStack recipeInput : sharpenedRecipes.keySet()) {
                if (input.isItemEqual(recipeInput)) {
                    return sharpenedUpgradeItem;
                }
            }
        }
        return null;
    }

    public int getUpgradeTime(ItemStack input) {
        if (input != null) {
            for (ItemStack result : chippedRecipes.keySet()) {
                if (input.isItemEqual(result)) {
                    return chippedUpgradeTime;
                }
            }
            for (ItemStack result : polishedRecipes.keySet()) {
                if (input.isItemEqual(result)) {
                    return polishedUpgradeTime;
                }
            }
            for (ItemStack result : sharpenedRecipes.keySet()) {
                if (input.isItemEqual(result)) {
                    return sharpenedUpgradeTime;
                }
            }
        }
        return 0;
    }

    public boolean isUpgradeItem(ItemStack itemStack) {
        return itemStack != null && (itemStack.isItemEqual(chippedUpgradeItem) || itemStack.isItemEqual(polishedUpgradeItem) || itemStack.isItemEqual(sharpenedUpgradeItem));
    }
}
