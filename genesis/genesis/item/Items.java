package genesis.genesis.item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import net.minecraftforge.common.EnumHelper;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.LogHelper;
import genesis.genesis.lib.Names;

public class Items {
	
	public static class ItemsSuitOfArmor {
		public ItemGenesisArmor helmet;
		public ItemGenesisArmor chestplate;
		public ItemGenesisArmor leggings;
		public ItemGenesisArmor boots;
		
		public ItemsSuitOfArmor(int startID, EnumArmorMaterial armorMaterial, String materialName)
		{
			helmet = new ItemGenesisArmor(startID, armorMaterial, 0, materialName);
			chestplate = new ItemGenesisArmor(startID + 1, armorMaterial, 1, materialName);
			leggings = new ItemGenesisArmor(startID + 2, armorMaterial, 2, materialName);
			boots = new ItemGenesisArmor(startID + 3, armorMaterial, 3, materialName);
		}
	}

	public static ItemGenesis zircon;
	public static ItemsSuitOfArmor zirconArmor;
	
	public static ItemGenesis quartz;
	
	public static ItemGenesis olivine;
	
	public static void init()
	{
		zircon = (ItemGenesis)new ItemGenesis(IDs.itemZirconID)
				.setUnlocalizedName(Names.itemZircon_unloc).setTextureName("zircon");
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsZirconArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemZirconArmorMaterial_unloc.toUpperCase(), 17, new int[] {2, 7, 5, 2}, 9),
					Names.itemZirconArmorMaterial_unloc);
		
		quartz = (ItemGenesis)new ItemGenesis(IDs.itemQuartzID)
				.setUnlocalizedName(Names.itemQuartz_unloc).setTextureName("quartz");
		
		olivine = (ItemGenesis)new ItemGenesis(IDs.itemOlivineID)
				.setUnlocalizedName(Names.itemOlivine_unloc).setTextureName("olivine");
	}
	
	public static void registerItems()
	{
		LogHelper.log(Level.INFO, "Registering recipes.");
		adaptNetherQuartzRecipes();
	}
	
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
	
}
