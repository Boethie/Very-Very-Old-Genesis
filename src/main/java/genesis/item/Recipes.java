package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.GenesisModBlocks;
import genesis.block.plants.GenesisPlantBlocks;
import genesis.block.trees.GenesisTreeBlocks;
import genesis.lib.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Level;

public class Recipes {
    //Items/blocks working as dye
    public static final Object[] DYE_ITEMS = new Object[]{GenesisModItems.manganese, GenesisModItems.hematite, GenesisModItems.malachite};

    public static void registerRecipes() {
        LogHelper.log(Level.INFO, "Registering recipes.");

        for (Block log : GenesisTreeBlocks.logs) {
            ItemStack handle = new ItemStack(log, 1, OreDictionary.WILDCARD_VALUE);
            GenesisModItems.granite_tools.registerRecipes(GenesisModBlocks.granite, handle.copy());
            GenesisModItems.rhyolite_tools.registerRecipes(GenesisModBlocks.rhyolite, handle.copy());
            GenesisModItems.quartzite_tools.registerRecipes(GenesisModBlocks.quartzite, handle.copy());
            GenesisModItems.limestone_tools.registerRecipes(GenesisModBlocks.limestone, handle.copy());
            GenesisModItems.brownish_flint_tools.registerRecipes(GenesisModItems.brownish_flint_pebble, handle.copy());
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
        GameRegistry.addSmelting(GenesisModItems.raw_eryops, new ItemStack(GenesisModItems.cooked_eryops), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_aphthoroblattina, new ItemStack(GenesisModItems.cooked_aphthoroblattina), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_ceratites, new ItemStack(GenesisModItems.cooked_ceratites), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_climatius, new ItemStack(GenesisModItems.cooked_climatius), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_gigantoraptor_thigh, new ItemStack(GenesisModItems.cooked_gigantoraptor_thigh), 0.1F);
        GameRegistry.addSmelting(GenesisModItems.raw_liopleurodon, new ItemStack(GenesisModItems.cooked_liopleurodon), 0.1F);
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
        GameRegistry.addShapelessRecipe(new ItemStack(GenesisPlantBlocks.calamites, 9), GenesisPlantBlocks.calamites_block);
    }
}
