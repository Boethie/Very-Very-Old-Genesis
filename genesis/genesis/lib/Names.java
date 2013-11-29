package genesis.genesis.lib;

import genesis.genesis.common.Genesis;

public class Names {
	
	public static final String mod = Genesis.MOD_ID + ".";

	// ---- Blocks ----
	public static final String blockStorage = mod + "storage.";
	public static final String blockTikiTorch = mod + "tikiTorch";
	
	// Moss
	public static final String blockMoss = mod + "moss";
	
	// Rock blocks
	public static final String blockRock = mod + "rock.";
	public static final String blockGranite = blockRock + "granite";
	public static final String blockGraniteMossy = blockRock + "graniteMossy";
	public static final String blockLimestone = blockRock + "limestone";
	public static final String blockStromatolite = blockRock + "stromatolite";
	
	// Ores
	public static final String blockOre = mod + "ore.";
	
	public static final String blockZirconOre = blockOre + "zircon";
	public static final String blockZircon = blockStorage + "zircon";
	
	public static final String blockQuartzGraniteOre = blockOre + "quartzGranite";
	
	public static final String blockOlivineOre = blockOre + "olivine";
	public static final String blockOlivine = blockStorage + "olivine";
	
	// Trees
	public static final String blockLogGenesis = mod + "log.";
	public static final String blockSaplingGenesis = mod + "sapling.";
	public static final String blockLeavesGenesis = mod + "leaves.";
	public static final String blockWoodGenesis = mod + "wood.";
	public static final String blockStairsGenesis = mod + "stairs.";
	public static final String blockRottenLogGenesis = mod + "logRotten.";
	
	// Plants
	public static final String blockPlant = mod + "plant.";
	public static final String blockFlowerPot = blockPlant + "flowerpot";
	
	public static final String blockCalamitesPlant = blockPlant + "calamites";
	public static final String blockCalamites = blockStorage + "calamites";
	
	public static final String blockNeuropterisPlant = blockPlant + "neuropteris";
	
	public static final String blockFlower = blockPlant + "flower";
	
	// ---- Items ----
	// Crafting items
	public static final String itemZircon = mod + "zircon";
	public static final String itemZirconMaterial = "zircon";
	
	public static final String itemQuartz = mod + "quartz";
	
	public static final String itemOlivine = mod + "olivine";
	public static final String itemOlivineMaterial = "olivine";

	// Swords and tools
	public static final String itemTool = mod + "tool.";
	public static final String itemSword = mod + "sword.";
	public static final String itemPickaxe = itemTool + "pickaxe.";
	public static final String itemAxe = itemTool + "axe.";
	public static final String itemSpade = itemTool + "shovel.";
	public static final String itemHoe = itemTool + "hoe.";
	
	// Armor
	public static final String itemArmor = mod + "armor.";
	public static final String[] itemArmorTypes = {"helmet.", "chestplate.", "leggings.", "boots."};
	
}
