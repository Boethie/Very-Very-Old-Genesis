package genesis.genesis.block;

import java.util.Arrays;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import genesis.genesis.block.gui.BlockCampfire;
import genesis.genesis.block.plants.PlantBlocks;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.Items;
import genesis.genesis.item.itemblock.ItemBlockGenesisTree;
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

	public static Block moss;
	public static Block tikiTorch;
	public static BlockCampfire campfire;
	
	// Rocks
	public static Block granite;
	public static Block graniteMossy;
	public static Block limestone;
	public static Block gneiss;
	public static Block stromatolite;
	
	public static Block permafrost;
	
	// Ores
	public static BlockGenesisOre zirconOre;
	public static BlockGenesisOreStorage zirconBlock;

	public static BlockGenesisOre quartzGraniteOre;

	public static BlockGenesisOre olivineOre;
	public static BlockGenesisOreStorage olivineBlock;

	public static BlockGenesisOre hematiteOre;
	public static BlockGenesisOreStorage hematiteBlock;
	
	public static BlockGenesisOre garnetGraniteOre;
	
	public static void init()
	{
		moss = new BlockMoss(IDs.blockMossID).setTextureName("moss")
				.setUnlocalizedName(Names.blockMoss);

		tikiTorch = new BlockTikiTorch(IDs.blockTikiTorchID).setTextureName("tiki_torch")
				.setUnlocalizedName(Names.blockTikiTorch);
		
		campfire = (BlockCampfire)new BlockCampfire(IDs.blockCampfireID).setTextureName("campfire")
				.setUnlocalizedName(Names.blockCampfire);
		
		
		// Rocks
		granite = new BlockGenesisRock(IDs.blockGraniteID, Material.rock, 0)
				.setTextureName("granite")
				.setUnlocalizedName(Names.blockGranite).setHardness(2.0F).setResistance(10);
		graniteMossy = new BlockGenesisRock(IDs.blockGraniteMossyID, Material.rock, 0)
				.setTextureName("granite_mossy")
				.setUnlocalizedName(Names.blockGraniteMossy).setHardness(2.0F).setResistance(10);
		limestone = new BlockGenesisRock(IDs.blockLimestoneID, Material.rock, 0)
				.setTextureName("limestone")
				.setUnlocalizedName(Names.blockLimestone).setHardness(0.75F).setResistance(7.5F);
		gneiss = new BlockGenesisRock(IDs.blockGneissID, Material.rock, 0)
				.setTextureName("gneiss")
				.setUnlocalizedName(Names.blockGneiss).setHardness(1.65F).setResistance(9);
		stromatolite = new BlockGenesisRock(IDs.blockStromatoliteID, Material.rock, 0)
				.setTextureName("stromatolite")
				.setUnlocalizedName(Names.blockStromatolite).setHardness(0.7F).setResistance(5);
		
		permafrost = new BlockPermafrost(IDs.blockPermafrostID).setTextureName("permafrost")
				.setUnlocalizedName(Names.blockPermafrost);
		
		// Ores
		zirconOre = (BlockGenesisOre)new BlockGenesisOre(IDs.blockZirconOreID,
				Material.rock, 2,
				1, 1, 2, 6)
				.setTextureName("zircon_ore").setUnlocalizedName(Names.blockZirconOre)
				.setHardness(3).setResistance(10);
		zirconBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockZirconID, Material.iron, 2)
				.setTextureName("zircon_block").setUnlocalizedName(Names.blockZircon)
				.setHardness(3).setResistance(10);
		
		quartzGraniteOre = (BlockGenesisOre)new BlockGenesisOre(IDs.blockQuartzGraniteOreID,
				Material.rock, 1,
				1, 1, 2, 5)
				.setTextureName("quartz_granite_ore").setUnlocalizedName(Names.blockQuartzGraniteOre)
				.setHardness(3).setResistance(10);
		
		olivineOre = (BlockGenesisOre)new BlockGenesisOre(IDs.blockOlivineOreID,
				Material.rock, 2,
				1, 1, 3, 8)
				.setTextureName("olivine_ore").setUnlocalizedName(Names.blockOlivineOre)
				.setHardness(3).setResistance(10);
		olivineBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockOlivineID, Material.iron, 2)
				.setTextureName("olivine_block").setUnlocalizedName(Names.blockOlivine)
				.setHardness(3).setResistance(10);
		
		hematiteOre = (BlockGenesisOre)new BlockGenesisOre(IDs.blockHematiteOreID,
				Material.rock, 2,
				1, 1, 3, 8)
				.setTextureName("hematite_ore").setUnlocalizedName(Names.blockHematiteOre)
				.setHardness(3).setResistance(10);
		hematiteBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockHematiteID, Material.iron, 2)
				.setTextureName("hematite_block").setUnlocalizedName(Names.blockHematite)
				.setHardness(3).setResistance(10);
		
		garnetGraniteOre = (BlockGenesisOre) new BlockGenesisOre(IDs.blockGarnetGraniteOreID, Material.rock, 2, 1, 1, 3, 7)
				.setTextureName("garnet_ore").setUnlocalizedName(Names.blockGarnetGraniteOre)
				.setHardness(3).setResistance(10);
		
		TreeBlocks.init();
		PlantBlocks.init();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(moss, Names.blockMoss);
		
		zirconOre.setDrop(Items.zircon, 0, 0.5F);

		quartzGraniteOre.setDrop(Items.quartz, 0, 0.2F);
		
		olivineOre.setDrop(Items.olivine, 0, 0.8F);

		hematiteOre.setDrop(new ItemStack(Items.hematite));
		hematiteOre.setSmelting(new ItemStack(Item.ingotIron), 0.7F);
		
		garnetGraniteOre.setDrop(Items.garnet, 0, 1.0F);
		
        MinecraftForge.setBlockHarvestLevel(moss, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(permafrost, "shovel", 0);

		zirconBlock.setRecipe(Items.zircon, false);
		olivineBlock.setRecipe(Items.olivine, false);
		hematiteBlock.setRecipe(Items.hematite, false);
		
		TreeBlocks.registerBlocks();
		PlantBlocks.registerBlocks();
	}
	
}
