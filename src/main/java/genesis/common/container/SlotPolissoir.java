package genesis.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPolissoir extends Slot {
    private EntityPlayer player;
    private int amountCrafted;

    public SlotPolissoir(EntityPlayer entityPlayer, IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        player = entityPlayer;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if (getHasStack()) {
            amountCrafted += Math.min(amount, getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
        onCrafting(itemStack);
        super.onPickupFromSlot(entityPlayer, itemStack);
    }

    @Override
    protected void onCrafting(ItemStack itemStack, int count) {
        amountCrafted += count;
        onCrafting(itemStack);
    }

    @Override
    protected void onCrafting(ItemStack itemStack) {
        itemStack.onCrafting(player.worldObj, player, amountCrafted);
        amountCrafted = 0;
    }
}
