package genesis.item;

import genesis.block.ModBlocks;
import genesis.block.plants.PlantBlocks;
import genesis.fluid.item.ItemGenesisBucket;
import genesis.item.ItemSets.ItemsToolSet;
import genesis.lib.Names;
import net.minecraft.init.Items;

public class ModItems {

    // Tools
    public static ItemsToolSet graniteTools;
    public static ItemsToolSet rhyoliteTools;
    public static ItemsToolSet quartziteTools;
    public static ItemsToolSet limestoneTools;
    public static ItemsToolSet flintTools;

    // Crafting
    public static ItemGenesis quartz;
    public static ItemGenesis zircon;
    public static ItemGenesis garnet;
    public static ItemGenesis manganese;
    public static ItemGenesis hematite;
    public static ItemGenesis malachite;
    public static ItemGenesis olivine;

    public static ItemGenesis sphenoFiber;
    public static ItemGenesis bjuviaSeeds;

    public static ItemGenesis ceratitesShell;
    public static ItemGenesis arthropleuraChitin;
    public static ItemGenesis tyrannosaurusTooth;
    public static ItemGenesis utahraptorClaw;
    public static ItemGenesis utahraptorFeather;
    public static ItemGenesis epidexipteryxFeather;

    public static ItemGenesis cynognathusHide;
    public static ItemGenesis leaellynasauraHide;

    public static ItemGenesis brownishFlintPebble;

    // Seeds
    public static ItemGenesisSeed rhizome;
    public static ItemGenesisSeed sphenoSpores;

    // Foods
    public static ItemGenesisFood rawEryops;
    public static ItemGenesisFood cookedEryops;

    public static ItemGenesisFood rawAphthoroblattina;
    public static ItemGenesisFood cookedAphthoroblattina;

    public static ItemGenesisFood rawCeratites;
    public static ItemGenesisFood cookedCeratites;

    public static ItemGenesisFood rawLiopleurodonFillet;
    public static ItemGenesisFood cookedLiopleurodonFillet;

    public static ItemGenesisFood rawGigantoraptorThigh;
    public static ItemGenesisFood cookedGigantoraptorThigh;

    public static ItemGenesisFood rawClimatius;
    public static ItemGenesisFood cookedClimatius;

    // Buckets
    public static ItemGenesisBucket komatiiticLavaBucket;

    public static void init() {
        // Tools
        graniteTools = new ItemsToolSet(Names.itemGraniteMaterial, ModBlocks.granite, Items.stick,
                new Object[]{1, 375, 4.0F, 1.0F, 5},
                new Object[]{1, 390, 5.2F, 1.0F, 5},
                new Object[]{1, 415, 7.2F, 1.0F, 5},
                new Object[]{1, 425, 8.0F, 1.0F, 5});

        rhyoliteTools = new ItemsToolSet(Names.itemRhyoliteMaterial, ModBlocks.rhyolite, Items.stick,
                new Object[]{1, 375, 4.0F, 1.0F, 5},
                new Object[]{1, 390, 5.2F, 1.0F, 5},
                new Object[]{1, 415, 7.2F, 1.0F, 5},
                new Object[]{1, 425, 8.0F, 1.0F, 5});

        quartziteTools = new ItemsToolSet(Names.itemQuartziteMaterial, ModBlocks.quartzite, Items.stick,
                new Object[]{1, 437, 4.0F, 1.0F, 5},
                new Object[]{1, 452, 5.2F, 1.0F, 5},
                new Object[]{1, 477, 7.2F, 1.0F, 5},
                new Object[]{1, 487, 8.0F, 1.0F, 5});

        limestoneTools = new ItemsToolSet(Names.itemLimestoneMaterial, ModBlocks.limestone, Items.stick,
                new Object[]{1, 187, 4.0F, 1.0F, 5},
                new Object[]{1, 202, 5.2F, 1.0F, 5},
                new Object[]{1, 247, 7.2F, 1.0F, 5},
                new Object[]{1, 237, 8.0F, 1.0F, 5});

        flintTools = new ItemsToolSet(Names.itemBrownishFlintMaterial, Items.flint, Items.stick,
                new Object[]{1, 353, 4.0F, 1.0F, 5},
                new Object[]{1, 368, 5.2F, 1.0F, 5},
                new Object[]{1, 493, 7.2F, 1.0F, 5},
                new Object[]{1, 403, 8.0F, 1.0F, 5});

        // Crafting
        brownishFlintPebble = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemBrownishFlintPebble).setTextureName("brownish_flint_pebble");
        quartz = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemQuartz).setTextureName("quartz");
        zircon = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemZircon).setTextureName("zircon");
        garnet = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemGarnet).setTextureName("garnet");
        manganese = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemManganese).setTextureName("manganese");
        hematite = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemHematite).setTextureName("hematite");
        malachite = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemMalachite).setTextureName("malachite");
        olivine = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemOlivine).setTextureName("olivine");

        sphenoSpores = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.sphenophyllum, false)
                .setUnlocalizedName(Names.itemSphenoSpores).setTextureName("sphenophyllum_spores");
        sphenoFiber = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemSphenoFiber).setTextureName("sphenophyllum_fiber");

        bjuviaSeeds = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemBjuviaSeeds).setTextureName("seeds_bjuvia");

        ceratitesShell = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemCeratitesShell).setTextureName("ceratites_shell");
        arthropleuraChitin = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemArthopleuraChitin).setTextureName("arthropleura_chitin");
        tyrannosaurusTooth = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemTyrannosaurasTooth).setTextureName("tyrannosaurus_tooth");
        utahraptorClaw = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemUtahraptorClaw).setTextureName("utahraptor_claw");
        utahraptorFeather = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemUtahraptorFeather).setTextureName("utahraptor_feather");
        epidexipteryxFeather = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemEpidexipteryxFeather).setTextureName("epidexipteryx_feather");

        cynognathusHide = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemCynognathusHide).setTextureName("cynognathus_hide");
        leaellynasauraHide = (ItemGenesis) new ItemGenesis()
                .setUnlocalizedName(Names.itemLeaellynasauraHide).setTextureName("leaellynasaura_hide");

        rhizome = (ItemGenesisSeed) new ItemGenesisSeed(PlantBlocks.zingiberopsis, 1, 0.5f, true)
                .setUnlocalizedName(Names.itemRhizome).setTextureName("zingiberopsis_rhizome");

        // Foods
        rawEryops = (ItemGenesisFood) new ItemGenesisFood(3, 1.8F)
                .setUnlocalizedName(Names.itemRawEryops).setTextureName("eryops_raw_leg");
        cookedEryops = (ItemGenesisFood) new ItemGenesisFood(8, 12.8F)
                .setUnlocalizedName(Names.itemCookedEryops).setTextureName("eryops_cooked_leg");

        rawAphthoroblattina = (ItemGenesisFood) new ItemGenesisFood(1, 0.6F)
                .setUnlocalizedName(Names.itemRawAphthoroblattina).setTextureName("aphthoroblattina_raw");
        cookedAphthoroblattina = (ItemGenesisFood) new ItemGenesisFood(2, 1.2F)
                .setUnlocalizedName(Names.itemCookedAphthoroblattina).setTextureName("aphthoroblattina_cooked");

        rawCeratites = (ItemGenesisFood) new ItemGenesisFood(1, 1.2F)
                .setUnlocalizedName(Names.itemRawCeratites).setTextureName("ceratites_raw");
        cookedCeratites = (ItemGenesisFood) new ItemGenesisFood(5, 6.0F)
                .setUnlocalizedName(Names.itemCookedCeratites).setTextureName("ceratites_cooked");

        rawLiopleurodonFillet = (ItemGenesisFood) new ItemGenesisFood(1, 1.2F)
                .setUnlocalizedName(Names.itemRawLiopleurodon).setTextureName("liopleurodon_fillet_raw");
        cookedLiopleurodonFillet = (ItemGenesisFood) new ItemGenesisFood(5, 6.0F)
                .setUnlocalizedName(Names.itemCookedLiopleurodon).setTextureName("liopleurodon_fillet_cooked");

        rawGigantoraptorThigh = (ItemGenesisFood) new ItemGenesisFood(2, 1.2F)
                .setUnlocalizedName(Names.itemRawGigantoraptor).setTextureName("gigantoraptor_thigh_raw");
        cookedGigantoraptorThigh = (ItemGenesisFood) new ItemGenesisFood(6, 7.2F)
                .setUnlocalizedName(Names.itemCookedGigantoraptor).setTextureName("gigantoraptor_thigh_cooked");

        rawClimatius = (ItemGenesisFood) new ItemGenesisFood(2, 1.2F)
                .setUnlocalizedName(Names.itemRawClimatius).setTextureName("climatius_raw");
        cookedClimatius = (ItemGenesisFood) new ItemGenesisFood(5, 6F)
                .setUnlocalizedName(Names.itemCookedClimatius).setTextureName("climatius_cooked");

        // Buckets
        komatiiticLavaBucket = (ItemGenesisBucket) new ItemGenesisBucket(ModBlocks.komatiiticLava,
                ModBlocks.fluidKomatiiticLava).setUnlocalizedName(Names.bucketKomatiiticLava)
                .setTextureName("komatiitic_lava_bucket");
    }

    public static void registerItems() {
        Recipes.registerRecipes();
        ItemSets.registerAllRecipes();
    }
}
