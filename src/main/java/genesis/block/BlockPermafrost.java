package genesis.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockPermafrost extends BlockGenesis {

	public BlockPermafrost() {
		super(Material.ground);
		
		setHardness(0.5F);
		setStepSound(soundTypeGravel);
		setTickRandomly(true);
	}
	
	// TODO: Currently mimics behaviour of ice, change if necessary
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.getSavedLightValue(EnumSkyBlock.Block, x, y + 1, z) > 8) {
			world.setBlock(x, y, z, Blocks.dirt);
		}
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(Blocks.dirt));
		return list;
	}
}
