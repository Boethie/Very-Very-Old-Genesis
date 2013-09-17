package genesis.genesis.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockTikiTorchRenderer implements ISimpleBlockRenderingHandler{

	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		int metadata = world.getBlockMetadata(x, y, z);
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		tess.setColorOpaque_F(1F, 1F, 1F);
		if(metadata == 0)
		{
			renderer.setOverrideBlockTexture(BlockTikiTorch.tikiTorchLower);
			renderer.renderTorchAtAngle(block, x, y, z, 0, 0, 0);
			renderer.clearOverrideBlockTexture();
		}
		else
		{
			renderer.setOverrideBlockTexture(BlockTikiTorch.tikiTorchUpper);
			renderer.renderTorchAtAngle(block, x, y, z, 0, 0, 0);
			renderer.clearOverrideBlockTexture();
		}
		
		return true;
		
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
