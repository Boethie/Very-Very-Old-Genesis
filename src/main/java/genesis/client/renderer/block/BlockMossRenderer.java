package genesis.client.renderer.block;

import genesis.block.BlockMoss;
import genesis.client.renderer.RenderMoss;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockMossRenderer implements ISimpleBlockRenderingHandler {

    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
    public static int pass = 0;

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        RenderMoss rendererGen = new RenderMoss(renderer.blockAccess, renderer);
    	rendererGen.renderBlockAsItem(block, metadata, 1);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
        pass = 0;
        boolean snow = BlockMoss.isSnowed(blockAccess, x, y, z);

        if (!snow && !renderer.hasOverrideBlockTexture()) {
            renderer.renderStandardBlock(block, x, y, z);
        }

        pass = 1;
        renderer.renderStandardBlock(block, x, y, z);
        pass = -1;

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
