package genesis.genesis.item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
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
	
	public static class ItemsToolSet {
		public ItemGenesisSword sword;
		public ItemGenesisPickaxe pickaxe;
		public ItemGenesisAxe axe;
		public ItemGenesisSpade spade;
		public ItemGenesisHoe hoe;
		
		public ItemsToolSet(int startID, EnumToolMaterial toolMaterial, String materialName)
		{
			sword = (ItemGenesisSword)new ItemGenesisSword(startID, toolMaterial, materialName);
			pickaxe = (ItemGenesisPickaxe)new ItemGenesisPickaxe(startID + 1, toolMaterial, materialName);
			axe = (ItemGenesisAxe)new ItemGenesisAxe(startID + 2, toolMaterial, materialName);
			spade = (ItemGenesisSpade)new ItemGenesisSpade(startID + 3, toolMaterial, materialName);
			hoe = (ItemGenesisHoe)new ItemGenesisHoe(startID + 4, toolMaterial, materialName);
		}
	}

	public static ItemGenesis zircon;
	public static ItemsToolSet zirconTools;
	public static ItemsSuitOfArmor zirconArmor;
	
	public static ItemGenesis quartz;
	
	public static ItemGenesis olivine;
	public static ItemGenesis hematite;
	
	public static void init()
	{
		zircon = (ItemGenesis)new ItemGenesis(IDs.itemZirconID)
				.setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		zirconTools = new ItemsToolSet(IDs.itemsZirconToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemZirconMaterial.toUpperCase(), 2, 1000, 6.5F, 1.5F, 12),
				Names.itemZirconMaterial);
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsZirconArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemZirconMaterial.toUpperCase(), 17, new int[] {2, 7, 5, 2}, 9),
					Names.itemZirconMaterial);
		
		quartz = (ItemGenesis)new ItemGenesis(IDs.itemQuartzID)
				.setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
		hematite = (ItemGenesis)new ItemGenesis(IDs.itemHematiteID)
		.setUnlocalizedName(Names.itemHematite).setTextureName("hematite");

		
		olivine = (ItemGenesis)new ItemGenesis(IDs.itemOlivineID)
				.setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");
		zirconTools = new ItemsToolSet(IDs.itemsOlivineToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemOlivineMaterial.toUpperCase(), 3, 1561, 8, 3, 10),
				Names.itemOlivineMaterial);
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsOlivineArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 17, new int[] {3, 8, 6, 3}, 9),
					Names.itemOlivineMaterial);
	}
	
	public static void registerItems()
	{
		Recipes.registerRecipes();
	}
	
}
