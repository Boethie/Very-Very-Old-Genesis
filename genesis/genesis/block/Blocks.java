package genesis.genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.Items;
import genesis.genesis.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;

public class Blocks {

	private static final Material rockNoTool = new Material(MapColor.stoneColor);

	public static Block moss = new BlockMoss(IDs.blockMossID).setTextureName("moss")
			.setUnlocalizedName(Names.blockMoss_unloc);
	
	// Rocks
	public static Block granite = new BlockGenesisRock(IDs.blockGraniteID, Material.rock, 0)
			.setTextureName("granite")
			.setUnlocalizedName(Names.blockGranite_unloc).setHardness(1.5F).setResistance(10);
	public static Block graniteMossy = new BlockGenesisRock(IDs.blockGraniteMossyID, Material.rock, 0)
			.setTextureName("granite_mossy")
			.setUnlocalizedName(Names.blockGraniteMossy_unloc).setHardness(1.5F).setResistance(10);
	public static Block limestone = new BlockGenesisRock(IDs.blockLimestoneID, rockNoTool, 0)
			.setTextureName("limestone")
			.setUnlocalizedName(Names.blockLimestone_unloc).setHardness(1).setResistance(7.5F);
	
	// Ores
	public static Block zirconOre = new BlockGenesisOre(IDs.blockZirconOreID, new Object[]{Items.zircon}, null,
			Material.rock, 2,
			1, 1, 2, 6)
			.setTextureName("zircon_ore").setUnlocalizedName(Names.blockZirconOre_unloc)
			.setHardness(3).setResistance(10);
	public static Block zirconBlock = new BlockGenesisOreStorage(IDs.blockZirconID, Material.iron, 2, Items.zircon, false);
	
	public static Block quartzGraniteOre = new BlockGenesisOre(IDs.blockQuartzGraniteOreID, new Object[]{Items.quartz}, null,
			Material.rock, 1,
			1, 1, 2, 5)
			.setTextureName("quartz_granite_ore").setUnlocalizedName(Names.blockQuartzGraniteOre_unloc)
			.setHardness(3).setResistance(10);
	
	public static Block olivineOre = new BlockGenesisOre(IDs.blockOlivineOreID, new Object[]{Items.olivine}, null,
			Material.rock, 2,
			1, 1, 3, 8)
			.setTextureName("olivine_ore").setUnlocalizedName(Names.blockOlivineOre_unloc)
			.setHardness(3).setResistance(10);
	
	public static void init()
	{
		TreeBlocks.init();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(moss, ItemBlock.class, Genesis.MOD_ID + ".moss");

		FurnaceRecipes.smelting().addSmelting(zirconOre.blockID, 0, new ItemStack(Items.zircon), 0.5F);
		FurnaceRecipes.smelting().addSmelting(quartzGraniteOre.blockID, 0, new ItemStack(Items.quartz), 0.2F);

        MinecraftForge.setBlockHarvestLevel(moss, "shovel", 0);
		
		TreeBlocks.registerBlocks();
	}
	
}
