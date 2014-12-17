package genesis.block.tiles;

import static genesis.handler.GenesisGuiHandler.Element.POLISSOIR;
import genesis.Genesis;
import genesis.client.ClientProxy;
import genesis.lib.GenesisTabs;
import genesis.tileentity.TileEntityPolissoir;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPolissoir extends BlockContainer {

    public BlockPolissoir() {
        super(Material.rock);
        setHarvestLevel("pickaxe", 0);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public static void updatePolissoirBlockState(World world, int x, int y, int z) {
        TileEntity tileentity = world.getTileEntity(x, y, z);

        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte metadata = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                metadata = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                metadata = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                metadata = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                metadata = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
        }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityPolissoir();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        player.openGui(Genesis.instance, POLISSOIR.getId(), world, x, y, z);
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        int rotation = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (rotation == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (rotation == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (rotation == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (rotation == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName()) {
            ((TileEntityPolissoir) world.getTileEntity(x, y, z)).setInventoryName(itemStack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        TileEntityPolissoir polissoir = (TileEntityPolissoir) world.getTileEntity(x, y, z);

        if (polissoir != null) {
            for (int i1 = 0; i1 < polissoir.getSizeInventory(); ++i1) {
                ItemStack itemstack = polissoir.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j1 = world.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, par6);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    @Override
    public int getRenderType() {
        return ClientProxy.polissoirRenderID;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }


}
