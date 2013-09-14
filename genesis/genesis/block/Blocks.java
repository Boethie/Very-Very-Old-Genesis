package genesis.genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.Items;
import genesis.genesis.itemblock.ItemBlockGenesisTree;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import genesis.genesis.plants.blocks.PlantBlocks;
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

	public static Block moss;
	
	// Rocks
	public static Block granite;
	public static Block graniteMossy;
	public static Block limestone;
	public static Block stromatolite;
	
	// Ores
	public static Block zirconOre;
	public static BlockGenesisOreStorage zirconBlock;
	
	public static Block quartzGraniteOre;
	
	public static Block olivineOre;
	public static BlockGenesisOreStorage olivineBlock;
	
	public static void init()
	{
		moss = new BlockMoss(IDs.blockMossID).setTextureName("moss")
				.setUnlocalizedName(Names.blockMoss_unloc);
		
		// Rocks
		granite = new BlockGenesisRock(IDs.blockGraniteID, Material.rock, 0)
				.setTextureName("granite")
				.setUnlocalizedName(Names.blockGranite_unloc).setHardness(1.5F).setResistance(10);
		graniteMossy = new BlockGenesisRock(IDs.blockGraniteMossyID, Material.rock, 0)
				.setTextureName("granite_mossy")
				.setUnlocalizedName(Names.blockGraniteMossy_unloc).setHardness(1.5F).setResistance(10);
		limestone = new BlockGenesisRock(IDs.blockLimestoneID, Material.rock, 0)
				.setTextureName("limestone")
				.setUnlocalizedName(Names.blockLimestone_unloc).setHardness(1).setResistance(7.5F);
		stromatolite = new BlockGenesisRock(IDs.blockStromatoliteID, Material.rock, 0)
				.setTextureName("stromatolite")
				.setUnlocalizedName(Names.blockStromatolite_unloc).setHardness(0.7F).setResistance(5);
		
		// Ores
		zirconOre = new BlockGenesisOre(IDs.blockZirconOreID, new Object[]{Items.zircon}, null,
				Material.rock, 2,
				1, 1, 2, 6)
				.setTextureName("zircon_ore").setUnlocalizedName(Names.blockZirconOre_unloc)
				.setHardness(3).setResistance(10);
		zirconBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockZirconID, Material.iron, 2)
				.setTextureName("zircon_block").setUnlocalizedName(Names.blockZircon_unloc)
				.setHardness(3).setResistance(10);
		
		quartzGraniteOre = new BlockGenesisOre(IDs.blockQuartzGraniteOreID, new Object[]{Items.quartz}, null,
				Material.rock, 1,
				1, 1, 2, 5)
				.setTextureName("quartz_granite_ore").setUnlocalizedName(Names.blockQuartzGraniteOre_unloc)
				.setHardness(3).setResistance(10);
		
		olivineOre = new BlockGenesisOre(IDs.blockOlivineOreID, new Object[]{Items.olivine}, null,
				Material.rock, 2,
				1, 1, 3, 8)
				.setTextureName("olivine_ore").setUnlocalizedName(Names.blockOlivineOre_unloc)
				.setHardness(3).setResistance(10);
		olivineBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockOlivineID, Material.iron, 2)
				.setTextureName("olivine_block").setUnlocalizedName(Names.blockOlivine_unloc)
				.setHardness(3).setResistance(10);
		
		TreeBlocks.init();
		PlantBlocks.init();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(moss, ItemBlock.class, Genesis.MOD_ID + ".moss");

		FurnaceRecipes.smelting().addSmelting(zirconOre.blockID, 0, new ItemStack(Items.zircon), 0.5F);
		FurnaceRecipes.smelting().addSmelting(quartzGraniteOre.blockID, 0, new ItemStack(Items.quartz), 0.2F);

        MinecraftForge.setBlockHarvestLevel(moss, "shovel", 0);
		
		TreeBlocks.registerBlocks();

		zirconBlock.setRecipe(Items.zircon, false);
		olivineBlock.setRecipe(Items.olivine, false);
	}
	
}
