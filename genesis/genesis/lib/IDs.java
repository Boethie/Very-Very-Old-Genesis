package genesis.genesis.lib;

public class IDs {
	
	public static class IDSet {
		int[] ids = null;
		
		public IDSet(Object... args) {
			ids = new int[args.length];
			
			int i = 0;
			
			for (Object objID : args) {
				ids[i] = (Integer)objID;
				i++;
			}
		}
		
		/* TODO: Currently unused private method - consider removing or making public
		private void expand() {
			int[] newIDs;
			
			if (ids != null)
				newIDs = new int[ids.length + 1];
			else
				newIDs = new int[1];
			
			int i = 0;
			
			for (int id : ids) {
				newIDs[i] = id;
				i++;
			}
			
			ids = newIDs;
		}
		*/
		
		public int getIDCount() {
			return ids.length;
		}
		
		public void setID(int index, int id) {
			ids[index] = id;
		}
		
		public int getID(int index) {
			return ids[index];
		}
	}

	// ---- Blocks ----
	public static int blockMossID = 2171;
	
	// Rocks
	public static int blockGraniteID = 2172;
	public static int blockGraniteMossyID = 2173;
	public static int blockLimestoneID = 2174;
	public static int blockGneissID = 2175;
	public static int blockSchistID = 2176;
	public static int blockStromatoliteID = 2178;
	
	public static int blockPermafrostID = 2180;
	
	// Ores and ore storage
	public static int blockZirconOreID = 2181;
	public static int blockZirconID = 2182;
	
	public static int blockQuartzGraniteOreID = 2183;
	
	public static int blockOlivineOreID = 2184;
	public static int blockOlivineID = 2185;
	
	public static int blockGarnetOreID = 2186;
	
	public static int blockMalachiteOreID = 2188;
	public static int blockMalachiteID = 2189;
	
	public static int blockIronMeteoriteID = 2177;
	public static int blockStorageIronMeteoriteID = 2179;
	
	public static int blockChalcopyriteOreID = 2204;
	public static int blockChalcopyriteID = 2205;
	
	// Trees
	public static final int TREE_ID_SET_SIZE = 2;
	
	public static IDSet blockLogID = new IDSet(2190, 2191);
	public static IDSet blockSaplingID = new IDSet(2193, 2194);
	public static IDSet blockLeavesID = new IDSet(2196, 2197);
	
	public static IDSet blockWoodID = new IDSet(2400, 2401);
	public static IDSet blockSlabID = new IDSet(2403, 2404);
	public static IDSet blockDoubleSlabID = new IDSet(2406, 2407);
	public static IDSet blockRottenLogID = new IDSet(2409, 2410);
	
	public static int blockStairsStartID = 2412;
	
	// Plants
	public static int blockFlowerPotID = 2199;
	
	public static int blockCalamitesPlantID = 2200;
	public static int blockCalamitesBlockID = 2201;
	
	public static int blockFlowerID = 2203;
	
	// Special
	public static int blockCampfireID = 2210;
	public static int blockTikiTorchID = 2219;
	
	// ---- Items ----
	// Ore items, tools (6 IDs) and armors (4 IDs)
	public static int itemsGraniteToolsStartID = 2225;
	
	public static int itemZirconID = 2231;
	public static int itemsZirconToolsStartID = 2232;
	public static int itemsZirconArmorStartID = 2238;
	
	public static int itemQuartzID = 2242;
	
	public static int itemOlivineID = 2243;
	public static int itemsOlivineToolsStartID = 2244;
	public static int itemsOlivineArmorStartID = 2250;
	
	public static int itemMeteoricIronIngotID = 2254;
	public static int itemsMeteoricIronToolsStartID = 2255;
	public static int itemsMeteoricIronArmorStartID = 2261;
	
	public static int itemGarnetID = 2265;
	
	public static int itemMalachiteID = 2266;
	
	public static int itemChalcopyriteIngotID = 2267;
	public static int itemsChalcopyriteToolsStartID = 2268;
	//public static int itemsChalcopyriteArmorStartID = 2274;
	
}
