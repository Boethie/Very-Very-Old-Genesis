package genesis.util.rendering;

public class Tri {

    public Vec4 vert0;
    public Vec2 uv0;

    public Vec4 vert1;
    public Vec2 uv1;

    public Vec4 vert2;
    public Vec2 uv2;

    private Vec4 normal;

    public Tri(Vec4 vert0, Vec4 vert1, Vec4 vert2,
               Vec2 uv0, Vec2 uv1, Vec2 uv2) {
        this.vert0 = vert0;
        this.uv0 = uv0;

        this.vert1 = vert1;
        this.uv1 = uv1;

        this.vert2 = vert2;
        this.uv2 = uv2;
    }

    public Vec4 getNormal() {
        if (normal == null) {
            Vec4 edge0 = vert1.sub(vert0);
            Vec4 edge1 = vert2.sub(vert0);
            normal = edge0.cross(edge1).normalized();
        }

        return normal;
    }

    public Tri copy() {
        return new Tri(vert0, vert1, vert2, uv0, uv1, uv2);
    }

    public Vec4 getCenter() {
        return vert0.add(vert1).add(vert2).div(3);
    }

}
