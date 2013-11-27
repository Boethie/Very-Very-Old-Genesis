package genesis.genesis.util.rendering;

public class Vec2 {
	
	public double u;
	public double v;
	
	public Vec2(double u, double v)
	{
		this.u = u;
		this.v = v;
	}
	
	public Vec2 add(Vec2 vec2)
	{
		return new Vec2(u + vec2.u, v + vec2.v);
	}
	
	public Vec2 sub(Vec2 vec2)
	{
		return new Vec2(u - vec2.u, v - vec2.v);
	}
	
	public Vec2 mul(Vec2 vec2)
	{
		return new Vec2(u * vec2.u, v * vec2.v);
	}
	
	public Vec2 mul(double scalar)
	{
		return new Vec2(u * scalar, v * scalar);
	}
	
	public Vec2 div(Vec2 vec2)
	{
		return new Vec2(u / vec2.u, v / vec2.v);
	}
	
	public Vec2 div(double scalar)
	{
		return new Vec2(u / scalar, v / scalar);
	}
	
	public double length()
	{
		double sqr = u * u + v * v;
		
		return Math.sqrt(sqr);
	}
	
	public Vec2 normalized()
	{
		return this.div(length());
	}
	
	public void normalize()
	{
		double length = length();
		u /= length;
		v /= length;
	}
	
}
