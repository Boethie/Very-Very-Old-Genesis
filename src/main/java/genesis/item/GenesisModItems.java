package genesis.item;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.block.GenesisModBlocks;
import genesis.block.plants.GenesisPlantBlocks;
import genesis.fluid.BucketHandler;
import genesis.fluid.item.ItemGenesisBucket;
import genesis.lib.Names;
import net.minecraft.item.Item;

public class GenesisModItems {
    // Tools
    public static ItemsToolSet granite_tools;
    public static ItemsToolSet rhyolite_tools;
    public static ItemsToolSet quartzite_tools;
    public static ItemsToolSet limestone_tools;
    public static ItemsToolSet brownish_flint_tools;

    // Crafting
    public static Item brownish_flint_pebble;
    public static Item quartz;
    public static Item zircon;
    public static Item garnet;
    public static Item manganese;
    public static Item hematite;
    public static Item malachite;
    public static Item olivine;

    // Plants
    public static Item sphenophyllum_spores;
    public static Item sphenophyllum_fiber;
    public static Item bjuvia_seeds;

    // Dinosaur Items
    public static Item arthropleura_chitin;
    public static Item cynognathus_hide;
    public static Item ceratites_shell;
    public static Item epidexipteryx_feather;
    public static Item liopleurodon_tooth;
    public static Item utahraptor_claw;
    public static Item utahraptor_feather;
    public static Item leaellynasaura_hide;
    public static Item tyrannosaurus_tooth;

    // Dinosaur Foods
    public static Item raw_climatius;
    public static Item cooked_climatius;
    public static Item raw_aphthoroblattina;
    public static Item cooked_aphthoroblattina;
    public static Item raw_eryops;
    public static Item cooked_eryops;
    public static Item raw_ceratites;
    public static Item cooked_ceratites;
    public static Item raw_liopleurodon;
    public static Item cooked_liopleurodon;
    public static Item raw_gigantoraptor_thigh;
    public static Item cooked_gigantoraptor_thigh;
    public static Item raw_tyrannosaurus;
    public static Item cooked_tyrannosaurus;

    // Plant Foods
    public static Item rhizome;

    // Buckets
    public static ItemGenesisBucket komatiitic_lava_bucket;

    public static void initiate() {
        // Tools
        granite_tools = new ItemsToolSet(Names.itemGraniteMaterial, new Object[]{1, 375, 4.0F, 1.0F, 5}, new Object[]{1, 390, 5.2F, 1.0F, 5}, new Object[]{1, 415, 7.2F, 1.0F, 5}, new Object[]{1, 425, 8.0F, 1.0F, 5});

        rhyolite_tools = new ItemsToolSet(Names.itemRhyoliteMaterial, new Object[]{1, 375, 4.0F, 1.0F, 5}, new Object[]{1, 390, 5.2F, 1.0F, 5}, new Object[]{1, 415, 7.2F, 1.0F, 5}, new Object[]{1, 425, 8.0F, 1.0F, 5});

        quartzite_tools = new ItemsToolSet(Names.itemQuartziteMaterial, new Object[]{1, 437, 4.0F, 1.0F, 5}, new Object[]{1, 452, 5.2F, 1.0F, 5}, new Object[]{1, 477, 7.2F, 1.0F, 5}, new Object[]{1, 487, 8.0F, 1.0F, 5});

        limestone_tools = new ItemsToolSet(Names.itemLimestoneMaterial, new Object[]{1, 187, 4.0F, 1.0F, 5}, new Object[]{1, 202, 5.2F, 1.0F, 5}, new Object[]{1, 247, 7.2F, 1.0F, 5}, new Object[]{1, 237, 8.0F, 1.0F, 5});

        brownish_flint_tools = new ItemsToolSet(Names.itemBrownishFlintMaterial, new Object[]{1, 353, 4.0F, 1.0F, 5}, new Object[]{1, 368, 5.2F, 1.0F, 5}, new Object[]{1, 493, 7.2F, 1.0F, 5}, new Object[]{1, 403, 8.0F, 1.0F, 5});

        // Crafting
        brownish_flint_pebble = new ItemGenesis().setUnlocalizedName(Names.itemBrownishFlintPebble).setTextureName("brownish_flint_pebble");
        quartz = new ItemGenesis().setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
        zircon = new ItemGenesis().setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
        garnet = new ItemGenesis().setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
        manganese = new ItemGenesis().setUnlocalizedName(Names.itemManganese).setTextureName("manganese");
        hematite = new ItemGenesis().setUnlocalizedName(Names.itemHematite).setTextureName("hematite");
        malachite = new ItemGenesis().setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
        olivine = new ItemGenesis().setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");

        // Plants
        sphenophyllum_spores = new ItemGenesisSeed(GenesisPlantBlocks.sphenophyllum, false).setUnlocalizedName(Names.itemSphenoSpores).setTextureName("sphenophyllum_spores");
        sphenophyllum_fiber = new ItemGenesis().setUnlocalizedName(Names.itemSphenoFiber).setTextureName("sphenophyllum_fiber");
        bjuvia_seeds = new ItemGenesis().setUnlocalizedName(Names.itemBjuviaSeeds).setTextureName("seeds_bjuvia");

        // Dinosaur Items
        arthropleura_chitin = new ItemGenesis().setUnlocalizedName(Names.itemArthopleuraChitin).setTextureName("arthropleura_chitin");
        cynognathus_hide = new ItemGenesis().setUnlocalizedName(Names.itemCynognathusHide).setTextureName("cynognathus_hide");
        ceratites_shell = new ItemGenesis().setUnlocalizedName(Names.itemCeratitesShell).setTextureName("ceratites_shell");
        epidexipteryx_feather = new ItemGenesis().setUnlocalizedName(Names.itemEpidexipteryxFeather).setTextureName("epidexipteryx_feather");
        liopleurodon_tooth = new ItemGenesis().setUnlocalizedName(Names.itemLiopleurodonTooth).setTextureName("liopleurodon_tooth");
        utahraptor_claw = new ItemGenesis().setUnlocalizedName(Names.itemUtahraptorClaw).setTextureName("utahraptor_claw");
        utahraptor_feather = new ItemGenesis().setUnlocalizedName(Names.itemUtahraptorFeather).setTextureName("utahraptor_feather");
        leaellynasaura_hide = new ItemGenesis().setUnlocalizedName(Names.itemLeaellynasauraHide).setTextureName("leaellynasaura_hide");
        tyrannosaurus_tooth = new ItemGenesis().setUnlocalizedName(Names.itemTyrannosaurasTooth).setTextureName("tyrannosaurus_tooth");

        // Dinosaur Foods
        raw_climatius = new ItemGenesisFood(2, 1.2F).setUnlocalizedName(Names.itemRawClimatius).setTextureName("climatius_raw");
        cooked_climatius = new ItemGenesisFood(5, 6F).setUnlocalizedName(Names.itemCookedClimatius).setTextureName("climatius_cooked");
        raw_aphthoroblattina = new ItemGenesisFood(1, 0.6F).setUnlocalizedName(Names.itemRawAphthoroblattina).setTextureName("aphthoroblattina_raw");
        cooked_aphthoroblattina = new ItemGenesisFood(2, 1.2F).setUnlocalizedName(Names.itemCookedAphthoroblattina).setTextureName("aphthoroblattina_cooked");
        raw_eryops = new ItemGenesisFood(3, 1.8F).setUnlocalizedName(Names.itemRawEryops).setTextureName("eryops_raw_leg");
        cooked_eryops = new ItemGenesisFood(8, 12.8F).setUnlocalizedName(Names.itemCookedEryops).setTextureName("eryops_cooked_leg");
        raw_ceratites = new ItemGenesisFood(1, 1.2F).setUnlocalizedName(Names.itemRawCeratites).setTextureName("ceratites_raw");
        cooked_ceratites = new ItemGenesisFood(5, 6.0F).setUnlocalizedName(Names.itemCookedCeratites).setTextureName("ceratites_cooked");
        raw_liopleurodon = new ItemGenesisFood(1, 1.2F).setUnlocalizedName(Names.itemRawLiopleurodon).setTextureName("liopleurodon_raw");
        cooked_liopleurodon = new ItemGenesisFood(5, 6.0F).setUnlocalizedName(Names.itemCookedLiopleurodon).setTextureName("liopleurodon_cooked");
        raw_gigantoraptor_thigh = new ItemGenesisFood(2, 1.2F).setUnlocalizedName(Names.itemRawGigantoraptor).setTextureName("gigantoraptor_thigh_raw");
        cooked_gigantoraptor_thigh = new ItemGenesisFood(6, 7.2F).setUnlocalizedName(Names.itemCookedGigantoraptor).setTextureName("gigantoraptor_thigh_cooked");
        raw_tyrannosaurus = new ItemGenesisFood(3, 1.8F).setUnlocalizedName(Names.itemRawTyrannosaurus).setTextureName("tyrannosaurus_raw");
        cooked_tyrannosaurus = new ItemGenesisFood(8, 12.8F).setUnlocalizedName(Names.itemCookedTyrannosaurus).setTextureName("tyrannosaurus_cooked");

        // Plant Foods
        rhizome = new ItemGenesisSeed(GenesisPlantBlocks.zingiberopsis, true, 1, 0.5f).setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");

        // Buckets
        komatiitic_lava_bucket = (ItemGenesisBucket) new ItemGenesisBucket(GenesisModBlocks.komatiiticLava).setUnlocalizedName(Names.bucketKomatiiticLava).setTextureName("komatiitic_lava_bucket");
    }

    public static void registerItems() {
        GameRegistry.registerItem(brownish_flint_pebble, Names.Registry.itemBrownishFlintPebble);
        GameRegistry.registerItem(quartz, Names.Registry.itemQuartz);
        GameRegistry.registerItem(zircon, Names.Registry.itemZircon);
        GameRegistry.registerItem(garnet, Names.Registry.itemGarnet);
        GameRegistry.registerItem(manganese, Names.Registry.itemManganese);
        GameRegistry.registerItem(hematite, Names.Registry.itemHematite);
        GameRegistry.registerItem(malachite, Names.Registry.itemMalachite);
        GameRegistry.registerItem(olivine, Names.Registry.itemOlivine);

        GameRegistry.registerItem(sphenophyllum_spores, Names.Registry.itemSphenoSpores);
        GameRegistry.registerItem(sphenophyllum_fiber, Names.Registry.itemSphenoFiber);
        GameRegistry.registerItem(bjuvia_seeds, Names.Registry.itemBjuviaSeeds);

        GameRegistry.registerItem(arthropleura_chitin, Names.Registry.itemArthopleuraChitin);
        GameRegistry.registerItem(cynognathus_hide, Names.Registry.itemCynognathusHide);
        GameRegistry.registerItem(ceratites_shell, Names.Registry.itemCeratitesShell);
        GameRegistry.registerItem(epidexipteryx_feather, Names.Registry.itemEpidexipteryxFeather);
        GameRegistry.registerItem(liopleurodon_tooth, Names.Registry.itemLiopleurodonTooth);
        GameRegistry.registerItem(utahraptor_claw, Names.Registry.itemUtahraptorClaw);
        GameRegistry.registerItem(utahraptor_feather, Names.Registry.itemUtahraptorFeather);
        GameRegistry.registerItem(leaellynasaura_hide, Names.Registry.itemLeaellynasauraHide);
        GameRegistry.registerItem(tyrannosaurus_tooth, Names.Registry.itemTyrannosaurasTooth);

        GameRegistry.registerItem(raw_climatius, Names.Registry.itemRawClimatius);
        GameRegistry.registerItem(cooked_climatius, Names.Registry.itemCookedClimatius);
        GameRegistry.registerItem(raw_aphthoroblattina, Names.Registry.itemRawAphthoroblattina);
        GameRegistry.registerItem(cooked_aphthoroblattina, Names.Registry.itemCookedAphthoroblattina);
        GameRegistry.registerItem(raw_eryops, Names.Registry.itemRawEryops);
        GameRegistry.registerItem(cooked_eryops, Names.Registry.itemCookedEryops);
        GameRegistry.registerItem(raw_ceratites, Names.Registry.itemRawCeratites);
        GameRegistry.registerItem(cooked_ceratites, Names.Registry.itemCookedCeratites);
        GameRegistry.registerItem(raw_liopleurodon, Names.Registry.itemRawLiopleurodon);
        GameRegistry.registerItem(cooked_liopleurodon, Names.Registry.itemCookedLiopleurodon);
        GameRegistry.registerItem(raw_gigantoraptor_thigh, Names.Registry.itemRawGigantoraptor);
        GameRegistry.registerItem(cooked_gigantoraptor_thigh, Names.Registry.itemCookedGigantoraptor);
        GameRegistry.registerItem(raw_tyrannosaurus, Names.Registry.itemRawTyrannosaurus);
        GameRegistry.registerItem(cooked_tyrannosaurus, Names.Registry.itemCookedTyrannosaurus);

        GameRegistry.registerItem(rhizome, Names.Registry.itemRhizome);

        GameRegistry.registerItem(komatiitic_lava_bucket, Names.Registry.bucketKomatiiticLava);
        BucketHandler.getInstance().bindBucketToFluid(komatiitic_lava_bucket, GenesisModBlocks.komatiiticLava, GenesisModBlocks.fluidKomatiiticLava);
    }
}
