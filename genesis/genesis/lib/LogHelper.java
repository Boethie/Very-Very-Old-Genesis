package genesis.genesis.lib;

import genesis.genesis.common.Genesis;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
	private static Logger logger = Logger.getLogger(Genesis.modid);
	public static void init(){
		logger.setParent(FMLLog.getLogger());
	}
	public static void log(Level logLevel, String message){
		logger.log(logLevel, message);
	}

}
