package genesis.genesis.block.trees;

import genesis.genesis.block.plants.BlockGenesisFlowerPot;
import genesis.genesis.block.plants.IPlantInFlowerPot;
import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;
import genesis.genesis.lib.IDs;
import genesis.genesis.world.WorldGenTreeBase;
import genesis.genesis.world.WorldGenTreeCordaites;
import genesis.genesis.world.WorldGenTreeLepidodendron;
import genesis.genesis.world.WorldGenTreeSigillaria;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockGenesisSapling extends BlockSapling implements IBlockGenesisTrees, IPlantInFlowerPot {
	
	private static final int SET_MASK = 3;
	
	public int saplingSet;
	private static Icon[] saplingIcons = null;
	
	public BlockGenesisSapling(int blockID, int set) {
		super(blockID);
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(soundGrassFootstep);
		
		this.saplingSet = set;
	}
	
	public int getSetID(int metadata)
	{
		return metadata & SET_MASK;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		int id = (saplingSet * 4) + getSetID(metadata);
		return this.saplingIcons[id];
	}

	@Override
	public int damageDropped(int metadata)
	{
		return getSetID(metadata);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
	{
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, this.saplingSet);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		saplingIcons = new Icon[TreeBlocks.woodTypeCount];
		
		for (int i = 0; i < TreeBlocks.woodTypeCount; ++i)
		{
			saplingIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":sapling_" + TreeBlocks.woodTypes.get(i).toLowerCase());
		}
	}
	
	@Override
	protected ItemStack createStackedBlock(int metadata)
	{
		return new ItemStack(this.blockID, 1, getSetID(metadata));
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return blockID;
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random rand)
	{
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;

		int metadata = world.getBlockMetadata(x, y, z);
		WorldGenTreeBase gen = TreeBlocks.getTreeGenerator(TreeBlocks.woodTypes.get(getSetID(metadata)));
		
		if (gen != null)
		{
			world.setBlock(x, y, z, 0, 0, 4);
			
			if (!gen.generate(world, rand, x, y, z))
			{
				world.setBlock(x, y, z, this.blockID, metadata, 4);
			}
		}
	}

	@Override
	public int getBlockSet()
	{
		return this.saplingSet;
	}

	@Override
	public float renderScale(IBlockAccess world, int x, int y, int z)
	{
		return 0.75F;
	}
	
	@Override
	public int getRenderColor(IBlockAccess world, int x, int y, int z)
	{
        return 16777215;
	}
	
	@Override
	public Icon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata)
	{
		//return getIcon(0, world.getBlockMetadata(x, y, z));
		return null;
	}

	@Override
	public Block getBlockForRender(IBlockAccess world, int x, int y, int z)
	{
		return null;
	}

}
