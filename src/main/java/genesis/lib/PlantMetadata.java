package genesis.lib;

import java.util.Arrays;
import java.util.List;

/**
 * A storage class for all of the names and lists for
 * all of the plants that have metadata in the mod.<br /><br />
 * To free and tidy up PlantBlocks.class
 *
 * @author Arbiter
 */
public final class PlantMetadata {
    public static final String COOK_NAME = "cooksonia", BARA_NAME = "baragwanathia", SCIA_NAME = "sciadophyton", PSILO_NAME = "psilophyton", NOTHIA_NAME = "nothia", RHYNIA_NAME = "rhynia";
    public static final List<String> plantTypes = Arrays.asList(COOK_NAME, BARA_NAME, SCIA_NAME, PSILO_NAME, NOTHIA_NAME, RHYNIA_NAME);

    public static final String ZYGO_NAME = "zygopteris", MATON_NAME = "matonidium", ASTRA_NAME = "astralopteris", RUFF_NAME = "ruffordia", HAUS_NAME = "hausmannia";
    public static final List<String> fernTypes = Arrays.asList(ZYGO_NAME, MATON_NAME, ASTRA_NAME, RUFF_NAME, HAUS_NAME);

    public static final String HELIO_NAME = "heliolites", FAVOS_NAME = "favosites", HALY_NAME = "halysites";
    public static final List<String> coralTypes = Arrays.asList(HELIO_NAME, FAVOS_NAME, HALY_NAME);

    public static final String VAUXIA_NAME = "vauxia", HAZELIA_NAME = "hazelia", WAPKIA_NAME = "wapkia", DIAG_NAME = "diagoniella", PIR_NAME = "pirania", CHANCE_NAME = "chancelloria";
    public static final List<String> spongeTypes = Arrays.asList(VAUXIA_NAME, HAZELIA_NAME, WAPKIA_NAME, DIAG_NAME, PIR_NAME, CHANCE_NAME);

    public static final String MARGAR_NAME = "margaretia", BANGI_NAME = "bangiomorpha", DINOM_NAME = "dinomischus", THAUM_NAME = "thaumaptilon";
    public static final List<String> algaeTypes = Arrays.asList(MARGAR_NAME, BANGI_NAME, DINOM_NAME, THAUM_NAME);

    public static final String ARCH_NAME = "archaeopteris", SIGIL_NAME = "sigillaria", LEPID_NAME = "lepidodendron", CORD_NAME = "cordaites", PSAR_NAME = "psaronius", BJUVIA_NAME = "bjuvia", GLOSSO_NAME = "glossopteris", ARAUC_NAME = "araucarioxylon";
    public static final List<String> treeTypes = Arrays.asList(ARCH_NAME, SIGIL_NAME, LEPID_NAME, CORD_NAME, PSAR_NAME, BJUVIA_NAME, GLOSSO_NAME, ARAUC_NAME);

    public static final String MABELIA_NAME = "mabelia";
    public static final List<String> flowerTypes = Arrays.asList(MABELIA_NAME);
}