package genesis.client.renderer.block;

import genesis.block.BlockAndMeta;
import genesis.block.plants.BlockGenesisFlowerPot;
import genesis.block.plants.IPlantInFlowerPot;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockGenesisFlowerPotRenderer implements ISimpleBlockRenderingHandler {

    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
        BlockGenesisFlowerPot pot = (BlockGenesisFlowerPot) block;

        renderer.blockAccess = world;

        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        IIcon icon = renderer.getBlockIconFromSide(block, 0);

        int color = block.colorMultiplier(world, x, y, z);
        float bright = 1;
        float red = (float) (color >> 16 & 255) / 255;
        float green = (float) (color >> 8 & 255) / 255;
        float blue = (float) (color & 255) / 255;

        if (EntityRenderer.anaglyphEnable) {
            red = (red * 30 + green * 59 + blue * 11) / 100;
            green = (red * 30 + green * 70) / 100;
            blue = (red * 30 + blue * 70) / 100;
        }

        tessellator.setColorOpaque_F(bright * red, bright * green, bright * blue);

        float offset = 0.1865F;
        renderer.renderFaceXPos(block, x - 0.5F + offset, y, z, icon);
        renderer.renderFaceXNeg(block, x + 0.5F - offset, y, z, icon);
        renderer.renderFaceZPos(block, x, y, z - 0.5F + offset, icon);
        renderer.renderFaceZNeg(block, x, y, z + 0.5F - offset, icon);
        renderer.renderFaceYPos(block, x, y - 0.5F + offset + 0.1875F, z, renderer.getBlockIcon(Blocks.dirt));

        int metadata = renderer.blockAccess.getBlockMetadata(x, y, z);

        if (metadata != 0) {
            BlockAndMeta plantBlockAndMeta = pot.getPlantBlock(metadata);

            if (plantBlockAndMeta != null) {
                float offX = 0;
                float offY = 0.25F;
                float offZ = 0;
                tessellator.addTranslation(offX, offY, offZ);

                Block plantBlock = plantBlockAndMeta.block;
                IPlantInFlowerPot iPlant = (IPlantInFlowerPot) plantBlock;

                Block renderPlantBlock = iPlant.getBlockForRender(world, x, y, z);

                if (renderPlantBlock != null) {
                    renderer.renderBlockByRenderType(renderPlantBlock, x, y, z);
                } else {
                    color = iPlant.getRenderColor(world, x, y, z);
                    red = (float) (color >> 16 & 255) / 255;
                    green = (float) (color >> 8 & 255) / 255;
                    blue = (float) (color & 255) / 255;

                    if (EntityRenderer.anaglyphEnable) {
                        red = (red * 30 + green * 59 + blue * 11) / 100;
                        green = (red * 30 + green * 70) / 100;
                        blue = (red * 30 + blue * 70) / 100;
                    }

                    tessellator.setColorOpaque_F(bright * red, bright * green, bright * blue);

                    IIcon plantIcon = iPlant.getIconForFlowerPot(world, x, y, z, plantBlockAndMeta.metadata);

                    if (plantIcon != null) {
                        renderer.setOverrideBlockTexture(plantIcon);
                    }

                    renderer.drawCrossedSquares(plantBlock.getIcon(0, plantBlockAndMeta.metadata), (float) x, (float) y, (float) z, iPlant.renderScale(world, x, y, z));
                    renderer.clearOverrideBlockTexture();
                }

                tessellator.addTranslation(-offX, -offY, -offZ);
            }
        }

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}
