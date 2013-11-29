package genesis.genesis.util.rendering;

import net.minecraft.util.Icon;

public class FaceUVs
{
	
	public float uS;
	public float uE;
	public float vS;
	public float vE;
	
	public FaceUVs(float uS, float uE, float vS, float vE)
	{
		this.uS = uS;
		this.uE = uE;
		this.vS = vS;
		this.vE = vE;
	}
	
	public FaceUVs(Icon icon, int uS, int uE, int vS, int vE)
	{
		this.uS = icon.getInterpolatedU(uS);
		this.uE = icon.getInterpolatedU(uE);
		this.vS = icon.getInterpolatedV(vS);
		this.vE = icon.getInterpolatedV(vE);
	}
	
}