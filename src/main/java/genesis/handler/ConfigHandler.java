package genesis.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

    public static void init(File configFile) {
        Configuration config = new Configuration(configFile);
        config.load();

        /*
        for (int i = 0; i < IDs.TREE_ID_SET_SIZE; i++) {
            try {
                IDs.blockLogID.setID(i, config.getBlock(Names.blockLogGenesis + i, IDs.blockLogID.getID(i)).getInt());
                IDs.blockSaplingID.setID(i, config.getBlock(Names.blockSaplingGenesis + i, IDs.blockSaplingID.getID(i)).getInt());
                IDs.blockLeavesID.setID(i, config.getBlock(Names.blockLeavesGenesis + i, IDs.blockLeavesID.getID(i)).getInt());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
         */

        config.save();
    }
}
