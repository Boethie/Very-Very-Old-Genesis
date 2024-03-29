package genesis.item.itemblock;

import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class ItemBlockGenesisFern extends ItemBlockWithMetadata {
    public ItemBlockGenesisFern(Block block) {
        super(block, block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Block b = Block.getBlockFromItem(item.getItem());
        return b.getUnlocalizedName() + Names.Plants.FERN_TYPES.get(getMetadata(item.getItemDamage()));
    }

    @Override
    public int getMetadata(int meta) {
        return meta & 15;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int par2) {
        return field_150939_a.getRenderColor(stack.getItemDamage());
    }
}