package genesis.block;

import net.minecraft.block.material.Material;

import net.minecraftforge.common.MinecraftForge;

public class BlockGenesisRock extends BlockGenesis {

	public BlockGenesisRock(Material material, int harvestLevel) {
		super(material);

		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", harvestLevel, 0);
	}
}
