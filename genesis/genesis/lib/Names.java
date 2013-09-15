package genesis.genesis.lib;

public class Names {
	
	public static final String mod_unloc = "genesis.";

	// ---- Blocks ----
	public static final String blockStorage_unloc = mod_unloc + "storage.";
	// Moss
	public static final String blockMoss_unloc = mod_unloc + "moss";
	
	// Rock blocks
	public static final String blockRock_unloc = mod_unloc + "rock.";
	public static final String blockGranite_unloc = blockRock_unloc + "granite";
	public static final String blockGraniteMossy_unloc = blockRock_unloc + "graniteMossy";
	public static final String blockLimestone_unloc = blockRock_unloc + "limestone";
	public static final String blockStromatolite_unloc = blockRock_unloc + "stromatolite";
	
	// Ores
	public static final String blockOre_unloc = mod_unloc + "ore.";
	
	public static final String blockZirconOre_unloc = blockOre_unloc + "zircon";
	public static final String blockZircon_unloc = blockStorage_unloc + "zircon";
	
	public static final String blockQuartzGraniteOre_unloc = blockOre_unloc + "quartzGranite";
	
	public static final String blockOlivineOre_unloc = blockOre_unloc + "olivine";
	public static final String blockOlivine_unloc = blockStorage_unloc + "olivine";
	
	// Trees
	public static final String blockLogGenesis_unloc = mod_unloc + "log.";
	public static final String blockSaplingGenesis_unloc = mod_unloc + "sapling.";
	public static final String blockLeavesGenesis_unloc = mod_unloc + "leaves.";
	
	// Plants
	public static final String blockPlant_unloc = mod_unloc + "plant.";
	public static final String blockCalamitesPlant_unloc = blockPlant_unloc + "calamites";
	public static final String blockCalamites_unloc = blockStorage_unloc + "calamites";
	
	// ---- Items ----
	// Crafting items
	public static final String itemZircon_unloc = mod_unloc + "zircon";
	public static final String itemQuartz_unloc = mod_unloc + "quartz";
	public static final String itemOlivine_unloc = mod_unloc + "olivine";
	
	// Armor
	public static final String itemArmor_unloc = mod_unloc + "armor.";
	public static final String[] itemArmorTypes_unloc = {"helmet.", "chestplate.", "leggings.", "boots."};
	public static final String itemZirconArmorMaterial_unloc = "zircon";
	
}
