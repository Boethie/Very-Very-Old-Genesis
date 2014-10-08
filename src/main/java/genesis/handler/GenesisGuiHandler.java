package genesis.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import genesis.client.gui.GuiCampfire;
import genesis.client.gui.GuiPolissoir;
import genesis.common.container.ContainerCampfire;
import genesis.common.container.ContainerPolissoir;
import genesis.tileentity.TileEntityCampfire;
import genesis.tileentity.TileEntityPolissoir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GenesisGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (Element.values()[id]) {
        case CAMPFIRE:
            return new ContainerCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
        case POLISSOIR:
            return new ContainerPolissoir(player.inventory, (TileEntityPolissoir) world.getTileEntity(x, y, z));
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
        }

        return null;
    }

    public static enum Element {
        CAMPFIRE,
        POLISSOIR;

        public int getId() {
            return ordinal();
        }
    }
}
