package genesis.block.gui;

import genesis.common.Genesis;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class ContainerGuiCampfire extends GuiContainer {

    public static final ResourceLocation campfireGuiTextures = new ResourceLocation(Genesis.ASSETS + "textures/gui/container/campfire.png");
    private TileEntityCampfire campfire;

    public ContainerGuiCampfire(InventoryPlayer playerInv, TileEntityCampfire tileCampfire) {
        super(new ContainerCampfire(playerInv, tileCampfire));
        campfire = tileCampfire;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = campfire.hasCustomInventoryName() ? campfire.getInventoryName() : I18n.format(campfire.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);

        /*
         * GL11.glTranslatef(-guiLeft, -guiTop, 0); // Undo GUI translation for
         * extra stuffs
         *
         * GL11.glTranslatef(guiLeft, guiTop, 0);
         */
    }

    private void drawImage(int x, int y, int u, int v, int w, int h, int texW, int texH) {
        float scaleX = 1F / texW;
        float scaleY = 1F / texH;

        GL11.glColor4f(1, 1, 1, 1);

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + h, zLevel, u * scaleX, (v + h) * scaleY);
        tessellator.addVertexWithUV(x + w, y + h, zLevel, (u + w) * scaleX, (v + h) * scaleY);
        tessellator.addVertexWithUV(x + w, y, zLevel, (u + w) * scaleX, v * scaleY);
        tessellator.addVertexWithUV(x, y, zLevel, u * scaleX, v * scaleY);
        tessellator.draw();
    }

    private void drawSlot(int x, int y, int u, int v) {
        drawImage(x, y, u, v, 18, 18, 128, 128);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        GL11.glColor4f(1, 1, 1, 1);

        int originX = (width - xSize) / 2;
        int originY = (height - ySize) / 2;

        mc.renderEngine.bindTexture(campfireGuiTextures);
        drawTexturedModalRect(originX, originY, 0, 0, xSize, ySize);

        // Burn progress
        drawImage(originX + 47, originY + 33, 204, 0, 18, 18, 256, 256);

        int fireHeight = campfire.getBurnTimeRemainingScaled(13);

        if (fireHeight >= 13) {
            fireHeight = 18;
        }

        drawImage(originX + 47, originY + 48 - fireHeight, // Pos
                204, 34 - fireHeight, // U, V
                18, fireHeight + 3, // W, H
                256, 256); // Tex

        // Cook progress
        drawImage(originX + 72, originY + 33, 229, 0, 22, 18, 256, 256);

        int cookWidth = campfire.getCookProgressScaled(22);
        drawImage(originX + 72, originY + 33, // Pos
                229, 19, // U, V
                cookWidth, 18, // W, H
                256, 256); // Tex

        mc.renderEngine.bindTexture(statIcons);

        for (Object slotObj : inventorySlots.inventorySlots) {
            Slot slot = (Slot) slotObj;
            int x = originX + slot.xDisplayPosition - 1;
            int y = originY + slot.yDisplayPosition - 1;

            if (slot instanceof SlotFurnace) {
                mc.renderEngine.bindTexture(campfireGuiTextures);
                drawImage(x - 4, y - 4, 177, 0, 26, 26, 256, 256);
                mc.renderEngine.bindTexture(statIcons);
            } else {
                drawSlot(x, y, 0, 0);
            }
        }
    }
}
