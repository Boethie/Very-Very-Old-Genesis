package genesis.util.rendering;

public class Quad {

    public Vec4 bL;
    public Vec4 bR;
    public Vec4 tR;
    public Vec4 tL;
    public FaceUVs uvs;

    private Vec4 normal;

    public Quad(Vec4 bL, Vec4 bR, Vec4 tR, Vec4 tL, FaceUVs uvs) {
        this.bL = bL;
        this.bR = bR;
        this.tR = tR;
        this.tL = tL;
        this.uvs = uvs;
    }

    public Vec4 getNormal() {
        if (normal == null) {
            Vec4 right = bR.sub(bL);
            Vec4 up = tL.sub(bL);
            normal = right.cross(up);
            normal = normal.normalized();
        }

        return normal;
    }

    public Quad copy() {
        return new Quad(bL, bR, tR, tL, uvs);
    }

    public Vec4 getCenter() {
        return bL.add(tR).div(2);
    }

}
