package genesis.util.rendering;

public class Vec4 {

    double x;
    double y;
    double z;
    double w;

    public Vec4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Vec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4(double val) {
        x = y = z = w = val;
    }

    public Vec4 add(Vec4 vec2) {
        return new Vec4(x + vec2.x, y + vec2.y, z + vec2.z, w + vec2.w);
    }

    public Vec4 sub(Vec4 vec2) {
        return new Vec4(x - vec2.x, y - vec2.y, z - vec2.z, w - vec2.w);
    }

    public Vec4 mul(Vec4 vec2) {
        return new Vec4(x * vec2.x, y * vec2.y, z * vec2.z, w * vec2.w);
    }

    public Vec4 mul(double scalar) {
        return new Vec4(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vec4 div(Vec4 vec2) {
        return new Vec4(x / vec2.x, y / vec2.y, z / vec2.z, w / vec2.w);
    }

    public Vec4 div(double scalar) {
        return new Vec4(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public double length() {
        double sqr = x * x + y * y + z * z;

        return Math.sqrt(sqr);
    }

    public Vec4 normalized() {
        return div(length());
    }

    public void normalize() {
        double length = length();
        x /= length;
        y /= length;
        z /= length;
    }

    public double dot(Vec4 vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    public Vec4 cross(Vec4 vec2) {
        return new Vec4(y * vec2.z - z * vec2.y,
                z * vec2.x - x * vec2.z,
                x * vec2.y - y * vec2.x,
                0);
    }

    public boolean isValid() {
        return !Double.isNaN(x) &&
                !Double.isNaN(y) &&
                !Double.isNaN(z) &&
                !Double.isInfinite(x) &&
                !Double.isInfinite(y) &&
                !Double.isInfinite(z);
    }

    public double[] toArray(int length) {
        double[] out = new double[length];

        for (int i = 0; i < out.length; i++) {
            switch (i) {
                case 0:
                    out[i] = x;
                    break;
                case 1:
                    out[i] = y;
                    break;
                case 2:
                    out[i] = z;
                    break;
                default:
                    out[i] = w;
                    break;
            }
        }

        return out;
    }

    private boolean closeEnough(double val1, double val2) {
        double check = 1.0E-161;
        return Math.abs(val1 - val2) < check;
    }

    public boolean vecNearEq(Vec4 vec2) {
        return closeEnough(x, vec2.x) &&
                closeEnough(y, vec2.y) &&
                closeEnough(z, vec2.z);
    }

    public void print() {
        String print = "{" + x + ", " + y + ", " + z + "}";

        System.out.println(print);
    }
}