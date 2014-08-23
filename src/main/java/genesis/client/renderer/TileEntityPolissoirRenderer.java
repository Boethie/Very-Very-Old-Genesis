package genesis.client.renderer;

import genesis.client.model.ModelPolissoir;
import genesis.common.Genesis;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPolissoirRenderer extends TileEntitySpecialRenderer {
    private static final ResourceLocation polissoirTextures = new ResourceLocation(Genesis.ASSETS + "textures/entity/polissoir/polissoir.png");
    private final ModelPolissoir model = new ModelPolissoir();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
        bindTexture(polissoirTextures);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        switch (tileEntity.getBlockMetadata()) {
        case 3:
            scale = 180.0F;
            break;
        case 4:
            scale = -90.0F;
            break;
        case 5:
            scale = 90.0F;
            break;
        default:
            scale = 0.0F;
        }
        GL11.glRotatef(scale, 0.0F, 1.0F, 0.0F);

        GL11.glRotatef(90, 0, 1.0F, 0);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
