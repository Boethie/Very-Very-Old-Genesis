package genesis.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import genesis.block.BlockMoss;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BlockMossRenderer implements ISimpleBlockRenderingHandler {

    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
    public static boolean renderingInventory = false;
    public static int pass = 0;

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        renderingInventory = true;
        renderer.renderBlockAsItem(block, metadata, 1);
        renderingInventory = false;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
        BlockMoss moss = (BlockMoss) block;

        pass = 0;
        boolean snow = BlockMoss.isSnowed(blockAccess, x, y, z);

        if (!snow && !renderer.hasOverrideBlockTexture())
            renderer.renderStandardBlock(block, x, y, z);

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
