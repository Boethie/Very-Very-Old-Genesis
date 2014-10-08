package genesis.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.Genesis;
import genesis.handler.GenesisVersionHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import java.util.HashMap;
import java.util.Map;

public class GenesisClientEventHandler {
    /**
     * The mapping of {@link net.minecraftforge.fluids.Fluid} to {@link net.minecraftforge.fluids.BlockFluidBase}.
     */
    public static Map<Fluid, BlockFluidBase> fluidMap = new HashMap<Fluid, BlockFluidBase>();

    /**
     * After the texture stitching.
     *
     * @param event The received event.
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void postStitch(TextureStitchEvent.Post event) {
        for (Map.Entry<Fluid, BlockFluidBase> fluids : GenesisClientEventHandler.fluidMap.entrySet()) {
            fluids.getKey().setIcons(fluids.getValue().getBlockTextureFromSide(0), fluids.getValue().getBlockTextureFromSide(1));
        }
    }

    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.DrawScreenEvent event) {
        if (event.gui instanceof GuiMainMenu) {
            FontRenderer font = event.gui.mc.fontRenderer;
            String line = "Genesis  " + Genesis.MOD_VERSION;
            String status = getStringFromStatus(GenesisVersionHandler.getStatus());
            event.gui.drawCenteredString(font, line, font.getStringWidth(line) / 2 + 2, event.gui.height - (event.gui.height / 4) - font.FONT_HEIGHT, -1);
            event.gui.drawCenteredString(font, status, font.getStringWidth(status) / 2 + 2, event.gui.height - (event.gui.height / 4), -1);
        }
    }

    private String getStringFromStatus(GenesisVersionHandler.Status status) {
        switch (status) {
        case OUTDATED:
            return EnumChatFormatting.RED + "OUTDATED: Genesis " + GenesisVersionHandler.getTarget();
        case UP_TO_DATE:
            return EnumChatFormatting.GREEN + "Genesis is up-to-date";
        case AHEAD:
            return EnumChatFormatting.YELLOW + "Genesis is ahead!";
        case FAILED:
            return EnumChatFormatting.RED + "Error with Versioning";
        case PENDING:
            return EnumChatFormatting.BLUE + "The version has yet to be retrieved";
        default:
            return "Unknown ";
        }
    }
}
