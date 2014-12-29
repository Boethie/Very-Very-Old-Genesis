package genesis.block.plants.aquatic;

import genesis.Genesis;
import genesis.lib.Names;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockGenesisSponge extends BlockGenesisAquaticPlant {
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockGenesisSponge() {
		super(Material.water);
		setStepSound(soundTypeCloth);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		int size = Names.Plants.SPONGE_TYPES.size();
		for (int i = 0; i < size; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[Names.Plants.SPONGE_TYPES.size()];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = register.registerIcon(Genesis.ASSETS + Names.Plants.SPONGE_TYPES.get(i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta) {
		return icons[meta];
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos parBlockPos, Entity entity)
	{
		//GET THE META CORRECTLY!
		if (world.getBlockMetadata(parBlockPos) == 5)
		{
			entity.attackEntityFrom(DamageSource.cactus, 1.0f);
		}
	}
}