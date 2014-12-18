package genesis.util.rendering;

import java.util.ArrayList;

import net.minecraft.util.IIcon;

public class FaceHelper {

    public static ArrayList<Vec4> verts = new ArrayList();
    public static ArrayList<Vec2> uvs = new ArrayList();

    public static void clearPrevious() {
        verts.clear();
        uvs.clear();
    }

    public static Tri getNextInStrip(Vec4 vertex, Vec2 uv) {
        verts.add(vertex);
        uvs.add(uv);

        int vert0I = verts.size() - 3;

        if (vert0I < 0) {
            return null;
        }

        return new Tri(verts.get(vert0I), verts.get(vert0I + 1), verts.get(vert0I + 2), uvs.get(vert0I), uvs.get(vert0I + 1), uvs.get(vert0I + 2));
    }

    public static Tri getNextInCircle(Vec4 vertex, Vec2 uv) {
        verts.add(vertex);
        uvs.add(uv);

        int vert0I = verts.size() - 2;

        if (vert0I < 1 || verts.size() == 0) {
            return null;
        }

        return new Tri(verts.get(0), verts.get(vert0I + 1), verts.get(vert0I), uvs.get(0), uvs.get(vert0I + 1), uvs.get(vert0I));
    }

    public static Vec2 uvFromIcon(double u, double v, IIcon icon) {
        return new Vec2(icon.getInterpolatedU(u * 16), icon.getInterpolatedV(v * 16));
    }
}
