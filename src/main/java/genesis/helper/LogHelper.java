package genesis.helper;

import genesis.Genesis;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.FMLCommonHandler;

public class LogHelper {

    private static final Logger logger = LogManager.getLogger(Genesis.MOD_ID);

    public static void init() {
        // logger.setParent(FMLLog.getLogger());

        logger.info("Starting Project Genesis " + Genesis.MOD_VERSION);
    }

    public static void log(Level logLevel, String message) {
        logger.log(logLevel, message);
    }

    // For debugging
    public static void log(String message) {
        log(Level.TRACE, "[" + FMLCommonHandler.instance().getEffectiveSide() + "] " + message);
    }
}
