package genesis.managers;

import genesis.Genesis;
import genesis.entity.EntityEryops;
import genesis.lib.Names;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static void init() {
        registerEntity(EntityEryops.class, "Eryops");
        EntityRegistry.addSpawn(EntityEryops.class, 5, 2, 2, EnumCreatureType.monster, BiomeGenBase.beach);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String name) {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, Names.mod + name, entityID, Genesis.instance, 64, 3, false);

        // Add spawner egg's temporary, just for development.
        long seed = name.hashCode();
        Random random = new Random(seed);
        int primaryColor = random.nextInt() * 16777215;
        int SecondaryColor = random.nextInt() * 16777215;
        EntityList.entityEggs.put(entityID, new EntityList.EntityEggInfo(entityID, primaryColor, SecondaryColor));
    }
}
