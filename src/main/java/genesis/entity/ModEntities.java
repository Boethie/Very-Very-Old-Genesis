package genesis.entity;

import genesis.common.Genesis;
import genesis.lib.Names;

import java.util.Random;

import net.minecraft.entity.EntityList;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void init() {
		ModEntities.registerEntity(EntityEryops.class, "Eryops");
	}

	private static void registerEntity(Class entityClass, String name) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, Names.mod + name, entityID, Genesis.instance, 64, 3, false);
		
		// Add spawner egg's temporary, just for development.
		long seed = name.hashCode();
		Random random = new Random(seed);
		int primaryColor = random.nextInt() * 16777215;
		int SecondaryColor = random.nextInt() * 16777215;
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, SecondaryColor));
	}
}
