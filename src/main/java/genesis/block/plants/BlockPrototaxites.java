package genesis.block.plants;

import genesis.Genesis;
import genesis.block.BlockGenesis;
import genesis.lib.GenesisTabs;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockPrototaxites extends BlockGenesis implements IPlantable
{
	@SideOnly(Side.CLIENT)
	private IIcon[] blockIcons;
	
	public BlockPrototaxites()
	{
		super(Material.cactus);
		setStepSound(soundTypeCloth);
		setCreativeTab(GenesisTabs.tabGenesisDecoration);
		setTickRandomly(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? blockIcons[0] : (side == 0 ? blockIcons[1] : blockIcons[2]);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcons = new IIcon[3];
		blockIcons[0] = register.registerIcon(Genesis.MOD_ID + ":prototaxites_top");
		blockIcons[1] = register.registerIcon(Genesis.MOD_ID + ":prototaxites_bottom");
		blockIcons[2] = register.registerIcon(Genesis.MOD_ID + ":prototaxites_side");
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isAirBlock(x, y + 1, z))
		{
			if (random.nextInt(30) == 0) // feel free to modify the growth rate here
			{
				world.setBlock(x, y + 1, z, GenesisPlantBlocks.prototaxites, 2, 0);
			}
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y - 1, z);
		return (block == GenesisPlantBlocks.prototaxites || block == GenesisPlantBlocks.mycelia);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		if (b == null || b != GenesisPlantBlocks.mycelia || b != GenesisPlantBlocks.prototaxites)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!canBlockStay(world, x, y, z))
		{
			world.func_147480_a(x, y, z, true);		
		}	
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return EnumPlantType.Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
	{
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	{
		return 0;
	}
}