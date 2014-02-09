package genesis.util.rendering;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;

public abstract class RotateableBase
{
	
	public Tessellator tessellator = Tessellator.instance;
	
	public Matrix matrix = new Matrix(4, 4,
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1);
	
	protected boolean shadeless = false;
	
	public IShadingMethod shadingMethod;

	public RotateableBase setShadeless(boolean use)
	{
		shadeless = use;
		
		return this;
	}
	
	public RotateableBase transform(Matrix mat2)
	{
		matrix = matrix.mult(mat2);
		
		return this;
	}
	
	public RotateableBase translate(double x, double y, double z)
	{
		return transform(new Matrix(4, 4,
				1, 0, 0, x,
				0, 1, 0, y,
				0, 0, 1, z,
				0, 0, 0, 1));
	}
	
	public RotateableBase scale(double x, double y, double z)
	{
		return transform(new Matrix(4, 4,
				x,	0,	0,	0,
				0,	y,	0,	0,
				0,	0,	z,	0,
				0,	0,	0,	1));
	}
	
	private double radToDegrees(double radians)
	{
		return radians * (Math.PI / 180);
	}
	
	public RotateableBase rotate(double angle, Vec4 axis)
	{
		axis.normalize();
		
		angle = radToDegrees(angle);
		double cos = MathHelper.cos((float)angle);
		double sin = MathHelper.sin((float)angle);
		
		double cosOpp = 1 - cos;
		
		return transform(new Matrix(4, 4,
				cos + axis.x * axis.x * cosOpp,				axis.x * axis.y * cosOpp - axis.z * sin,	axis.x * axis.z * cosOpp + axis.y * sin,	0,
				axis.y * axis.x * cosOpp + axis.z * sin,	cos + axis.y * axis.y * cosOpp,				axis.y * axis.z * cosOpp - axis.x * sin,	0,
				axis.z * axis.x * cosOpp - axis.y * sin,	axis.z * axis.y * cosOpp + axis.x * sin,	cos + axis.z * axis.z * cosOpp,				0,
				0,											0,											0,											1));
	}
	
	public RotateableBase rotateYTowards(Vec4 newUp)
	{
		Vec4 crossWith = new Vec4(0, 1, 0, 0);
		
		newUp.normalize();
		Vec4 side = crossWith.cross(newUp).normalized();
		Vec4 forward = side.cross(newUp).normalized();
		
		Matrix rot = new Matrix(4, 4,
				side.x,	newUp.x,	forward.x,	0,
				side.y,	newUp.y,	forward.y,	0,
				side.z,	newUp.z,	forward.z,	0,
				0,		0,			0,			1);
		
		if (rot.isValid())
		{
			transform(rot);
		}
		
		return this;
	}
	
	public Vec4 getTransformedVert(Vec4 vert)
	{
		return matrix.mult(vert).toVec();
	}
	
	public void addVert(Vec4 vert, double u, double v)
	{
        tessellator.addVertexWithUV(vert.x, vert.y, vert.z, u, v);
	}
	
	public Vec4 getShadedBrightness(Vec4 center, Vec4 normal)
	{
		if (shadingMethod != null)
		{
			return shadingMethod.getShadedBrightness(center, normal);
		}
		
		if (!shadeless)
		{
			float dotUp = (float)normal.dot(new Vec4(0, 1, 0, 0));
			float dotZ = Math.abs((float)normal.dot(new Vec4(0, 0, 1, 0)));
			float bright = MathHelper.clamp_float(0.4F + (dotUp / 1) + (dotZ / 4), 0.5F, 1);
			
			return new Vec4(bright, bright, bright, 1);
		}
		
		return new Vec4(1, 1, 1, 1);
	}
	
	public abstract void render();
}
