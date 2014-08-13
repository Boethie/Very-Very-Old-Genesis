package genesis.entity;

import genesis.common.Genesis;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void init()
	{
		EntityRegistry.registerModEntity(EntityEryops.class, Genesis.MOD_ID + ".Eryops", 0, Genesis.instance, 3, 1, false);
	}
}
