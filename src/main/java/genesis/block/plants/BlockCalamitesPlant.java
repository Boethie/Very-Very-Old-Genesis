package genesis.block.plants;

import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.lib.MiscHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCalamitesPlant extends BlockGenesisPlant {

	public static final int PLAIN_META_MASK = 7;
	public static final int EGGS_META = 8;
	
	@SideOnly(Side.CLIENT) IIcon calamitesPlant;
	@SideOnly(Side.CLIENT) IIcon calamitesPlantTop;

	@SideOnly(Side.CLIENT) IIcon calamitesPlantEggs1;
	@SideOnly(Side.CLIENT) IIcon calamitesPlantEggs2;

	@SideOnly(Side.CLIENT) IIcon calamitesPlantTopEggs1;
	@SideOnly(Side.CLIENT) IIcon calamitesPlantTopEggs2;

	public BlockCalamitesPlant() {
		setHardness(1.5F);
		setStepSound(soundTypeWood);
		setPlantBoundsSize(0.25F);
		setStackable(10);
		setPlantableTypes(new EnumPlantType[] { EnumPlantType.Plains, EnumPlantType.Desert });
		setHarvestLevel("axe", 0);
		setCreativeTab(GenesisTabs.tabGenesisMaterials);
	}

	public static class CalamitesProperties {

		public int height;
		public int position;
		public boolean hasEggs;
		public boolean top;
		public ArrayList<ChunkPosition> positions;

		public CalamitesProperties(int height, int position, boolean hasEggs, ArrayList<ChunkPosition> positions) {
			this.height = height;
			this.position = position;
			this.hasEggs = hasEggs;
			top = position == height - 1;
			this.positions = positions;
		}
	}

	public CalamitesProperties getProperties(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);

		if (block == this) {
			int height = 1;
			int position = 0;
			boolean hasEggs = hasEggs(metadata);

			int up = 2;
			int off = 1;
			ArrayList<ChunkPosition> downPositions = new ArrayList();
			ArrayList<ChunkPosition> upPositions = new ArrayList();

			do {
				if (up == 2)
					off = -1;
				else
					off = 1;

				do {
					block = world.getBlock(x, y + off, z);
					metadata = world.getBlockMetadata(x, y + off, z);

					if (block == this) {
						height++;

						if (up == 2) {
							position++;
							downPositions.add(new ChunkPosition(x, y + off, z));
						} else
							upPositions.add(new ChunkPosition(x, y + off, z));
					}

					if (!hasEggs && hasEggs(metadata))
						hasEggs = true;

					if (up == 2)
						off--;
					else
						off++;
				} while (block == this);

				up--;
			} while (up > 0);

			ArrayList<ChunkPosition> positions = new ArrayList();

			for (int i = downPositions.size() - 1; i >= 0; i--)
				positions.add(downPositions.get(i));

			positions.add(new ChunkPosition(x, y, z));

			for (int i = 0; i < upPositions.size(); i++)
				positions.add(upPositions.get(i));

			return new CalamitesProperties(height, position, hasEggs, positions);
		}

		return null;
	}

	private boolean isTop(IBlockAccess world, int x, int y, int z) {
		return world.getBlock(x, y + 1, z) != this;
	}

	private int setHasEggs(int metadata, boolean hasEggs) {
		return metadata & PLAIN_META_MASK | (hasEggs ? EGGS_META : 0);
	}

	private boolean hasEggs(int metadata) {
		return (metadata & EGGS_META) != 0;
	}

	@Override
	protected int setAge(int metadata, int age) {
		return metadata & EGGS_META | Math.min(age, PLAIN_META_MASK);
	}

	@Override
	protected int getAge(int metadata) {
		return metadata & PLAIN_META_MASK;
	}

	private void resetAll(World world, ArrayList<ChunkPosition> positions, int exceptY, boolean skipTop) {
		ChunkPosition lastPos = null;

		if (skipTop)
			lastPos = positions.get(positions.size() - 1);

		for (ChunkPosition pos : positions)
			if (pos != lastPos && pos.chunkPosY != exceptY) {
				int resetMetadata = world.getBlockMetadata(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
				world.setBlockMetadataWithNotify(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, setAge(resetMetadata, 0), 3);
			}
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return super.canPlaceBlockOn(block) || block == PlantBlocks.calamitesPlant
				|| block == Blocks.dirt;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canPlaceBlockOn(world.getBlock(x, y - 1, z))
				&& MiscHelpers.isWaterInRange(world, x, y - 1, z, 1, 15)
				&& canBlockStay(world, x, y, z); // 15 so the calamites can grow high
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return canPlaceBlockOn(world.getBlock(x, y - 1, z)) 
				&& MiscHelpers.isWaterInRange(world, x, y - 1, z, 1, 15) // 15 so the calamites can grow high
				&& canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;

		CalamitesProperties props = getProperties(world, x, y, z);

		int metadata = world.getBlockMetadata(x, y, z);
		int age = getAge(metadata);
		boolean resetAges = false;

		if (hasEggs(metadata) && age >= 4) {
			metadata = setHasEggs(metadata, false);
			resetAges = true;
		} else if (props.top && props.height < stackedLimit) {
			if (age >= PLAIN_META_MASK && world.getBlock(x, y + 1, z).getMaterial().isReplaceable() && rand.nextBoolean())
				world.setBlock(x, y + 1, z, this);
		} else if (!props.hasEggs && age >= PLAIN_META_MASK && rand.nextBoolean()) {
			metadata = setHasEggs(metadata, true);
			resetAges = true;
		}

		if (resetAges) {
			age = -1;
			resetAll(world, props.positions, y, props.height < stackedLimit);
		}

		world.setBlockMetadataWithNotify(x, y, z, setAge(metadata, age + 1), 3);
	}

	private boolean secondSide;
	private boolean reverseTex;

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side == 0) {
			Random rand = new Random((x + z) * y * y * x * z);
			rand.nextFloat(); // Just to (hopefully) help the booleans be more
								// random
			secondSide = rand.nextBoolean();
			reverseTex = rand.nextBoolean();
		}

		int metadata = world.getBlockMetadata(x, y, z);
		boolean isTop = isTop(world, x, y, z);

		if (hasEggs(metadata))
			if (secondSide ? side == 1 || side == 3 : side == 0 || side == 2)
				return isTop ? calamitesPlantTopEggs1 : calamitesPlantEggs1;
			else
				switch (side) {
					case 0:
						if (secondSide && !reverseTex)
							return isTop ? calamitesPlantTopEggs2 : calamitesPlantEggs2;
						break;
					case 1:
						if (!secondSide && reverseTex)
							return isTop ? calamitesPlantTopEggs2 : calamitesPlantEggs2;
						break;
					case 2:
						if (secondSide && reverseTex)
							return isTop ? calamitesPlantTopEggs2 : calamitesPlantEggs2;
						break;
					case 3:
						if (!secondSide && !reverseTex)
							return isTop ? calamitesPlantTopEggs2 : calamitesPlantEggs2;
						break;
				}

		return isTop ? calamitesPlantTop : calamitesPlant;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return calamitesPlant;
	}

	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
		return reverseTex;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		String texStart = Genesis.MOD_ID + ":" + getTextureName();

		calamitesPlant = iconRegister.registerIcon(texStart);
		calamitesPlantTop = iconRegister.registerIcon(texStart + "_top");

		calamitesPlantEggs1 = iconRegister.registerIcon(texStart + "_eggs_1");
		calamitesPlantEggs2 = iconRegister.registerIcon(texStart + "_eggs_2");
		calamitesPlantTopEggs1 = iconRegister.registerIcon(texStart + "_eggs_top_1");
		calamitesPlantTopEggs2 = iconRegister.registerIcon(texStart + "_eggs_top_2");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemIconName() {
		return Genesis.MOD_ID + ":" + getTextureName();
	}

	private boolean dropEggs(World world, int x, int y, int z, int metadata) {
		if (hasEggs(metadata)) {
			if (world.isRemote)
				return true;

			if (world.getBlock(x, y, z) == this)
				world.setBlockMetadataWithNotify(x, y, z, setHasEggs(metadata, false), 3);

			EntityItem itemDrop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(Items.egg));
			double div = Math.sqrt(itemDrop.motionX * itemDrop.motionX + itemDrop.motionY * itemDrop.motionY + itemDrop.motionZ * itemDrop.motionZ) * 2;
			double dirX = itemDrop.motionX / div;
			double dirY = itemDrop.motionY / div;
			double dirZ = itemDrop.motionZ / div;
			itemDrop.setPosition(itemDrop.posX + dirX, itemDrop.posY + dirY, itemDrop.posZ + dirZ);

			world.spawnEntityInWorld(itemDrop);

			return true;
		}

		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return dropEggs(world, x, y, z, world.getBlockMetadata(x, y, z));
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);

		if (!world.isRemote && world.rand.nextFloat() <= chance)
			dropEggs(world, x, y, z, metadata);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		return metadata;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		float size = 0.15F;
		return AxisAlignedBB.getBoundingBox(x + 0.5 - size, y, z + 0.5 - size, x + 0.5 + size, y + 1, z + 0.5 + size);
	}

	@Override
	public float renderScale(IBlockAccess world, int x, int y, int z) {
		return 0.75F;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}

}
