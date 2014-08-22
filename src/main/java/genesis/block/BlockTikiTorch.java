package genesis.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.client.renderer.BlockTikiTorchRenderer;
import genesis.common.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockTikiTorch extends BlockGenesis {

    public static final int TORCH_META = 8;
    public static final int DIR_META = 7;

    public static IIcon iconLower;
    public static IIcon iconUpper;

    protected BlockTikiTorch() {
        super(Material.circuits);

        setTickRandomly(true);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setHardness(0.0F);
        setLightLevel(0.9375F);
    }

    @Override
    public int getRenderType() {
        return BlockTikiTorchRenderer.renderID;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconUpper = iconRegister.registerIcon(getTextureName() + "_upper");
        iconLower = iconRegister.registerIcon(getTextureName() + "_lower");

        blockIcon = iconUpper;
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return isUpper(metadata) ? iconUpper : iconLower;
    }

    @Override
    public String getItemIconName() {
        return getTextureName();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public int setDirection(int metadata, int direction) {
        return metadata & TORCH_META | direction;
    }

    public int getDirection(int metadata) {
        return metadata & DIR_META;
    }

    public int setUpper(int metadata, boolean upper) {
        return metadata & DIR_META | (upper ? TORCH_META : 0);
    }

    public boolean isUpper(int metadata) {
        return (metadata & TORCH_META) != 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        int metadata = world.getBlockMetadata(x, y, z);

        if (isUpper(metadata)) {
            double xPos = x + 0.5;
            double yPos = y + 0.7;
            double zPos = z + 0.5;

            double off = 0.125;

            if (getDirection(metadata) != 5) {
                yPos += 0.1875;
            }
            switch (getDirection(metadata)) {
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
    public void updateTick(World world, int x, int y, int z, Random random) {
        checkIfCanStay(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        checkIfCanStay(world, x, y, z);
    }

    public void checkIfCanStay(World world, int x, int y, int z) {
        if (!canTorchStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    protected boolean canPlaceTorchOn(World world, int x, int y, int z) {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z))
            return true;
        else {
            Block block = world.getBlock(x, y, z);
            return block != null && block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    public boolean canPlaceTikiTorchAt(World world, int x, int y, int z) {
        return (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) ||
                world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) ||
                world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) ||
                world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) ||
                canPlaceTorchOn(world, x, y - 1, z)) && world.getBlock(x, y + 1, z).getMaterial().isReplaceable();
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canPlaceTikiTorchAt(world, x, y, z) || (world.getBlock(x, y - 1, z).getMaterial().isReplaceable() && canPlaceTikiTorchAt(world, x, y - 1, z));
    }

    protected int correctSide(World world, int x, int y, int z, int metadata) {
        if (!canTorchStay(world, x, y, z, metadata, true)) {
            if (world.getBlock(x, y + 1, z).getMaterial().isReplaceable())
                if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true))
                    return 1;
                else if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true))
                    return 2;
                else if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true))
                    return 3;
                else if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true))
                    return 4;
                else if (canPlaceTorchOn(world, x, y - 1, z))
                    return 5;

            return -1;
        }

        return 0;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata
     */
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (side == 0)
            side = 1;

        int output = setDirection(metadata, 6 - side);

        if (world.getBlock(x, y + 1, z).getMaterial().isReplaceable())
            output = setUpper(output, false);

        int correctSide = correctSide(world, x, y, z, output);

        if (correctSide > 0)
            output = setDirection(output, correctSide);
        else if (correctSide < 0) {
            output = setUpper(output, true);
            y--;

            correctSide = correctSide(world, x, y, z, setUpper(output, false));

            if (correctSide > 0)
                output = setDirection(output, correctSide);
        }

        return output;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int metadata = world.getBlockMetadata(x, y, z);

        if (!isUpper(metadata)) {
            if (world.getBlock(x, y + 1, z).getMaterial().isReplaceable())
                world.setBlock(x, y + 1, z, this, setUpper(metadata, true), 3);
        } else if (world.getBlock(x, y - 1, z).getMaterial().isReplaceable())
            world.setBlock(x, y - 1, z, this, setUpper(metadata, false), 3);
        else
            checkIfCanStay(world, x, y, z);
    }

    public boolean canTorchStay(World world, int x, int y, int z) {
        return canTorchStay(world, x, y, z, world.getBlockMetadata(x, y, z), false);
    }

    public boolean canTorchStay(World world, int x, int y, int z, int metadata, boolean blankUpper) {
        if (isUpper(metadata))
            return world.getBlock(x, y - 1, z) == this && !isUpper(world.getBlockMetadata(x, y - 1, z));
        else if (blankUpper && world.getBlock(x, y + 1, z).getMaterial().isReplaceable() || world.getBlock(x, y + 1, z) == this && isUpper(world.getBlockMetadata(x, y + 1, z)))
            switch (getDirection(metadata)) {
                case 1:
                    if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true))
                        return true;
                    break;
                case 2:
                    if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true))
                        return true;
                    break;
                case 3:
                    if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true))
                        return true;
                    break;
                case 4:
                    if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true))
                        return true;
                    break;
                case 5:
                    if (canPlaceTorchOn(world, x, y - 1, z))
                        return true;
                    break;
            }

        return false;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        setBlockBounds(0.4F, 0.1875F, 0.4F, 0.6F, 1.7875F, 0.6F);

        int metadata = world.getBlockMetadata(x, y, z);

        if (isUpper(metadata)) {
            minY -= 1;
            maxY -= 1;
        }

        double outOff = 0.125;

        switch (getDirection(metadata)) {
            case 1:
                minX = 0;
                maxX -= outOff;
                break;
            case 2:
                minX += outOff;
                maxX = 1;
                break;
            case 3:
                minZ = 0;
                maxZ -= outOff;
                break;
            case 4:
                minZ += outOff;
                maxZ = 1;
                break;
            default:
                minY -= 0.1875F;
                maxY -= 0.1875F;
                break;
        }

        return super.collisionRayTrace(world, x, y, z, start, end);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        int otherY = y;

        if (!isUpper(metadata))
            otherY++;
        else
            otherY--;

        if (world.getBlock(x, otherY, z) == this) {
            if (!world.isRemote) {
                /*
                 * PacketDispatcher.sendPacketToAllAround(otherX + 0.5, otherY +
				 * 0.5, otherZ + 0.5, 64, world.provider.dimensionId, new
				 * BreakingParticlesPacket(otherX, otherY, otherZ,
				 * world).makePacket());
				 */
            } else if (world == Minecraft.getMinecraft().theWorld)
                Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(x, otherY, z, this, world.getBlockMetadata(x, otherY, z));

            world.setBlockToAir(x, otherY, z);
        }

        super.breakBlock(world, x, y, z, this, metadata);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata) {
        if (world.isRemote)
            breakBlock(world, x, y, z, this, metadata);
    }

    @Override
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        if (!isUpper(meta))
            blockIcon = iconLower;
        else
            blockIcon = iconUpper;

        return false;
    }
}
