package genesis.genesis.item.itemblock;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;

public class ItemGenesisWoodSlab extends ItemSlab {

	private static BlockHalfSlab singleSlab;
	private static BlockHalfSlab doubleSlab;
	
	public static void setSlabs(BlockHalfSlab singleSlab, BlockHalfSlab doubleSlab) {
		ItemGenesisWoodSlab.singleSlab = singleSlab;
		ItemGenesisWoodSlab.doubleSlab = doubleSlab;
	}
	
	public ItemGenesisWoodSlab(int id) {
		super(id, singleSlab, doubleSlab, id == doubleSlab.blockID);
	}
}
