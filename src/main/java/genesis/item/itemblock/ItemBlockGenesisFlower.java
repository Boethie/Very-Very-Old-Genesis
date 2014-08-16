package genesis.item.itemblock;

import genesis.lib.PlantMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * @author Arbiter
 */
public class ItemBlockGenesisFlower extends ItemBlockWithMetadata {
    public ItemBlockGenesisFlower(Block block) {
        super(block, block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Block b = Block.getBlockFromItem(item.getItem());
        return b.getUnlocalizedName() + PlantMetadata.flowerTypes.get(getMetadata(item.getItemDamage()));
    }

    @Override
    public int getMetadata(int meta) {
        return meta & 15;
    }
}