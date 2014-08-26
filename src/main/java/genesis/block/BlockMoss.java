package genesis.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.client.renderer.BlockMossRenderer;
import genesis.common.Genesis;
import genesis.common.GenesisSounds;
import genesis.common.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockMoss extends BlockGrass {
    protected IIcon iconGrassTop;
    protected IIcon iconSnowSide;
    protected IIcon iconSideOverlay;

    public BlockMoss() {
        super();
        setCreativeTab(GenesisTabs.tabGenesis);
        setHardness(0.6F);
        setStepSound(GenesisSounds.soundTypeMoss);
        setHarvestLevel("shovel", 0);
    }

    public static boolean isSnowed(IBlockAccess blockAccess, int x, int y, int z) {
        Material material = blockAccess.getBlock(x, y + 1, z).getMaterial();
        return material == Material.snow || material == Material.craftedSnow;
    }

    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        switch (plantType) {
        case Plains:
            return true;
        case Cave:
            return true;
        case Beach:
            return (world.getBlock(x - 1, y, z).getMaterial() == Material.water ||
                    world.getBlock(x + 1, y, z).getMaterial() == Material.water ||
                    world.getBlock(x, y, z - 1).getMaterial() == Material.water ||
                    world.getBlock(x, y, z + 1).getMaterial() == Material.water);
        default:
            return false;
        }
    }

    private boolean canMossStay(World world, int x, int y, int z) {
        int blockAboveLight = world.getBlockLightValue(x, y + 1, z);
        /*
         * Block blockAbove = blocksList[world.getBlockId(x, y + 1, z)];
         * Material blockAboveMat = world.getBlockMaterial(x, y, z);
         *
         * return blockAbove == null || blockAbove instanceof BlockFlower ||
         * !blockAboveMat.getCanBlockGrass() || blockAboveLight >= 1;
         */

        return blockAboveLight >= 1 || world.getBlockLightOpacity(x, y + 1, z) < 255;
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            // if (blockAboveLight < 1)
            // System.out.println("block " + blockAbove + " canblock " +
            // blockAboveLight);

            if (!canMossStay(world, x, y, z)) {
                world.setBlock(x, y, z, Blocks.dirt);
            } else if (world.getBlockLightValue(x, y + 1, z) <= 14) {
                for (int i = 0; i < 4; i++) {
                    int randX = x + random.nextInt(3) - 1;
                    int randY = y + random.nextInt(5) - 3;
                    int randZ = z + random.nextInt(3) - 1;

                    Block randBlock = world.getBlock(randX, randY, randZ);
                    int aboveLight = world.getFullBlockLightValue(randX, randY + 1, randZ);

                    if (randBlock == Blocks.dirt && aboveLight <= 14 && canMossStay(world, randX, randY, randZ)) {
                        world.setBlock(randX, randY, randZ, this);
                    }
                }
            }
        }
    }

    @Override
    public int getRenderType() {
        if (BlockMossRenderer.renderingInventory) {
            return 0;
        }

        return BlockMossRenderer.renderID;
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(getTextureName() + "_side");
        iconGrassTop = iconRegister.registerIcon(getTextureName() + "_top");
        iconSnowSide = iconRegister.registerIcon(getTextureName() + "_side_snowed");
        iconSideOverlay = iconRegister.registerIcon(getTextureName() + "_side_overlay");
    }

    @Override
    public IIcon getIcon(int side, int metdata) {
        return side == 1 ? iconGrassTop : (side == 0 ? Blocks.dirt.getBlockTextureFromSide(side) : blockIcon);
    }

    @Override
    public int getBlockColor() {
        return ColorizerGrass.getGrassColor(0.3, 0.3);
    }

    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        if (isSnowed(blockAccess, x, y, z) || BlockMossRenderer.pass == 0 || BlockMossRenderer.renderingInventory) {
            return 16777215;
        }

        return super.colorMultiplier(blockAccess, x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        boolean snow = isSnowed(blockAccess, x, y, z);

        if (!snow && BlockMossRenderer.pass == 0) {
            if (side == 0) {
                return Blocks.dirt.getBlockTextureFromSide(side);
            } else {
                return blockIcon;
            }
        } else {
            if (side == 1) {
                return iconGrassTop;
            } else if (side == 0) {
                return Blocks.dirt.getBlockTextureFromSide(side);
            } else {
                return snow ? iconSnowSide : iconSideOverlay;
            }
        }
    }

    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return !(BlockMossRenderer.pass == 1 && side == 0) && super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    public IIcon getPlainSideTexture() {
        return blockIcon;
    }
}
