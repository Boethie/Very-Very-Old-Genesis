package genesis.client.renderer;

import genesis.block.gui.TileEntityPolissoir;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPolissoirRender implements IItemRenderer {
    private TileEntityPolissoirRenderer render = new TileEntityPolissoirRenderer();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        }
        render.renderTileEntityAt(new TileEntityPolissoir(), 0.0D, 0.0D, 0.0D, 0.0F);
    }
}