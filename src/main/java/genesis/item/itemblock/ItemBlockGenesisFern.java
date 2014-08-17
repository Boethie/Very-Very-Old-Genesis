package genesis.item.itemblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.block.plants.BlockGenesisFern;
import genesis.lib.PlantMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

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
        return b.getUnlocalizedName() + PlantMetadata.fernTypes.get(getMetadata(item.getItemDamage()));
    }

    @Override
    public int getMetadata(int meta) {
        return meta & 15;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int par2) {
        return ((BlockGenesisFern) this.field_150939_a).getRenderColor(stack.getItemDamage());
    }
}