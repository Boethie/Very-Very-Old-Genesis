package genesis.block;

import net.minecraft.block.material.Material;

public class BlockGenesisRockOres extends BlockGenesisRock {
    public BlockGenesisRockOres(int harvestLevel) {
        super(Material.rock, harvestLevel);
    }

    public BlockGenesisRockOres(Material material, int harvestLevel) {
        super(material, harvestLevel);
    }
}
