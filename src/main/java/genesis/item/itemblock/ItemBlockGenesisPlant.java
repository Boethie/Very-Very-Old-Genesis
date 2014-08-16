package genesis.item.itemblock;

import genesis.block.plants.PlantBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockGenesisPlant extends ItemBlockWithMetadata {

    public ItemBlockGenesisPlant(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        Block block = Block.getBlockFromItem(stack.getItem());
        return block.getUnlocalizedName() + PlantBlocks.plantTypes.get(getMetadata(stack.getItemDamage()));
    }

    @Override
    public int getMetadata(int damage) {
        return damage & 15;
    }

}
