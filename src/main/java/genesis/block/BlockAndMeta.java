package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockAndMeta {
	
	public int blockID;
	public int metadata;
	
	public BlockAndMeta(int blockID, int metadata)
	{
		this.blockID = blockID;
		this.metadata = metadata;
	}
	
	public ItemStack getStack() {
		return new ItemStack(blockID, 1, Block.blocksList[blockID].damageDropped(metadata));
	}
	
}
