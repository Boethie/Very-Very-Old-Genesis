package genesis.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.block.trees.TreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.item.itemblock.IItemBlockWithSubNames;

public class BlockGenesisLeaves extends BlockLeaves implements IItemBlockWithSubNames {

    protected String[] blockNames;
    protected IIcon[] blockIcons;
    
	public BlockGenesisLeaves(int group) {
		super();
		
		if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
			blockNames = new String[TreeType.GROUP_SIZE];
		else
			blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
		
		for (int i = 0; i < blockNames.length; i++)
			blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
		
		blockIcons = new IIcon[blockNames.length * 2];
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(soundTypeGrass);
		//setBurnProperties(blockID, 2, 8);
		setLightOpacity(1);
		setHardness(0.2F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < blockIcons.length; i += 2) {
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":leaves_" + blockNames[i / 2]);						// Fancy graphics texture
			blockIcons[i + 1] = iconRegister.registerIcon(Genesis.MOD_ID + ":leaves_" + blockNames[i / 2] + "_opaque");		// Fast graphics texture
		}
    }
	
	@Override
    public IIcon getIcon(int id, int metadata) {
		metadata &= 3;
		
        if (Minecraft.getMinecraft().gameSettings.fancyGraphics)
        	return blockIcons[metadata * 2];
        else
        	return blockIcons[((metadata + 1) * 2) - 1];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
		for (int metadata = 0; metadata < blockNames.length; metadata++)
			list.add(new ItemStack(item, 1, metadata));
    }
	
	@Override
	public Item getItemDropped(int metadata, Random random, int unused) {
		return Item.getItemFromBlock(TreeBlocks.blocksSaplings[TreeType.valueOf(getSubName(metadata).toUpperCase()).getGroup()]);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return !Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		// graphicsLevel = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		field_150121_P = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		return super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}
	
	@Override
	public String[] func_150125_e() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* IItemBlockWithSubNames methods */
	
	@Override
	public String getSubName(int metadata) {
		return blockNames[metadata & 3];
	}
}
