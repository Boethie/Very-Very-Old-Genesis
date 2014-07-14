package genesis.block.plants;

import genesis.block.ModBlocks;
import genesis.common.Genesis;
import genesis.item.ModItems;
import genesis.lib.Author;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Author("Arbiter")
public class BlockSphenophyllumBase extends BlockGenesisCrop
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockSphenophyllumBase()
	{
		super(ModItems.sphenoSpore, ModItems.sphenoFiber, Blocks.farmland, 8, 2);
		disableStats();
		setTickRandomly(true);
		setCreativeTab((CreativeTabs)null);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[stages];
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + 
					"_stage_" + i + (i >= 5 ? "_bottom" : ""));
		}
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return block == soilBlock || block == ModBlocks.moss || block == Blocks.grass;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta)
	{
		return icons[meta];
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= 5)
		{
			checkAndUpdateTop(world, x, y, z, meta);
		}
	}
	
	protected void checkAndUpdateTop(World world, int x, int y, int z, int meta)
	{
		if (meta >= 5)
		{
			if (world.getBlock(x, y + 1, z) == Blocks.air)
			{
				world.setBlock(x, y + 1, z, PlantBlocks.sphenoTop, meta - 5, 2);
			}
			else if (world.getBlock(x, y + 1, z) instanceof BlockSphenophyllumTop)
			{
				world.setBlockMetadataWithNotify(x, y + 1, z, meta - 5, 2);
			}
		}
	}
	
	@Override
	public int quantityDropped(int par1, int par2, Random random)
	{
		return par1 == stages - 1 ? random.nextInt(3) : 0;
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block b = world.getBlock(x, y + 1, z);
		if (b instanceof BlockSphenophyllumTop)
		{
			world.setBlockToAir(x, y + 1, z);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		int count = world.rand.nextInt(3) + 1;
		if (meta == 7)
		{
			for (int i = 0; i < count + fortune; i++)
			{
				list.add(new ItemStack(seedItem, 1, 0));
			}
			list.add(new ItemStack(cropItem, 1, 0));
		}
		return list;
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		return canPlaceBlockOn(world.getBlock(x, y - 1, z));
	}
}