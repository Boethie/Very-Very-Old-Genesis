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
	
	public static IDSet blockLogGenesisID = new IDSet(422, 423);
	public static IDSet blockSaplingGenesisID = new IDSet(424, 425);
	public static IDSet blockLeavesGenesisID = new IDSet(426, 427);
	
}
