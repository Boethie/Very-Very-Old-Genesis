package genesis.item;

import genesis.block.plants.BlockGenesisCrop;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemGenesisSeed extends ItemFood implements IPlantable {
    protected BlockCrops cropBlock;
    protected boolean isEdible;
    protected boolean isFarmlandCrop;

    public ItemGenesisSeed(BlockCrops cropBlock, boolean farmlandCrop, int healAmmount, float saturationModifier) {
        this(cropBlock, farmlandCrop, healAmmount, saturationModifier, true);
    }

    /**
     * Not edible version
     */
    public ItemGenesisSeed(BlockCrops blockCrop, boolean farmlandCrop) {
        this(blockCrop, farmlandCrop, 0, 0, false);
        setCreativeTab(GenesisTabs.tabGenesisMaterials);
    }

    public ItemGenesisSeed(BlockCrops blockCrop, boolean farmlandCrop, int healAmount, float saturationModifier, boolean edible) {
        super(healAmount, saturationModifier, false);
        cropBlock = blockCrop;
        isFarmlandCrop = farmlandCrop;
        isEdible = edible;
        setCreativeTab(GenesisTabs.tabGenesisFood);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (isEdible) {
            return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
        }
        return p_77659_1_;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
            Block soil = world.getBlock(x, y, z);

            if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, isFarmlandCrop ? this : cropBlock) && cropBlock.canPlaceBlockAt(world, x, y + 1, z)) {
                world.setBlock(x, y + 1, z, cropBlock);
                stack.stackSize--;

                return true;
            }
        }

        return false;
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName);
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
