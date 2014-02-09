package genesis.item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.lib.LogHelper;

public class Recipes {

	public static void adaptNetherQuartzRecipes() {
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		ArrayList<IRecipe> addRecipes = new ArrayList();

		for (IRecipe recipe : recipeList) {
			IRecipe cloned = null;

			if (recipe instanceof ShapedRecipes) {
				ShapedRecipes shaped = (ShapedRecipes) recipe;
				ItemStack[] items = new ItemStack[shaped.recipeItems.length];

				boolean replacedNetherQuartz = false;

				for (int i = 0; i < shaped.recipeItems.length; i++) {
					ItemStack stack = shaped.recipeItems[i];

					if (stack != null)
						if (stack.getItem() == Items.quartz) {
							items[i] = new ItemStack(ModItems.quartz);
							replacedNetherQuartz = true;
						} else
							items[i] = stack.copy();
				}

				if (replacedNetherQuartz)
					cloned = new ShapedRecipes(shaped.recipeWidth, shaped.recipeHeight, items, shaped.getRecipeOutput().copy());
			} else if (recipe instanceof ShapelessRecipes) {
				ShapelessRecipes shapeless = (ShapelessRecipes) recipe;

				boolean replacedNetherQuartz = false;
				ArrayList<ItemStack> itemList = new ArrayList();

				for (ItemStack stack : (List<ItemStack>) shapeless.recipeItems)
					if (stack.getItem() == Items.quartz) {
						itemList.add(new ItemStack(ModItems.quartz));
						replacedNetherQuartz = true;
					} else
						itemList.add(stack.copy());

				if (replacedNetherQuartz)
					cloned = new ShapelessRecipes(shapeless.getRecipeOutput().copy(), itemList);
			}

			if (cloned != null)
				addRecipes.add(cloned);
		}

		for (IRecipe recipe : addRecipes)
			recipeList.add(recipe);
	}

	public static void registerRecipes() {
		LogHelper.log(Level.INFO, "Registering recipes.");
		adaptNetherQuartzRecipes();

		CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.tikiTorch, 2), "c", "|", "|", 'c', Items.coal, '|', PlantBlocks.calamitesPlant);

		GameRegistry.addSmelting(ModBlocks.ironMeteorite, new ItemStack(ModItems.meteoricIronIngot, 1), 0.75F);
		GameRegistry.addSmelting(ModBlocks.chalcopyriteOre, new ItemStack(ModItems.chalcopyriteIngot, 1), 0.7F);
	}

}
