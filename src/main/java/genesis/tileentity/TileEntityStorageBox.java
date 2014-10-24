package genesis.tileentity;

import genesis.block.tiles.BlockStorageBox;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityStorageBox extends TileEntityChest{

	private ItemStack[] contents = new ItemStack[36];
	private String inventoryName;
	private int cachedBoxType;

	public TileEntityStorageBox adjacentChestZNeg;
    public TileEntityStorageBox adjacentChestXPos;
    public TileEntityStorageBox adjacentChestXNeg;
    public TileEntityStorageBox adjacentChestZPos;
	
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

	private void checkAdjecentChests(TileEntityStorageBox box, int orientation){
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

			if (this.hasAdjecentChest(this.xCoord - 1, this.yCoord, this.zCoord))
			{
				this.adjacentChestXNeg = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			}

			if (this.hasAdjecentChest(this.xCoord + 1, this.yCoord, this.zCoord))
			{
				this.adjacentChestXPos = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			}

			if (this.hasAdjecentChest(this.xCoord, this.yCoord, this.zCoord - 1))
			{
				this.adjacentChestZNeg = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			}

			if (this.hasAdjecentChest(this.xCoord, this.yCoord, this.zCoord + 1))
			{
				this.adjacentChestZPos = (TileEntityStorageBox)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			}

			if (this.adjacentChestZNeg != null)
			{
				this.adjacentChestZNeg.checkAdjecentChests(this, 0);
			}

			if (this.adjacentChestZPos != null)
			{
				this.adjacentChestZPos.checkAdjecentChests(this, 2);
			}

			if (this.adjacentChestXPos != null)
			{
				this.adjacentChestXPos.checkAdjecentChests(this, 1);
			}

			if (this.adjacentChestXNeg != null)
			{
				this.adjacentChestXNeg.checkAdjecentChests(this, 3);
			}
		}
	}
	
	private boolean hasAdjecentChest(int x, int y, int z)
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
