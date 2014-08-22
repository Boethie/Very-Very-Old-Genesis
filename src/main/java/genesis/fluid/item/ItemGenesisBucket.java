package genesis.fluid.item;

import genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

/**
 * Generic class for fluid buckets.
 *
 * @author rubensworks
 */
public class ItemGenesisBucket extends ItemBucket {
    public ItemGenesisBucket(Block block) {
        super(block);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName);
    }
}
