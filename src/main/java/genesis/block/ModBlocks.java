package genesis.block;

import genesis.block.gui.BlockCampfire;
import genesis.block.plants.PlantBlocks;
import genesis.block.trees.TreeBlocks;
import genesis.item.ModItems;
import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	/* Currently unused constant
	private static final Material rockNoTool = new Material(MapColor.stoneColor); */

	public static Block moss;
	public static Block tikiTorch;
	public static BlockCampfire campfire;
	
	// Rocks
	public static Block granite;
	public static Block graniteMossy;
	public static Block rhyolite;
	public static Block komatiite;
	public static Block fauxAmphibolite;
	public static Block gneiss;
	public static Block quartzite;
	public static Block limestone;
	public static Block stromatolite;
	public static Block shale;
	public static Block permafrost;

	// Ores
	public static Block ironMeteorite;
	
	public static BlockGenesisOre quartzGraniteOre;
	
	public static BlockGenesisOre malachiteOre;
	public static BlockGenesisOreStorage malachiteBlock;
	
	public static BlockGenesisOre hematiteOre;
	//public static BlockGenesisOreStorage chalcopyriteBlock;
	
	public static BlockGenesisOre zirconOre;
	public static BlockGenesisOreStorage zirconBlock;
	
	public static BlockGenesisOre garnetOre;
	public static BlockGenesisOreStorage garnetBlock;

	public static BlockGenesisOre olivineOre;
	public static BlockGenesisOreStorage olivineBlock;
	
	public static void init() {
		moss = new BlockMoss().setBlockTextureName("moss")
				.setBlockName(Names.blockMoss);

		tikiTorch = new BlockTikiTorch().setBlockTextureName("tiki_torch")
				.setBlockName(Names.blockTikiTorch);
		
		campfire = (BlockCampfire) new BlockCampfire().setBlockTextureName("campfire")
				.setBlockName(Names.blockCampfire);
		
		
		// Rocks
		granite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("granite")
				.setBlockName(Names.blockGranite).setHardness(2.0F).setResistance(10.0F);
		graniteMossy = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("granite_mossy")
				.setBlockName(Names.blockGraniteMossy).setHardness(2.0F).setResistance(10.0F);
		rhyolite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("rhyolite")
				.setBlockName(Names.blockRhyolite).setHardness(1.65F).setResistance(10.0F);
		komatiite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("komatiite")
				.setBlockName(Names.blockKomatiite).setHardness(1.95F).setResistance(10.0F);
		fauxAmphibolite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("faux_amphibolite")
				.setBlockName(Names.blockFauxAmphibolite).setHardness(1.2F).setResistance(10.0F);
		gneiss = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("gneiss")
				.setBlockName(Names.blockGneiss).setHardness(1.65F).setResistance(10.0F);
		quartzite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("quartzite")
				.setBlockName(Names.blockQuartzite).setHardness(1.95F).setResistance(10.0F);
		limestone = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("limestone")
				.setBlockName(Names.blockLimestone).setHardness(0.75F).setResistance(8.5F);
		stromatolite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("stromatolite")
				.setBlockName(Names.blockStromatolite).setHardness(0.75F).setResistance(8.5F);
		shale = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("shale")
				.setBlockName(Names.blockShale).setHardness(0.75F).setResistance(8.5F);
		
		permafrost = new BlockPermafrost().setBlockTextureName("permafrost")
				.setBlockName(Names.blockPermafrost);
		
		// Ores
		ironMeteorite = new BlockGenesisRock(Material.rock, 0)
				.setBlockTextureName("iron_meteorite")
				.setBlockName(Names.blockIronMeteorite).setHardness(1.0F).setResistance(10.0F);
		
		quartzGraniteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 1, 1, 1, 2, 5)
				.setBlockTextureName("quartz_granite_ore").setBlockName(Names.blockQuartzGraniteOre)
				.setHardness(3.0F).setResistance(10.0F);
		
		malachiteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 1, 4, 8, 2, 5)
				.setBlockTextureName("malachite_ore").setBlockName(Names.blockMalachiteOre)
				.setHardness(3.0F).setResistance(10.0F);
		malachiteBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(Material.iron, 1)
				.setBlockTextureName("malachite_block").setBlockName(Names.blockMalachite)
				.setHardness(5.0F).setResistance(10.0F);
		
		hematiteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 3)
				.setBlockTextureName("hematite_ore").setBlockName(Names.blockHematiteOre)
				.setHardness(3.0F).setResistance(10.0F);
		
		/*chalcopyriteBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(Material.iron, 2)
				.setBlockTextureName("chalcopyrite_block").setBlockName(Names.blockChalcopyrite)
				.setHardness(5.0F).setResistance(10.0F);*/

		zirconOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1, 3, 7)
				.setBlockTextureName("zircon_ore").setBlockName(Names.blockZirconOre)
				.setHardness(3.0F).setResistance(10.0F);
		zirconBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(Material.iron, 2)
				.setBlockTextureName("zircon_block").setBlockName(Names.blockZircon)
				.setHardness(5.0F).setResistance(10.0F);
		
		garnetOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1, 3, 7)
				.setBlockTextureName("garnet_ore").setBlockName(Names.blockGarnetOre)
				.setHardness(3.0F).setResistance(10.0F);
		garnetBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(Material.iron, 2)
				.setBlockTextureName("garnet_block").setBlockName(Names.blockGarnet)
				.setHardness(5.0F).setResistance(10.0F);
		
		
		olivineOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1, 3, 7)
				.setBlockTextureName("olivine_ore").setBlockName(Names.blockOlivineOre)
				.setHardness(3.0F).setResistance(10.0F);
		olivineBlock = (BlockGenesisOreStorage) new BlockGenesisOreStorage(Material.iron, 2)
				.setBlockTextureName("olivine_block").setBlockName(Names.blockOlivine)
				.setHardness(5.0F).setResistance(10.0F);
		
		TreeBlocks.init();
		PlantBlocks.init();
		//CropBlocks.init(); #Cropblocks are basically just PlantBlocks
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(moss, Names.blockMoss);
		
		zirconOre.setDropAndSmelting(ModItems.zircon, 0, 1.0F);

		quartzGraniteOre.setDropAndSmelting(ModItems.quartz, 0, 0.2F);
		
		olivineOre.setDropAndSmelting(ModItems.olivine, 0, 1.0F);
		
		garnetOre.setDropAndSmelting(ModItems.garnet, 0, 1.0F);
		
		malachiteOre.setDropAndSmelting(ModItems.malachite, 0, 0.2F);
		
		hematiteOre.setDrop(new ItemStack(ModItems.hematite, 1, 0), 2, 4);
		
        moss.setHarvestLevel("shovel", 0);
        permafrost.setHarvestLevel("shovel", 0);

		zirconBlock.setRecipe(ModItems.zircon, false);
		olivineBlock.setRecipe(ModItems.olivine, false);
		malachiteBlock.setRecipe(ModItems.malachite, false);
		garnetBlock.setRecipe(ModItems.garnet, false);
		
		TreeBlocks.registerBlocks();
		PlantBlocks.registerBlocks();
	}
}
