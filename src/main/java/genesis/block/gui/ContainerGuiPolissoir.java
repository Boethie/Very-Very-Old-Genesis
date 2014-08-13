package genesis.block.gui;

import genesis.common.Genesis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ContainerGuiPolissoir extends GuiContainer {
    public static final ResourceLocation polissoirGuiTextures = new ResourceLocation(Genesis.MOD_ID, "textures/gui/container/polissoir.png");
    private TileEntityPolissoir polissoir;

    public ContainerGuiPolissoir(InventoryPlayer playerInv, TileEntityPolissoir tilePolissoir) {
        super(new ContainerPolissoir(playerInv, tilePolissoir));
        polissoir = tilePolissoir;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = polissoir.hasCustomInventoryName() ? polissoir.getInventoryName() : I18n.format(polissoir.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 14737632);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 14737632);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(polissoirGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        if (polissoir.isBurning()) {
            int i1 = polissoir.getCookProgressScaled(24);
            drawTexturedModalRect(k + 71, l + 24, 176, 0, i1 + 1, 16);
        }
    }
}
