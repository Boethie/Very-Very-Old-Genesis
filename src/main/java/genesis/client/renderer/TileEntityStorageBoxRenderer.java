package genesis.client.renderer;

import genesis.Genesis;
import genesis.block.tiles.BlockStorageBox;
import genesis.client.model.tiles.ModelBox;
import genesis.client.model.tiles.ModelDoubleBox;
import genesis.tileentity.TileEntityStorageBox;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;

public class TileEntityStorageBoxRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public static final TileEntityStorageBoxRenderer instance = new TileEntityStorageBoxRenderer();
	
	private static final ResourceLocation texture_single = new ResourceLocation(Genesis.MOD_ID, "textures/entity/normal_box.png");
	private static final ResourceLocation texture_double = new ResourceLocation(Genesis.MOD_ID, "textures/entity/normal_double_box.png");
	private ModelBox modelChest = new ModelBox();
	private ModelBox modelLargeChest = new ModelDoubleBox();

	public int renderID;
	
	public TileEntityStorageBoxRenderer(){
		renderID = RenderingRegistry.getNextAvailableRenderId();
	}

	public void renderTileEntityAt(TileEntityStorageBox box, double x, double y, double z, float f)
	{
		int i;

		if (!box.hasWorldObj()){
			i = 0;
		}
		else{
			Block block = box.getBlockType();
			i = box.getBlockMetadata();

			if (block instanceof BlockStorageBox && i == 0){
				try{
					((BlockStorageBox)block).func_149954_e(box.getWorldObj(), box.xCoord, box.yCoord, box.zCoord);
				}
				catch (ClassCastException e){
					FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", box.xCoord, box.yCoord, box.zCoord);
				}
				i = box.getBlockMetadata();
			}

			box.checkForAdjacentChests();
		}

		if (box.adjacentChestZNeg == null && box.adjacentChestXNeg == null){
			ModelBox model;

			if (box.adjacentChestXPos == null && box.adjacentChestZPos == null){
				model = this.modelChest;
				this.bindTexture(texture_single);
			}
			else{
				model = this.modelLargeChest;
				this.bindTexture(texture_double);
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float)x, (float)y +1.0F, (float)z + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (i == 2){
				short1 = -90;
			}

			if (i == 3){
				short1 = 90;
			}

			if (i == 4){
				short1 = 180;
			}

			if (i == 5){
				short1 = 0;
			}

			if (i == 2 && box.adjacentChestXPos != null){
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if (i == 5 && box.adjacentChestZPos != null){
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);
			}

			GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f1 = box.prevLidAngle + (box.lidAngle - box.prevLidAngle) * f;
			float f2;

			if (box.adjacentChestZNeg != null){
				f2 = box.adjacentChestZNeg.prevLidAngle + (box.adjacentChestZNeg.lidAngle - box.adjacentChestZNeg.prevLidAngle) * f;

				if (f2 > f1){
					f1 = f2;
				}
			}

			if (box.adjacentChestXNeg != null){
				f2 = box.adjacentChestXNeg.prevLidAngle + (box.adjacentChestXNeg.lidAngle - box.adjacentChestXNeg.prevLidAngle) * f;

				if (f2 > f1){
					f1 = f2;
				}
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			model.lid.rotateAngleZ = -(f1 * (float)Math.PI / 2.0F);
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		this.renderTileEntityAt((TileEntityStorageBox)tile, x, y, z, f);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(-1.0F, 0.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		modelChest.lid.rotateAngleZ = 0.0F;
		bindTexture(texture_single);
		modelChest.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
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
