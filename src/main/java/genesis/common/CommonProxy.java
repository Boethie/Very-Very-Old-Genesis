package genesis.common;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StringUtils;

public class CommonProxy {
    public void preInit() {
        registerRenderers();
    }

    public void registerRenderers() {}

    public void init() {}

    public void registerBlock(Block block, String name, Class<? extends ItemBlock> itemClass) {
        registerBlock(block, name, itemClass, new Object[0]);
    }

    public void registerBlock(Block block, String name, Class<? extends ItemBlock> itemClass, Object[] itemClassArgs) {
        GameRegistry.registerBlock(block, itemClass, name, itemClassArgs);
    }

    public boolean areLeavesOpaque() {
        return false;
    }
    
    public void playSound(double x, double y, double z, String sound, float volume, float frequency) {
    	// Nothing if server-side.
    }
}
