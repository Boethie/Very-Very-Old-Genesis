package genesis.util.rendering;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;

public class RotateableBox extends RotateableBase {
	
	public float widthX;
	public float height;
	public float widthZ;
	
	public float uS;
	public float uE;
	public float vS;
	public float vE;
	
	public Vec4[] vertices;
	
	public int texSize;

	public boolean useTransformForUVs = true;
	public boolean useFullUVs = false;
	
	protected float transX;
	protected float transY;
	protected float transZ;
	
	public RotateableBox(float widthX, float height, float widthZ,
			float uS, float uE, float vS, float vE,
			int texSize)
	{
		float x = widthX / 2;
		float y = height / 2;
		float z = widthZ / 2;
		
		vertices = new Vec4[]{
				new Vec4(-x, -y, -z, 1),// 0
				new Vec4(x, -y, -z, 1),	// 1
				new Vec4(-x, y, -z, 1),	// 2
				new Vec4(-x, -y, z, 1),	// 3
				new Vec4(x, y, -z, 1),	// 4
				new Vec4(-x, y, z, 1),	// 5
				new Vec4(x, -y, z, 1),	// 6
				new Vec4(x, y, z, 1),	// 7
		};
		
		this.widthX = widthX;
		this.height = height;
		this.widthZ = widthZ;
		this.uS = uS;
		this.uE = uE;
		this.vS = vS;
		this.vE = vE;
		this.texSize = texSize;
	}
	
	public RotateableBox setUseTransformedSizeForUVs(boolean use)
	{
		useTransformForUVs = use;
		
		return this;
	}
	
	public RotateableBox setUseFullUVs(boolean use)
	{
		useFullUVs = use;
		
		return this;
	}
	
	public void updateTransformedSize()
	{
		if (useTransformForUVs)
		{
			transX = (float)vertices[0].sub(vertices[1]).length();
			transY = (float)vertices[0].sub(vertices[2]).length();
			transZ = (float)vertices[0].sub(vertices[3]).length();
		}
		else
		{
			transX = widthX;
			transY = height;
			transZ = widthZ;
		}
	}
	
	public float roundToPixel(float coord)
	{
		return (float)Math.ceil(coord * texSize) / texSize;
	}
	
	public FaceUVs getUVs(int uPart, int vPart, boolean side)
	{
		if (useFullUVs)
		{
			return new FaceUVs(uS, uE, vS, vE);
		}
		else
		{
			float sizeU = transX * 2 + transZ * 2;
			float sizeV = transY + transZ;
			
			float size;

			float sizeX;
			float sizeY;
			float sizeZ;
			
			float scaledSizeU = -1;
			float scaledSizeV = -1;
			
			int tries = 0;
			
			do
			{
				if (scaledSizeU > 1 || scaledSizeV > 1)
				{
					float div = Math.max(scaledSizeU, scaledSizeV);
					sizeU *= div;
					sizeV *= div;
				}
				
				size = Math.max(sizeU, sizeV);
				
				sizeX = roundToPixel(transX / size);
				sizeY = roundToPixel(transY / size);
				sizeZ = roundToPixel(transZ / size);
				
				if (tries > 1)
					break;
				
				scaledSizeU = sizeX * 2 + sizeZ * 2;
				scaledSizeV = sizeY + sizeZ;
				
				tries++;
			}
			while ((scaledSizeU > 1 || scaledSizeV > 1));
			
			float oUS = 0;
			float oUE = 0;
			float oVS = 0;
			float oVE = 0;
			
			for (int i = 0; i <= uPart; i++)
			{
				oUS = oUE;
				
				if (i % 2 == 0)
				{
					oUE += sizeX;
				}
				else
				{
					oUE += sizeZ;
				}
			}
			
			oVE += sizeY;
			
			if (vPart > 0)
			{
				oVS = oVE;
				oVE += sizeZ;
			}
			
			float dU = this.uE - this.uS;
			float dV = this.vE - this.vS;
			
			oUS = this.uS + dU * oUS;
			oUE = this.uS + dU * oUE;
			oVS = this.vS + dV * oVS;
			oVE = this.vS + dV * oVE;
			
			return new FaceUVs(oUS, oUE, oVS, oVE);
		}
	}
	
	public void addSideFace(Vec4 start, Vec4 end, int uPart, int vPart)
	{
		FaceUVs uvs = getUVs(uPart, vPart, true);
		
		Vec4 bL = getTransformedVert(new Vec4(start.x, start.y, start.z, 1));
		Vec4 bR = getTransformedVert(new Vec4(end.x, start.y, end.z, 1));
		Vec4 tR = getTransformedVert(new Vec4(end.x, end.y, end.z, 1));
		Vec4 tL = getTransformedVert(new Vec4(start.x, end.y, start.z, 1));
		Quad face = new Quad(bL, bR, tR, tL, uvs);
		
		Vec4 center = face.getCenter();
		Vec4 normal = face.getNormal();
		
		Vec4 colorBright = getShadedBrightness(center, normal);
		tessellator.setColorOpaque_F((float)colorBright.x, (float)colorBright.y, (float)colorBright.z);
		
		addVert(bL, uvs.uS, uvs.vE);
		addVert(bR, uvs.uE, uvs.vE);
		addVert(tR, uvs.uE, uvs.vS);
		addVert(tL, uvs.uS, uvs.vS);
	}
	
	public void addVerticalFace(Vec4 start, Vec4 end, int uPart, int vPart)
	{
		FaceUVs uvs = getUVs(uPart, vPart, true);
		
		Vec4 bL = getTransformedVert(new Vec4(start.x, start.y, start.z, 1));
		Vec4 bR = getTransformedVert(new Vec4(end.x, start.y, start.z, 1));
		Vec4 tR = getTransformedVert(new Vec4(end.x, start.y, end.z, 1));
		Vec4 tL = getTransformedVert(new Vec4(start.x, start.y, end.z, 1));
		Quad face = new Quad(bL, bR, tR, tL, uvs);
		
		Vec4 center = face.getCenter();
		Vec4 normal = face.getNormal();
		
		Vec4 colorBright = getShadedBrightness(center, normal);
		tessellator.setColorOpaque_F((float)colorBright.x, (float)colorBright.y, (float)colorBright.z);
		
		addVert(bL, uvs.uS, uvs.vS);
		addVert(bR, uvs.uE, uvs.vS);
		addVert(tR, uvs.uE, uvs.vE);
		addVert(tL, uvs.uS, uvs.vE);
	}
	
	public void render()
	{
		updateTransformedSize();
		/*new Vec(-x, -y, -z),// 0
		new Vec(x, -y, -z),	// 1
		new Vec(-x, y, -z),	// 2
		new Vec(-x, -y, z),	// 3
		new Vec(x, y, -z),	// 4
		new Vec(-x, y, z),	// 5
		new Vec(x, -y, z),	// 6
		new Vec(x, y, z),	// 7*/
		
		addSideFace(vertices[1], vertices[2], 0, 0);
		addSideFace(vertices[0], vertices[5], 1, 0);
		addSideFace(vertices[3], vertices[7], 2, 0);
		addSideFace(vertices[6], vertices[4], 3, 0);
		
		addVerticalFace(vertices[4], vertices[5], 0, 1);	// Top
		addVerticalFace(vertices[0], vertices[6], 2, 1);	// Bottom
	}
}
