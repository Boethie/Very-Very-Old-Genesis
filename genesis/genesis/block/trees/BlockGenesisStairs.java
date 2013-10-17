package genesis.genesis.block.trees;

import genesis.genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

public class BlockGenesisStairs extends BlockStairs {

	public BlockGenesisStairs(int blockID, Block modelBlock, int metadata) {
		super(blockID, modelBlock, metadata);
		
		if (modelBlock.blockMaterial == Material.wood)
			setBurnProperties(blockID, 5, 5);
		
		setCreativeTab(Genesis.tabGenesis);
	}
}
