package genesis.common;

public class CommonProxy {
    public void preInit() {
        registerRenderers();
    }

    public void registerRenderers() {}

    public void init() {}

    public boolean areLeavesOpaque() {
        return false;
    }

    public void playSound(double x, double y, double z, String sound, float volume, float frequency) {
        // Nothing if server-side.
    }
}
