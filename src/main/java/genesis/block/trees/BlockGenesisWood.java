package genesis.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.block.trees.TreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.item.itemblock.IItemBlockWithSubNames;

public class BlockGenesisWood extends BlockWood implements IItemBlockWithSubNames {

	protected String[] blockNames;
	protected IIcon[] blockIcons;
	
	public BlockGenesisWood(int id, int group) {
		super(id);
		
		if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
			blockNames = new String[TreeType.GROUP_SIZE];
		else
			blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
		
		for (int i = 0; i < blockNames.length; i++)
			blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
		
		blockIcons = new IIcon[blockNames.length];
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(Block.soundWoodFootstep);
		setBurnProperties(blockID, 4, 4);
		setHardness(2.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i++)
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":planks_" + blockNames[i]);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (metadata >= blockNames.length)
			metadata = 0;
		
		return blockIcons[metadata];
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

	/* IItemBlockWithSubNames methods */
	
	@Override
	public String getSubName(int metadata) {
		if (metadata >= blockNames.length)
			metadata = 0;
		
		return blockNames[metadata];
	}
}
