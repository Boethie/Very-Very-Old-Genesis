package genesis.block;

import net.minecraft.block.material.Material;

import net.minecraftforge.common.MinecraftForge;

public class BlockGenesisRock extends BlockGenesis {

	public BlockGenesisRock(int blockID, Material mat, int harvestLevel) {
		super(blockID, mat);
		
        setStepSound(soundStoneFootstep);
        
        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", harvestLevel);
	}
}
