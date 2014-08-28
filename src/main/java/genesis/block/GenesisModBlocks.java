package genesis.block;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.aquatic.GenesisAquaticBlocks;
import genesis.block.gui.BlockCampfire;
import genesis.block.gui.BlockPolissoir;
import genesis.block.plants.GenesisPlantBlocks;
import genesis.block.trees.GenesisTreeBlocks;
import genesis.fluid.FluidKomatiiticLava;
import genesis.fluid.block.BlockKomatiiticLava;
import genesis.item.GenesisModItems;
import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public class GenesisModBlocks {
    /* Currently unused constant
    private static final Material rockNoTool = new Material(MapColor.stoneColor); */

    public static Block moss;
    public static BlockCampfire campfire;
    public static Block polissoir;
    public static Block tiki_torch;
    public static Block portal;

    // Rocks
    public static Block granite;
    public static Block mossy_granite;
    public static Block rhyolite;
    public static Block komatiite;
    public static Block trondhjemite;
    public static Block faux_amphibolite;
    public static Block gneiss;
    public static Block quartzite;
    public static Block old_red_sandstone;
    public static Block limestone;
    public static Block stromatolite;
    public static Block shale;
    public static Block octaedrite;
    public static Block permafrost;

    // Ores
    public static BlockGenesisOre granite_quartz_ore;
    public static BlockGenesisOre zircon_ore;
    public static BlockGenesisOre garnet_ore;
    public static BlockGenesisOre manganese_ore;
    public static BlockGenesisOre hematite_ore;
    public static BlockGenesisOre malachite_ore;
    public static BlockGenesisOre olivine_ore;

    // Fluids
    public static Fluid fluidKomatiiticLava;
    public static Block komatiiticLava;

    public static void initiate() {
        moss = new BlockMoss().setBlockTextureName("moss").setBlockName(Names.blockMoss);
        campfire = (BlockCampfire) new BlockCampfire().setBlockTextureName("campfire").setBlockName(Names.blockCampfire);
        polissoir = new BlockPolissoir().setBlockTextureName("stone").setBlockName(Names.blockPolissoir);
        tiki_torch = new BlockTikiTorch().setBlockTextureName("tiki_torch").setBlockName(Names.blockTikiTorch);
        portal = new BlockPortalGenesis().setBlockTextureName("genesis_portal").setBlockName(Names.blockPortal);

        // Rocks
        granite = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("granite").setBlockName(Names.blockGranite).setHardness(2.0F).setResistance(10.0F);
        mossy_granite = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("granite_mossy").setBlockName(Names.blockGraniteMossy).setHardness(2.0F).setResistance(10.0F);
        rhyolite = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("rhyolite").setBlockName(Names.blockRhyolite).setHardness(1.65F).setResistance(10.0F);
        komatiite = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("komatiite").setBlockName(Names.blockKomatiite).setHardness(1.95F).setResistance(10.0F);
        trondhjemite = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("trondhjemite").setBlockName(Names.blockTrondhjemite).setHardness(2.0F).setResistance(10.0F);
        faux_amphibolite = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("faux_amphibolite").setBlockName(Names.blockFauxAmphibolite).setHardness(1.2F).setResistance(10.0F);
        gneiss = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("gneiss").setBlockName(Names.blockGneiss).setHardness(1.65F).setResistance(10.0F);
        quartzite = new BlockGenesisRockOres(Material.rock, 0).setBlockTextureName("quartzite").setBlockName(Names.blockQuartzite).setHardness(1.95F).setResistance(10.0F);
        old_red_sandstone = new BlockOldRedSandstone().setBlockName(Names.blockOldRedSandstone).setBlockTextureName("old_red_sandstone");
        limestone = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("limestone").setBlockName(Names.blockLimestone).setHardness(0.75F).setResistance(8.5F);
        stromatolite = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("stromatolite").setBlockName(Names.blockStromatolite).setHardness(0.75F).setResistance(8.5F);
        shale = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("shale").setBlockName(Names.blockShale).setHardness(0.75F).setResistance(8.5F);
        octaedrite = new BlockGenesisRock(Material.rock, 0).setBlockTextureName("octaedrite_fragment").setBlockName(Names.blockOctaedrite).setHardness(1.0F).setResistance(10.0F);
        permafrost = new BlockPermafrost().setBlockTextureName("permafrost").setBlockName(Names.blockPermafrost);

        // Ores
        granite_quartz_ore = (BlockGenesisOre) new BlockGenesisOre(1, 1, 1).setBlockTextureName("quartz_granite_ore").setBlockName(Names.blockQuartzGraniteOre);
        zircon_ore = (BlockGenesisOre) new BlockGenesisOre(2, 1, 1).setBlockTextureName("zircon_ore").setBlockName(Names.blockZirconOre);
        garnet_ore = (BlockGenesisOre) new BlockGenesisOre(2, 1, 1).setBlockTextureName("garnet_ore").setBlockName(Names.blockGarnetOre);
        manganese_ore = (BlockGenesisOre) new BlockGenesisOre(2, 1, 1).setBlockTextureName("manganese_ore").setBlockName(Names.blockManganeseOre);
        hematite_ore = (BlockGenesisOre) new BlockGenesisOre(3).setBlockTextureName("hematite_ore").setBlockName(Names.blockHematiteOre);
        malachite_ore = (BlockGenesisOre) new BlockGenesisOre(1, 2, 4).setBlockTextureName("malachite_ore").setBlockName(Names.blockMalachiteOre);
        olivine_ore = (BlockGenesisOre) new BlockGenesisOre(2, 1, 1).setBlockTextureName("olivine_ore").setBlockName(Names.blockOlivineOre);

        // Fluids
        fluidKomatiiticLava = new FluidKomatiiticLava(Names.fluidKomatiiticLava);
        komatiiticLava = new BlockKomatiiticLava(fluidKomatiiticLava, Material.lava).setBlockTextureName("komatiitic_lava").setBlockName(Names.blockKomatiiticLava);

        GenesisTreeBlocks.initiate();
        GenesisPlantBlocks.initiate();
        GenesisAquaticBlocks.initiate();
    }

    public static void registerBlocks() {
        granite_quartz_ore.setDrop(new ItemStack(GenesisModItems.quartz));
        zircon_ore.setDrop(new ItemStack(GenesisModItems.zircon));
        garnet_ore.setDrop(new ItemStack(GenesisModItems.garnet));
        manganese_ore.setDrop(new ItemStack(GenesisModItems.manganese, 1, 0), 2, 4);
        hematite_ore.setDrop(new ItemStack(GenesisModItems.hematite, 1, 0), 2, 4);
        malachite_ore.setDrop(new ItemStack(GenesisModItems.malachite));
        olivine_ore.setDrop(new ItemStack(GenesisModItems.olivine));

        GameRegistry.registerBlock(moss, Names.Registry.blockMoss);

        GameRegistry.registerBlock(campfire, Names.Registry.blockCampfire);
        Item.getItemFromBlock(campfire).setFull3D();

        GameRegistry.registerBlock(polissoir, Names.Registry.blockPolissoir);

        GameRegistry.registerBlock(tiki_torch, Names.Registry.blockTikiTorch);

        GameRegistry.registerBlock(portal, Names.Registry.blockPortal);

        GameRegistry.registerBlock(granite, Names.Registry.blockGranite);
        GameRegistry.registerBlock(mossy_granite, Names.Registry.blockGraniteMossy);
        GameRegistry.registerBlock(rhyolite, Names.Registry.blockRhyolite);
        GameRegistry.registerBlock(komatiite, Names.Registry.blockKomatiite);
        GameRegistry.registerBlock(trondhjemite, Names.Registry.blockTrondhjemite);
        GameRegistry.registerBlock(faux_amphibolite, Names.Registry.blockFauxAmphibolite);
        GameRegistry.registerBlock(gneiss, Names.Registry.blockGneiss);
        GameRegistry.registerBlock(quartzite, Names.Registry.blockQuartzite);
        GameRegistry.registerBlock(old_red_sandstone, Names.Registry.blockOldRedSandstone);
        GameRegistry.registerBlock(limestone, Names.Registry.blockLimestone);
        GameRegistry.registerBlock(stromatolite, Names.Registry.blockStromatolite);
        GameRegistry.registerBlock(shale, Names.Registry.blockShale);

        GameRegistry.registerBlock(octaedrite, Names.Registry.blockOctaedrite);

        GameRegistry.registerBlock(permafrost, Names.Registry.blockPermafrost);

        GameRegistry.registerBlock(granite_quartz_ore, Names.Registry.blockQuartzGraniteOre);
        GameRegistry.registerBlock(zircon_ore, Names.Registry.blockZirconOre);
        GameRegistry.registerBlock(garnet_ore, Names.Registry.blockGarnetOre);
        GameRegistry.registerBlock(manganese_ore, Names.Registry.blockManganeseOre);
        GameRegistry.registerBlock(hematite_ore, Names.Registry.blockHematiteOre);
        GameRegistry.registerBlock(malachite_ore, Names.Registry.blockMalachiteOre);
        GameRegistry.registerBlock(olivine_ore, Names.Registry.blockOlivineOre);

        GameRegistry.registerBlock(komatiiticLava, Names.Registry.blockKomatiiticLava);

        GenesisTreeBlocks.registerBlocks();
        GenesisPlantBlocks.registerBlocks();
        GenesisAquaticBlocks.registerBlocks();
    }
}
