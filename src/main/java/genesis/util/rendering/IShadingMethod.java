package genesis.util.rendering;

public interface IShadingMethod {

    public Vec4 getShadedBrightness(Vec4 faceCenter, Vec4 normal);
}
