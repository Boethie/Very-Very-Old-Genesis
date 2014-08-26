package genesis.item.itemblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.block.plants.GenesisPlantBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author Arbiter
 */
public class ItemBlockAsteroxylon extends ItemBlockWithMetadata {
    public ItemBlockAsteroxylon(Block block) {
        super(block, block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        Block b = Block.getBlockFromItem(item.getItem());
        return b.getUnlocalizedName() + (getMetadata(item.getItemDamage()) == 1 ? ".tall" : "");
    }

    public IIcon getIconFromDamage(int meta) {
        return meta == 0 ? GenesisPlantBlocks.asteroxylon.icons[0] : GenesisPlantBlocks.asteroxylon_top.getIcon(0, 0);
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