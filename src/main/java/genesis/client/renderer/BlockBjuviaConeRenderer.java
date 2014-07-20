package genesis.client.renderer;

import genesis.lib.MiscHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockBjuviaConeRenderer implements ISimpleBlockRenderingHandler
{
	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks render) {}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks render)
	{
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		int color = block.colorMultiplier(world, x, y, z);
		float red = (float)(color >> 16 & 0xff) / 255.0F;
		float green = (float)(color >> 8 & 0xff) / 255.0F;
		float blue = (float)(color & 0xff) / 255.0F;
		if (EntityRenderer.anaglyphEnable)
		{
			float f3 = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float f4 = (red * 30.0F + green * 70.0F) / 100.0F;
            float f5 = (red * 30.0F + green * 70.0F) / 100.0F;
            red = f3;
            green = f4;
            blue = f5;
		}
		tess.setColorOpaque_F(red, green, blue);
		IIcon icon = block.getIcon(0, world.getBlockMetadata(x, y, z));
		int meta = world.getBlockMetadata(x, y, z);
		meta = (meta == 0 ? -1 : (meta == 1 ? 0 : 1));
		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getInterpolatedU(7.0D);
		double maxV = icon.getInterpolatedV(7.0D);
		{	
			// base block sides
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(4 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(4 - meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(4 - meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(4 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(12 + meta), minU, minV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(12 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(12 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(12 + meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(12 + meta), minU, minV);
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(12 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(4 - meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(4 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(4 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(4 - meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(12 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(12 + meta), maxU, minV);
		}
		minU = icon.getInterpolatedU(8.0D);
		minV = icon.getMinV();
		maxU = icon.getInterpolatedU(15.0D);
		maxV = icon.getInterpolatedV(7.0D);
		{
			// base block bottom & top
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(4 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(4 - meta), y + cvt(8), z + cvt(12 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(12 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(12 + meta), y + cvt(8), z + cvt(4 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(4 - meta), maxU, minV);
			tess.addVertexWithUV(x + cvt(12 + meta), y, z + cvt(12 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(12 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(4 - meta), y, z + cvt(4 - meta), minU, minV);
		}
		minU = icon.getMinU();
		minV = icon.getMinV();
		maxU = icon.getInterpolatedU(7.0D);
		maxV = icon.getInterpolatedV(7.0D);
		{
			// top block sides
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(5 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(5 - meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(5 - meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(5 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(11 + meta), minU, minV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(11 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(11 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(11 + meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(11 + meta), minU, minV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(11 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(5 - meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(5 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(5 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(5 - meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(11 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(11 + meta), maxU, minV);
		}
		minU = icon.getInterpolatedU(8.0D);
		minV = icon.getMinV();
		maxU = icon.getInterpolatedU(15.0D);
		maxV = icon.getInterpolatedV(7.0D);
		{
			// top block bottom & top
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(5 - meta), minU, minV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(14 + meta), z + cvt(11 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(11 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(14 + meta), z + cvt(5 - meta), maxU, minV);
			
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(5 - meta), maxU, minV);
			tess.addVertexWithUV(x + cvt(11 + meta), y + cvt(8), z + cvt(11 + meta), maxU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(11 + meta), minU, maxV);
			tess.addVertexWithUV(x + cvt(5 - meta), y + cvt(8), z + cvt(5 - meta), minU, minV);
		}
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelID)
	{
		return false;
	}
	
	@Override
	public int getRenderId()
	{
		return renderID;
	}
	
	/**
	 * Reference method so I do not have to write MiscHelpers.getLengthFromPixels() everytime.
	 * @param pixels
	 * @return
	 */
	private static double cvt(int pixels)
	{
		return MiscHelpers.getLengthFromPixels(pixels);
	}
}