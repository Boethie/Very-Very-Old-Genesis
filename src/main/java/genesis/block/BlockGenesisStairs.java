package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

import genesis.common.Genesis;

public class BlockGenesisStairs extends BlockStairs {

	public BlockGenesisStairs(Block modelBlock, int metadata) {
		super(modelBlock, metadata);
		
		if (modelBlock.getMaterial() == Material.wood)
			//setBurnProperties(blockID, 5, 5);
		
		setLightOpacity(0);
		setCreativeTab(Genesis.tabGenesis);
	}
}
