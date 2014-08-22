package genesis.block.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPolissoir extends Container {
    private TileEntityPolissoir polissoir;
    private int lastUpgradeTime;

    public ContainerPolissoir(InventoryPlayer playerInventory, TileEntityPolissoir tilePolissoir) {
        polissoir = tilePolissoir;
        addSlotToContainer(new Slot(tilePolissoir, 0, 48, 25));
        addSlotToContainer(new Slot(tilePolissoir, 1, 76, 51));
        addSlotToContainer(new SlotPolissoir(playerInventory.player, tilePolissoir, 2, 108, 25));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 83 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 141));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return polissoir.isUseableByPlayer(player);
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, polissoir.polissoirUpgradeTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object obj : crafters) {
            ICrafting icrafting = (ICrafting) obj;

            if (lastUpgradeTime != polissoir.polissoirUpgradeTime) {
                icrafting.sendProgressBarUpdate(this, 0, polissoir.polissoirUpgradeTime);
            }
        }

        lastUpgradeTime = polissoir.polissoirUpgradeTime;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            polissoir.polissoirUpgradeTime = par2;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex == 2) {
                if (!mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotIndex != 1 && slotIndex != 0) {
                if (PolissoirRecipes.instance().getResult(itemstack1) != null) {
                    if (!mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (PolissoirRecipes.instance().isUpgradeItem(itemstack1)) {
                    if (!mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (slotIndex >= 3 && slotIndex < 30) {
                    if (!mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (slotIndex >= 30 && slotIndex < 39 && !mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        polissoir.closeInventory();
    }
}
