package genesis.item.itemblock;

import genesis.lib.PlantMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * @author Arbiter
 */
public class ItemBlockGenesisAlgae extends ItemBlockWithMetadata {
    public ItemBlockGenesisAlgae(Block block) {
        super(block, block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta & 0x0f;
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Block b = Block.getBlockFromItem(item.getItem());
        return b.getUnlocalizedName() + PlantMetadata.algaeTypes.get(getMetadata(item.getItemDamage()));
    }
}