package genesis.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import genesis.block.gui.ContainerCampfire;
import genesis.block.gui.ContainerGuiCampfire;
import genesis.block.gui.TileEntityCampfire;

public class GenesisGuiHandler implements IGuiHandler {

	public static final int GUI_CAMPFIRE = 0;

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
			case GUI_CAMPFIRE:
				return new ContainerCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
			case GUI_CAMPFIRE:
				return new ContainerGuiCampfire(player.inventory, (TileEntityCampfire) world.getTileEntity(x, y, z));
		}

		return null;
	}

}
