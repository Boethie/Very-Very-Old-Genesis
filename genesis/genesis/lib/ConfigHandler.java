package genesis.genesis.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static void init(File configFile){
		Configuration config = new Configuration(configFile);
		config.load();
		Ids.blockLogGenesisID_actual = config.getBlock(Names.blockLogGenesis_name, Ids.blockLogGenesisID_default).getInt();
		Ids.blockLogGenesis1ID_actual = config.getBlock(Names.blockLogGenesis1_name, Ids.blockLogGenesis1ID_default).getInt();
		
		Ids.blockSaplingGenesisID_actual = config.getBlock(Names.blockSaplingGenesis_name, Ids.blockSaplingGenesisID_default).getInt();
		Ids.blockSaplingGenesis1ID_actual = config.getBlock(Names.blockSaplingGenesis1_name, Ids.blockSaplingGenesis1ID_default).getInt();
		
		Ids.blockLeavesGenesisID_actual = config.getBlock(Names.blockLeavesGenesis_name, Ids.blockLeavesGenesisID_default).getInt();
		Ids.blockLeavesGenesis1ID_actual = config.getBlock(Names.blockLeavesGenesis1_name, Ids.blockLeavesGenesis1ID_default).getInt();
		config.save();
	}

}
