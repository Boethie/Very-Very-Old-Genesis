package genesis.genesis.item.itemblock;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.plants.PlantBlocks;
import genesis.genesis.block.trees.IBlockGenesisTrees;
import genesis.genesis.block.trees.TreeBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisPlant extends ItemBlockWithMetadata {

	public ItemBlockGenesisPlant(int itemID, Block block) {
		super(itemID, block);
	}
	
	public String getUnlocalizedName(ItemStack stack)
    {
		Block block = Block.blocksList[stack.itemID];
		
		return block.getUnlocalizedName() + "." + PlantBlocks.flowerTypes.get(getMetadata(stack.getItemDamage()));
    }
	
	public int getMetadata(int damage)
    {
          return damage & 15;
    }

}
