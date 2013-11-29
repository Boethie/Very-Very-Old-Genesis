package genesis.genesis.util.rendering;

public class RotateableModelQuads extends RotateableBase {
	
	public Quad[] faces;
	
	public RotateableModelQuads(Quad[] faces)
	{
		this.faces = faces;
	}
	
	@Override
	public void render()
	{
		for (Quad face : faces)
		{
			FaceUVs uvs = face.uvs;

			Quad faceTrans = face.copy();
			faceTrans.bL = getTransformedVert(face.bL);
			faceTrans.bR = getTransformedVert(face.bR);
			faceTrans.tR = getTransformedVert(face.tR);
			faceTrans.tL = getTransformedVert(face.tL);
			
			Vec4 center = face.getCenter();
			Vec4 normal = face.getNormal();
			
			Vec4 colorBright = getShadedBrightness(center, normal);
			tessellator.setColorOpaque_F((float)colorBright.x, (float)colorBright.y, (float)colorBright.z);
			
			addVert(faceTrans.bL, uvs.uS, uvs.vE);
			addVert(faceTrans.bR, uvs.uE, uvs.vE);
			addVert(faceTrans.tR, uvs.uE, uvs.vS);
			addVert(faceTrans.tL, uvs.uS, uvs.vS);
		}
	}
	
}
