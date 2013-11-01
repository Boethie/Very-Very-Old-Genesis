package genesis.genesis.item;

import net.minecraft.item.ItemStack;

public interface IRecipeWithDefault {

	int getRecipeCount();

	int getRecipeWidth(int recipe);
	
	ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack);

	ItemStack getOutput(int recipe);
	
}
