package genesis.helper;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class MiscHelpers {

    public static boolean isWaterInRange(World world, int x, int y, int z, int horizDist, int vertDist) {
        for (int wX = x - horizDist; wX <= x + horizDist; wX++) {
            for (int wY = y - vertDist - 1; wY <= y + vertDist - 1; wY++) {
                for (int wZ = z - horizDist; wZ <= z + horizDist; wZ++) {
                    if (world.getBlock(wX, wY, wZ).getMaterial() == Material.water) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static double getLengthFromPixels(int pixels) {
        return (1.0d / 16.0D) * (double) pixels;
    }
}
