package genesis.item;

import genesis.block.trees.GenesisTreeBlocks;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.handler.GenesisFuelHandler;
import genesis.helper.LogHelper;
import genesis.managers.GenesisModBlocks;
import genesis.managers.GenesisModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	//Items/blocks working as dye
	public static final Object[] DYE_ITEMS = new Object[]{GenesisModItems.manganese, GenesisModItems.hematite, GenesisModItems.malachite};

	public static void register() {
		LogHelper.log(Level.INFO, "Registering recipes.");

		for(TreeType type : TreeType.values()){
			GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.storageBox), new Object[]{"###","# #","###",'#', GenesisTreeBlocks.logs[type.ordinal()]});
		}

		/*for (Block log : GenesisTreeBlocks.logs) {
			GameRegistry.addSmelting(log, new ItemStack(Items.coal, 1, 1), 0.15F);

			ItemStack handle = new ItemStack(log, 1, OreDictionary.WILDCARD_VALUE);
			GenesisModItems.granite_tools.registerRecipes(GenesisModBlocks.granite, handle.copy());
			GenesisModItems.rhyolite_tools.registerRecipes(GenesisModBlocks.rhyolite, handle.copy());
			GenesisModItems.dolerite_tools.registerRecipes(GenesisModBlocks.dolerite, handle.copy());
			GenesisModItems.quartzite_tools.registerRecipes(GenesisModBlocks.quartzite, handle.copy());
			GenesisModItems.brownish_flint_tools.registerRecipes(GenesisModItems.brownish_flint_pebble, handle.copy());
			//Add Bone Recipe
		}

		//add red dye functionality into Hematite
		for (int i = 0; i < DYE_ITEMS.length; i++) {
			if (DYE_ITEMS[i] != null) {
				ItemStack dye;
				if (DYE_ITEMS[i] instanceof Item) {
					dye = new ItemStack((Item) DYE_ITEMS[i], 1, 0);
				} else if (DYE_ITEMS[i] instanceof Block) {
					dye = new ItemStack((Block) DYE_ITEMS[i], 1, 0);
				} else {
					dye = (ItemStack) DYE_ITEMS[i];
				}
				GameRegistry.addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(i)), dye, new ItemStack(Blocks.wool, 1, 0));
				GameRegistry.addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.hardened_clay), 'X', dye);
				GameRegistry.addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.glass), 'X', dye);
			}
		}

		GameRegistry.addSmelting(GenesisModItems.hematite, new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(GenesisModItems.raw_climatius, new ItemStack(GenesisModItems.cooked_climatius), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_aphthoroblattina, new ItemStack(GenesisModItems.cooked_aphthoroblattina), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_eryops, new ItemStack(GenesisModItems.cooked_eryops), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_ceratites, new ItemStack(GenesisModItems.cooked_ceratites), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_liopleurodon, new ItemStack(GenesisModItems.cooked_liopleurodon), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_gigantoraptor_thigh, new ItemStack(GenesisModItems.cooked_gigantoraptor_thigh), 0.1F);
		GameRegistry.addSmelting(GenesisModItems.raw_tyrannosaurus, new ItemStack(GenesisModItems.cooked_tyrannosaurus), 0.1F);

		GameRegistry.addRecipe(new ShapedOreRecipe(GenesisModBlocks.campfire, " A ", "A A", "BBB", 'A', "logWood", 'B', GenesisModBlocks.granite));
		GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.polissoir), "XXX", "XXX", 'X', GenesisModBlocks.granite);
		GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.tiki_torch, 2), "c", "|", "|", 'c', new ItemStack(Items.coal, 0, OreDictionary.WILDCARD_VALUE), '|', GenesisPlantBlocks.calamites);
		GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.tiki_torch, 2), "c", "|", "|", 'c', GenesisModItems.resin, '|', GenesisPlantBlocks.calamites);

		GameRegistry.addSmelting(GenesisModItems.quartz, new ItemStack(GenesisModBlocks.granite_quartz_ore), 0.2F);
		GameRegistry.addSmelting(GenesisModItems.zircon, new ItemStack(GenesisModBlocks.zircon_ore), 1.0F);
		GameRegistry.addSmelting(GenesisModItems.garnet, new ItemStack(GenesisModBlocks.garnet_ore), 1.0F);
		GameRegistry.addSmelting(GenesisModItems.malachite, new ItemStack(GenesisModBlocks.malachite_ore), 0.2F);
		GameRegistry.addSmelting(GenesisModItems.olivine, new ItemStack(GenesisModBlocks.olivine_ore), 1.0F);

		GameRegistry.addRecipe(new ItemStack(GenesisPlantBlocks.calamites_block), "CCC", "CCC", "CCC", 'C', GenesisPlantBlocks.calamites);
		GameRegistry.addShapelessRecipe(new ItemStack(GenesisPlantBlocks.calamites, 9), GenesisPlantBlocks.calamites_block);*/

		GameRegistry.registerFuelHandler(GenesisFuelHandler.instance());
		/*for (Block sapling : GenesisTreeBlocks.saplings) {
			addFuel(new ItemStack(sapling, 1, OreDictionary.WILDCARD_VALUE), Blocks.sapling);
		}
		addFuel(new ItemStack(GenesisPlantBlocks.calamites), Blocks.sapling);*/
		addFuel(new ItemStack(GenesisModItems.komatiitic_lava_bucket), Items.lava_bucket);
		addFuel(new ItemStack(GenesisModItems.araucarioxylon_cone),120);
	}

	public static void addFuel(ItemStack fuel, Item burnTimeItem) {
		addFuel(fuel, new ItemStack(burnTimeItem));
	}

	public static void addFuel(ItemStack fuel, Block burnTimeBlock) {
		addFuel(fuel, new ItemStack(burnTimeBlock));
	}

	public static void addFuel(ItemStack fuel, ItemStack burnTimeStack) {
		addFuel(fuel, TileEntityFurnace.getItemBurnTime(burnTimeStack));
	}

	public static void addFuel(ItemStack fuel, int burnTime) {
		GenesisFuelHandler.instance().addFuel(fuel, burnTime);
	}
}
