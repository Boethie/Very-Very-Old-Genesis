package genesis.genesis.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.common.Genesis;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;

public class BlockTikiTorch extends BlockGenesis {

	public static final int TORCH_META = 8;
	public static final int DIR_META = 7;
	
	public static Icon tikiTorchLower;
	public static Icon tikiTorchUpper;
	
	protected BlockTikiTorch(int par1) {
		super(par1, Material.circuits);
		
		setTickRandomly(true);
		setCreativeTab(Genesis.tabGenesis);
		setHardness(0.0F);
		setLightValue(0.9375F);
	}
	
	public void registerBlock(String name)
	{
		super.registerBlock(name);
		
		Item.itemsList[blockID].setFull3D();
	}

	@Override
	public int getRenderType()
	{
		return BlockTikiTorchRenderer.renderID;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.tikiTorchUpper = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_upper");
		this.tikiTorchLower = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_lower");
		
		this.blockIcon = this.tikiTorchUpper;
	}
	
	public Icon getIcon(int side, int metadata)
	{
		return isUpper(metadata) ? this.tikiTorchUpper : this.tikiTorchLower;
	}
	
	@Override
	public String getItemIconName()
	{
		return Genesis.MOD_ID + ":" + getTextureName();
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
	
	public int setDirection(int metadata, int direction)
	{
		return (metadata & TORCH_META) | direction;
	}
	
	public int getDirection(int metadata)
	{
		return metadata & DIR_META;
	}
	
	public int setUpper(int metadata, boolean upper)
	{
		return (metadata & DIR_META) | (upper ? TORCH_META : 0);
	}
	
	public boolean isUpper(int metadata)
	{
		return (metadata & TORCH_META) != 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (isUpper(metadata))
		{
			double xPos = x + 0.5;
			double yPos = y + 0.7;
			double zPos = z + 0.5;
			
			double off = 0.125;
			
			switch (getDirection(metadata))
			{
			case 1:
				xPos -= off;
				break;
			case 2:
				xPos += off;
				break;
			case 3:
				zPos -= off;
				break;
			case 4:
				zPos += off;
				break;
			}
	
			world.spawnParticle("smoke", xPos, yPos, zPos, 0, 0, 0);
			world.spawnParticle("flame", xPos, yPos, zPos, 0, 0, 0);
		}
		
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		checkIfCanStay(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourblockID)
	{
		checkIfCanStay(world, x, y, z);
	}
	
	public void checkIfCanStay(World world, int x, int y, int z)
	{
		if (!canTorchStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlock(x, y, z, 0);
		}
	}
	
	protected boolean canPlaceTorchOn(World world, int x, int y, int z)
	{
		if (world.doesBlockHaveSolidTopSurface(x, y, z))
		{
			return true;
		}
		else
		{
			Block block = Block.blocksList[world.getBlockId(x, y, z)];
			return block != null && block.canPlaceTorchOnTop(world, x, y, z);
		}
	}
	
	public boolean canPlaceTikiTorchAt(World world, int x, int y, int z)
	{
		return (world.isBlockSolidOnSide(x - 1, y, z, EAST,  true) ||
				world.isBlockSolidOnSide(x + 1, y, z, WEST,  true) ||
				world.isBlockSolidOnSide(x, y, z - 1, SOUTH, true) ||
				world.isBlockSolidOnSide(x, y, z + 1, NORTH, true) ||
				canPlaceTorchOn(world, x, y - 1, z)) &&
				world.getBlockMaterial(x, y + 1, z).isReplaceable();
	}
	
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack stack)
    {
    	if (canPlaceTikiTorchAt(world, x, y, z))
    		return true;
    	
    	if (world.getBlockMaterial(x, y - 1, z).isReplaceable())
    		return canPlaceTikiTorchAt(world, x, y - 1, z);
    	
    	return false;
    }
    
    protected int correctSide(World world, int x, int y, int z, int metadata)
    {
		if (!canTorchStay(world, x, y, z, metadata, true))
		{
			if (world.getBlockMaterial(x, y + 1, z).isReplaceable())
			{
				if (world.isBlockSolidOnSide(x - 1, y, z, EAST, true))
				{
					return 1;
				}
				else if (world.isBlockSolidOnSide(x + 1, y, z, WEST, true))
				{
					return 2;
				}
				else if (world.isBlockSolidOnSide(x, y, z - 1, SOUTH, true))
				{
					return 3;
				}
				else if (world.isBlockSolidOnSide(x, y, z + 1, NORTH, true))
				{
					return 4;
				}
				else if (canPlaceTorchOn(world, x, y - 1, z))
				{
					return 5;
				}
			}
			
			return -1;
		}
		
		return 0;
    }
	
	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side == 0)
			side = 1;
		
		int output = setDirection(metadata, 6 - side);
		
		if (world.getBlockMaterial(x, y + 1, z).isReplaceable())
		{
			output = setUpper(output, false);
		}
		
		int correctSide = correctSide(world, x, y, z, output);
		
		if (correctSide > 0)
		{
			output = setDirection(output, correctSide);
		}
		else if (correctSide < 0)
		{
			output = setUpper(output, true);
			y--;
			
			correctSide = correctSide(world, x, y, z, setUpper(output, false));
			
			if (correctSide > 0)
			{
				output = setDirection(output, correctSide);
			}
		}
		
		return output;
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (!isUpper(metadata))
		{
			if (world.getBlockMaterial(x, y + 1, z).isReplaceable())
				world.setBlock(x, y + 1, z, blockID, setUpper(metadata, true), 3);
		}
		else if (world.getBlockMaterial(x, y - 1, z).isReplaceable())
		{
			world.setBlock(x, y - 1, z, blockID, setUpper(metadata, false), 3);
		}
		else
		{
			checkIfCanStay(world, x, y, z);
		}
	}
	
	public boolean canTorchStay(World world, int x, int y, int z)
	{
		return canTorchStay(world, x, y, z, world.getBlockMetadata(x, y, z), false);
	}
		
	public boolean canTorchStay(World world, int x, int y, int z, int metadata, boolean blankUpper)
	{
		if (isUpper(metadata))
		{
			return world.getBlockId(x, y - 1, z) == this.blockID &&
					!isUpper(world.getBlockMetadata(x, y - 1, z));
		}
		else if ((blankUpper && world.getBlockMaterial(x, y + 1, z).isReplaceable()) ||
				(world.getBlockId(x, y + 1, z) == this.blockID &&
				isUpper(world.getBlockMetadata(x, y + 1, z))))
		{
			switch (getDirection(metadata))
			{
			case 1:
				if (world.isBlockSolidOnSide(x - 1, y, z, EAST, true))
					return true;
				break;
			case 2:
				if (world.isBlockSolidOnSide(x + 1, y, z, WEST, true))
					return true;
				break;
			case 3:
				if (world.isBlockSolidOnSide(x, y, z - 1, SOUTH, true))
					return true;
				break;
			case 4:
				if (world.isBlockSolidOnSide(x, y, z + 1, NORTH, true))
					return true;
				break;
			case 5:
				if (canPlaceTorchOn(world, x, y - 1, z))
					return true;
				break;
			}
		}
		
		return false;
	}
	
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end)
	{
		setBlockBounds(0.4F, 0.1875F, 0.4F,
				0.6F, 1.7875F, 0.6F);
		
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (isUpper(metadata))
		{
			this.minY -= 1;
			this.maxY -= 1;
		}
		
		double outOff = 0.125;
		
		switch (getDirection(metadata))
		{
		case 1:
			this.minX = 0;
			this.maxX -= outOff;
			break;
		case 2:
			this.minX += outOff;
			this.maxX = 1;
			break;
		case 3:
			this.minZ = 0;
			this.maxZ -= outOff;
			break;
		case 4:
			this.minZ += outOff;
			this.maxZ = 1;
			break;
		}
		
		return super.collisionRayTrace(world, x, y, z, start, end);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	public void breakBlock(World world, int x, int y, int z, int blockID, int metadata)
	{
		int otherX = 0;
		int otherY = 0;
		int otherZ = 0;
		
		if (!isUpper(metadata))
		{
			otherX = x;
			otherY = y + 1;
			otherZ = z;
		}
		else
		{
			otherX = x;
			otherY = y - 1;
			otherZ = z;
		}
		
		if (world.getBlockId(otherX, otherY, otherZ) == this.blockID)
		{
			world.playAuxSFX(2001, otherX, otherY, otherZ,
					this.blockID + (world.getBlockMetadata(otherX, otherY, otherZ) << 12));
			world.setBlockToAir(otherX, otherY, otherZ);
		}
	}

	public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
	{
		if (!isUpper(meta))
		{
			this.blockIcon = tikiTorchLower;
		}
		else
		{
			this.blockIcon = tikiTorchUpper;
		}
		
		return false;
	}
	
}
