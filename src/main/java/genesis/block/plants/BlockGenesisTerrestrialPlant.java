package genesis.block.plants;

import genesis.block.ModBlocks;
import genesis.common.Genesis;
import genesis.lib.PlantMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenesisTerrestrialPlant extends BlockFlower implements IPlantRenderSpecials, IPlantInFlowerPot, IShearable {

	public IIcon[] blockIcons;

	public EnumPlantType defaultType = EnumPlantType.Plains;
	public EnumPlantType[] typesPlantable = {};
	private EnumPlantType testingType;

	protected BlockGenesisTerrestrialPlant() {
		super(0);

		setCreativeTab(Genesis.tabGenesis);
		setPlantBoundsSize(0.375F);

		setStepSound(soundTypeGrass);
	}

	public BlockGenesisTerrestrialPlant setPlantableTypes(EnumPlantType[] types) {
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

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemList) {
		int size = PlantMetadata.plantTypes.size();
		for (int set = 0; set < size; set++)
			itemList.add(new ItemStack(item, 1, set));
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	protected void setPlantBoundsSize(float size) {
		setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcons = new IIcon[PlantMetadata.plantTypes.size()];
		
		for (int i = 0; i < blockIcons.length; i++)	{
			blockIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":" + PlantMetadata.plantTypes.get(i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return blockIcons[par2];
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

	@Override
	public Item getItemDropped(int par1, Random random, int meta)
	{
		return null;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		dropIfCannotStay(world, x, y, z);
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return super.canPlaceBlockOn(block) || block == ModBlocks.moss;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean out = false;
		Block block = world.getBlock(x, y - 1, z);
		
		if (block != null)
			out = block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		return out;
	}

	@Override
	public boolean canBlockStay(World par1World, int x, int y, int z) {
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
		 * if (!world.isRemote && side != 1) updateTick(world, x, y, z,
		 * world.rand);
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
		return this;
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
		list.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return list;
	}
}
