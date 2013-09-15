package genesis.genesis.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockGenesisPlantRenderer implements ISimpleBlockRenderingHandler {
	
	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) { }

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelID, RenderBlocks renderer)
	{
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        
        int color = block.colorMultiplier(world, x, y, z);
        float red = (float)(color >> 16 & 255) / 255;
        float green = (float)(color >> 8 & 255) / 255;
        float blue = (float)(color & 255) / 255;

        if (EntityRenderer.anaglyphEnable)
        {
            red = red * 0.3F + green * 0.59F + blue * 0.11F;
            green = red * 0.3F + green * 0.7F;
            blue = red * 0.3F + blue * 0.7F;
        }
        
        tessellator.setColorOpaque_F(red, green, blue);
        
        Icon icon;

        if (renderer.hasOverrideBlockTexture())
            icon = renderer.overrideBlockTexture;
        else
        	icon = renderer.getBlockIcon(block, world, x, y, z, 0);

        double minU = (double)icon.getMinU();
        double minV = (double)icon.getMinV();
        double maxU = (double)icon.getMaxU();
        double maxV = (double)icon.getMaxV();
        
        double size = 0.45;
        double minX = x + 0.5 - size;
        double maxX = x + 0.5 + size;
        double minZ = z + 0.5 - size;
        double maxZ = z + 0.5 + size;
        
        tessellator.addVertexWithUV(minX, y + 1, minZ, minU, minV);
        tessellator.addVertexWithUV(minX, y,     minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y,     maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1, maxZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y,     maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y,     minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1, minZ, maxU, minV);
        tessellator.addVertexWithUV(minX, y + 1, maxZ, minU, minV);
        tessellator.addVertexWithUV(minX, y,     maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, y,     minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, y + 1, minZ, maxU, minV);
        tessellator.addVertexWithUV(maxX, y + 1, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, y,     minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, y,     maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, y + 1, maxZ, maxU, minV);
        
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return renderID;
	}
	
}
