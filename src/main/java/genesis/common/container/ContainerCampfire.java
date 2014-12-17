package genesis.common.container;

import genesis.tileentity.TileEntityCampfire;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCampfire extends Container {

    protected TileEntityCampfire campfire;

    protected int lastCookTime;
    protected int lastBurnTime;
    protected int lastItemBurnTime;

    public ContainerCampfire(InventoryPlayer inventoryPlayer, TileEntityCampfire campfireTileEntity) {
        campfire = campfireTileEntity;

        addSlotToContainer(new Slot(campfireTileEntity, 0, 56, 17));
        addSlotToContainer(new Slot(campfireTileEntity, 1, 56, 53));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, campfireTileEntity, 2, 116, 35));

        // Add player inv
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 83 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 141));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        switch (id) {
        case 0:
            campfire.furnaceCookTime = value;
            break;
        case 1:
            campfire.furnaceBurnTime = value;
            break;
        case 2:
            campfire.currentItemBurnTime = value;
            break;
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);

        iCrafting.sendProgressBarUpdate(this, 0, campfire.furnaceCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, campfire.furnaceBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, campfire.currentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting iCrafting : (List<ICrafting>) crafters) {
            if (lastCookTime != campfire.furnaceCookTime) {
                iCrafting.sendProgressBarUpdate(this, 0, campfire.furnaceCookTime);
            }

            if (lastBurnTime != campfire.furnaceBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 1, campfire.furnaceBurnTime);
            }

            if (lastItemBurnTime != campfire.currentItemBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 2, campfire.currentItemBurnTime);
            }
        }

        lastCookTime = campfire.furnaceCookTime;
        lastBurnTime = campfire.furnaceBurnTime;
        lastItemBurnTime = campfire.currentItemBurnTime;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return campfire.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack originalStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            originalStack = stack.copy();

            if (slotIndex == 2) {
                if (!mergeItemStack(stack, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(stack, originalStack);
            } else if (slotIndex > 1) {
                boolean inserted = false;

                for (int i = 1; i >= 0; i--) {
                    if (campfire.canInsertItem(i, stack, 0)) {
                        inserted = true;

                        if (!mergeItemStack(stack, i, i + 1, false)) {
                            return null;
                        }

                        break;
                    }
                }

                if (!inserted) {
                    if (slotIndex >= 3 && slotIndex < 30)    // Player inventory
                    {
                        if (!mergeItemStack(stack, 30, 39, false)) {
                            return null;
                        }
                    } else if (slotIndex >= 30 && slotIndex < 39 && !mergeItemStack(stack, 3, 30, false)) {
                        return null;
                    }
                }
            } else if (!mergeItemStack(stack, 3, 39, false)) {
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stack.stackSize == originalStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stack);
        }

        return originalStack;
    }
}