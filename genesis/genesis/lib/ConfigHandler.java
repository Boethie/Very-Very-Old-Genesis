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
			IDs.blockLogGenesisID.setID(set,
					config.getBlock(Names.blockLogGenesis_name + set, IDs.blockLogGenesisID.getID(set)).getInt());
			
			IDs.blockSaplingGenesisID.setID(set,
					config.getBlock(Names.blockSaplingGenesis_name + set, IDs.blockSaplingGenesisID.getID(set)).getInt());
			
			IDs.blockLeavesGenesisID.setID(set,
					config.getBlock(Names.blockLeavesGenesis_name + set, IDs.blockLeavesGenesisID.getID(set)).getInt());
			}
			catch (Throwable e)
			{
				e.printStackTrace();
			}
		}
		
		config.save();
	}

}
