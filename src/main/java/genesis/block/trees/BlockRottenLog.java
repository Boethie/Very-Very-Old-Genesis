package genesis.block.trees;

import genesis.Genesis;
import genesis.lib.GenesisSounds;
import genesis.lib.GenesisTabs;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Rotten logs spawn mobs upon breaking
 */
public class BlockRottenLog extends BlockGenesisLog {

	public BlockRottenLog(int group) {
		super(group);
		setCreativeTab(GenesisTabs.tabGenesisDecoration);
		setStepSound(GenesisSounds.soundTypeRottenLog);
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
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[2];
		icons[0] = iconRegister.registerIcon(Genesis.ASSETS + "log_" + treeType.name().toLowerCase() + "_rotten");
		icons[1] = iconRegister.registerIcon(Genesis.ASSETS + "log_" + treeType.name().toLowerCase() + "_rotten_top");
	}
}
