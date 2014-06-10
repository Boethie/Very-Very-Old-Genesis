package genesis.item.itemblock;

import genesis.block.plants.PlantBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Arbiter
 *
 */
public class ItemBlockGenesisSponge extends ItemBlockWithMetadata
{
	public ItemBlockGenesisSponge(Block block)
	{
		super(block, block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta % 15;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		Block b = Block.getBlockFromItem(item.getItem());
		return b.getUnlocalizedName() + PlantBlocks.spongeTypes.get(getMetadata(item.getItemDamage()));
	}
}