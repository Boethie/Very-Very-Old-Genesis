package genesis.tileentity;

import genesis.Genesis;
import genesis.block.tiles.BlockStorageBox;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

import java.util.Iterator;
import java.util.List;

public class TileEntityStorageBox extends TileEntityChest{

	private ItemStack[] contents = new ItemStack[36];
	private String inventoryName;
	private int cachedBoxType;

	public TileEntityStorageBox adjacentChestZNeg;
    public TileEntityStorageBox adjacentChestXPos;
    public TileEntityStorageBox adjacentChestXNeg;
    public TileEntityStorageBox adjacentChestZPos;
    private int ticksSinceSync;

    public String getInventoryName(){
		return this.hasCustomInventoryName() ? inventoryName : "container.storageBox";
	}

	public int getSizeInventory(){
		return 27;
	}

	public ItemStack getStackInSlot(int p_70301_1_){
		return this.contents[p_70301_1_];
	}

	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_){
		if (this.contents[p_70298_1_] != null){
			ItemStack itemstack;

			if (this.contents[p_70298_1_].stackSize <= p_70298_2_){
				itemstack = this.contents[p_70298_1_];
				this.contents[p_70298_1_] = null;
				this.markDirty();
				return itemstack;
			}
			else{
				itemstack = this.contents[p_70298_1_].splitStack(p_70298_2_);

				if (this.contents[p_70298_1_].stackSize == 0){
					this.contents[p_70298_1_] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int p_70304_1_){
		if (this.contents[p_70304_1_] != null){
			ItemStack itemstack = this.contents[p_70304_1_];
			this.contents[p_70304_1_] = null;
			return itemstack;
		}
		else{
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_){
		this.contents[p_70299_1_] = p_70299_2_;

		if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()){
			p_70299_2_.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public boolean hasCustomInventoryName(){
		return this.inventoryName != null && this.inventoryName.length() > 0;
	}

	public void func_145976_a(String name){
		this.inventoryName = name;
	}

	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];

		if (nbt.hasKey("customBoxName", 8)){
			this.inventoryName = nbt.getString("customBoxName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i){
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.contents.length){
				this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.contents.length; ++i){
			if (this.contents[i] != null){
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.contents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()){
			nbt.setString("customBoxName", this.inventoryName);
		}
	}

	public int getInventoryStackLimit(){
		return 64;
	}

	private void checkAdjacentChests(TileEntityStorageBox box, int orientation){
		if (box.isInvalid()){
			this.adjacentChestChecked = false;
		}
		else if (this.adjacentChestChecked){
			switch (orientation){
			case 0:
				if (this.adjacentChestZPos != box){
					this.adjacentChestChecked = false;
				}

				break;
			case 1:
				if (this.adjacentChestXNeg != box){
					this.adjacentChestChecked = false;
				}

				break;
			case 2:
				if (this.adjacentChestZNeg != box){
					this.adjacentChestChecked = false;
				}

				break;
			case 3:
				if (this.adjacentChestXPos != box){
					this.adjacentChestChecked = false;
				}
			}
		}
	}

	public void checkForAdjacentChests()
	{
		if (!this.adjacentChestChecked)
		{
			this.adjacentChestChecked = true;
			this.adjacentChestZNeg = null;
			this.adjacentChestXPos = null;
			this.adjacentChestXNeg = null;
			this.adjacentChestZPos = null;

			if (this.hasAdjacentChest(this.xCoord - 1, this.yCoord, this.zCoord))
			{
				this.adjacentChestXNeg = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			}

			if (this.hasAdjacentChest(this.xCoord + 1, this.yCoord, this.zCoord))
			{
				this.adjacentChestXPos = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			}

			if (this.hasAdjacentChest(this.xCoord, this.yCoord, this.zCoord - 1))
			{
				this.adjacentChestZNeg = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			}

			if (this.hasAdjacentChest(this.xCoord, this.yCoord, this.zCoord + 1))
			{
				this.adjacentChestZPos = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			}

			if (this.adjacentChestZNeg != null)
			{
				this.adjacentChestZNeg.checkAdjacentChests(this, 0);
			}

			if (this.adjacentChestZPos != null)
			{
				this.adjacentChestZPos.checkAdjacentChests(this, 2);
			}

			if (this.adjacentChestXPos != null)
			{
				this.adjacentChestXPos.checkAdjacentChests(this, 1);
			}

			if (this.adjacentChestXNeg != null)
			{
				this.adjacentChestXNeg.checkAdjacentChests(this, 3);
			}
		}
	}

    @Override
    public void updateEntity()
    {
        this.checkForAdjacentChests();
        ++this.ticksSinceSync;
        float f;

        if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double) ((float) this.xCoord - f), (double) ((float) this.yCoord - f), (double) ((float) this.zCoord - f), (double) ((float) (this.xCoord + 1) + f), (double) ((float) (this.yCoord + 1) + f), (double) ((float) (this.zCoord + 1) + f)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;
        double d2;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double d1 = (double)this.xCoord + 0.5D;
            d2 = (double)this.zCoord + 0.5D;

            if (this.adjacentChestZPos != null)
            {
                d2 += 0.5D;
            }

            if (this.adjacentChestXPos != null)
            {
                d1 += 0.5D;
            }

            Genesis.proxy.playSound(xCoord, yCoord, zCoord, "storagebox.open", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += f;
            }
            else
            {
                this.lidAngle -= f;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                d2 = (double)this.xCoord + 0.5D;
                double d0 = (double)this.zCoord + 0.5D;

                if (this.adjacentChestZPos != null)
                {
                    d0 += 0.5D;
                }

                if (this.adjacentChestXPos != null)
                {
                    d2 += 0.5D;
                }

                Genesis.proxy.playSound(xCoord, yCoord, zCoord, "storagebox.close", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }
	
	private boolean hasAdjacentChest(int x, int y, int z)
    {
        if (this.worldObj == null)
        {
            return false;
        }
        else
        {
            Block block = this.worldObj.getBlock(x, y, z);
            return block instanceof BlockStorageBox && ((BlockStorageBox)block).field_149956_a == this.func_145980_j();
        }
    }
	
	public int func_145980_j()
    {
        if (this.cachedBoxType == -1)
        {
            if (this.worldObj == null || !(this.getBlockType() instanceof BlockStorageBox))
            {
                return 0;
            }

            this.cachedBoxType = ((BlockStorageBox)this.getBlockType()).field_149956_a;
        }

        return this.cachedBoxType;
    }
}
