package genesis.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class CommonProxy {

    public void registerRenderers() {}

    public void preInit() {}

    public void init() {}

    public void registerBlock(Block block, String name, Class itemClass) {
        registerBlock(block, name, itemClass, new Object[0]);
    }

    public void registerBlock(Block block, String name, Class itemClass, Object[] itemClassArgs) {
        GameRegistry.registerBlock(block, itemClass, Genesis.MOD_ID + "." + name, Genesis.MOD_ID, itemClassArgs);
    }

    public boolean areLeavesOpaque() {
        return false;
    }

}
