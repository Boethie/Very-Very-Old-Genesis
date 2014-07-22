package genesis.lib;

import java.util.ArrayList;

/**
 * A storage class for all of the names and arraylists for
 * all of the plants that have metadata in the mod.<br /><br />
 * To free and tidy up PlantBlocks.class
 * @author Arbiter
 *
 */
public final class PlantMetadata
{
	public static final String COOK_NAME = "cooksonia", BARA_NAME = "baragwanathia", 
			SCIA_NAME = "sciadophyton", PSILO_NAME = "psilophyton", NOTHIA_NAME = "nothia",
			RHYNIA_NAME = "rhynia";
	
	public static final String ZYGO_NAME = "zygopteris", MATON_NAME = "matonidium", 
			ASTRA_NAME = "astralopteris", RUFF_NAME = "ruffordia", HAUS_NAME = "hausmannia";
	
	public static final String HELIO_NAME = "heliolites", FAVOS_NAME = "favosites", 
			HALY_NAME = "halysites";
	
	public static final String VAUXIA_NAME = "vauxia", HAZELIA_NAME = "hazelia", 
			WAPKIA_NAME = "wapkia", DIAG_NAME = "diagoniella", PIR_NAME = "pirania";
	
	public static final String MARGAR_NAME = "margaretia", BANGI_NAME = "bangiomorpha";
	
	public static final String ARCH_NAME = "archaeopteris", SIGIL_NAME = "sigillaria",
			LEPID_NAME = "lepidodendron", CORD_NAME = "cordaites", PSAR_NAME = "psaronius",
			BJUVIA_NAME = "bjuvia", GLOSSO_NAME = "glossopteris", ARAUC_NAME = "auracarioxylon";

	public static final ArrayList<String> plantTypes = new ArrayList() {
		{
			add(COOK_NAME);
			add(BARA_NAME);
			add(SCIA_NAME);
			add(PSILO_NAME);
			add(NOTHIA_NAME);
			add(RHYNIA_NAME);
		}
	};
	
	public static final ArrayList<String> fernTypes = new ArrayList() {
		{
			add(ZYGO_NAME);
			add(MATON_NAME);
			add(ASTRA_NAME);
			add(RUFF_NAME);
			add(HAUS_NAME);
		}
	};
	
	public static final ArrayList<String> coralTypes = new ArrayList() {
		{
			add(HELIO_NAME);
			add(FAVOS_NAME);
			add(HALY_NAME);
		}
	};
	
	public static final ArrayList<String> spongeTypes = new ArrayList() {
		{
			add(VAUXIA_NAME);
			add(HAZELIA_NAME);
			add(WAPKIA_NAME);
			add(DIAG_NAME);
			add(PIR_NAME);
		}
	};
	
	public static final ArrayList<String> algaeTypes = new ArrayList() {
		{
			add(MARGAR_NAME);
			add(BANGI_NAME);
		}
	};
	
	public static final ArrayList<String> treeTypes = new ArrayList() {
		{
			add(ARCH_NAME);
			add(SIGIL_NAME);
			add(LEPID_NAME);
			add(CORD_NAME);
			add(PSAR_NAME);
			add(BJUVIA_NAME);
			add(GLOSSO_NAME);
			add(ARAUC_NAME);
		}
	};
}