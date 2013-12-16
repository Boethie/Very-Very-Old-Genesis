package genesis.genesis.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockPermafrost extends BlockGenesis {

	public BlockPermafrost(int blockID) {
		super(blockID, Material.ground);
		
		setHardness(0.6F);
		setStepSound(soundGravelFootstep);
		setTickRandomly(true);
	}
	
	public int idDropped(int par1, Random random, int par3) {
		return Block.dirt.blockID;
	}
	
	// TODO: Currently mimics behaviour of ice, change if necessary
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.getSavedLightValue(EnumSkyBlock.Block, x, y + 1, z) > 8)
			world.setBlock(x, y, z, Block.dirt.blockID);
	}
}
