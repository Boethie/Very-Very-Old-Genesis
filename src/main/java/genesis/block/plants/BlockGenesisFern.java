package genesis.block.plants;

import genesis.block.ModBlocks;
import genesis.common.Genesis;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * I am sure there is a much more easier and shorter way of writing this, but it is designed to be
 * flexible. i.e if we need to add/remove more ferns or change the properties of one. 
 * Code based off BlockGenesisFlower
 * @author Arbiter
 *
 */
public class BlockGenesisFern extends BlockTallGrass implements IPlantable, IShearable
{
	private IIcon[] blockIcons;
	public EnumPlantType defaultType = EnumPlantType.Plains;
	public EnumPlantType[] typesPlantable = {};
	
	public BlockGenesisFern()
	{
		super();
		setCreativeTab(Genesis.tabGenesis);
		setPlantBoundsSize(0.45f);
		setStepSound(soundTypeGrass);
	}
	
	public BlockGenesisFern setPlantableTypes(EnumPlantType[] types)
	{
		typesPlantable = types;
		return this;
	}
	
	protected void setPlantBoundsSize(float size)
	{
		setBlockBounds(0.5f - size, 0.0f, 0.5f - size, 0.5f + size, 1.0f, 0.5f + size);
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	/**
	 * Not fully written yet. No need at this moment as I have no idea where these ferns will
	 * generate.
	 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		if (typesPlantable.length == 0)
		{
			return defaultType;
		}
		return typesPlantable[0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		int count = PlantBlocks.fernTypes.size(); // so we only call size() once
		for (int i = 0; i < count; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcons = new IIcon[PlantBlocks.fernTypes.size()];
		for (int i = 0; i < blockIcons.length; i++)
		{
			blockIcons[i] = register.registerIcon(Genesis.MOD_ID + ":" + PlantBlocks.fernTypes.get(i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 >= blockIcons.length)
		{
			par2 = 0;
		}
		return blockIcons[par2];
	}
	
	protected void dropIfCannotStay(World world, int x, int y, int z)
	{
		if (!canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		dropIfCannotStay(world, x, y, z);
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
		list.add(new ItemStack(this, 1, 0));
		return list;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = quantityDropped(meta, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            Item item = getItemDropped(meta, world.rand, fortune);
            if (item != null)
            {
                ret.add(new ItemStack(item, 1, meta));
            }
        }
        return ret;
	}
	
	@Override
	public int quantityDroppedWithBonus(int par1, Random random)
	{
		return 0;
	}
	
	// biome-dependent color code
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
	protected boolean canPlaceBlockOn(Block block)
	{
		return super.canPlaceBlockOn(block) || block == ModBlocks.moss;
	}
}