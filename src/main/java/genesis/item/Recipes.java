package genesis.item;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.lib.LogHelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	//Items/blocks working as dye
	public static final Object[] DYE_ITEMS=new Object[]{null,ModItems.hematite,ModItems.malachite};
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

		CraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.tikiTorch, 2), "c", "|", "|", 'c', new ItemStack(Items.coal,0,OreDictionary.WILDCARD_VALUE), '|', PlantBlocks.calamitesPlant);
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.campfire,new Object[]{" A ","A A","BBB",Character.valueOf('A'),"logWood",Character.valueOf('B'),ModBlocks.granite}));
		//add red die functionality into Hematite
		for(int i=0;i<Recipes.DYE_ITEMS.length;i++)
		{
			if(Recipes.DYE_ITEMS[i]!=null){
				ItemStack dye;
				if(Recipes.DYE_ITEMS[i] instanceof Item)
					dye = new ItemStack((Item)Recipes.DYE_ITEMS[i], 1, 0);
				else if(Recipes.DYE_ITEMS[i] instanceof Block)
					dye = new ItemStack((Block)Recipes.DYE_ITEMS[i], 1, 0);
				else
					dye = (ItemStack) Recipes.DYE_ITEMS[i];
				CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(i)), dye, new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0));
				CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.hardened_clay), 'X', dye);
				CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.glass), 'X', dye);
				CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.carpet, 3, i), "##", '#', dye);
			}
		}
		GameRegistry.addSmelting(ModItems.hematite, new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(ModItems.rawEryops, new ItemStack(ModItems.cookedEryops), 0.1F);
		GameRegistry.addSmelting(ModItems.rawAphthoroblattina, new ItemStack(ModItems.cookedAphthoroblattina), 0.1F);
		GameRegistry.addSmelting(ModItems.rawCeratites, new ItemStack(ModItems.cookedCeratites), 0.1F);
	}

}
