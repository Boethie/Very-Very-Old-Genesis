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
import genesis.block.plants.BlockGenesisCrop;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.lib.Names;

public class ItemGenesisSeed extends ItemFood implements IPlantable{

	protected BlockGenesisCrop cropBlock;
	protected boolean edible;
	protected boolean farmlandCrop;
	/**
	 * 
	 * @param cropBlock
	 * @param healAmmount
	 * @param saturationModifier
	 */
	public ItemGenesisSeed(BlockGenesisCrop cropBlock, int healAmmount, float saturationModifier,boolean farmlandCrop) {
		super(healAmmount, saturationModifier, false);
		this.farmlandCrop=farmlandCrop;
		this.edible=true;
		this.cropBlock = cropBlock;
		setCreativeTab(GenesisTabs.tabGenesisMaterials);
	}
	/**
	 * Not edible version
	 * @param cropBlock
	 */
	public ItemGenesisSeed(BlockGenesisCrop cropBlock,boolean farmlandCrop) {
		super(0,0, false);
		this.cropBlock = cropBlock;
		setCreativeTab(GenesisTabs.tabGenesisMaterials);
	}
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if(this.edible){
        	return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
        }
        return p_77659_1_;
    }
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this.farmlandCrop?this:cropBlock) && cropBlock.canPlaceBlockAt(world, x, y+1, z)) {
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
		// TODO Auto-generated method stub
		return EnumPlantType.Crop;
	}
	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return this.cropBlock;
	}
	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}
}
