package genesis.block.plants;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
import genesis.lib.Names;
import genesis.managers.GenesisModBlocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockGenesisFlower extends BlockFlower implements IPlantRenderSpecials, IPlantInFlowerPot {
    public EnumPlantType defaultType = EnumPlantType.Plains;
    public EnumPlantType[] typesPlantable = {};
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;
    private EnumPlantType testingType;

    public BlockGenesisFlower() {
        super(0);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setPlantBoundsSize(0.375F);
        setStepSound(soundTypeGrass);
    }

    public BlockGenesisFlower setPlantableTypes(EnumPlantType[] types) {
        typesPlantable = types;
        return this;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        if (testingType != null) {
            return testingType;
        }
        EnumPlantType output = EnumPlantType.Plains;
        for (EnumPlantType type : typesPlantable) {
            testingType = type;
            if (world.getBlock(x, y - 1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this)) {
                output = type;
            }
        }
        testingType = null;
        return output;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        int size = Names.Plants.FLOWER_TYPES.size();
        for (int i = 0; i < size; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    protected void setPlantBoundsSize(float size) {
        setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        blockIcons = new IIcon[Names.Plants.FLOWER_TYPES.size()];
        for (int i = 0; i < blockIcons.length; i++) {
            blockIcons[i] = register.registerIcon(Genesis.ASSETS + Names.Plants.FLOWER_TYPES.get(i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return blockIcons[meta];
    }

    @Override
    public boolean canPlaceBlockOn(Block block) {
        return super.canPlaceBlockOn(block) || block == GenesisModBlocks.moss;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        boolean out = false;
        Block block = world.getBlock(x, y - 1, z);
        if (block != null) {
            out = block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
        }
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
    public void updateTick(World world, int x, int y, int z, Random random) {
        dropIfCannotStay(world, x, y, z);
    }

    @Override
    public float renderScale(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    @Override
    public int getRenderColor(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    @Override
    public IIcon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
        return blockIcons[plantMetadata];
    }

    @Override
    public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    @Override
    public double randomPos(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    @Override
    public double randomYPos(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}