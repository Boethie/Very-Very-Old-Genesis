package genesis.block.tiles;

import static genesis.handler.GenesisGuiHandler.Element.CAMPFIRE;
import genesis.Genesis;
import genesis.client.renderer.BlockCampfireRenderer;
import genesis.lib.GenesisTabs;
import genesis.managers.GenesisModBlocks;
import genesis.tileentity.TileEntityCampfire;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCampfire extends BlockContainer {

    public static final int DIR_BITS = 7;
    public static final int FIRE_BIT = 8;

    protected IIcon poleIcon;
    protected IIcon poleVIcon;

    public BlockCampfire() {
        super(Material.rock);
        this.setHardness(0.8F);
        this.setHarvestLevel("pickaxe", 2);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setBlockBounds(0, 0, 0, 1, 1, 1);
        setTickRandomly(true);
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        poleIcon = iconRegister.registerIcon(getTextureName() + "_pole");
        poleVIcon = iconRegister.registerIcon(getTextureName() + "_v_pole");

        blockIcon = GenesisModBlocks.gneiss.getIcon(0, 0);
    }

    @Override
    public IIcon getIcon(int part, int metadata) {
        switch (part) {
        case 1:
            return poleIcon;
        case 2:
            return poleVIcon;
        default:
            return blockIcon;
        }
    }

    @Override
    public String getItemIconName() {
        return getTextureName();
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canBlockStay(world, x, y, z);
    }
    
    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {    	
    	return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        TileEntityCampfire campfire = getTileEntityAt(world, x, y, z);

        if (campfire != null && campfire.isBurning()) {
            double randAmtH = 0.375;
            double randAmtV = 0.375;

            double mX = x + 0.5;
            double mY = y + 0.0625;
            double mZ = z + 0.5;
            
            world.playSound(mX, ((double)y) + 0.5d, mZ, "fire.fire", 1.0f + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);

            for (int i = 0; i < rand.nextInt(2); i++) {
                double pX = mX - randAmtH / 2 + rand.nextFloat() * randAmtH;
                double pY = mY + rand.nextFloat() * randAmtV;
                double pZ = mZ - randAmtH / 2 + rand.nextFloat() * randAmtH;

                world.spawnParticle("largesmoke", pX, pY, pZ, 0, 0, 0);
            }

            if (campfire.getStackInSlot(0) != null) {
                randAmtH = 0.25;
                randAmtV = 0.25;
                mY = y + 1;

                for (int i = 0; i < rand.nextInt(3); i++) {
                    double offX = rand.nextFloat() * randAmtH - randAmtH / 2;
                    double offZ = rand.nextFloat() * randAmtH - randAmtH / 2;

                    double pX = mX + offX;
                    double pY = mY + rand.nextFloat() * randAmtV;
                    double pZ = mZ + offZ;

                    world.spawnParticle("smoke", pX, pY, pZ, offX * 0.1, 0, offZ * 0.1);
                }
            }
        }
    }

    protected AxisAlignedBB makeBB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    protected void addIfIntersects(AxisAlignedBB aabb, AxisAlignedBB mask, List list) {
        if (aabb != null && aabb.intersectsWith(mask)) {
            list.add(aabb);
        }
    }

    protected AxisAlignedBB reverseBB(int x, int y, int z, AxisAlignedBB bb) {
        return makeBB(bb.minZ - z + x, bb.minY, -(bb.minX - x - 0.5) + z + 0.5, bb.maxZ - z + x, bb.maxY, -(bb.maxX - x - 0.5) + z + 0.5);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        int direction = getDirection(world.getBlockMetadata(x, y, z)) % 4;

        addIfIntersects(makeBB(x, y, z, x + 1, y + 0.25, z + 1), mask, list);

        double stickSize = 0.125;
        double stickSize2 = stickSize / 2;

        double cos45 = 0.70710678118654752440084436210485;
        double dist45 = cos45 * 0.5;

        AxisAlignedBB stick0 = null;
        AxisAlignedBB stick1 = null;

        switch (direction % 2) {
        case 0:
            stick0 = makeBB(x + 0.5 - stickSize2, y, z, x + 0.5 + stickSize2, y + 1, z + stickSize);
            stick1 = makeBB(x + 0.5 - stickSize2, y, z + 1 - stickSize, x + 0.5 + stickSize2, y + 1, z + 1);
            break;
        case 1:
            stick0 = makeBB(x + 0.5 + dist45 - stickSize, y, z + 0.5 + dist45 - stickSize, x + 0.5 + dist45 + stickSize2 * cos45, y + 1, z + 0.5 + dist45 + stickSize2 * cos45);
            stick1 = makeBB(x + 0.5 - dist45 + stickSize, y, z + 0.5 - dist45 + stickSize, x + 0.5 - dist45 - stickSize2 * cos45, y + 1, z + 0.5 - dist45 - stickSize2 * cos45);
            break;
        }

        switch (direction) {
        case 2:
        case 3:
            stick0 = reverseBB(x, y, z, stick0);
            stick1 = reverseBB(x, y, z, stick1);
        }

        addIfIntersects(stick0, mask, list);
        addIfIntersects(stick1, mask, list);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote) {
            TileEntityCampfire campfire = getTileEntityAt(world, x, y, z);

            if (campfire.isBurning()) {
                double posX = entity.posX;
                double posY = entity.posY + entity.ySize - entity.yOffset;
                double posZ = entity.posZ;

                double dist = 0.25 + 0.1;
                AxisAlignedBB fireBB = makeBB(x + 0.5, y, z + 0.5, x + 0.5, y + 0.3, z + 0.5);
                fireBB = fireBB.expand(dist, 0, dist);

                Vec3 vec = Vec3.createVectorHelper(posX, posY, posZ);

                if (fireBB.isVecInside(vec)) {
                    entity.setFire(8);

                    if (!entity.isImmuneToFire()) {
                        entity.attackEntityFrom(DamageSource.inFire, 1);
                    }
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return makeBB(x, y, z, x + 1, y + 1, z + 1);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        Random rand = world.rand;
        TileEntityCampfire campfire = getTileEntityAt(world, x, y, z);

        if (campfire != null) {
            for (int slot = 0; slot < campfire.getSizeInventory(); ++slot) {
                ItemStack stack = campfire.getStackInSlot(slot);

                if (stack != null) {
                    float offX = rand.nextFloat() * 0.8F + 0.1F;
                    float offY = rand.nextFloat() * 0.8F + 0.1F;
                    float offZ = rand.nextFloat() * 0.8F + 0.1F;

                    while (stack.stackSize > 0) {
                        int subSize = rand.nextInt(21) + 10;

                        if (subSize > stack.stackSize) {
                            subSize = stack.stackSize;
                        }

                        stack.stackSize -= subSize;

                        EntityItem dropItem = new EntityItem(world, x + offX, y + offY, z + offZ, new ItemStack(stack.getItem(), subSize, stack.getItemDamage()));

                        if (stack.hasTagCompound()) {
                            dropItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                        }

                        float mul = 0.05F;
                        dropItem.motionX = rand.nextGaussian() * mul;
                        dropItem.motionY = rand.nextGaussian() * mul + 0.2;
                        dropItem.motionZ = rand.nextGaussian() * mul;

                        world.spawnEntityInWorld(dropItem);
                    }
                }
            }

            // TODO: What was this supposed to do? Suggestion to author: write comments when using obfuscated method names.
            // world.func_96440_m(x, y, z, blockID);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    public int getDirection(int metadata) {
        return metadata & DIR_BITS;
    }

    private int setDirection(int metadata, int direction) {
        return metadata & ~DIR_BITS | direction & DIR_BITS;
    }

    public float getBlockRotationAt(IBlockAccess world, int x, int y, int z) {
        return getDirection(world.getBlockMetadata(x, y, z)) * 45;
    }

    public boolean isFireLit(int metadata) {
        return (metadata & FIRE_BIT) != 0;
    }

    public int setFireLit(int metadata, boolean lit) {
        return metadata & ~FIRE_BIT | (lit ? FIRE_BIT : 0);
    }

    @Override
    public int getRenderType() {
        return BlockCampfireRenderer.renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem();

        if (held != null && held.getItem() == Items.water_bucket) {
            TileEntityCampfire campfire = getTileEntityAt(world, x, y, z);
            campfire.setWet();

            return true;
        } else {
            player.openGui(Genesis.instance, CAMPFIRE.getId(), world, x, y, z);

            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCampfire();
    }

    public TileEntityCampfire getTileEntityAt(IBlockAccess world, int x, int y, int z) {
        TileEntity tileEnt = world.getTileEntity(x, y, z);

        if (tileEnt instanceof TileEntityCampfire) {
            return (TileEntityCampfire) tileEnt;
        }

        return null;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int direction = Math.round(-entity.rotationYaw / 45) + 2;

        world.setBlockMetadataWithNotify(x, y, z, setDirection(world.getBlockMetadata(x, y, z), direction), 2);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int lightVal = super.getLightValue(world, x, y, z);

        if (world.getBlock(x, y, z) == this && isFireLit(world.getBlockMetadata(x, y, z))) {
            return Math.max(lightVal, 15);
        }

        return lightVal;
    }

    public Random getRandomAt(IBlockAccess world, int x, int y, int z) {
        return new Random(x * 30000L + y * 300000000L + z * 3000000000000L);
    }
}
