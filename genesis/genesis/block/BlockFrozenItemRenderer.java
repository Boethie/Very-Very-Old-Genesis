package genesis.genesis.block;

import java.util.Random;

import genesis.genesis.client.ClientProxy;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockFrozenItemRenderer implements ISimpleBlockRenderingHandler{

	
	public static int renderPass;
	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {	
		Tessellator tess = Tessellator.instance;
		TextureManager tmanager = Minecraft.getMinecraft().renderEngine;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.drawItem(tess, -0.5F, -0.5F, -0.5F, tmanager, 0);
		tmanager.bindTexture(TextureMap.locationBlocksTexture);
		renderer.renderBlockAsItem(Block.ice, metadata, 1);	
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;
		if(renderPass == 0)
		{
			
			TextureManager tmanager = Minecraft.getMinecraft().renderEngine;
			if(tess.isDrawing)
			{
				tess.draw();
			}
			
			this.drawItem(tess, x, y, z, tmanager, world.getBlockMetadata(x, y, z));
			if(!tess.isDrawing)
			{
				tess.startDrawingQuads();
			}
			
			tmanager.bindTexture(TextureMap.locationBlocksTexture);
		}
		else
		{
			tess.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			renderer.setRenderBoundsFromBlock(block);
			float red = 1F;
	        float green = 1F;
	        float blue = 1F;
			renderer.renderStandardBlockWithAmbientOcclusion(Block.ice, x, y, z, red, green, blue);
		}
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}
	
	public void drawItem(Tessellator tess, float x, float y, float z, TextureManager tmanager, int metadata)
	{		
		tmanager.bindTexture(TextureMap.locationItemsTexture);
		ItemStack is = new ItemStack(BlockFrozenItem.getItemFromMetadata(metadata), 1);
		Icon icon = Minecraft.getMinecraft().thePlayer.getItemIcon(is, 1);
		
		float minU = icon.getMinU();
		float minV = icon.getMinV();
		float maxU = icon.getMaxU();
		float maxV = icon.getMaxV();
		GL11.glTranslatef(x, y, z + 0.5F);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		ItemRenderer.renderItemIn2D(tess, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);	
		GL11.glTranslatef(-x, -y, -z - 0.5F);
		
	}

}
