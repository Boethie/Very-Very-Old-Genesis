package genesis.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderMoss
{
    RenderBlocks renderer;
	
	public RenderMoss(IBlockAccess blockAccess, RenderBlocks renderer) {
		renderer.blockAccess = blockAccess;
        renderer.field_152631_f = false;
        renderer.flipTexture = false;
    	this.renderer = renderer;
	}

	public void renderBlockAsItem(Block block, int metadata, int i) {
		int l = block.getRenderColor(metadata);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        renderInventoryBlockWithColorMultiplier(block, metadata, f, f1, f2);
	}

    /**
     * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, meta, r, g, b
     */
    public void renderInventoryBlockWithColorMultiplier(Block p_147736_1_, int meta, float r, float g, float b)
    {
    	renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        renderer.renderFaceYNeg(p_147736_1_, 0, 0, 0, p_147736_1_.getIcon(0, meta));
        
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.setColorOpaque_F(r, g, b);
        renderer.renderFaceYPos(p_147736_1_, 0, 0, 0, p_147736_1_.getIcon(1, meta));
        
        IIcon iicon;

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = p_147736_1_.getIcon(2, meta);
        renderer.renderFaceZNeg(p_147736_1_, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = p_147736_1_.getIcon(3, meta);
        renderer.renderFaceZPos(p_147736_1_, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = p_147736_1_.getIcon(4, meta);
        renderer.renderFaceXNeg(p_147736_1_, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = p_147736_1_.getIcon(5, meta);
        renderer.renderFaceXPos(p_147736_1_, 0, 0, 0, iicon);
        
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
