package genesis.block.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.lib.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StringUtils;

public class TileEntityPolissoir extends TileEntity implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};
    public int polissoirUpgradeTime;
    private ItemStack[] polissoirItemStacks = new ItemStack[3];
    private ItemStack lastUpgradeItem;
    private String inventoryName;

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
    }

    @Override
    public int getSizeInventory() {
        return polissoirItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return polissoirItemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (polissoirItemStacks[slot] != null) {
            ItemStack itemstack;

            if (polissoirItemStacks[slot].stackSize <= amount) {
                itemstack = polissoirItemStacks[slot];
                polissoirItemStacks[slot] = null;
                return itemstack;
            } else {
                itemstack = polissoirItemStacks[slot].splitStack(amount);

                if (polissoirItemStacks[slot].stackSize == 0) {
                    polissoirItemStacks[slot] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (polissoirItemStacks[slot] != null) {
            ItemStack itemstack = polissoirItemStacks[slot];
            polissoirItemStacks[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        polissoirItemStacks[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? inventoryName : Names.containerPolissoir;
    }

    public void setInventoryName(String customName) {
        inventoryName = customName;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return inventoryName != null && inventoryName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
        polissoirUpgradeTime = 0;
        lastUpgradeItem = null;
    }

    @Override
    public void closeInventory() {
        polissoirUpgradeTime = 0;
        lastUpgradeItem = null;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return slot != 2 && (slot != 1 || PolissoirRecipes.instance().isUpgradeItem(itemStack));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
        polissoirItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < polissoirItemStacks.length) {
                polissoirItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        polissoirUpgradeTime = nbtTagCompound.getShort("CookTime");

        if (nbtTagCompound.hasKey("CustomName", 8)) {
            inventoryName = nbtTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("CookTime", (short) polissoirUpgradeTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < polissoirItemStacks.length; ++i) {
            if (polissoirItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                polissoirItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);

        if (hasCustomInventoryName()) {
            nbtTagCompound.setString("CustomName", inventoryName);
        }
    }

    @Override
    public void updateEntity() {
        boolean wasBurning = isBurning();
        boolean updateInv = false;
        boolean smeltItem = false;

        if (!isBurning()) {
            polissoirUpgradeTime = 0;
            lastUpgradeItem = null;
        }

        if (!worldObj.isRemote) {
            if (polissoirUpgradeTime > 0 || polissoirItemStacks[1] != null && polissoirItemStacks[0] != null) {
                if (polissoirUpgradeTime == 0 && canSmelt()) {
                    if (polissoirItemStacks[1] != null && PolissoirRecipes.instance().getRequirement(polissoirItemStacks[0]).isItemEqual(polissoirItemStacks[1])) {
                        updateInv = true;

                        ++polissoirUpgradeTime;

                        lastUpgradeItem = polissoirItemStacks[1].copy();
                    }
                }

                if (isBurning() && canSmelt()) {
                    ++polissoirUpgradeTime;

                    if (polissoirUpgradeTime == PolissoirRecipes.instance().getUpgradeTime(polissoirItemStacks[0])) {
                        polissoirUpgradeTime = 0;
                        lastUpgradeItem = null;
                        smeltItem = true;
                    }
                }
            }

            if (wasBurning != isBurning()) {
                updateInv = true;
                BlockPolissoir.updatePolissoirBlockState(worldObj, xCoord, yCoord, zCoord);
            }
        }

        if (smeltItem) {
            smeltItem();
            updateInv = true;
        }

        if (updateInv) {
            markDirty();
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        int upgradeTime = 200;
        if (polissoirItemStacks[0] != null) {
            upgradeTime = PolissoirRecipes.instance().getUpgradeTime(polissoirItemStacks[0]);
        }
        return polissoirUpgradeTime * par1 / upgradeTime;
    }

    public boolean isBurning() {
        ItemStack requirement = PolissoirRecipes.instance().getRequirement(polissoirItemStacks[0]);
        return polissoirItemStacks[0] != null && requirement != null && (lastUpgradeItem == null || requirement.isItemEqual(lastUpgradeItem)) && polissoirUpgradeTime > 0;
    }

    public boolean canSmelt() {
        if (polissoirItemStacks[0] == null) {
            return false;
        } else {
            ItemStack result = PolissoirRecipes.instance().getResult(polissoirItemStacks[0]);
            if (result == null) {
                return false;
            }
            if (polissoirItemStacks[2] == null) {
                return true;
            }
            if (!polissoirItemStacks[2].isItemEqual(result)) {
                return false;
            }
            int stackSize = polissoirItemStacks[2].stackSize + result.stackSize;
            return stackSize <= getInventoryStackLimit() && stackSize <= polissoirItemStacks[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (canSmelt()) {
            boolean hasChippedRecipe = PolissoirRecipes.instance().hasChippedRecipe(polissoirItemStacks[0]);
            boolean hasPolishedRecipe = PolissoirRecipes.instance().hasPolishedRecipe(polissoirItemStacks[0]);
            boolean hasSharpenedRecipe = PolissoirRecipes.instance().hasSharpenedRecipe(polissoirItemStacks[0]);
            String sound = hasChippedRecipe ? "chipped" : hasPolishedRecipe || hasSharpenedRecipe ? "polished" : null;
            if (!StringUtils.isNullOrEmpty(sound)) {
                Genesis.proxy.playSound(xCoord, yCoord, zCoord, "polissoir." + sound, 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (!worldObj.isRemote) {
                ItemStack itemstack = PolissoirRecipes.instance().getResult(polissoirItemStacks[0]);

                if (polissoirItemStacks[2] == null) {
                    polissoirItemStacks[2] = itemstack.copy();
                } else if (polissoirItemStacks[2].getItem() == itemstack.getItem()) {
                    polissoirItemStacks[2].stackSize += itemstack.stackSize;
                }

                if (polissoirItemStacks[0].isItemStackDamageable() && polissoirItemStacks[2].isItemStackDamageable()) {
                    polissoirItemStacks[2].setItemDamage(polissoirItemStacks[0].getItemDamage());
                }

                if (polissoirItemStacks[0].hasTagCompound()) {
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setTag("tag", polissoirItemStacks[0].getTagCompound());
                    polissoirItemStacks[2].readFromNBT(compound);
                }

                --polissoirItemStacks[1].stackSize;

                if (polissoirItemStacks[1].stackSize == 0) {
                    polissoirItemStacks[1] = polissoirItemStacks[1].getItem().getContainerItem(polissoirItemStacks[1]);
                }

                --polissoirItemStacks[0].stackSize;

                if (polissoirItemStacks[0].stackSize <= 0) {
                    polissoirItemStacks[0] = null;
                }
            }
        }
    }
}
