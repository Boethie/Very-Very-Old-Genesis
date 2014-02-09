package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockAndMeta {
	
	public Block block;
	public int metadata;
	
	public BlockAndMeta(Block block, int metadata)
	{
		this.block = block;
		this.metadata = metadata;
	}
	
	public ItemStack getStack() {
		return new ItemStack(block, 1, block.damageDropped(metadata));
	}
	
}
