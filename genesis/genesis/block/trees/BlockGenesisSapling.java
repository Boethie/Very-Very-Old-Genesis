package genesis.genesis.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.plants.IPlantInFlowerPot;
import genesis.genesis.block.trees.TreeBlocks.TreeType;
import genesis.genesis.common.Genesis;
import genesis.genesis.item.itemblock.IItemBlockWithSubNames;
import genesis.genesis.world.WorldGenTreeBase;

public class BlockGenesisSapling extends BlockSapling implements IPlantInFlowerPot, IItemBlockWithSubNames {
	
	protected String[] blockNames;
	protected Icon[] blockIcons;
	
	public BlockGenesisSapling(int id, int group) {
		super(id);
		
		if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
			blockNames = new String[TreeType.GROUP_SIZE];
		else
			blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
		
		for (int i = 0; i < blockNames.length; i++)
			blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
		
		blockIcons = new Icon[blockNames.length];
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(soundGrassFootstep);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i++)
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":sapling_" + blockNames[i]);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return blockIcons[metadata & 3];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int metadata = 0; metadata < blockNames.length; metadata++)
			list.add(new ItemStack(blockID, 1, metadata));
	}
	
	@Override
	public int idDropped(int metadata, Random random, int unused) {
		return blockID;
	}
	
	@Override
	public void growTree(World world, int x, int y, int z, Random random) {
		if (!TerrainGen.saplingGrowTree(world, random, x, y, z))
			return;
		
		int metadata = world.getBlockMetadata(x, y, z) & 3;
		WorldGenTreeBase gen = TreeBlocks.getTreeGenerator(blockNames[metadata]);
		
		if (gen == null)
			return;
		
		world.setBlock(x, y, z, 0, 0, 4);
		
		if (!gen.generate(world, random, x, y, z))
			world.setBlock(x, y, z, blockID, metadata, 4);
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
	public Icon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
		// return getIcon(0, world.getBlockMetadata(x, y, z);
		return null;
	}

	@Override
	public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
		return null;
	}
	
	/* IItemBlockWithSubNames methods */
	
	@Override
	public String getSubName(int metadata) {
		return blockNames[metadata & 3];
	}
}
