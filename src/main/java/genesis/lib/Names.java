package genesis.lib;

import genesis.Genesis;

import java.util.Arrays;
import java.util.List;

public final class Names {

    public static final String mod = Genesis.MOD_ID + ".";

    // ---- Blocks ----
    public static final String blockMoss = mod + "moss";
    public static final String blockCampfire = mod + "campfire";
    public static final String blockPolissoir = mod + "polissoir";
    public static final String blockTikiTorch = mod + "tikiTorch";
    public static final String blockPortal = mod + "portal";

    // Rock blocks
    public static final String blockGranite = mod + "granite";
    public static final String blockGraniteMossy = mod + "graniteMossy";
    public static final String blockRhyolite = mod + "rhyolite";
    public static final String blockDolerite = mod + "dolerite";
    public static final String blockKomatiite = mod + "komatiite";
    public static final String blockTrondhjemite = mod + "trondhjemite";
    public static final String blockFauxAmphibolite = mod + "fauxamphibolite";
    public static final String blockGneiss = mod + "gneiss";
    public static final String blockQuartzite = mod + "quartzite";
    public static final String blockOldRedSandstone = mod + "oldRedSandstone";
    public static final String blockLimestone = mod + "limestone";
    public static final String blockStromatolite = mod + "stromatolite";
    public static final String blockShale = mod + "shale";
    public static final String blockOctaedrite = mod + "octaedrite";
    public static final String blockPermafrost = mod + "permafrost";

    // Ores
    public static final String blockOre = mod + "ore.";
    public static final String blockQuartzGraniteOre = blockOre + "quartzGranite";
    public static final String blockZirconOre = blockOre + "zircon";
    public static final String blockGarnetOre = blockOre + "garnet";
    public static final String blockManganeseOre = blockOre + "manganese";
    public static final String blockHematiteOre = blockOre + "hematite";
    public static final String blockMalachiteOre = blockOre + "malachite";
    public static final String blockOlivineOre = blockOre + "olivine";

    // Fluids
    public static final String fluidKomatiiticLava = mod + "komatiiticLava";
    public static final String blockKomatiiticLava = mod + "komatiiticLavaBlock";

    // Trees
    public static final String blockLogGenesis = mod + "log.";
    public static final String blockSaplingGenesis = mod + "sapling.";
    public static final String blockLeavesGenesis = mod + "leaves.";
    public static final String blockRottenLogGenesis = mod + "logRotten.";
    public static final String blockBjuviaCone = mod + "bjuviaCone";

    // Plants
    public static final String blockPlant = mod + "plant.";
    public static final String blockFern = mod + "fern.";
    public static final String blockFlower = mod + "flower.";

    //public static final String blockFlowerPot = mod + "flowerpot";
    public static final String blockCalamitesPlant = mod + "calamites";
    public static final String blockCalamitesBlock = mod + "blockCalamites";
    public static final String blockZingiberopsis = mod + "zingiberopsis";
    public static final String blockAsteroxylon = mod + "asteroxylon";
    public static final String blockSphenophyllum = mod + "sphenophyllum";

    // Aquatic
    public static final String blockAlgae = mod + "algae.";
    public static final String blockCoral = mod + "coral.";
    public static final String blockSponge = mod + "sponge.";
    public static final String blockPteridinium = mod + "pteridinium";
    public static final String blockCharnia = mod + "charnia";

    // ---- Items ----
    // Tools
    public static final String itemTool = mod + "tool.";
    public static final String itemPickaxe = itemTool + "pickaxe.";
    public static final String itemAxe = itemTool + "axe.";
    public static final String itemSpade = itemTool + "shovel.";
    public static final String itemHoe = itemTool + "hoe.";
    public static final String itemKnife = mod + "knife.";

    // Crafting items
    public static final String itemBrownishFlintPebble = mod + "brownishFlintPebble";
    public static final String itemQuartz = mod + "quartz";
    public static final String itemZircon = mod + "zircon";
    public static final String itemGarnet = mod + "garnet";
    public static final String itemManganese = mod + "manganese";
    public static final String itemHematite = mod + "hematite";
    public static final String itemMalachite = mod + "malachite";
    public static final String itemOlivine = mod + "olivine";

    // Plants
    public static final String itemSeeds = mod + "seeds.";
    public static final String itemResin = mod + "resin";
    public static final String itemSphenoSpores = itemSeeds + "sphenophyllumSpores";
    public static final String itemSphenoFiber = mod + "sphenoFiber";
    public static final String itemBjuviaSeeds = mod + "bjuviaSeeds";

    // Dinosaur Items
    public static final String itemArthopleuraChitin = mod + "arthropleuraChitin";
    public static final String itemCynognathusHide = mod + "cynognathusHide";
    public static final String itemCeratitesShell = mod + "ceratitesShell";
    public static final String itemMegateuthisRostrum = mod + "megateuthisRostrum";
    public static final String itemEpidexipteryxFeather = mod + "epidexipteryxFeather";
    public static final String itemLiopleurodonTooth = mod + "liopleurodonTooth";
    public static final String itemUtahraptorClaw = mod + "utahraptorClaw";
    public static final String itemUtahraptorFeather = mod + "utahraptorFeather";
    public static final String itemLeaellynasauraHide = mod + "leaellynasauraHide";
    public static final String itemPachyrhinosaurusHorn = mod + "pachyrhinosaurusHorn";
    public static final String itemPuertasaurusRib = mod + "puertasaurusRib";
    public static final String itemShantungosaurusScapula = mod + "shantungosaurusScapula";
    public static final String itemGallimimusMetatarsus = mod + "gallimimusMetatarsus";
    public static final String itemTyrannosaurasTooth = mod + "tyrannosaurusTooth";

    // Foods
    public static final String itemFood = mod + "food.";

    public static final String itemRawClimatius = itemFood + "rawClimatius";
    public static final String itemCookedClimatius = itemFood + "cookedClimatius";
    public static final String itemRawAphthoroblattina = itemFood + "rawAphthoroblattina";
    public static final String itemCookedAphthoroblattina = itemFood + "cookedAphthoroblattina";
    public static final String itemRawEryops = itemFood + "rawEryops";
    public static final String itemCookedEryops = itemFood + "cookedEryops";
    public static final String itemRawCeratites = itemFood + "rawCeratites";
    public static final String itemCookedCeratites = itemFood + "cookedCeratites";
    public static final String itemRawLiopleurodon = itemFood + "rawLiopleurodon";
    public static final String itemCookedLiopleurodon = itemFood + "cookedLiopleurodon";
    public static final String itemRawGigantoraptor = itemFood + "rawGigantoraptor";
    public static final String itemCookedGigantoraptor = itemFood + "cookedGigantoraptor";
    public static final String itemRawTyrannosaurus = itemFood + "rawTyrannosaurus";
    public static final String itemCookedTyrannosaurus = itemFood + "cookedTyrannosaurus";

    public static final String itemAraucarioxylonSeeds = mod + "araucarioxylonSeeds";
    public static final String itemZingiberopsisRhizome = itemSeeds + "zingiberopsisRhizome";

    // Buckets
    public static final String bucketKomatiiticLava = mod + "komatiiticLavaBucket";

    // --- Other ---
    // Materials
    public static final String materialGranite = "granite";
    public static final String materialRhyolite = "rhyolite";
    public static final String materialDolerite = "dolerite";
    public static final String materialQuartzite = "quartzite";
    public static final String materialBrownishFlint = "brownish_flint";

    // Containers
    public static final String container = "container." + mod;
    public static final String containerCampfire = container + "campfire";
    public static final String containerPolissoir = container + "polissoir";

    // Creative Tabs
    public static final String tabBlock = mod + "tabBlock";
    public static final String tabDecorations = mod + "tabDecorations";
    public static final String tabMisc = mod + "tabMisc";
    public static final String tabFood = mod + "tabFood";
    public static final String tabTools = mod + "tabTools";
    public static final String tabMaterials = mod + "tabMaterials";

    // --- More Plants ---
    public static final class Plants {
        public static final String ARCHAEOPTERIS = "archaeopteris";
        public static final String SIGILLARIA = "sigillaria";
        public static final String LEPIDODENDRON = "lepidodendron";
        public static final String CORDAITES = "cordaites";
        public static final String PSARONIUS = "psaronius";
        public static final String BJUVIA = "bjuvia";
        public static final String GLOSSOPTERIS = "glossopteris";
        public static final String ARAUCARIOXYLON = "araucarioxylon";
        public static final String VOLTZIA = "voltzia";
        public static final String DRYOPHYLLUM = "dryophyllum";
        public static final String CREDNERIA = "credneria";
        public static final List<String> TREE_TYPES = Arrays.asList(ARCHAEOPTERIS, SIGILLARIA, LEPIDODENDRON, CORDAITES, PSARONIUS, BJUVIA, GLOSSOPTERIS, ARAUCARIOXYLON, VOLTZIA, DRYOPHYLLUM, CREDNERIA);

        public static final String COOKSONIA = "cooksonia";
        public static final String BARAGWANATHIA = "baragwanathia";
        public static final String SCIADOPHYTON = "sciadophyton";
        public static final String PSILOPHYTON = "psilophyton";
        public static final String NOTHIA = "nothia";
        public static final String RHYNIA = "rhynia";
        public static final List<String> PLANT_TYPES = Arrays.asList(COOKSONIA, BARAGWANATHIA, SCIADOPHYTON, PSILOPHYTON, NOTHIA, RHYNIA);

        public static final String ZYGOPTERIS = "zygopteris";
        public static final String MATONIDIUM = "matonidium";
        public static final String ASTRALOPTERIS = "astralopteris";
        public static final String RUFFORDIA = "ruffordia";
        public static final List<String> FERN_TYPES = Arrays.asList(ZYGOPTERIS, MATONIDIUM, ASTRALOPTERIS, RUFFORDIA);

        public static final String MABELIA = "mabelia";
        public static final List<String> FLOWER_TYPES = Arrays.asList(MABELIA);

        public static final String MARGARETIA = "margaretia";
        public static final String BANGIOMORPHA = "bangiomorpha";
        public static final String MARPOLIA = "marpolia";
        public static final String DINOMISCHUS = "dinomischus";
        public static final String THAUMAPTILON = "thaumaptilon";
        public static final List<String> ALGAE_TYPES = Arrays.asList(MARGARETIA, BANGIOMORPHA, MARPOLIA, DINOMISCHUS, THAUMAPTILON);

        public static final String HELIOLITES = "heliolites";
        public static final String FAVOSITES = "favosites";
        public static final String HALYSITES = "halysites";
        public static final List<String> CORAL_TYPES = Arrays.asList(HELIOLITES, FAVOSITES, HALYSITES);

        public static final String VAUXIA = "vauxia";
        public static final String HAZELIA = "hazelia";
        public static final String WAPKIA = "wapkia";
        public static final String DIAGONIELLA = "diagoniella";
        public static final String PIRANIA = "pirania";
        public static final String CHANCELLORIA = "chancelloria";
        public static final List<String> SPONGE_TYPES = Arrays.asList(VAUXIA, HAZELIA, WAPKIA, DIAGONIELLA, PIRANIA, CHANCELLORIA);
    }

    // --- Registry Names (i.e. GameRegistry) ---
    public static final class Registry {
        public static final String blockTikiTorch = "tiki_torch";
        public static final String blockCampfire = "campfire";
        public static final String blockPolissoir = "polissoir";

        public static final String blockMoss = "moss";

        public static final String blockPortal = "portal";

        public static final String blockGranite = "granite";
        public static final String blockGraniteMossy = "mossy_granite";
        public static final String blockRhyolite = "rhyolite";
        public static final String blockDolerite = "dolerite";
        public static final String blockKomatiite = "komatiite";
        public static final String blockFauxAmphibolite = "fauxamphibolite";
        public static final String blockGneiss = "gneiss";
        public static final String blockQuartzite = "quartzite";
        public static final String blockOldRedSandstone = "old_red_sandstone";
        public static final String blockLimestone = "limestone";
        public static final String blockStromatolite = "stromatolite";
        public static final String blockShale = "shale";
        public static final String blockTrondhjemite = "trondhjemite";
        public static final String blockOctaedrite = "octaedrite";
        public static final String blockPermafrost = "permafrost";

        public static final String blockQuartzGraniteOre = "granite_quartz_ore";
        public static final String blockMalachiteOre = "malachite_ore";
        public static final String blockHematiteOre = "hematite_ore";
        public static final String blockZirconOre = "zircon_ore";
        public static final String blockGarnetOre = "garnet_ore";
        public static final String blockOlivineOre = "olivine_ore";
        public static final String blockManganeseOre = "manganese_ore";

        public static final String blockLogGenesis = "log";
        public static final String blockSaplingGenesis = "sapling";
        public static final String blockLeavesGenesis = "leaves";
        public static final String blockRottenLogGenesis = "rotten_log";
        public static final String blockBjuviaCone = "bjuvia_cone";

        public static final String blockPlant = "plant";
        public static final String blockZingiberopsis = "zingiberopsis";
        public static final String blockZingiberopsisTop = "zingiberopsis_top";
        public static final String blockSphenophyllum = "sphenophyllum";
        public static final String blockSphenophyllumTop = "sphenophyllum_top";
        public static final String blockFern = "fern";
        public static final String blockFlower = "flower";
        public static final String blockCalamitesPlant = "calamites";
        public static final String blockCalamitesBlock = "calamites_block";
        public static final String blockAsteroxylon = "asteroxylon";
        public static final String blockAsteroxylonTop = "asteroxylon_top";

        public static final String blockCoral = "coral";
        public static final String blockSponge = "sponge";
        public static final String blockAlgae = "algae";
        public static final String blockPteridinium = "pteridinium";
        public static final String blockCharnia = "charnia";
        public static final String blockCharniaTop = "charnia_top";

        public static final String itemZingiberopsisRhizome = "zingiberopsis_rhizome";
        public static final String itemAraucarioxylonSeeds = "araucarioxylon_seeds";
        public static final String itemResin = "resin";
        public static final String itemSphenoSpores = "sphenophyllum_spores";
        public static final String itemSphenoFiber = "sphenophyllum_fiber";
        public static final String itemBjuviaSeeds = "bjuvia_seeds";

        public static final String itemCeratitesShell = "ceratites_shell";
        public static final String itemArthopleuraChitin = "arthropleura_chitin";
        public static final String itemTyrannosaurasTooth = "tyrannosaurus_tooth";
        public static final String itemUtahraptorClaw = "utahraptor_claw";
        public static final String itemUtahraptorFeather = "utahraptor_feather";
        public static final String itemMegateuthisRostrum = "megateuthis_rostrum";
        public static final String itemEpidexipteryxFeather = "epidexipteryx_feather";
        public static final String itemLiopleurodonTooth = "liopleurodon_tooth";
        public static final String itemCynognathusHide = "cynognathus_hide";
        public static final String itemLeaellynasauraHide = "leaellynasaura_hide";
        public static final String itemPachyrhinosaurusHorn = "pachyrhinosaurus_horn";
        public static final String itemPuertasaurusRib = "puertasaurus_rib";
        public static final String itemShantungosaurusScapula = "shantungosaurus_scapula";
        public static final String itemGallimimusMetatarsus = "gallimimus_metatarsus";
        public static final String itemZircon = "zircon";
        public static final String itemBrownishFlintPebble = "brownish_flint_pebble";
        public static final String itemQuartz = "quartz";
        public static final String itemOlivine = "olivine";
        public static final String itemGarnet = "garnet";
        public static final String itemMalachite = "malachite";
        public static final String itemHematite = "hematite";
        public static final String itemManganese = "manganese";

        public static final String itemRawEryops = "raw_eryops";
        public static final String itemCookedEryops = "cooked_eryops";
        public static final String itemRawAphthoroblattina = "raw_aphthoroblattina";
        public static final String itemCookedAphthoroblattina = "cooked_aphthoroblattina";
        public static final String itemRawCeratites = "raw_ceratites";
        public static final String itemCookedCeratites = "cooked_ceratites";
        public static final String itemRawLiopleurodon = "raw_liopleurodon";
        public static final String itemCookedLiopleurodon = "cooked_liopleurodon";
        public static final String itemRawGigantoraptor = "raw_gigantoraptor";
        public static final String itemCookedGigantoraptor = "cooked_gigantoraptor";
        public static final String itemRawClimatius = "raw_climatius";
        public static final String itemCookedClimatius = "cooked_climatius";
        public static final String itemRawTyrannosaurus = "raw_tyrannosaurus";
        public static final String itemCookedTyrannosaurus = "cooked_tyrannosaurus";

        public static final String blockKomatiiticLava = "komatiitic_lava_block";
        public static final String bucketKomatiiticLava = "komatiitic_lava_bucket";
    }
}
