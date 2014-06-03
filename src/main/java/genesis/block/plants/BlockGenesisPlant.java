package genesis.block.plants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import genesis.client.renderer.BlockGenesisPlantRenderer;
import genesis.common.Genesis;

public class BlockGenesisPlant extends BlockFlower implements IPlantRenderSpecials, IPlantInFlowerPot {

	public boolean stackable = false;
	public int stackedLimit = 1;
	public int maxAge = 15;

	public EnumPlantType defaultType = EnumPlantType.Plains;
	public EnumPlantType[] typesPlantable = {};
	private EnumPlantType testingType;

	protected BlockGenesisPlant() {
		super(0);

		setCreativeTab(Genesis.tabGenesis);
		setPlantBoundsSize(0.375F);
	}

	public BlockGenesisPlant setPlantableTypes(EnumPlantType[] types) {
		typesPlantable = types;
		return this;
	}

	/*
	 * Overridden method to make it possible to plant plants on multiple land
	 * types.
	 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		if (testingType != null)
			return testingType;

		EnumPlantType output = EnumPlantType.Plains;

		for (EnumPlantType type : typesPlantable) {
			testingType = type;

			if (world.getBlock(x, y - 1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this))
				output = type;
		}

		testingType = null;

		return output;
	}

	protected BlockGenesisPlant setStackable(int stackedLimit) {
		if (stackedLimit >= 0) {
			stackable = true;
			this.stackedLimit = stackedLimit;
		} else {
			stackable = false;
			stackedLimit = 0;
		}

		return this;
	}

	protected void setPlantBoundsSize(float size) {
		setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
	}

	@Override
	public int getRenderType() {
		return BlockGenesisPlantRenderer.renderID;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
	}

	protected int getVerticalPosition(IBlockAccess world, int x, int y, int z) {
		Block checkBlock = this;
		int count = world.getBlock(x, y, z) == this ? 1 : 0;
		int off = 1;

		while (checkBlock == this) {
			checkBlock = world.getBlock(x, y - off, z);

			if (checkBlock == this)
				count++;

			off++;
		}

		return count;
	}

	protected boolean isTop(World world, int x, int y, int z) {
		return world.getBlock(x, y + 1, z) != this;
	}

	protected int setAge(int metadata, int age) {
		return metadata & 15 - maxAge | MathHelper.clamp_int(age, 0, maxAge);
	}

	protected int getAge(int metadata) {
		return metadata & maxAge;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		int metadata = world.getBlockMetadata(x, y, z);
		int age = getAge(metadata);

		if (isTop(world, x, y, z) && getVerticalPosition(world, x, y, z) < stackedLimit)
			if (age >= maxAge && world.getBlock(x, y + 1, z).getMaterial().isReplaceable()) {
				world.setBlock(x, y + 1, z, this);
				age = -1;
			}

		world.setBlockMetadataWithNotify(x, y, z, setAge(metadata, age + 1), 3);
	}

	protected boolean placing = false;

	protected boolean canStayStacked(World world, int x, int y, int z, Block underBlock) {
		if (stackable && underBlock == this) {
			if (stackedLimit == 0)
				return true;

			if (getVerticalPosition(world, x, y, z) <= stackedLimit - (placing ? 1 : 0))
				return true;
		}

		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean out = false;
		Block block = world.getBlock(x, y - 1, z);

		if (block != null)
			if (block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this))
				out = true;
			else {
				out = canStayStacked(world, x, y, z, this);
				placing = true;
			}

		return out;
	}

	@Override
	public boolean canBlockStay(World par1World, int x, int y, int z) {
		placing = false;
		return canPlaceBlockAt(par1World, x, y, z);
	}

	protected void dropIfCannotStay(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		dropIfCannotStay(world, x, y, z);
	}

	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		/*
		 * if (!world.isRemote && side != 1) { updateTick(world, x, y, z,
		 * world.rand); }
		 */

		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public float renderScale(IBlockAccess world, int x, int y, int z) {
		return 1;
	}

	@Override
	public int getRenderColor(IBlockAccess world, int x, int y, int z) {
		return 16777215;
	}

	@Override
	public IIcon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
		return getIcon(world, x, y, z, 0);
	}

	@Override
	public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
		return null;
	}

}
