package genesis.item;

import genesis.lib.LogHelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.registry.GameRegistry;

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

		//CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.tikiTorch, 2), "c", "|", "|", 'c', Items.coal, '|', PlantBlocks.calamitesPlant);
		
		//add red die functionality into Hematite
		{
			ItemStack hematite = new ItemStack(ModItems.hematite, 1, 0);
			
			int i = 1;
			
			CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(i)), hematite, new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0));
			CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.hardened_clay), 'X', hematite);
			CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.glass), 'X', hematite);
			CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.carpet, 3, i), "##", '#', hematite);
		}
		GameRegistry.addSmelting(ModItems.hematite, new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(ModItems.rawEryops, new ItemStack(ModItems.cookedEryops), 0.1F);
		GameRegistry.addSmelting(ModItems.rawAphthoroblattina, new ItemStack(ModItems.cookedAphthoroblattina), 0.1F);
		GameRegistry.addSmelting(ModItems.rawCeratites, new ItemStack(ModItems.cookedCeratites), 0.1F);
	}

}
