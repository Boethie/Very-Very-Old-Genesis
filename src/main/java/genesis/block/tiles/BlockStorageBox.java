package genesis.block.tiles;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import genesis.Genesis;
import genesis.client.renderer.TileEntityStorageBoxRenderer;
import genesis.lib.GenesisTabs;
import genesis.tileentity.TileEntityStorageBox;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import static genesis.handler.GenesisGuiHandler.Element.STORAGE_BOX;

public class BlockStorageBox extends BlockChest{

	private final Random rand = new Random();
	
	public BlockStorageBox(int chest) {
		super(chest);
		this.setCreativeTab(GenesisTabs.tabGenesisDecoration);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
	}
	
	public int getRenderType(){
		return TileEntityStorageBoxRenderer.instance.renderID;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_){}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		byte b0 = 0;
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			b0 = 2;
		}

		if (l == 1)
		{
			b0 = 5;
		}

		if (l == 2)
		{
			b0 = 3;
		}

		if (l == 3)
		{
			b0 = 4;
		}

		if (block != this && block1 != this && block2 != this && block3 != this)
		{
			world.setBlockMetadataWithNotify(x, y, z, b0, 3);
		}
		else
		{
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5))
			{
				if (block == this)
				{
					world.setBlockMetadataWithNotify(x, y, z - 1, b0, 3);
				}
				else
				{
					world.setBlockMetadataWithNotify(x, y, z + 1, b0, 3);
				}

				world.setBlockMetadataWithNotify(x, y, z, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3))
			{
				if (block2 == this)
				{
					world.setBlockMetadataWithNotify(x - 1, y, z, b0, 3);
				}
				else
				{
					world.setBlockMetadataWithNotify(x + 1, y, z, b0, 3);
				}

				world.setBlockMetadataWithNotify(x, y, z, b0, 3);
			}
		}

		if (stack.hasDisplayName())
		{
			((TileEntityStorageBox)world.getTileEntity(x, y, z)).func_145976_a(stack.getDisplayName());
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        TileEntityStorageBox tileentitychest = (TileEntityStorageBox)world.getTileEntity(x, y, z);

        if (tileentitychest != null)
        {
            tileentitychest.updateContainingBlockInfo();
        }
    }
	
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntityStorageBox tileentitychest = (TileEntityStorageBox)world.getTileEntity(x, y, z);

        if (tileentitychest != null)
        {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f1, float f2, float f3)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            IInventory iinventory = this.func_149951_m(world, x, y, z);

            if (iinventory != null)
            {
                player.openGui(Genesis.instance, STORAGE_BOX.ordinal(), world, x, y, z);
            }

            return true;
        }
    }

    public static IInventory getInventory(World world, int x, int y, int z, Block block){
    	Object object = (TileEntityStorageBox)world.getTileEntity(x, y, z);

        if (object == null)
        {
            return null;
        }
        else if (world.isSideSolid(x, y + 1, z, DOWN))
        {
            return null;
        }
        else if (func_149953_o(world, x, y, z))
        {
            return null;
        }
        else if (world.getBlock(x - 1, y, z) == block && (world.isSideSolid(x - 1, y + 1, z, DOWN) || func_149953_o(world, x - 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x + 1, y, z) == block && (world.isSideSolid(x + 1, y + 1, z, DOWN) || func_149953_o(world, x + 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z - 1) == block && (world.isSideSolid(x, y + 1, z - 1, DOWN) || func_149953_o(world, x, y, z - 1)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z + 1) == block && (world.isSideSolid(x, y + 1, z + 1, DOWN) || func_149953_o(world, x, y, z + 1)))
        {
            return null;
        }
        else
        {
            if (world.getBlock(x - 1, y, z) == block)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (TileEntityStorageBox)world.getTileEntity(x - 1, y, z), (IInventory)object);
            }

            if (world.getBlock(x + 1, y, z) == block)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (IInventory)object, (TileEntityStorageBox)world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == block)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (TileEntityStorageBox)world.getTileEntity(x, y, z - 1), (IInventory)object);
            }

            if (world.getBlock(x, y, z + 1) == block)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (IInventory)object, (TileEntityStorageBox)world.getTileEntity(x, y, z + 1));
            }

            return (IInventory)object;
        }
    }
    
    public IInventory func_149951_m(World world, int x, int y, int z)
    {
        /*Object object = (TileEntityStorageBox)world.getTileEntity(x, y, z);

        if (object == null)
        {
            return null;
        }
        else if (world.isSideSolid(x, y + 1, z, DOWN))
        {
            return null;
        }
        else if (func_149953_o(world, x, y, z))
        {
            return null;
        }
        else if (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, DOWN) || func_149953_o(world, x - 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, DOWN) || func_149953_o(world, x + 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, DOWN) || func_149953_o(world, x, y, z - 1)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, DOWN) || func_149953_o(world, x, y, z + 1)))
        {
            return null;
        }
        else
        {
            if (world.getBlock(x - 1, y, z) == this)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (TileEntityStorageBox)world.getTileEntity(x - 1, y, z), (IInventory)object);
            }

            if (world.getBlock(x + 1, y, z) == this)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (IInventory)object, (TileEntityStorageBox)world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == this)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (TileEntityStorageBox)world.getTileEntity(x, y, z - 1), (IInventory)object);
            }

            if (world.getBlock(x, y, z + 1) == this)
            {
                object = new InventoryLargeChest("container.largeStorageBox", (IInventory)object, (TileEntityStorageBox)world.getTileEntity(x, y, z + 1));
            }

            return (IInventory)object;
        }*/
    	return getInventory(world, x, y, z, this);
    }
    
    private static boolean func_149953_o(World world, int x, int y, int z)
    {
        Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)x, (double)(y + 1), (double)z, (double)(x + 1), (double)(y + 2), (double)(z + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            Entity entity = (Entity)iterator.next();
            entityocelot = (EntityOcelot)entity;
        }
        while (!entityocelot.isSitting());

        return true;
    }

	public Block setBlockTextureName(String texture){
		return super.setBlockTextureName(Genesis.ASSETS + "texture");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityStorageBox();
	}
}
