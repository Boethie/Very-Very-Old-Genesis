package genesis.fluid.item;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
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
        setCreativeTab(GenesisTabs.tabGenesisMisc);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName);
    }
}
