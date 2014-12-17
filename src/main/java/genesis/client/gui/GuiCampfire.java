package genesis.client.gui;

import genesis.Genesis;
import genesis.common.container.ContainerCampfire;
import genesis.tileentity.TileEntityCampfire;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiCampfire extends GuiContainer {

    public static final ResourceLocation campfireGuiTextures = new ResourceLocation(Genesis.ASSETS + "textures/tiles/container/campfire.png");
    private TileEntityCampfire campfire;

    public GuiCampfire(InventoryPlayer playerInv, TileEntityCampfire tileEntityCampfire) {
        super(new ContainerCampfire(playerInv, tileEntityCampfire));
        campfire = tileEntityCampfire;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = campfire.hasCustomInventoryName() ? campfire.getInventoryName() : I18n.format(campfire.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 14737632);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 14737632);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(campfireGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        if (campfire.isBurning()) {
            int i1 = campfire.getBurnTimeRemainingScaled(13);
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
            i1 = campfire.getCookProgressScaled(24);
            drawTexturedModalRect(k + 80, l + 34, 176, 14, i1, 16);
        }
    }
}
