package genesis.block.plants;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockAsteroxylonTop extends BlockGenesisPlantTop implements IShearable
{
	public BlockAsteroxylonTop()
	{
		super();
		float size = 0.375F;
		setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
	}

	@Override
	protected void updateBlock(World world, int x, int y, int z) 
	{
		Block b = world.getBlock(x, y - 1, z);
		if (b != null && b instanceof BlockAsteroxylon)
		{
			int meta = world.getBlockMetadata(x, y - 1, z);
			if (meta != 1)
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, 1, 2);
			}
		}
		else
		{
			world.setBlockToAir(x, y, z);
		}
	}	
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1)
	{
		return ColorizerGrass.getGrassColor(0.5d, 1.0d);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block below = world.getBlock(x, y - 1, z);
		if (below != null && below instanceof BlockAsteroxylon)
		{
			world.setBlockToAir(x, y - 1, z);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return new ItemStack(Item.getItemFromBlock(PlantBlocks.asteroxylon), 1, 1).getItem();
	}
	
	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) 
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) 
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(PlantBlocks.asteroxylon, 2, 0));
		return list;
	}
}