package genesis.block.gui;

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
	
	protected TileEntityCampfire campfireEnt;
	
	protected int lastCookTime;
	protected int lastBurnTime;
	protected int lastItemBurnTime;
	
	public ContainerCampfire(InventoryPlayer inventoryPlayer, TileEntityCampfire campfire)
	{
		campfireEnt = campfire;

		addSlotToContainer(new Slot(campfireEnt, 0, 48, 16));
		addSlotToContainer(new Slot(campfireEnt, 1, 48, 52));
		addSlotToContainer(new SlotFurnace(inventoryPlayer.player, campfireEnt, 2, 107, 34));
		
		// Add player inv
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
    	switch (id)
    	{
    	case 0:
            campfireEnt.furnaceCookTime = value;
            break;
    	case 1:
        	campfireEnt.furnaceBurnTime = value;
            break;
    	case 2:
        	campfireEnt.currentItemBurnTime = value;
            break;
    	}
    }
	
	@Override
	public void addCraftingToCrafters(ICrafting iCrafting)
	{
		super.addCraftingToCrafters(iCrafting);
		
		iCrafting.sendProgressBarUpdate(this, 0, campfireEnt.furnaceCookTime);
		iCrafting.sendProgressBarUpdate(this, 1, campfireEnt.furnaceBurnTime);
		iCrafting.sendProgressBarUpdate(this, 2, campfireEnt.currentItemBurnTime);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (ICrafting iCrafting : (List<ICrafting>)crafters)
		{
			if (lastCookTime != campfireEnt.furnaceCookTime)
			{
				iCrafting.sendProgressBarUpdate(this, 0, campfireEnt.furnaceCookTime);
			}
			
			if (lastBurnTime != campfireEnt.furnaceBurnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 1, campfireEnt.furnaceBurnTime);
			}
			
			if (lastItemBurnTime != campfireEnt.currentItemBurnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 2, campfireEnt.currentItemBurnTime);
			}
		}
		
		lastCookTime = campfireEnt.furnaceCookTime;
		lastBurnTime = campfireEnt.furnaceBurnTime;
		lastItemBurnTime = campfireEnt.currentItemBurnTime;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return campfireEnt.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
        ItemStack originalStack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            originalStack = stack.copy();

            if (slotIndex == 2)
            {
                if (!mergeItemStack(stack, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(stack, originalStack);
            }
            else if (slotIndex > 1)
            {
            	boolean inserted = false;
            	
            	for (int i = 1; i >= 0; i--)
            	{
            		if (campfireEnt.canInsertItem(i, stack, 0))
            		{
            			inserted = true;
            			
                        if (!mergeItemStack(stack, i, i + 1, false))
                        {
                            return null;
                        }
                        
                        break;
            		}
            	}
            	
            	if (!inserted)
            	{
	            	if (slotIndex >= 3 && slotIndex < 30)	// Player inventory
	                {
	                    if (!mergeItemStack(stack, 30, 39, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (slotIndex >= 30 && slotIndex < 39 && !mergeItemStack(stack, 3, 30, false))
	                {
	                    return null;
	                }
            	}
            }
            else if (!mergeItemStack(stack, 3, 39, false))
            {
                return null;
            }

            if (stack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (stack.stackSize == originalStack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, stack);
        }

        return originalStack;
	}
	
}