package genesis.client.renderer;

import java.util.Random;

import genesis.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import genesis.block.gui.BlockCampfire;
import genesis.block.gui.TileEntityCampfire;
import genesis.util.rendering.FaceHelper;
import genesis.util.rendering.FaceUVs;
import genesis.util.rendering.IShadingMethod;
import genesis.util.rendering.Matrix;
import genesis.util.rendering.Quad;
import genesis.util.rendering.RotateableBox;
import genesis.util.rendering.RotateableModelQuads;
import genesis.util.rendering.RotateableModelTris;
import genesis.util.rendering.Tri;
import genesis.util.rendering.Vec4;

public class BlockCampfireRenderer implements ISimpleBlockRenderingHandler {

	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public static class ShadingMethodFirelight implements IShadingMethod {

		public Vec4 center;

		public ShadingMethodFirelight(Vec4 center) {
			this.center = center;
		}

		@Override
		public Vec4 getShadedBrightness(Vec4 faceCenter, Vec4 normal) {
			Vec4 darkColor = new Vec4(0.6, 0.55, 0.5, 1);
			Vec4 fireColor = new Vec4(1.25, 1.25, 1.25, 1);
			Vec4 colorDiff = fireColor.sub(darkColor);

			Vec4 toCenter = center.sub(faceCenter).normalized();
			double shade = Math.max(normal.dot(toCenter) / 2 + 0.5, 0);
			shade = Math.sqrt(shade);

			return darkColor.add(colorDiff.mul(shade));
		}

	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		BlockCampfire campfire = (BlockCampfire) block;
		TileEntityCampfire campfireEnt = campfire.getTileEntityAt(world, x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		boolean lit = campfire.isFireLit(metadata);

		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(campfire.getMixedBrightnessForBlock(world, x, y, z));

		IIcon icon = ModBlocks.granite.getIcon(0, 0);

		Vec4 up = new Vec4(0, 1, 0);

		Random blockRand = campfire.getRandomAt(world, x, y, z);
		float rockPosRot = campfire.getBlockRotationAt(world, x, y, z);

		IShadingMethod shading = null;

		if (lit)
			shading = new ShadingMethodFirelight(new Vec4(x + 0.5, y + 0.125, z + 0.5, 1));

		RotateableBox rock = new RotateableBox(0.25F, 0.25F, 0.25F, icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV(), 16);
		rock.shadingMethod = shading;
		rock.translate(x + 0.5, y + rock.height / 2, z + 0.5);

		rock.rotate(rockPosRot, up);

		Block blockUnder = world.getBlock(x, y - 1, z);
		int metadataUnder = world.getBlockMetadata(x, y - 1, z);
		boolean dirtyBlock = false;

		if (blockUnder != Blocks.dirt)
			try {
				dirtyBlock = Block.getBlockFromItem(blockUnder.getItemDropped(metadataUnder, null, 0)) == Blocks.dirt;
			} catch (NullPointerException e) {}

		icon = Blocks.dirt.getIcon(0, 0);

		int count = 9;
		double rotStep = 360.0 / count;

		Tri[] tris = new Tri[count];
		double offY = 0.0001;
		double radians = Math.PI / 180;

		FaceHelper.clearPrevious();
		FaceHelper.getNextInCircle(new Vec4(0, offY, 0, 1), FaceHelper.uvFromIcon(0.5, 0.5, icon));

		for (int i = 0; i < count; i++) {
			float randRot = blockRand.nextFloat() * 360;
			double off = 0.4 + blockRand.nextFloat() * 0.1;

			rock.translate(off, 0, 0);
			rock.rotate(randRot, up);
			rock.render();
			rock.rotate(-randRot, up);
			rock.translate(-off, 0, 0);

			rock.rotate(rotStep, up);

			double angle = rotStep * i;
			double offX = Math.cos(angle * radians) * off;
			double offZ = Math.sin(angle * radians) * off;
			Tri tri = FaceHelper.getNextInCircle(new Vec4(offX, offY, offZ, 1), FaceHelper.uvFromIcon(0.5 + offX, 0.5 + offZ, icon));

			if (tri != null)
				tris[i - 1] = tri;
		}

		tris[tris.length - 1] = FaceHelper.getNextInCircle(FaceHelper.verts.get(1), FaceHelper.uvs.get(1));

		if (dirtyBlock) {
			RotateableModelTris dirt = new RotateableModelTris(tris);
			dirt.translate(x + 0.5, y, z + 0.5);
			dirt.render();
		}

		icon = renderer.getBlockIconFromSideAndMetadata(campfire, 1, metadata);

		float distH = 0.0625F;
		float height = 1;

		Quad xPos = new Quad(new Vec4(-distH, 0, -distH, 1), new Vec4(-distH, 0, distH, 1), new Vec4(-distH, height, distH, 1), new Vec4(-distH, height, -distH, 1), new FaceUVs(icon, 1, 3, 0, 16));
		Quad zPos = new Quad(new Vec4(-distH, 0, distH, 1), new Vec4(distH, 0, distH, 1), new Vec4(distH, height, distH, 1), new Vec4(-distH, height, distH, 1), new FaceUVs(icon, 3, 5, 0, 16));
		Quad xNeg = new Quad(new Vec4(distH, 0, distH, 1), new Vec4(distH, 0, -distH, 1), new Vec4(distH, height, -distH, 1), new Vec4(distH, height, distH, 1), new FaceUVs(icon, 5, 7, 0, 16));
		Quad zNeg = new Quad(new Vec4(distH, 0, -distH, 1), new Vec4(-distH, 0, -distH, 1), new Vec4(-distH, height, -distH, 1), new Vec4(distH, height, -distH, 1), new FaceUVs(icon, 7, 9, 0, 16));
		Quad yPos = new Quad(new Vec4(distH, height, -distH, 1), new Vec4(-distH, height, -distH, 1), new Vec4(-distH, height, distH, 1), new Vec4(distH, height, distH, 1), new FaceUVs(icon, 11, 13, 5, 7));
		Quad yNeg = new Quad(new Vec4(-distH, 0, -distH, 1), new Vec4(distH, 0, -distH, 1), new Vec4(distH, 0, distH, 1), new Vec4(-distH, 0, distH, 1), new FaceUVs(icon, 11, 13, 1, 3));

		RotateableModelQuads vertStick = new RotateableModelQuads(new Quad[] { xPos, zPos, xNeg, zNeg, yPos, yNeg });
		vertStick.shadingMethod = shading;

		vertStick.translate(x + 0.5, y, z + 0.5);
		vertStick.rotate(rockPosRot, up);

		double off = 0.4375;

		vertStick.translate(0, 0, -off);
		vertStick.render();
		vertStick.translate(0, 0, off);

		vertStick.rotate(180, up);
		vertStick.translate(0, 0, -off);
		vertStick.render();
		vertStick.translate(0, 0, off);

		icon = renderer.getBlockIconFromSideAndMetadata(campfire, 2, metadata);

		RotateableBox vStick = new RotateableBox(0.08838834764831844055010554526311F, 0.125F, 0.126F, icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV(), 6);
		vStick.shadingMethod = shading;
		vStick.translate(x + 0.5, y + height, z + 0.5);
		vStick.rotate(rockPosRot, up);

		for (int i = 0; i < 2; i++) {
			vStick.translate(0, 0, -off);

			vStick.rotate(-45, new Vec4(0, 0, 1));
			vStick.translate(vStick.widthX / 2, vStick.height / 2, 0);
			vStick.render();
			vStick.translate(-vStick.widthX / 2, -vStick.height / 2, 0);
			vStick.rotate(45, new Vec4(0, 0, 1));

			vStick.rotate(180, new Vec4(0, 1, 0));

			vStick.rotate(-45, new Vec4(0, 0, 1));
			vStick.translate(vStick.widthX / 2, vStick.height / 2, 0);
			vStick.render();
			vStick.translate(-vStick.widthX / 2, -vStick.height / 2, 0);
			vStick.rotate(45, new Vec4(0, 0, 1));

			vStick.rotate(-180, new Vec4(0, 1, 0));

			vStick.translate(0, 0, off);
			vStick.rotate(180, new Vec4(0, 1, 0));
		}

		if (lit) {
			icon = renderer.getBlockIconFromSideAndMetadata(Blocks.fire, 0, 0);

			distH = 0.375F;
			height = 1;
			float distT = 0.25F;
			float distB = 0.375F;
			FaceUVs iconUVs = new FaceUVs(icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV());

			Quad xNegOut = new Quad(new Vec4(-distB, 0, -distH, 1), new Vec4(-distB, 0, distH, 1), new Vec4(-distT, height, distH, 1), new Vec4(-distT, height, -distH, 1), iconUVs);
			Quad xPosOut = new Quad(new Vec4(distB, 0, distH, 1), new Vec4(distB, 0, -distH, 1), new Vec4(distT, height, -distH, 1), new Vec4(distT, height, distH, 1), iconUVs);
			Quad zNegOut = new Quad(new Vec4(distH, 0, -distB, 1), new Vec4(-distH, 0, -distB, 1), new Vec4(-distH, height, -distT, 1), new Vec4(distH, height, -distT, 1), iconUVs);
			Quad zPosOut = new Quad(new Vec4(-distH, 0, distB, 1), new Vec4(distH, 0, distB, 1), new Vec4(distH, height, distT, 1), new Vec4(-distH, height, distT, 1), iconUVs);

			distH *= -1;
			distT = 0.3125F;
			distB = -0.0625F;

			Quad xNegIn = new Quad(new Vec4(-distB, 0, -distH, 1), new Vec4(-distB, 0, distH, 1), new Vec4(-distT, height, distH, 1), new Vec4(-distT, height, -distH, 1), iconUVs);
			Quad xPosIn = new Quad(new Vec4(distB, 0, distH, 1), new Vec4(distB, 0, -distH, 1), new Vec4(distT, height, -distH, 1), new Vec4(distT, height, distH, 1), iconUVs);
			Quad zNegIn = new Quad(new Vec4(distH, 0, -distB, 1), new Vec4(-distH, 0, -distB, 1), new Vec4(-distH, height, -distT, 1), new Vec4(distH, height, -distT, 1), iconUVs);
			Quad zPosIn = new Quad(new Vec4(-distH, 0, distB, 1), new Vec4(distH, 0, distB, 1), new Vec4(distH, height, distT, 1), new Vec4(-distH, height, distT, 1), iconUVs);

			RotateableModelQuads fire = new RotateableModelQuads(new Quad[] { xNegOut, xPosOut, zNegOut, zPosOut, xNegIn, xPosIn, zNegIn, zPosIn });
			fire.setShadeless(true);

			fire.translate(x + 0.5, y, z + 0.5);
			fire.render();

			icon = Blocks.coal_block.getIcon(0, 0);
			float coalWidth = 0.25F;
			RotateableBox coals = new RotateableBox(coalWidth, 0.125F, coalWidth, icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV(), 16);

			coals.setShadeless(true);
			coals.translate(x + 0.5, y + coals.height / 2, z + 0.5);
			coals.rotate(rockPosRot, new Vec4(0, 1, 0));

			coals.rotate(blockRand.nextDouble() * 360, new Vec4(0, 1, 0));

			coals.translate(0, 0, -0.125);

			coals.translate(-0.125, 0, 0);
			coals.render();

			Matrix oldMat = coals.matrix.copy();

			coals.translate(0.25, 0.06, 0);
			coals.rotate(-30, new Vec4(0, 0, 1));
			coals.rotate((blockRand.nextDouble() - 0.5) * 90, new Vec4(0, 1, 0));
			coals.render();

			coals.matrix = oldMat;
			coals.rotate((blockRand.nextDouble() - 0.5) * 45, new Vec4(0, 1, 0));

			coals.translate(0, 0.06, 0.25);
			coals.rotate(30, new Vec4(1, 0, 0));
			coals.rotate((blockRand.nextDouble() - 0.5) * 90, new Vec4(0, 1, 0));
			coals.render();
		}

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
