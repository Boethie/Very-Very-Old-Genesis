package genesis.block.plants;

import genesis.Genesis;
import genesis.block.BlockGenesis;
import genesis.lib.GenesisTabs;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockMycelia extends BlockGenesis
{
	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcons;
	
	public BlockMycelia()
	{
		super(Material.ground);
		setCreativeTab(GenesisTabs.tabGenesisBlock);
		setStepSound(soundTypeGrass);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcons = new IIcon[2];
		blockIcons[0] = register.registerIcon(Genesis.MOD_ID + ":prototaxites_mycelia_side");
		blockIcons[1] = register.registerIcon(Genesis.MOD_ID + ":prototaxites_mycelia_top");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? blockIcons[1] : (side == 0 ? Blocks.dirt.getIcon(side, 0) : blockIcons[0]);
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2, int par3)
	{
		return Blocks.dirt.getItemDropped(0, par2, par3);
	}
}