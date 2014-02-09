package genesis.block.plants;

import net.minecraft.world.IBlockAccess;

public interface IPlantRenderSpecials {
	
	boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side);
	
}
