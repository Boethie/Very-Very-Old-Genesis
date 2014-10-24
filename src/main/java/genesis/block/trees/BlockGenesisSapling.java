package genesis.block.trees;

import genesis.Genesis;
import genesis.block.plants.IPlantInFlowerPot;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.lib.GenesisTabs;
import genesis.lib.Names;
import genesis.managers.GenesisModBlocks;
import genesis.world.gen.feature.WorldGenTreeBase;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenesisSapling extends BlockSapling implements IPlantInFlowerPot{

	private TreeType treeType;

	public BlockGenesisSapling(int type) {
		super();
		setCreativeTab(GenesisTabs.tabGenesisDecoration);
		setStepSound(soundTypeGrass);
		setHardness(0.0F);
		treeType = TreeType.get(type);
	}

	public String getUnlocalizedName(){
		return "tile." + Names.blockSaplingGenesis + treeType.name().toLowerCase();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(Genesis.ASSETS + "sapling_" + treeType.name().toLowerCase());
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){
		return blockIcon;
	}

	public int damageDropped(int meta){
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public void func_149878_d(World world, int x, int y, int z, Random random) {
		if (!TerrainGen.saplingGrowTree(world, random, x, y, z)) {
			return;
		}

		WorldGenTreeBase gen = GenesisTreeBlocks.getTreeGenerator(treeType);

		if (gen == null) {
			return;
		}

		world.setBlock(x, y, z, Blocks.air, 0, 4);

		if (!gen.generate(world, random, x, y, z)) {
			world.setBlock(x, y, z, this, 0, 4);
		}
	}

	/* IPlantInFlowerPot methods */

	@Override
	public float renderScale(IBlockAccess world, int x, int y, int z) {
		return 0.75F;
	}

	@Override
	public int getRenderColor(IBlockAccess world, int x, int y, int z) {
		return 16777215;
	}

	@Override
	public IIcon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
		// return getIcon(0, world.getBlockMetadata(x, y, z);
		return null;
	}

	protected boolean canPlaceBlockOn(Block block) {
		return super.canPlaceBlockOn(block) || block == GenesisModBlocks.moss;
	}

	@Override
	public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
		return null;
	}
}
