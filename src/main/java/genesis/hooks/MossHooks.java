package genesis.hooks;

import genesis.block.BlockMoss;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityDiggingFX;

public class MossHooks {

    public static boolean handleParticleColors(EntityDiggingFX fx, Block block, int side) {
        if (block instanceof BlockMoss && side != 1)
            return false;

        return true;
    }

    public static boolean shouldGrasslikeRender(Block block, boolean likeGrass) {
        if (block instanceof BlockMoss)
            likeGrass = true;

        return likeGrass;
    }

}
