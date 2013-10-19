package genesis.genesis.lib;

public class IDs {
	
	public static class IDSet {
		int[] ids = null;
		
		public IDSet(Object... args)
		{
			ids = new int[args.length];
			
			int i = 0;
			
			for (Object objID : args)
			{
				ids[i] = (Integer)objID;
				i++;
			}
		}
		
		private void expand()
		{
			int[] newIDs;
			
			if (ids != null)
			{
				newIDs = new int[ids.length + 1];
			}
			else
			{
				newIDs = new int[1];
			}
			
			int i = 0;
			
			for (int id : ids)
			{
				newIDs[i] = id;
				i++;
			}
			
			ids = newIDs;
		}
		
		public int getIDCount()
		{
			return ids.length;
		}
		
		public void setID(int index, int id)
		{
			ids[index] = id;
		}
		
		public int getID(int index)
		{
			return ids[index];
		}
	}

	public static final int TREE_BLOCK_COUNT = 2;

	// ---- Blocks ----
	public static int blockMossID = 2172;
	
	// Rocks
	public static int blockGraniteID = 2171;
	public static int blockGraniteMossyID = 2173;
	public static int blockLimestoneID = 2174;
	
	public static int blockStromatoliteID = 2176;
	
	// Ores and ore storage
	public static int blockZirconOreID = 2181;
	public static int blockZirconID = 2182;
	
	public static int blockQuartzGraniteOreID = 2183;
	
	public static int blockOlivineOreID = 2184;
	public static int blockOlivineID = 2185;

	public static int blockHematiteOreID = 2186;
	public static int blockHematiteID = 2187;
	
	// Trees
	public static IDSet blockLogID = new IDSet(2190, 2191);
	public static IDSet blockSaplingID = new IDSet(2193, 2194);
	public static IDSet blockLeavesID = new IDSet(2196, 2197);
	public static IDSet blockWoodID = new IDSet(2400, 2401);
	public static int blockStairsStartID = 2403;
	
	// Plants
	public static int blockFlowerPotID = 2199;
	
	public static int blockCalamitesPlantID = 2200;
	public static int blockCalamitesBlockID = 2201;
	
	public static int blockNeuropterisPlantID = 2202;
	
	// Special
	public static int blockTikiTorchID = 2219;
	
	// ---- Items ----
	// Ore items, tools (5 IDs) and armors (4 IDs)
	public static int itemZirconID = 2220;
	public static int itemsZirconToolsStartID = 2221;
	public static int itemsZirconArmorStartID = 2226;
	
	public static int itemQuartzID = 2230;
	
	public static int itemOlivineID = 2231;
	public static int itemsOlivineToolsStartID = 2232;
	public static int itemsOlivineArmorStartID = 2238;
	
	public static int itemHematiteID = 2239;
	public static int itemsHematiteToolsStartID = 2240;
	public static int itemsHematiteArmorStartID = 2245;
	
}
