package genesis.block.gui;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

import genesis.client.ClientProxy;

public class TileEntityCampfireRenderer extends TileEntitySpecialRenderer {

	public static final ResourceLocation RES_CAMPFIRE_MODEL = new ResourceLocation("genesis:textures/blocks/campfire_model.png");

	public ModelCampfireTE model;

	public TileEntityCampfireRenderer() {
		model = new ModelCampfireTE();
	}

	protected void renderItemStack(ItemStack stack) {
		float scale = 0.375F;
		GL11.glScaled(scale, scale, scale);

		Item item = stack.getItem();
		IIcon icon = stack.getIconIndex();
		
		int passes = 1;

		IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(stack, ItemRenderType.EQUIPPED);

		if (customRenderer == null && (stack.getItemSpriteNumber() != 0 || !RenderBlocks.renderItemIn3d(Block.getBlockFromItem(stack.getItem()).getRenderType()))) {
			GL11.glScalef(1, 1, 3);
			GL11.glTranslatef(0.53125F, -0.3125F, 0.05F);
			GL11.glRotatef(-335, 0, 0, 1);
			GL11.glRotatef(-50, 0, 1, 0);
		} else
			GL11.glScalef(1.5F, 1.5F, 1.5F);

		if (item.requiresMultipleRenderPasses())
			passes = item.getRenderPasses(stack.getItemDamage());

		for (int pass = 0; pass < passes; pass++)
			Minecraft.getMinecraft().entityRenderer.itemRenderer.renderItem(Minecraft.getMinecraft().thePlayer, stack, pass, ItemRenderType.EQUIPPED);
	}

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderTileEntityCampfireAt(TileEntityCampfire campfire, double x, double y, double z, float partialTick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1, z + 0.5);

		campfire.getBlockType();

		Random blockRand = campfire.blockCampfire.getRandomAt(campfire.getWorldObj(), campfire.xCoord, campfire.yCoord, campfire.zCoord);

		GL11.glRotatef(campfire.blockCampfire.getBlockRotationAt(campfire.getWorldObj(), campfire.xCoord, campfire.yCoord, campfire.zCoord) - 90, 0, 1, 0);

		model.stick.rotationPointY = 0.75F;
		model.stick.rotateAngleX = 0;
		model.stick.rotateAngleY = campfire.prevRot + (campfire.rot - campfire.prevRot) * partialTick;
		model.stick.rotateAngleZ = (float) Math.PI / 2;

		ItemStack cooking = campfire.getStackInSlot(0);

		if (cooking != null) {
			GL11.glPushMatrix();
			model.stick.postRender(0.0625F);
			renderItemStack(cooking);
			GL11.glPopMatrix();
		}

		ItemStack output = campfire.getStackInSlot(2);

		if (output != null) {
			GL11.glPushMatrix();

			int count = Math.min(output.stackSize, 6);
			float div = Math.max(count, 4);

			for (int i = 0; i < count; i++) {
				GL11.glPushMatrix();
				GL11.glTranslatef(0.4F, -0.75F, 0.4F);

				float randAmt = 15;
				GL11.glRotatef(45 - randAmt / 2 + blockRand.nextFloat() * randAmt, 0, 1, 0);

				GL11.glRotatef(-45, 1, 0, 0);
				renderItemStack(output);
				GL11.glPopMatrix();

				if (count % 2 == 0 && i % 2 == 0)
					GL11.glRotatef(180, 0, 1, 0);
				else
					GL11.glRotatef(360 / div, 0, 1, 0);
			}

			GL11.glPopMatrix();
		}

		bindTexture(RES_CAMPFIRE_MODEL);
		model.renderAll();

		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
		renderTileEntityCampfireAt((TileEntityCampfire) tileEntity, x, y, z, partialTick);
	}
}
