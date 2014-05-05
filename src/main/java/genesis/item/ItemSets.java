package genesis.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class ItemSets {

	public static void registerAllRecipes() {
		ItemsSuitOfArmor.registerAllCraftingRecipes();
		ItemsToolSet.registerAllCraftingRecipes();
	}

	public static ItemStack getStackFromObject(Object obj) {
		if (obj instanceof Item)
			return new ItemStack((Item) obj);
		else if (obj instanceof Block)
			return new ItemStack((Block) obj);
		else if (obj instanceof ItemStack)
			return (ItemStack) obj;

		return null;
	}

	public static void registerRecipeWithDefault(Object craftingObj, IRecipeWithDefault iRecipeDef) {
		if (craftingObj == null)
			return;

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
					if (obj instanceof Integer)
						recipeWidths[recipe] = (Integer) obj;
					else
						items[item] = getStackFromObject(obj);

					item++;
				}

				recipes[recipe] = items;
				recipe++;
			}
		} else {
			ItemStack craftStack = getStackFromObject(craftingObj);

			if (craftStack != null)
				for (int i = 0; i < recipeCount; i++) {
					recipes[i] = iRecipeDef.getDefaultRecipe(i, craftStack);
					recipeWidths[i] = iRecipeDef.getRecipeWidth(i);
				}
			else
				recipes = null;
		}

		if (recipes != null) {
			@SuppressWarnings("unchecked") List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

			int recipe = 0;

			for (ItemStack[] items : recipes) {
				int width = recipeWidths[recipe];
				ItemStack output = iRecipeDef.getOutput(recipe);

				ShapedRecipes shaped = new ShapedRecipes(width, (int) Math.ceil((double) items.length / width), items, output);
				recipeList.add(shaped);

				recipe++;
			}
		}
	}

	public static class ItemsSuitOfArmor implements IRecipeWithDefault {

		private static final ArrayList<ItemsSuitOfArmor> suits = new ArrayList<ItemsSuitOfArmor>();

		public static void registerAllCraftingRecipes() {
			for (ItemsSuitOfArmor suit : suits)
				suit.registerRecipes();
		}

		public ItemGenesisArmor helmet;
		public ItemGenesisArmor chestplate;
		public ItemGenesisArmor leggings;
		public ItemGenesisArmor boots;

		public Object craftingObj;
		
		public ItemsSuitOfArmor(ItemArmor.ArmorMaterial armorMaterial, String materialName, Object crafting){
			this (armorMaterial, materialName, crafting, true, true, true, true);
		}
		
		public ItemsSuitOfArmor(ItemArmor.ArmorMaterial armorMaterial, String materialName, Object crafting,
				boolean enableHelmet,
				boolean enableChestplate,
				boolean enableLeggings,
				boolean enableBoots) {
			
			if (enableHelmet)
				helmet = new ItemGenesisArmor(armorMaterial, 0, materialName);
			
			if (enableChestplate)
				chestplate = new ItemGenesisArmor(armorMaterial, 1, materialName);
			
			if (enableLeggings)
				leggings = new ItemGenesisArmor(armorMaterial, 2, materialName);
			
			if (enableBoots)
				boots = new ItemGenesisArmor(armorMaterial, 3, materialName);
			
			craftingObj = crafting;

			suits.add(this);
		}
		
		
		
		public void registerRecipes() {
			registerRecipeWithDefault(craftingObj, this);
		}

		@Override
		public int getRecipeCount() {
			return 4;
		}

		@Override
		public int getRecipeWidth(int recipe) {
			return 3;
		}

		@Override
		public ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack) {
			switch (recipe) {
				case 0:
					return new ItemStack[] { craftStack, craftStack, craftStack, craftStack, null, craftStack };
				case 1:
					return new ItemStack[] { craftStack, null, craftStack, craftStack, craftStack, craftStack, craftStack, craftStack, craftStack };
				case 2:
					return new ItemStack[] { craftStack, craftStack, craftStack, craftStack, null, craftStack, craftStack, null, craftStack };
				case 3:
					return new ItemStack[] { craftStack, null, craftStack, craftStack, null, craftStack };
				default:
					return null;
			}
		}

		@Override
		public ItemStack getOutput(int recipe) {
			Item item = null;
			
			switch (recipe) {
				case 0:
					item = helmet;
					break;
				case 1:
					item = chestplate;
					break;
				case 2:
					item = leggings;
					break;
				case 3:
					item = boots;
					break;
			}
			
			if (item == null){
				return null;
			}else{
				return new ItemStack(item);
			}
		}
	}

	public static class ItemsToolSet implements IRecipeWithDefault {
		private static final ArrayList<ItemsToolSet> sets = new ArrayList<ItemsToolSet>();

		public static void registerAllCraftingRecipes() {
			for (ItemsToolSet set : sets)
				set.registerRecipes();
		}

		public ItemGenesisSword sword;
		public ItemGenesisKnife knife;
		public ItemGenesisPickaxe pickaxe;
		public ItemGenesisAxe axe;
		public ItemGenesisSpade spade;
		public ItemGenesisHoe hoe;

		public Object craftingObj;
		public Object craftingHandleObj;
		
		public ItemsToolSet(Item.ToolMaterial toolMaterial, String materialName, Object crafting, Object craftingHandle){
			this (toolMaterial, materialName, crafting, craftingHandle, true, true, true, true, true, true);
		}
		
		public ItemsToolSet(Item.ToolMaterial toolMaterial, String materialName, Object crafting, Object craftingHandle,
				boolean enableSword,
				boolean enableKnife,
				boolean enablePickaxe,
				boolean enableAxe,
				boolean enableSpade,
				boolean enableHoe) {
			
			if (enableSword)
				sword = new ItemGenesisSword(toolMaterial, materialName);
			
			if (enableKnife)
				knife = new ItemGenesisKnife(toolMaterial, materialName);
			
			if (enablePickaxe)
				pickaxe = new ItemGenesisPickaxe(toolMaterial, materialName);
			
			if (enableAxe)
				axe = new ItemGenesisAxe(toolMaterial, materialName);
			
			if (enableSpade)
				spade = new ItemGenesisSpade(toolMaterial, materialName);
			
			if (enableHoe)
				hoe = new ItemGenesisHoe(toolMaterial, materialName);
			

			craftingObj = crafting;
			craftingHandleObj = craftingHandle;

			sets.add(this);
		}

		public void registerRecipes() {
			registerRecipeWithDefault(craftingObj, this);
		}

		@Override
		public int getRecipeCount() {
			return 6;
		}

		@Override
		public int getRecipeWidth(int recipe) {
			switch (recipe) {
				case 0: // Sword
				case 1: // knife
				case 4: // Spade
					return 1;
				case 3: // Axe
				case 5: // Hoe
					return 2;
				default:
					return 3;
			}
		}

		@Override
		public ItemStack[] getDefaultRecipe(int recipe, ItemStack craftStack) {
			ItemStack handle = getStackFromObject(craftingHandleObj);

			switch (recipe) {
				case 0: // Sword
					return new ItemStack[] { craftStack, craftStack, handle };
				case 1: // knife
					return new ItemStack[] { craftStack, handle };
				case 2: // Pickaxe
					return new ItemStack[] { craftStack, craftStack, craftStack, null, handle, null, null, handle, null };
				case 3: // Axe
					return new ItemStack[] { craftStack, craftStack, craftStack, handle, null, handle };
				case 4: // Spade
					return new ItemStack[] { craftStack, handle, handle };
				case 5: // Hoe
					return new ItemStack[] { craftStack, craftStack, null, handle, null, handle };
				default:
					return null;
			}
		}

		@Override
		public ItemStack getOutput(int recipe) {
			Item item = null;
			
			switch (recipe) {
				case 0:
					item = sword;
					break;
				case 1:
					item = knife;
					break;
				case 2:
					item = pickaxe;
					break;
				case 3:
					item = axe;
					break;
				case 4:
					item = spade;
					break;
				case 5:
					item = hoe;
					break;
			}
			
			if (item == null){
				return null;
			}else{
				return new ItemStack(item);
			}
		}
	}
}
