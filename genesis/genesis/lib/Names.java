package genesis.genesis.lib;

import genesis.genesis.common.Genesis;

public class Names {
	
	public static final String mod = Genesis.MOD_ID + ".";

	// ---- Blocks ----
	public static final String blockStorage = mod + "storage.";
	
	public static final String blockTikiTorch = mod + "tikiTorch";
	public static final String blockCampfire = mod + "campfire";
	
	// Moss
	public static final String blockMoss = mod + "moss";
	
	// Rock blocks
	public static final String blockRock = mod + "rock.";
	public static final String blockGranite = blockRock + "granite";
	public static final String blockGraniteMossy = blockRock + "graniteMossy";
	public static final String blockLimestone = blockRock + "limestone";
	public static final String blockGneiss = blockRock + "gneiss";
	public static final String blockSchist = blockRock + "schist";
	public static final String blockIronMeteorite = blockRock + "ironMeteorite";
	public static final String blockStromatolite = blockRock + "stromatolite";
	
	public static final String blockPermafrost = mod + "permafrost";
	
	// Ores
	public static final String blockOre = mod + "ore.";
	
	public static final String blockZirconOre = blockOre + "zircon";
	public static final String blockZircon = blockStorage + "zircon";
	
	public static final String blockQuartzGraniteOre = blockOre + "quartzGranite";
	
	public static final String blockOlivineOre = blockOre + "olivine";
	public static final String blockOlivine = blockStorage + "olivine";
	
	public static final String blockGarnetOre = blockOre + "garnet";
	
	// Trees
	public static final String blockLogGenesis = mod + "log.";
	public static final String blockSaplingGenesis = mod + "sapling.";
	public static final String blockLeavesGenesis = mod + "leaves.";
	public static final String blockWoodGenesis = mod + "wood.";
	public static final String blockSlabGenesis = mod + "slab.";
	public static final String blockRottenLogGenesis = mod + "logRotten.";
	public static final String blockStairsGenesis = mod + "stairs.";
	
	// Plants
	public static final String blockPlant = mod + "plant.";
	public static final String blockFlowerPot = blockPlant + "flowerpot";
	
	public static final String blockCalamitesPlant = blockPlant + "calamites";
	public static final String blockCalamites = blockStorage + "calamites";
	
	public static final String blockFlower = blockPlant + "flower";
	
	// ---- Items ----
	// Crafting items
	public static final String itemGraniteMaterial = "granite";
	
	public static final String itemZircon = mod + "zircon";
	public static final String itemZirconMaterial = "zircon";
	
	public static final String itemQuartz = mod + "quartz";
	
	public static final String itemOlivine = mod + "olivine";
	public static final String itemOlivineMaterial = "olivine";
	
	public static final String itemMeteoricIronIngot = mod + "meteoricIronIngot";
	public static final String itemMeteoricIronMaterial = "meteoricIron";
	
	public static final String itemGarnet = mod + "garnet";

	// Swords and tools
	public static final String itemTool = mod + "tool.";
	public static final String itemSword = mod + "sword.";
	public static final String itemDagger = mod + "dagger.";
	public static final String itemPickaxe = itemTool + "pickaxe.";
	public static final String itemAxe = itemTool + "axe.";
	public static final String itemSpade = itemTool + "shovel.";
	public static final String itemHoe = itemTool + "hoe.";
	
	// Armor
	public static final String itemArmor = mod + "armor.";
	public static final String[] itemArmorTypes = {"helmet.", "chestplate.", "leggings.", "boots."};
	
}
