package genesis.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.block.trees.TreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.item.itemblock.IItemBlockWithSubNames;

public class BlockGenesisLog extends BlockLog implements IItemBlockWithSubNames {

	protected String[] blockNames;
	protected IIcon[] blockIcons;
	
	public BlockGenesisLog(int group) {
		if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
			blockNames = new String[TreeType.GROUP_SIZE];
		else
			blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
		
		for (int i = 0; i < blockNames.length; i++)
			blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
		
		blockIcons = new IIcon[blockNames.length * 2];
		
		setCreativeTab(Genesis.tabGenesis);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i += 2) {
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + blockNames[i / 2]);				// Side texture
			blockIcons[i + 1] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + blockNames[i / 2] + "_top");	// Top texture
		}
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		final int orientation = metadata & 12;
		int type = metadata & 3;
		
		if (type >= blockNames.length)
			type = 0;
		
		if ((orientation == 0 && (side == 1 || side == 0)) || (orientation == 4 && (side == 5 || side == 4)) || (orientation == 8 && (side == 2 || side == 3)))
			return blockIcons[((type + 1) * 2) - 1];
		else
			return blockIcons[type * 2];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int metadata = 0; metadata < blockNames.length; metadata++)
			list.add(new ItemStack(item, 1, metadata));
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int unused) {
		return Item.getItemFromBlock(TreeBlocks.blocksLogs[TreeType.valueOf(getSubName(metadata).toUpperCase()).getGroup()]);
	}
	
	/* IItemBlockWithSubNames methods */
	
	@Override
	public String getSubName(int metadata) {
		if (metadata >= blockNames.length)
			metadata = 0;
		
		return blockNames[metadata];
	}
}
