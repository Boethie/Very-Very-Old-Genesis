package genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.aquatic.AquaticBlocks;
import genesis.block.gui.BlockCampfire;
import genesis.block.gui.BlockPolissoir;
import genesis.block.plants.PlantBlocks;
import genesis.block.trees.TreeBlocks;
import genesis.fluid.FluidKomatiiticLava;
import genesis.fluid.block.BlockKomatiiticLava;
import genesis.item.ModItems;
import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public class ModBlocks {

	/* Currently unused constant
    private static final Material rockNoTool = new Material(MapColor.stoneColor); */

    public static Block moss;
    public static BlockCampfire campfire;
    public static Block polissoir;
    public static Block tikiTorch;
    public static BlockPortalGenesis portal;

    // Rocks
    public static Block granite;
    public static Block graniteMossy;
    public static Block rhyolite;
    public static Block komatiite;
    public static Block trondhjemite;
    public static Block fauxAmphibolite;
    public static Block gneiss;
    public static Block quartzite;
    public static Block oldRedSandstone;
    public static Block limestone;
    public static Block stromatolite;
    public static Block shale;
    public static Block octaedrite;
    public static Block permafrost;

    // Ores
    public static BlockGenesisOre quartzGraniteOre;
    public static BlockGenesisOre zirconOre;
    public static BlockGenesisOre garnetOre;
    public static BlockGenesisOre manganeseOre;
    public static BlockGenesisOre hematiteOre;
    public static BlockGenesisOre malachiteOre;
    public static BlockGenesisOre olivineOre;

    // Fluid(Blocks)
    public static BlockFluidBase komatiiticLava;
    public static Fluid fluidKomatiiticLava;


    public static void init() {
        moss = new BlockMoss()
                .setBlockTextureName("moss")
                .setBlockName(Names.blockMoss);

        campfire = (BlockCampfire) new BlockCampfire()
                .setBlockTextureName("campfire")
                .setBlockName(Names.blockCampfire);

        polissoir = new BlockPolissoir()
                .setBlockTextureName("stone")
                .setBlockName(Names.blockPolissoir);

        tikiTorch = new BlockTikiTorch()
                .setBlockTextureName("tiki_torch")
                .setBlockName(Names.blockTikiTorch);

        portal = (BlockPortalGenesis) new BlockPortalGenesis()
                .setBlockTextureName("genesis_portal")
                .setBlockName(Names.blockPortal);

        // Rocks
        granite = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("granite")
                .setBlockName(Names.blockGranite).setHardness(2.0F).setResistance(10.0F);
        graniteMossy = new BlockGenesisRock(Material.rock, 0)
                .setBlockTextureName("granite_mossy")
                .setBlockName(Names.blockGraniteMossy).setHardness(2.0F).setResistance(10.0F);
        rhyolite = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("rhyolite")
                .setBlockName(Names.blockRhyolite).setHardness(1.65F).setResistance(10.0F);
        komatiite = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("komatiite")
                .setBlockName(Names.blockKomatiite).setHardness(1.95F).setResistance(10.0F);
        trondhjemite = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("trondhjemite")
                .setBlockName(Names.blockTrondhjemite)
                .setHardness(2.0F).setResistance(10.0F);
        fauxAmphibolite = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("faux_amphibolite")
                .setBlockName(Names.blockFauxAmphibolite).setHardness(1.2F).setResistance(10.0F);
        gneiss = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("gneiss")
                .setBlockName(Names.blockGneiss).setHardness(1.65F).setResistance(10.0F);
        quartzite = new BlockGenesisRockOres(Material.rock, 0)
                .setBlockTextureName("quartzite")
                .setBlockName(Names.blockQuartzite).setHardness(1.95F).setResistance(10.0F);
        oldRedSandstone = new BlockOldRedSandstone().setBlockName(Names.blockOldRedSandstone)
                .setBlockTextureName("old_red_sandstone");
        limestone = new BlockGenesisRock(Material.rock, 0)
                .setBlockTextureName("limestone")
                .setBlockName(Names.blockLimestone).setHardness(0.75F).setResistance(8.5F);
        stromatolite = new BlockGenesisRock(Material.rock, 0)
                .setBlockTextureName("stromatolite")
                .setBlockName(Names.blockStromatolite).setHardness(0.75F).setResistance(8.5F);
        shale = new BlockGenesisRock(Material.rock, 0)
                .setBlockTextureName("shale")
                .setBlockName(Names.blockShale).setHardness(0.75F).setResistance(8.5F);

        octaedrite = new BlockGenesisRock(Material.rock, 0)
                .setBlockTextureName("octaedrite_fragment")
                .setBlockName(Names.blockOctaedrite).setHardness(1.0F).setResistance(10.0F);

        permafrost = new BlockPermafrost().setBlockTextureName("permafrost")
                .setBlockName(Names.blockPermafrost);

        // Ores
        quartzGraniteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 1, 1, 1)
                .setBlockTextureName("quartz_granite_ore").setBlockName(Names.blockQuartzGraniteOre)
                .setHardness(3.0F).setResistance(10.0F);
        quartzGraniteOre.setHarvestLevel("pickaxe", 1);
        zirconOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1)
                .setBlockTextureName("zircon_ore").setBlockName(Names.blockZirconOre)
                .setHardness(3.0F).setResistance(10.0F);
        zirconOre.setHarvestLevel("pickaxe", 1);
        garnetOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1)
                .setBlockTextureName("garnet_ore").setBlockName(Names.blockGarnetOre)
                .setHardness(3.0F).setResistance(10.0F);
        garnetOre.setHarvestLevel("pickaxe", 1);
        manganeseOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1)
                .setBlockTextureName("manganese_ore").setBlockName(Names.blockManganeseOre)
                .setHardness(3.0F).setResistance(10.0F);
        manganeseOre.setHarvestLevel("pickaxe", 1);
        hematiteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 3)
                .setBlockTextureName("hematite_ore").setBlockName(Names.blockHematiteOre)
                .setHardness(3.0F).setResistance(10.0F);
        hematiteOre.setHarvestLevel("pickaxe", 1);
        malachiteOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 1, 2, 4)
                .setBlockTextureName("malachite_ore").setBlockName(Names.blockMalachiteOre)
                .setHardness(3.0F).setResistance(10.0F);
        malachiteOre.setHarvestLevel("pickaxe", 1);
        olivineOre = (BlockGenesisOre) new BlockGenesisOre(Material.rock, 2, 1, 1)
                .setBlockTextureName("olivine_ore").setBlockName(Names.blockOlivineOre)
                .setHardness(3.0F).setResistance(10.0F);
        olivineOre.setHarvestLevel("pickaxe", 1);

        // Fluid blocks
        fluidKomatiiticLava = new FluidKomatiiticLava(Names.fluidKomatiiticLava);
        komatiiticLava = (BlockFluidBase) new BlockKomatiiticLava(fluidKomatiiticLava, Material.lava)
                .setBlockTextureName("komatiitic_lava")
                .setBlockName(Names.blockKomatiiticLava);

        TreeBlocks.init();
        PlantBlocks.init();
        AquaticBlocks.init();
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(moss, Names.blockMoss);
        GameRegistry.registerBlock(portal, Names.blockPortal);

        GameRegistry.registerBlock(komatiiticLava, Names.blockKomatiiticLava);

        GameRegistry.addRecipe(new ItemStack(polissoir), "XXX", "XXX", 'X', granite);

        quartzGraniteOre.setDropAndSmelting(ModItems.quartz, 0, 0.2F);

        zirconOre.setDropAndSmelting(ModItems.zircon, 0, 1.0F);

        garnetOre.setDropAndSmelting(ModItems.garnet, 0, 1.0F);

        manganeseOre.setDrop(new ItemStack(ModItems.manganese, 1, 0), 2, 4);

        hematiteOre.setDrop(new ItemStack(ModItems.hematite, 1, 0), 2, 4);

        malachiteOre.setDropAndSmelting(ModItems.malachite, 0, 0.2F);

        olivineOre.setDropAndSmelting(ModItems.olivine, 0, 1.0F);

        moss.setHarvestLevel("shovel", 0);
        permafrost.setHarvestLevel("shovel", 0);

        TreeBlocks.registerBlocks();
        PlantBlocks.registerBlocks();
        AquaticBlocks.registerBlocks();
    }
}
