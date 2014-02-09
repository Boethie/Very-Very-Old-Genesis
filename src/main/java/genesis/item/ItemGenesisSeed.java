package genesis.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
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

	protected int cropID;

	/**
	 * 
	 * @param seedID
	 * @param cropID
	 * @param soilID
	 * @param seedname
	 * @param healAmmount
	 * @param saturationModifier
	 */
	public ItemGenesisSeed(int seedID, int cropID, int soilID, String seedname, int healAmmount, int saturationModifier) {
		super(seedID, healAmmount, saturationModifier, false);

		this.cropID = cropID;
		setCreativeTab(Genesis.tabGenesis);
		setUnlocalizedName(Names.itemSeed + seedname);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 != 1)
			return false;
		else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
			int i1 = par3World.getBlockId(par4, par5, par6);
			Block soil = Block.blocksList[i1];

			if (soil != null && soil.canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, this) && par3World.isAirBlock(par4, par5 + 1, par6)) {
				par3World.setBlock(par4, par5 + 1, par6, cropID);
				--par1ItemStack.stackSize;
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
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return cropID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return 0;
	}
}
