package genesis.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import genesis.common.Genesis;
import genesis.lib.Names;

public class ModEntities {
    public static void init() {
        EntityRegistry.registerModEntity(EntityEryops.class, Names.mod + "Eryops", 0, Genesis.instance, 3, 1, false);
    }
}
