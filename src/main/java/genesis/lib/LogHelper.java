package genesis.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

import genesis.common.Genesis;

public class LogHelper {

	private static final Logger logger = Logger.getLogger(Genesis.MOD_ID);

	public static void init() {
		// logger.setParent(FMLLog.getLogger());
		
		logger.info("Starting Project Genesis " + Genesis.MOD_VERSION);
	}

	public static void log(Level logLevel, String message) {
		logger.log(logLevel, message);
	}

	// For debugging
	public static void log(String message) {
		log(Level.FINE, "[" + FMLCommonHandler.instance().getEffectiveSide() + "] " + message);
	}

}
