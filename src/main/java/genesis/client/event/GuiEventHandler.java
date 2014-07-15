package genesis.client.event;

import genesis.common.Genesis;
import genesis.lib.GenesisVersion;
import genesis.lib.GenesisVersion.Status;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
//import net.minecraftforge.client.event.GuiScreenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiEventHandler 
{
	@SubscribeEvent
	public void onGuiDraw(GuiScreenEvent.DrawScreenEvent event)
	{
		if(event.gui instanceof GuiMainMenu)
		{
			FontRenderer font = event.gui.mc.fontRenderer;
			String line = "Genesis  " + Genesis.MOD_VERSION;
			String status = getStringFromStatus(GenesisVersion.getStatus());
			event.gui.drawCenteredString(font, line, font.getStringWidth(line) / 2  + 2, event.gui.height - (event.gui.height / 4) - font.FONT_HEIGHT,  -1);
			event.gui.drawCenteredString(font, status, font.getStringWidth(status) / 2  + 2, event.gui.height - (event.gui.height / 4),  -1);
		}
	}

	private String getStringFromStatus(Status status) 
	{
		switch(status)
		{
		case OUTDATED: return EnumChatFormatting.RED + "OUTDATED: Genesis " + GenesisVersion.getTarget();
		case UP_TO_DATE: return EnumChatFormatting.GREEN + "Genesis is up-to-date";
		case AHEAD: return EnumChatFormatting.YELLOW + "Genesis is ahead!";
		case FAILED: return EnumChatFormatting.RED + "Error with Versioning";
		case PENDING: return EnumChatFormatting.BLUE + "The version has yet to be retrieved";
		default: return "Unknown ";
		}
	}
}
