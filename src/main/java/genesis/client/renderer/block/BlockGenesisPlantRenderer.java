package genesis.client.renderer.block;

import genesis.block.plants.BlockGenesisPlantTop;
import genesis.block.plants.IPlantRenderSpecials;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class BlockGenesisPlantRenderer implements ISimpleBlockRenderingHandler {

    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        IPlantRenderSpecials specials = (IPlantRenderSpecials) block;

        int color = block.colorMultiplier(world, x, y, z);
        float red = (float) (color >> 16 & 255) / 255;
        float green = (float) (color >> 8 & 255) / 255;
        float blue = (float) (color & 255) / 255;

        if (EntityRenderer.anaglyphEnable) {
            red = red * 0.3F + green * 0.59F + blue * 0.11F;
            green = red * 0.3F + green * 0.7F;
            blue = red * 0.3F + blue * 0.7F;
        }

        tessellator.setColorOpaque_F(red, green, blue);
        double xd = x;
        double yd = y;
        double zd = z;
        double xzscale;
        double yscale;
        int ybase = y;
        Block blockBelow = world.getBlock(x, y - 1, z);
        if (block instanceof BlockGenesisPlantTop) {
            if (blockBelow != null && blockBelow instanceof IPlantRenderSpecials) {
                ybase--;
                xzscale = ((IPlantRenderSpecials) blockBelow).randomPos(world, x, ybase, z);
                yscale = ((IPlantRenderSpecials) blockBelow).randomYPos(world, x, ybase, z);
            } else {
                return false;
            }
        } else {
            xzscale = specials.randomPos(world, x, y, z);
            yscale = specials.randomYPos(world, x, y, z);
        }
        long i1 = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) ybase;
        i1 = i1 * i1 * 42317861L + i1 * 11L;
        xd += ((double) ((float) (i1 >> 16 & 15L) / 15.0F) - 0.5D) * xzscale;
        yd += ((double) ((float) (i1 >> 20 & 15L) / 15.0F) - 1.0D) * yscale;
        zd += ((double) ((float) (i1 >> 24 & 15L) / 15.0F) - 0.5D) * xzscale;

        double size = 0.45;
        double minX = xd + 0.5 - size;
        double maxX = xd + 0.5 + size;
        double minZ = zd + 0.5 - size;
        double maxZ = zd + 0.5 + size;

        IIcon icon = renderer.overrideBlockTexture;
        double minU;
        double minV;
        double maxU;
        double maxV;

        // Side 0
        if (!renderer.hasOverrideBlockTexture()) {
            icon = renderer.getBlockIcon(block, world, x, y, z, 0);
        }

        minU = icon.getMinU();
        minV = icon.getMinV();
        maxU = icon.getMaxU();
        maxV = icon.getMaxV();

        if (specials.shouldReverseTex(world, x, y, z, 0)) {
            double tempMinU = minU;
            minU = maxU;
            maxU = tempMinU;
        }

        tessellator.addVertexWithUV(maxX, yd + 1, maxZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd, maxZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd, minZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + 1, minZ, maxU, minV);

        // Side 2
        if (!renderer.hasOverrideBlockTexture()) {
            icon = renderer.getBlockIcon(block, world, x, y, z, 2);
        }

        minU = icon.getMinU();
        minV = icon.getMinV();
        maxU = icon.getMaxU();
        maxV = icon.getMaxV();

        if (!specials.shouldReverseTex(world, x, y, z, 2)) {
            double tempMinU = minU;
            minU = maxU;
            maxU = tempMinU;
        }

        tessellator.addVertexWithUV(minX, yd + 1, minZ, maxU, minV);
        tessellator.addVertexWithUV(minX, yd, minZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd, maxZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 1, maxZ, minU, minV);

        // Side 1
        if (!renderer.hasOverrideBlockTexture()) {
            icon = renderer.getBlockIcon(block, world, x, y, z, 1);
        }

        minU = icon.getMinU();
        minV = icon.getMinV();
        maxU = icon.getMaxU();
        maxV = icon.getMaxV();

        if (specials.shouldReverseTex(world, x, y, z, 1)) {
            double tempMinU = minU;
            minU = maxU;
            maxU = tempMinU;
        }

        tessellator.addVertexWithUV(maxX, yd + 1, minZ, minU, minV);
        tessellator.addVertexWithUV(maxX, yd, minZ, minU, maxV);
        tessellator.addVertexWithUV(minX, yd, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(minX, yd + 1, maxZ, maxU, minV);

        // Side 3
        if (!renderer.hasOverrideBlockTexture()) {
            icon = renderer.getBlockIcon(block, world, x, y, z, 3);
        }

        minU = icon.getMinU();
        minV = icon.getMinV();
        maxU = icon.getMaxU();
        maxV = icon.getMaxV();

        if (!specials.shouldReverseTex(world, x, y, z, 3)) {
            double tempMinU = minU;
            minU = maxU;
            maxU = tempMinU;
        }

        tessellator.addVertexWithUV(minX, yd + 1, maxZ, maxU, minV);
        tessellator.addVertexWithUV(minX, yd, maxZ, maxU, maxV);
        tessellator.addVertexWithUV(maxX, yd, minZ, minU, maxV);
        tessellator.addVertexWithUV(maxX, yd + 1, minZ, minU, minV);

        return true;
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
