package genesis.genesis.item;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.plants.PlantBlocks;
import genesis.genesis.lib.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class Recipes {
	
	public static void adaptNetherQuartzRecipes()
	{
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		ArrayList<IRecipe> addRecipes = new ArrayList();
		
		for (IRecipe recipe : recipeList)
		{
			IRecipe cloned = null;
			
			if (recipe instanceof ShapedRecipes)
			{
				ShapedRecipes shaped = (ShapedRecipes)recipe;
				ItemStack[] items = new ItemStack[shaped.recipeItems.length];

				boolean replacedNetherQuartz = false;
				
				for (int i = 0; i < shaped.recipeItems.length; i++)
				{
					ItemStack stack = shaped.recipeItems[i];
					
					if (stack != null)
					{
						if (stack.itemID == Item.netherQuartz.itemID)
						{
							items[i] = new ItemStack(Items.quartz);
							replacedNetherQuartz = true;
						}
						else
						{
							items[i] = stack.copy();
						}
					}
				}
				
				if (replacedNetherQuartz)
				{
					cloned = new ShapedRecipes(shaped.recipeWidth, shaped.recipeHeight,
							items, shaped.getRecipeOutput().copy());
				}
			}
			else if (recipe instanceof ShapelessRecipes)
			{
				ShapelessRecipes shapeless = (ShapelessRecipes)recipe;
				
				boolean replacedNetherQuartz = false;
				ArrayList<ItemStack> itemList = new ArrayList();
				
				for (ItemStack stack : (List<ItemStack>)shapeless.recipeItems)
				{
					if (stack.itemID == Item.netherQuartz.itemID)
					{
						itemList.add(new ItemStack(Items.quartz));
						replacedNetherQuartz = true;
					}
					else
					{
						itemList.add(stack.copy());
					}
				}
				
				if (replacedNetherQuartz)
				{
					cloned = new ShapelessRecipes(shapeless.getRecipeOutput().copy(), itemList);
				}
			}
			
			if (cloned != null)
				addRecipes.add(cloned);
		}
		
		for (IRecipe recipe : addRecipes)
		{
			recipeList.add(recipe);
		}
	}
	
	public static void registerRecipes()
	{
		LogHelper.log(Level.INFO, "Registering recipes.");
		adaptNetherQuartzRecipes();
		
		CraftingManager.getInstance().addRecipe(new ItemStack(Blocks.tikiTorch, 2),
			"c",
			"|",
			"|",
			'c', Item.coal,
			'|', PlantBlocks.calamitesPlant);
		
		GameRegistry.addSmelting(Blocks.ironMeteorite.blockID, new ItemStack(Items.meteoricIronIngot, 1), 0.75F);
	}
	
}
