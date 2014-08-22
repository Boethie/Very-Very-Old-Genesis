package genesis.util.rendering;

public class RotateableModelTris extends RotateableBase {

    public Tri[] tris;

    public RotateableModelTris(Tri[] faces) {
        this.tris = faces;
    }

    @Override
    public void render() {
        for (Tri tri : tris) {
            Tri triTransf = tri.copy();
            triTransf.vert0 = getTransformedVert(tri.vert0);
            triTransf.vert1 = getTransformedVert(tri.vert1);
            triTransf.vert2 = getTransformedVert(tri.vert2);

            Vec4 center = tri.getCenter();
            Vec4 normal = tri.getNormal();

            Vec4 colorBright = getShadedBrightness(center, normal);
            tessellator.setColorOpaque_F((float) colorBright.x, (float) colorBright.y, (float) colorBright.z);

            Vec2 uv = tri.uv0;
            addVert(triTransf.vert0, uv.u, uv.v);

            uv = tri.uv1;
            addVert(triTransf.vert1, uv.u, uv.v);

            uv = tri.uv2;
            addVert(triTransf.vert2, uv.u, uv.v);
            addVert(triTransf.vert2, uv.u, uv.v);
        }
    }
}
