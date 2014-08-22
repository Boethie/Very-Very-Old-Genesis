package genesis.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import genesis.block.plants.BlockHausmanniaTop;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * @author Arbiter
 */
public class BlockHausmanniaRenderer implements ISimpleBlockRenderingHandler {
    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    /**
     * Derived from {@link RenderBlocks#renderBlockDoublePlant}
     */
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (block instanceof BlockHausmanniaTop) // to check if it is the correct block before casting
        {
            Tessellator tess = Tessellator.instance;
            tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
            int color = block.colorMultiplier(world, x, y, z);
            float red = (float) (color >> 16 & 0xff) / 255.0f;
            float green = (float) (color >> 8 & 0xff) / 255.0f;
            float blue = (float) (color & 0xff) / 255.0f;
            if (EntityRenderer.anaglyphEnable) {
                float f3 = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
                float f4 = (red * 30.0F + green * 70.0F) / 100.0F;
                float f5 = (red * 30.0F + green * 70.0F) / 100.0F;
                red = f3;
                green = f4;
                blue = f5;
            }
            tess.setColorOpaque_F(red, green, blue);
            long l = (long) (x * 3129871) ^ (z * 116129781L);
            l = l * l + 42317861L + l * 11L;
            double dx = (double) x;
            double dy = (double) y;
            double dz = (double) z;
            dx += (((double) ((float) (l >> 16 & 0x0fL) / 15.0F) - 0.5D) * 0.3D) + 0.1D;
            dz += (((double) ((float) (l >> 24 & 0x0fL) / 15.0F) - 0.5D) * 0.3D) + 0.1D;
            int meta = world.getBlockMetadata(x, y, z);
            IIcon icon = block.getIcon(2, meta);
            renderer.drawCrossedSquares(icon, dx, dy, dz, 0.4F);
            {
                icon = ((BlockHausmanniaTop) block).getTopIcon();
                double d2 = Math.cos((double) l * 0.8D) * Math.PI * 0.1D;
                double d3 = Math.cos(d2);
                double d4 = Math.sin(d2);
                double d5 = (double) icon.getMinU();
                double d6 = (double) icon.getMinV();
                double d7 = (double) icon.getMaxU();
                double d8 = (double) icon.getMaxV();
                double d11 = 1.0D, d12 = 1.0D, d13 = 1.0D, d18 = 1.0D;
                double d14 = 0.0D, d15 = 0.0D, d16 = 0.0D, d17 = 0.0D;
                tess.addVertexWithUV(dx + d15, dy + 0.4D, dz + d16, d5, d8);
                tess.addVertexWithUV(dx + d17, dy + 0.4D, dz + d18, d7, d8);
                tess.addVertexWithUV(dx + d11, dy + 0.4D, dz + d12, d7, d6);
                tess.addVertexWithUV(dx + d13, dy + 0.4D, dz + d14, d5, d6);
                d5 = (double) icon.getMinU();
                d6 = (double) icon.getMinV();
                d7 = (double) icon.getMaxU();
                d8 = (double) icon.getMaxV();
                tess.addVertexWithUV(dx + d17, dy + 0.4D, dz + d18, d5, d8);
                tess.addVertexWithUV(dx + d15, dy + 0.4D, dz + d16, d7, d8);
                tess.addVertexWithUV(dx + d13, dy + 0.4D, dz + d14, d7, d6);
                tess.addVertexWithUV(dx + d11, dy + 0.4D, dz + d12, d5, d6);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}