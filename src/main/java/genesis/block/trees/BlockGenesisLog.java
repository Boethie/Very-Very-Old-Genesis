package genesis.block.trees;

import genesis.Genesis;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.lib.GenesisTabs;
import genesis.managers.GenesisModItems;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGenesisLog extends BlockLog{

	protected String[] blockNames;
	protected IIcon[] icons;

	protected TreeType treeType;

	public BlockGenesisLog(int type) {
		super();
		treeType = TreeType.get(type);
		setCreativeTab(GenesisTabs.tabGenesisBlock);
	}

	public String getUnlocalizedName(){
        return super.getUnlocalizedName() + treeType.name().toLowerCase();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[2];
		String path = Genesis.ASSETS + "log_" + treeType.name().toLowerCase();
		icons[0] = register.registerIcon(path);
		icons[1] = register.registerIcon(path + "_top");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getSideIcon(int meta){
		return icons[0];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getTopIcon(int p_150161_1_){
		return icons[1];
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		if (!canSilkHarvest(world, player, x, y, z, metadata) || !EnchantmentHelper.getSilkTouchModifier(player)) {
			harvesters.set(player);

			int fortune = EnchantmentHelper.getFortuneModifier(player);
			dropBlockAsItem(world, x, y, z, metadata, fortune);

			if (player.getHeldItem() != null) {
				if (player.getHeldItem().getItem().getToolClasses(player.getHeldItem()).contains("axe")){
					if (treeType.equals(TreeType.CORDAITES, TreeType.ARAUCARIOXYLON, TreeType.VOLTZIA)) {
						if (world.rand.nextInt(20) == 0) {
							dropBlockAsItem(world, x, y, z, new ItemStack(GenesisModItems.resin));
						}
					}
				}
			}

			harvesters.set(null);
		} else {
			super.harvestBlock(world, player, x, y, z, metadata);
		}
	}
}
