package genesis.genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.genesis.block.gui.BlockCampfire;
import genesis.genesis.block.plants.PlantBlocks;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.item.Items;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;

public class Blocks {

	/* Currently unused constant
	private static final Material rockNoTool = new Material(MapColor.stoneColor); */

	public static Block moss;
	public static Block tikiTorch;
	public static BlockCampfire campfire;
	
	// Rocks
	public static Block granite;
	public static Block graniteMossy;
	public static Block limestone;
	public static Block gneiss;
	public static Block schist;
	public static Block ironMeteorite;
	public static Block stromatolite;
	
	public static Block permafrost;
	
	// Ores
	public static BlockGenesisOre zirconOre;
	public static BlockGenesisOreStorage zirconBlock;

	public static BlockGenesisOre quartzGraniteOre;

	public static BlockGenesisOre olivineOre;
	public static BlockGenesisOreStorage olivineBlock;
	
	public static BlockGenesisOre garnetOre;
	
	public static void init() {
		moss = new BlockMoss(IDs.blockMossID).setTextureName("moss")
				.setUnlocalizedName(Names.blockMoss);

		tikiTorch = new BlockTikiTorch(IDs.blockTikiTorchID).setTextureName("tiki_torch")
				.setUnlocalizedName(Names.blockTikiTorch);
		
		campfire = (BlockCampfire) new BlockCampfire(IDs.blockCampfireID).setTextureName("campfire")
				.setUnlocalizedName(Names.blockCampfire);
		
		
		// Rocks
		granite = new BlockGenesisRock(IDs.blockGraniteID, Material.rock, 0)
				.setTextureName("granite")
				.setUnlocalizedName(Names.blockGranite).setHardness(2.0F).setResistance(10.0F);
		graniteMossy = new BlockGenesisRock(IDs.blockGraniteMossyID, Material.rock, 0)
				.setTextureName("granite_mossy")
				.setUnlocalizedName(Names.blockGraniteMossy).setHardness(2.0F).setResistance(10.0F);
		limestone = new BlockGenesisRock(IDs.blockLimestoneID, Material.rock, 0)
				.setTextureName("limestone")
				.setUnlocalizedName(Names.blockLimestone).setHardness(0.75F).setResistance(8.5F);
		gneiss = new BlockGenesisRock(IDs.blockGneissID, Material.rock, 0)
				.setTextureName("gneiss")
				.setUnlocalizedName(Names.blockGneiss).setHardness(1.65F).setResistance(10.0F);
		schist = new BlockGenesisRock(IDs.blockSchistID, Material.rock, 0)
				.setTextureName("schist")
				.setUnlocalizedName(Names.blockSchist).setHardness(1.05F).setResistance(10.0F);
		ironMeteorite = new BlockGenesisRock(IDs.blockIronMeteoriteID, Material.rock, 0)
				.setTextureName("iron_meteorite")
				.setUnlocalizedName(Names.blockIronMeteorite).setHardness(1.0F).setResistance(10.0F);
		stromatolite = new BlockGenesisRock(IDs.blockStromatoliteID, Material.rock, 0)
				.setTextureName("stromatolite")
				.setUnlocalizedName(Names.blockStromatolite).setHardness(0.75F).setResistance(8.5F);
		
		permafrost = new BlockPermafrost(IDs.blockPermafrostID).setTextureName("permafrost")
				.setUnlocalizedName(Names.blockPermafrost);
		
		// Ores
		zirconOre = (BlockGenesisOre) new BlockGenesisOre(IDs.blockZirconOreID, Material.rock, 2, 1, 1, 3, 7)
				.setTextureName("zircon_ore").setUnlocalizedName(Names.blockZirconOre)
				.setHardness(3.0F).setResistance(10.0F);
		zirconBlock = (BlockGenesisOreStorage)new BlockGenesisOreStorage(IDs.blockZirconID, Material.iron, 2)
				.setTextureName("zircon_block").setUnlocalizedName(Names.blockZircon)
				.setHardness(5.0F).setResistance(10.0F);
		
		quartzGraniteOre = (BlockGenesisOre) new BlockGenesisOre(IDs.blockQuartzGraniteOreID, Material.rock, 1, 1, 1, 2, 5)
				.setTextureName("quartz_granite_ore").setUnlocalizedName(Names.blockQuartzGraniteOre)
				.setHardness(3.0F).setResistance(10.0F);
		
		olivineOre = (BlockGenesisOre) new BlockGenesisOre(IDs.blockOlivineOreID, Material.rock, 2, 1, 1, 3, 7)
				.setTextureName("olivine_ore").setUnlocalizedName(Names.blockOlivineOre)
				.setHardness(3.0F).setResistance(10.0F);
		olivineBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(IDs.blockOlivineID, Material.iron, 2)
				.setTextureName("olivine_block").setUnlocalizedName(Names.blockOlivine)
				.setHardness(5.0F).setResistance(10.0F);
		
		garnetOre = (BlockGenesisOre) new BlockGenesisOre(IDs.blockGarnetOreID, Material.rock, 2, 1, 1, 3, 7)
				.setTextureName("garnet_ore").setUnlocalizedName(Names.blockGarnetOre)
				.setHardness(3.0F).setResistance(10.0F);
		
		TreeBlocks.init();
		PlantBlocks.init();
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(moss, Names.blockMoss);
		
		zirconOre.setDrop(Items.zircon, 0, 1.0F);

		quartzGraniteOre.setDrop(Items.quartz, 0, 0.2F);
		
		olivineOre.setDrop(Items.olivine, 0, 1.0F);
		
		garnetOre.setDrop(Items.garnet, 0, 1.0F);
		
        MinecraftForge.setBlockHarvestLevel(moss, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(permafrost, "shovel", 0);

		zirconBlock.setRecipe(Items.zircon, false);
		olivineBlock.setRecipe(Items.olivine, false);
		
		TreeBlocks.registerBlocks();
		PlantBlocks.registerBlocks();
	}
}
