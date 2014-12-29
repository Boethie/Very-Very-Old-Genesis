package genesis.block.plants.aquatic;

import net.minecraft.block.material.Material;

/**
 * @author Arbiter
 */
public class BlockPteridinium extends BlockGenesisAquaticPlant {
    public BlockPteridinium() {
        super(Material.water);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
    }
}