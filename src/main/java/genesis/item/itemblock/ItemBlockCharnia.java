package genesis.item.itemblock;

import genesis.block.aquatic.GenesisAquaticBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author Arbiter
 */
public class ItemBlockCharnia extends ItemBlockWithMetadata {
    public ItemBlockCharnia(Block block) {
        super(block, block);
        setMaxDamage(0);
        setHasSubtypes(false);
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Block b = Block.getBlockFromItem(item.getItem());
        return b.getUnlocalizedName();
    }

    public IIcon getIconFromDamage(int meta) {
        return GenesisAquaticBlocks.charnia_top.getIcon(0, 0);
    }

    @Override
    public int getMetadata(int meta) {
        return meta & 15;
    }
}