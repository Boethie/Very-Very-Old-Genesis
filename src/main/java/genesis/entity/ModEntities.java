package genesis.entity;

import genesis.common.Genesis;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void init()
	{
		EntityRegistry.registerModEntity(EntityEryops.class, "EntityEryops", 0, Genesis.instance, 3, 1, false);
	}
}
