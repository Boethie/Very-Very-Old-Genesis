package genesis.genesis.block.gui;

import genesis.genesis.block.Blocks;
import genesis.genesis.common.Genesis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ContainerGuiCampfire extends GuiContainer {
	
	public static final ResourceLocation backgroundLocation = new ResourceLocation(Genesis.MOD_ID + ":textures/gui/campfire.png");
	
	InventoryPlayer playerInventory;
	TileEntityCampfire campfireEnt;

	protected ItemStack titleItem;

	public ContainerGuiCampfire(InventoryPlayer playerInv, TileEntityCampfire campfire)
	{
		super(new ContainerCampfire(playerInv, campfire));
		
		playerInventory = playerInv;
		campfireEnt = campfire;
		
		titleItem = new ItemStack(Blocks.campfire);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
	}

    protected void actionPerformed(GuiButton button)
    {
    	super.actionPerformed(button);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        fontRenderer.drawString(titleItem.getDisplayName(), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);

        /*GL11.glTranslatef(-guiLeft, -guiTop, 0);	// Undo GUI translation for extra stuffs
        
       	GL11.glTranslatef(guiLeft, guiTop, 0);*/
    }

    private void drawImage(int x, int y, int u, int v, int w, int h, int texW, int texH)
    {
    	float scaleX = 1F / texW;
    	float scaleY = 1F / texH;
    	
        GL11.glColor4f(1, 1, 1, 1);
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + h, zLevel, (float)u * scaleX, (float)(v + h) * scaleY);
        tessellator.addVertexWithUV(x + w, y + h, zLevel, (float)(u + w) * scaleX, (float)(v + h) * scaleY);
        tessellator.addVertexWithUV(x + w, y, zLevel, (float)(u + w) * scaleX, (float)v * scaleY);
        tessellator.addVertexWithUV(x, y, zLevel, (float)u * scaleX, (float)v * scaleY);
        tessellator.draw();
    }
    
    private void drawSlot(int x, int y, int u, int v)
    {
        drawImage(x, y, u, v, 18, 18, 128, 128);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
    {
        GL11.glColor4f(1, 1, 1, 1);
        
        int originX = (width - xSize) / 2;
        int originY = (height - ySize) / 2;
        
        mc.renderEngine.bindTexture(backgroundLocation);
        drawTexturedModalRect(originX, originY, 0, 0, xSize, ySize);
        
		// Burn progress
		drawImage(originX + 47, originY + 33,
				204, 0,
				18, 18,
				256, 256);
		
		int fireHeight = campfireEnt.getBurnTimeRemainingScaled(13);
		
		if (fireHeight >= 13)
			fireHeight = 18;
		
		drawImage(originX + 47, originY + 48 - fireHeight,	// Pos
				204, 34 - fireHeight,	// U, V
				18, fireHeight + 3,		// W, H
				256, 256);				// Tex
        
		// Cook progress
		drawImage(originX + 72, originY + 33,
				229, 0,
				22, 18,
				256, 256);
        
		int cookWidth = campfireEnt.getCookProgressScaled(22);
		drawImage(originX + 72, originY + 33,	// Pos
				229, 19,		// U, V
				cookWidth, 18,	// W, H
				256, 256);		// Tex
        
    	mc.renderEngine.bindTexture(statIcons);
        
        for (Object slotObj : inventorySlots.inventorySlots)
        {
        	Slot slot = (Slot)slotObj;
        	int x = originX + slot.xDisplayPosition - 1;
        	int y = originY + slot.yDisplayPosition - 1;
        	
        	if (slot instanceof SlotFurnace)
        	{
                mc.renderEngine.bindTexture(backgroundLocation);
        		drawImage(x - 4, y - 4, 177, 0, 26, 26, 256, 256);
            	mc.renderEngine.bindTexture(statIcons);
        	}
        	else
        	{
        		drawSlot(x, y, 0, 0);
        	}
        }
    }

}