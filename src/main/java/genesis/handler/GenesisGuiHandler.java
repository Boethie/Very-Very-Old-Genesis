package genesis.handler;

import genesis.block.tiles.BlockStorageBox;
import genesis.client.gui.GuiCampfire;
import genesis.client.gui.GuiPolissoir;
import genesis.client.gui.GuiStorageBox;
import genesis.common.container.ContainerCampfire;
import genesis.common.container.ContainerPolissoir;
import genesis.managers.GenesisModBlocks;
import genesis.tileentity.TileEntityCampfire;
import genesis.tileentity.TileEntityPolissoir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GenesisGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (Element.values()[id]) {
        case CAMPFIRE:
            return new ContainerCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
        case POLISSOIR:
            return new ContainerPolissoir(player.inventory, (TileEntityPolissoir) world.getTileEntity(x, y, z));
        case STORAGE_BOX:
        	return new ContainerChest(player.inventory, BlockStorageBox.getInventory(world, x, y, z, GenesisModBlocks.storageBox));
        }
        
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (Element.values()[id]) {
        case CAMPFIRE:
            return new GuiCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
        case POLISSOIR:
            return new GuiPolissoir(player.inventory, (TileEntityPolissoir) world.getTileEntity(x, y, z));
        case STORAGE_BOX:
        	return new GuiStorageBox(player.inventory, BlockStorageBox.getInventory(world, x, y, z, GenesisModBlocks.storageBox));
        }

        return null;
    }

    public static enum Element {
        CAMPFIRE,
        POLISSOIR,
        STORAGE_BOX;

        public int getId() {
            return ordinal();
        }
    }
}
