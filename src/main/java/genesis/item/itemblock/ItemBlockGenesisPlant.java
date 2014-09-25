package genesis.item.itemblock;

import genesis.lib.Names;
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
        return block.getUnlocalizedName() + Names.Plants.PLANT_TYPES.get(getMetadata(stack.getItemDamage()));
    }

    @Override
    public int getMetadata(int damage) {
        return damage & 15;
    }
}
