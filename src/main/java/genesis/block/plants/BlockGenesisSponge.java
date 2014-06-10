package genesis.block.plants;

import genesis.common.Genesis;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockGenesisSponge extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockGenesisSponge()
	{
		super(Material.sponge);
		setHardness(0.6F);
		setResistance(0.0F);
		setStepSound(soundTypeCloth);
		setCreativeTab(Genesis.tabGenesis);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		boolean flag = false;
		Block[] blocks = new Block[] {
				world.getBlock(x + 1, y, z),
				world.getBlock(x - 1, y, z),
				world.getBlock(x, y + 1, z),
				world.getBlock(x, y, z + 1),
				world.getBlock(x, y, z - 1)
		};
		int index = 0, waterCount = 0;
		for (Block b : blocks)
		{
			if (index == 2)
			{
				continue;
			}
			else if (b == null)
			{
				flag = false;
				break;
			}
			else if (b == Blocks.water)
			{
				waterCount++;
			}
			else if (b == PlantBlocks.sponge)
			{
				waterCount++;
			}
			index++;
		}
		if (waterCount >= 1 && blocks[2] == Blocks.water)
		{
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canBlockStay(world, x, y, z);
	}
	
	protected boolean canPlaceBlockOn(Block block)
	{
		return block == Blocks.dirt || block == Blocks.gravel || 
				block == Blocks.sand || block == Blocks.clay;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
			return canPlaceBlockOn(world.getBlock(x, y - 1, z)) &&
					canSustainPlant(world, x, y, z, ForgeDirection.DOWN, null);
	}
	
	protected void dropSpongeIfCannotStay(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (!canBlockStay(world, x, y, z))
		{
			dropSpongeIfCannotStay(world, x, y, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		super.onNeighborBlockChange(world, x, y, z, block);
		if (!canBlockStay(world, x, y, z))
		{
			dropSpongeIfCannotStay(world, x, y, z);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		int size = PlantBlocks.spongeTypes.size();
		for (int i = 0; i < size; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[PlantBlocks.spongeTypes.size()];
		for (int i = 0; i < icons.length; i++)
		{
			icons[i] = register.registerIcon(Genesis.MOD_ID + ":" + PlantBlocks.spongeTypes.get(i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int meta)
	{
		return icons[meta];
	}
}