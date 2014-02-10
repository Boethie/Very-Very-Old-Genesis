package genesis.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;
import genesis.lib.Names;

public class ItemGenesisSeed extends ItemFood implements IPlantable {

	protected Block cropBlock;

	/**
	 * 
	 * @param cropBlock
	 * @param seedName
	 * @param healAmmount
	 * @param saturationModifier
	 */
	public ItemGenesisSeed(Block cropBlock, String seedName, int healAmmount, float saturationModifier) {
		super(healAmmount, saturationModifier, false);

		this.cropBlock = cropBlock;
		setCreativeTab(Genesis.tabGenesis);
		setUnlocalizedName(Names.itemSeed + seedName);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y + 1, z, cropBlock);
				stack.stackSize--;
				
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void registerItem(String name) {
		GameRegistry.registerItem(this, name);
	}

	@Override
	public Item setUnlocalizedName(String unlocName) {
		registerItem(unlocName);
		return super.setUnlocalizedName(unlocName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(Genesis.MOD_ID + ":" + getIconString());
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return cropBlock;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
