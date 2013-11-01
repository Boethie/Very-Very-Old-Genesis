package genesis.genesis.item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import net.minecraftforge.common.EnumHelper;

import genesis.genesis.item.ItemSets.*;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.LogHelper;
import genesis.genesis.lib.Names;

public class Items {

	public static ItemGenesis zircon;
	public static ItemsToolSet zirconTools;
	public static ItemsSuitOfArmor zirconArmor;
	
	public static ItemGenesis quartz;
	
	public static ItemGenesis olivine;
	
	public static void init()
	{
		zircon = (ItemGenesis)new ItemGenesis(IDs.itemZirconID)
				.setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
		zirconTools = new ItemsToolSet(IDs.itemsZirconToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemZirconMaterial.toUpperCase(), 2, 1000, 6.5F, 1.5F, 12),
				Names.itemZirconMaterial,
				zircon, Item.stick);
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsZirconArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemZirconMaterial.toUpperCase(), 17, new int[] {2, 7, 5, 2}, 9),
					Names.itemZirconMaterial, zircon);
		
		quartz = (ItemGenesis)new ItemGenesis(IDs.itemQuartzID)
				.setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
		
		olivine = (ItemGenesis)new ItemGenesis(IDs.itemOlivineID)
				.setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");
		zirconTools = new ItemsToolSet(IDs.itemsOlivineToolsStartID, 
				EnumHelper.addToolMaterial(Names.itemOlivineMaterial.toUpperCase(), 3, 1561, 8, 3, 10),
				Names.itemOlivineMaterial,
				olivine, Item.stick);
		zirconArmor = new ItemsSuitOfArmor(IDs.itemsOlivineArmorStartID,
					EnumHelper.addArmorMaterial(Names.itemOlivineMaterial.toUpperCase(), 17, new int[] {3, 8, 6, 3}, 9),
					Names.itemOlivineMaterial, olivine);
	}
	
	public static void registerItems()
	{
		Recipes.registerRecipes();
		
		ItemSets.registerAllRecipes();
	}
	
}
