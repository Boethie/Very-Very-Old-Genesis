package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.GenesisModBlocks;
import genesis.block.plants.GenesisPlantBlocks;
import genesis.lib.LogHelper;
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

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    //Items/blocks working as dye
    public static final Object[] DYE_ITEMS = new Object[]{GenesisModItems.manganese, GenesisModItems.hematite, GenesisModItems.malachite};

    @SuppressWarnings("unchecked")
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
                            items[i] = new ItemStack(GenesisModItems.quartz);
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
                        itemList.add(new ItemStack(GenesisModItems.quartz));
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

        GenesisModItems.granite_tools.registerRecipes(GenesisModBlocks.granite, Items.stick);
        GenesisModItems.rhyolite_tools.registerRecipes(GenesisModBlocks.rhyolite, Items.stick);
        GenesisModItems.quartzite_tools.registerRecipes(GenesisModBlocks.quartzite, Items.stick);
        GenesisModItems.limestone_tools.registerRecipes(GenesisModBlocks.limestone, Items.stick);
        GenesisModItems.brownish_flint_tools.registerRecipes(GenesisModItems.brownish_flint_pebble, Items.stick);

        adaptNetherQuartzRecipes();

        //add red die functionality into Hematite
        for (int i = 0; i < Recipes.DYE_ITEMS.length; i++) {
            if (Recipes.DYE_ITEMS[i] != null) {
                ItemStack dye;
                if (Recipes.DYE_ITEMS[i] instanceof Item)
                    dye = new ItemStack((Item) Recipes.DYE_ITEMS[i], 1, 0);
                else if (Recipes.DYE_ITEMS[i] instanceof Block)
                    dye = new ItemStack((Block) Recipes.DYE_ITEMS[i], 1, 0);
                else
                    dye = (ItemStack) Recipes.DYE_ITEMS[i];
                GameRegistry.addShapelessRecipe(new ItemStack(Blocks.wool, 1, BlockColored.func_150031_c(i)), dye, new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0));
                GameRegistry.addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.hardened_clay), 'X', dye);
                GameRegistry.addRecipe(new ItemStack(Blocks.stained_glass, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.glass), 'X', dye);
                GameRegistry.addRecipe(new ItemStack(Blocks.carpet, 3, i), "##", '#', dye);
            }
        }

        GameRegistry.addSmelting(GenesisModItems.hematite, new ItemStack(Items.iron_ingot), 0.7F);
        GameRegistry.addSmelting(GenesisModItems.raw_eryops, new ItemStack(GenesisModItems.cooked_eryops), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_aphthoroblattina, new ItemStack(GenesisModItems.cooked_aphthoroblattina), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_ceratites, new ItemStack(GenesisModItems.cooked_ceratites), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_climatius, new ItemStack(GenesisModItems.cooked_climatius), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_gigantoraptor_thigh, new ItemStack(GenesisModItems.cooked_gigantoraptor_thigh), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_liopleurodon, new ItemStack(GenesisModItems.cooked_liopleurodon), 0.1F);

        GameRegistry.addRecipe(new ShapedOreRecipe(GenesisModBlocks.campfire, " A ", "A A", "BBB", 'A', "logWood", 'B', GenesisModBlocks.granite));
        GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.polissoir), "XXX", "XXX", 'X', GenesisModBlocks.granite);
        GameRegistry.addRecipe(new ItemStack(GenesisModBlocks.tiki_torch, 2), "c", "|", "|", 'c', new ItemStack(Items.coal, 0, OreDictionary.WILDCARD_VALUE), '|', GenesisPlantBlocks.calamites);

        GameRegistry.addSmelting(GenesisModItems.quartz, new ItemStack(GenesisModBlocks.granite_quartz_ore), 0.2F);
        GameRegistry.addSmelting(GenesisModItems.zircon, new ItemStack(GenesisModBlocks.zircon_ore), 1.0F);
        GameRegistry.addSmelting(GenesisModItems.garnet, new ItemStack(GenesisModBlocks.garnet_ore), 1.0F);
        GameRegistry.addSmelting(GenesisModItems.malachite, new ItemStack(GenesisModBlocks.malachite_ore), 0.2F);
        GameRegistry.addSmelting(GenesisModItems.olivine, new ItemStack(GenesisModBlocks.olivine_ore), 1.0F);

        GameRegistry.addRecipe(new ItemStack(GenesisPlantBlocks.calamites_block), "CCC", "CCC", "CCC", 'C', GenesisPlantBlocks.calamites);
        GameRegistry.addShapelessRecipe(new ItemStack(GenesisPlantBlocks.calamites, 9), GenesisPlantBlocks.calamites_block);
    }
}
