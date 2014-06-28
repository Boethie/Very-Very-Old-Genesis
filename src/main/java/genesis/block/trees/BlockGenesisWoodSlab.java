package genesis.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.block.trees.TreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.item.itemblock.IItemBlockWithSubNames;

public class BlockGenesisWoodSlab extends BlockWoodSlab implements IItemBlockWithSubNames {

	protected String[] blockNames;
	protected IIcon[] blockIcons;
	
	private static Block[] singleSlabBlocks = new Block[TreeType.getNumGroups()];
	
	public BlockGenesisWoodSlab(int group, boolean isDouble) {
		super(isDouble);
		
		if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
			blockNames = new String[TreeType.GROUP_SIZE];
		else
			blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
		
		for (int i = 0; i < blockNames.length; i++)
			blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
		
		blockIcons = new IIcon[blockNames.length];
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(Block.soundTypeWood);
		setHardness(2.0F);
		setResistance(5.0F);
		//setLightOpacity(0);
		
		if (!isDouble)
			singleSlabBlocks[group] = this;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i++)
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":planks_" + blockNames[i]);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcons[metadata & 3];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (Block block : singleSlabBlocks) {
			if (item == Item.getItemFromBlock(block)) {				
				for (int metadata = 0; metadata < blockNames.length; metadata++)
					list.add(new ItemStack(item, 1, metadata));
				
				return;
			}
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int group = TreeType.valueOf(getSubName(world.getBlockMetadata(x, y, z)).toUpperCase()).getGroup();
		return new ItemStack(Item.getItemFromBlock(singleSlabBlocks[group]), 1, world.getBlockMetadata(x, y, z) & 3);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int unused) {
		int group = TreeType.valueOf(getSubName(metadata).toUpperCase()).getGroup();
		return Item.getItemFromBlock(singleSlabBlocks[group]);
	}
	
	@Override
	// public string getFullSlabName(int metadata) { 
	public String func_150002_b(int metadata) {
		return getUnlocalizedName() + getSubName(metadata);
	}
	
	/* IItemBlockWithSubNames methods */
	
	@Override
	public String getSubName(int metadata) {
		return blockNames[metadata & 3];
	}
}
