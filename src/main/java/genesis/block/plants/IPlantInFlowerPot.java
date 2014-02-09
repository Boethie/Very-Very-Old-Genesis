package genesis.block.plants;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public interface IPlantInFlowerPot {
	
	public float renderScale(IBlockAccess world, int x, int y, int z);
	
	public int getRenderColor(IBlockAccess world, int x, int y, int z);
	
	public IIcon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata);

	public Block getBlockForRender(IBlockAccess world, int x, int y, int z);
	
}
