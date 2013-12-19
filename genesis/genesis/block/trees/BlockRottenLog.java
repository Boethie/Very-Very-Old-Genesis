package genesis.genesis.block.trees;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.common.Genesis;

/**
 * Rotten logs spawn mobs upon breaking
 */
public class BlockRottenLog extends BlockGenesisLog {

	public BlockRottenLog(int id, int group) {
		super(id, group);
		
		// setStepSound(BlockSounds.soundRottenLogFootstep);
	}
	
	// TODO: Change Silverfish to final mob
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int side) {
		if (!world.isRemote) {
			EntitySilverfish silverfish = new EntitySilverfish(world);
			silverfish.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(silverfish);
			silverfish.spawnExplosionParticle();
		}
		
		super.onBlockDestroyedByPlayer(world, x, y, z, side);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i += 2) {
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_"+ blockNames[i / 2] + "_rotten");				// Side texture
			blockIcons[i + 1] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + blockNames[i / 2] + "_rotten_top");	// Top texture
		}
	}
}
