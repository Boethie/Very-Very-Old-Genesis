package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

import genesis.common.Genesis;

public class BlockGenesisStairs extends BlockStairs {

	public BlockGenesisStairs(int blockID, Block modelBlock, int metadata) {
		super(blockID, modelBlock, metadata);
		
		if (modelBlock.blockMaterial == Material.wood)
			setBurnProperties(blockID, 5, 5);
		
		setLightOpacity(0);
		setCreativeTab(Genesis.tabGenesis);
	}
}
