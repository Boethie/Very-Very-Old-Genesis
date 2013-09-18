package genesis.genesis.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static void init(File configFile){
		Configuration config = new Configuration(configFile);
		config.load();
		
		for (int set = 0; set < IDs.TREE_BLOCK_COUNT; set++)
		{
			try
			{
			IDs.blockLogID.setID(set,
					config.getBlock(Names.blockLogGenesis + set, IDs.blockLogID.getID(set)).getInt());
			
			IDs.blockSaplingID.setID(set,
					config.getBlock(Names.blockSaplingGenesis + set, IDs.blockSaplingID.getID(set)).getInt());
			
			IDs.blockLeavesID.setID(set,
					config.getBlock(Names.blockLeavesGenesis + set, IDs.blockLeavesID.getID(set)).getInt());
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}
		}
		
		config.save();
	}

}
