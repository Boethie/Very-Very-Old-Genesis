package genesis.genesis.lib;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class MiscHelpers {
	
	public static boolean isWaterInRange(World world, int x, int y, int z, int horizDist, int vertDist)
	{
        for (int wX = x - horizDist; wX <= x + horizDist; wX++)
        {
            for (int wY = y - vertDist - 1; wY <= y + vertDist - 1; wY++)
            {
                for (int wZ = z - horizDist; wZ <= z + horizDist; wZ++)
                {
                    if (world.getBlockMaterial(wX, wY, wZ) == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
	}
	
}
