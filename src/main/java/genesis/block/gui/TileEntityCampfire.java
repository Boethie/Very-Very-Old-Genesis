package genesis.block.gui;

import genesis.common.Genesis;
import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityCampfire extends TileEntityFurnace {
    public float prevRot = 0;
    public float rot = 0;
    public String customName;
    protected ItemStack[] inventory = new ItemStack[3];
    protected boolean wasBurning = false;

    public TileEntityCampfire() {
        super();
    }

    public static int getItemBurnTime(ItemStack stack) {
        return (int) (TileEntityFurnace.getItemBurnTime(stack) * 1.125F);
    }

    public boolean canSmeltItemType(ItemStack stack) {
        if (stack == null) {
            return false;
        }

        if (stack.getItemUseAction().equals(EnumAction.eat)) {
            return true;
        }

        if (Block.getBlockFromItem(stack.getItem()) == Blocks.cactus) {
            return true;
        }

        ItemStack smeltResult = FurnaceRecipes.smelting().getSmeltingResult(stack);

        if (smeltResult != null) {
            if (smeltResult.getItemUseAction().equals(EnumAction.eat)) {
                return true;
            }

            if (smeltResult.getItem() == Items.coal) {
                return true;
            }
        }

        return false;
    }

    public boolean canSmelt() {
        ItemStack smeltingItem = getStackInSlot(0);

        if (!canSmeltItemType(smeltingItem)) {
            return false;
        }

        ItemStack smeltOutputStack = getStackInSlot(2);
        ItemStack smeltResult = FurnaceRecipes.smelting().getSmeltingResult(smeltingItem);

        if (smeltResult == null) {
            return false;
        }

        if (smeltOutputStack == null) {
            return true;
        }

        if (!smeltOutputStack.isItemEqual(smeltResult)) {
            return false;
        }

        int outputSize = smeltOutputStack.stackSize + smeltResult.stackSize;

        return outputSize <= getInventoryStackLimit() && outputSize <= smeltOutputStack.getMaxStackSize();
    }

    @Override
    public void smeltItem() {
        if (canSmelt()) {
            ItemStack smeltingItems = getStackInSlot(0);
            ItemStack outputItems = getStackInSlot(2);
            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(smeltingItems);

            if (outputItems == null) {
                outputItems = result.copy();
            } else if (outputItems.isItemEqual(result)) {
                outputItems.stackSize += result.stackSize;
            }

            --smeltingItems.stackSize;

            if (smeltingItems.stackSize <= 0) {
                smeltingItems = null;
            }

            setInventorySlotContents(0, smeltingItems);
            setInventorySlotContents(2, outputItems);
        }
    }

    protected boolean isRaining() {
        return worldObj.getRainStrength(1) >= 0.9F && worldObj.canLightningStrikeAt(xCoord, yCoord + 1, zCoord);
    }

    public void setWet() {
        if (isBurning()) {
            worldObj.playAuxSFX(1004, xCoord, yCoord, zCoord, 0);
        }

        furnaceBurnTime = Math.min(furnaceBurnTime, -200);
    }

    @Override
    public void updateEntity() {
        getBlockType();

        if (worldObj.isRemote) {
            prevRot = rot;

            if (canSmelt() && isBurning()) {
                rot += 0.05F;

                float sub = rot - rot % ((float) Math.PI * 2);

                if (sub > 0) {
                    prevRot -= sub;
                    rot -= sub;
                }
            }
        }

        boolean invChange = false;

        if (wasBurning) {
            furnaceBurnTime--;
        } else if (furnaceBurnTime < 0) {
            furnaceBurnTime++;
        }

        boolean raining = isRaining();

        if (!worldObj.isRemote) {
            if (raining) {
                setWet();
            } else if (furnaceBurnTime == 0) {
                ItemStack burningItem = getStackInSlot(1);
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(burningItem);

                if (furnaceBurnTime > 0) {
                    invChange = true;

                    if (burningItem != null) {
                        --burningItem.stackSize;

                        if (burningItem.stackSize == 0) {
                            setInventorySlotContents(1, burningItem.getItem().getContainerItem(burningItem));
                        }
                    }
                }
            }

            if (isBurning() && canSmelt()) {
                furnaceCookTime++;

                if (furnaceCookTime >= 200) {
                    furnaceCookTime = 0;
                    smeltItem();
                    invChange = true;
                }
            } else {
                furnaceCookTime = 0;
            }

            boolean burning = isBurning();

            if (wasBurning != burning) {
                BlockCampfire blockCampfire = (BlockCampfire) worldObj.getBlock(xCoord, yCoord, zCoord);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, blockCampfire.setFireLit(worldObj.getBlockMetadata(xCoord, yCoord, zCoord), burning), 2);

                invChange = true;
                wasBurning = burning;
            }
        }

        if (invChange) {
            markDirty();
        }
    }

    @Override
    public int getBurnTimeRemainingScaled(int pixels) {
        if (currentItemBurnTime == 0) {
            currentItemBurnTime = 200;
        }

        return (int) Math.ceil(furnaceBurnTime / (float) currentItemBurnTime * pixels);
    }

    @Override
    public int getCookProgressScaled(int pixels) {
        return (int) (furnaceCookTime / 200F * pixels);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        switch (slot) {
        case 0:
            return canSmeltItemType(stack);
        case 1:
            return isItemFuel(stack);
        }

        return false;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {
            setInventorySlotContents(slot, null);
        }

        return stack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);

                if (stack.stackSize <= 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return stack;
    }

    @Override
    public String getInventoryName() {
        return Names.containerCampfire;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return customName != null && customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        xCoord = compound.getInteger("x");
        yCoord = compound.getInteger("y");
        zCoord = compound.getInteger("z");

        NBTTagList tagList = compound.getTagList("Items", 10);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound itemCompound = tagList.getCompoundTagAt(i);
            byte slot = itemCompound.getByte("Slot");

            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(itemCompound);
            }
        }

        furnaceBurnTime = compound.getInteger("BurnTime");
        furnaceCookTime = compound.getInteger("CookTime");
        currentItemBurnTime = getItemBurnTime(inventory[1]);

        if (compound.hasKey("CustomName")) {
            customName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setString("id", Genesis.teClassToNameMap.get(getClass()));
        compound.setInteger("x", xCoord);
        compound.setInteger("y", yCoord);
        compound.setInteger("z", zCoord);

        compound.setInteger("BurnTime", furnaceBurnTime);
        compound.setInteger("CookTime", furnaceCookTime);

        NBTTagList itemList = new NBTTagList();
        int i = 0;

        for (ItemStack stack : inventory) {
            if (stack != null) {
                NBTTagCompound itemComp = new NBTTagCompound();
                itemComp.setByte("Slot", (byte) i);
                stack.writeToNBT(itemComp);

                itemList.appendTag(itemComp);
            }

            i++;
        }

        compound.setTag("Items", itemList);

        if (hasCustomInventoryName()) {
            compound.setString("CustomName", customName);
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 0.25, yCoord, zCoord - 0.25, xCoord + 1.25, yCoord + 1.25, zCoord + 1.25);
    }

    @Override
    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        return oldBlock != newBlock;
    }
}
