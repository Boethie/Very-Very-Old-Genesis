package genesis.handler;

import genesis.Genesis;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;

import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;

public class GenesisVersionHandler {
    private static Status status = Status.PENDING;
    private static String target = null;

    public static Status getStatus() {
        return status;
    }

    public static String getTarget() {
        return target;
    }

    public static void performVersioning() {
        new Thread("Genesis Version Checking") {
            @Override
            public void run() {
                try {
                    URL infoFile = new URL("https://www.dropbox.com/s/ngtsfpfx71m9yua/genesis_data.json?raw=1");
                    InputStream is = infoFile.openStream();
                    String data = new String(ByteStreams.toByteArray(is));
                    is.close();

                    Map<String, Object> json = new Gson().fromJson(data, Map.class);
                    Map<String, String> versionData = (Map<String, String>) json.get("data");

                    String newVersion = versionData.get(MinecraftForge.MC_VERSION + "-current");

                    ArtifactVersion version = new DefaultArtifactVersion(Genesis.MOD_VERSION);

                    if (newVersion != null) {
                        ArtifactVersion newVer = new DefaultArtifactVersion(newVersion);
                        int diff = newVer.compareTo(version);

                        if (diff == 0) {
                            status = Status.UP_TO_DATE;
                        } else if (diff > 0) {
                            status = Status.OUTDATED;
                        } else {
                            status = Status.AHEAD;
                        }

                        target = newVersion;
                    } else {
                        status = Status.FAILED;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status = Status.FAILED;
                }
            }
        }.start();
    }

    public enum Status {
        PENDING,
        FAILED,
        OUTDATED,
        UP_TO_DATE,
        AHEAD
    }
}
