package genesis.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;

public class ClientTickHandler implements ITickHandler {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	public static float partialTick;
	
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if (type.contains(TickType.RENDER))
		{
			partialTick = (Float)tickData[0];
		}
	}
	
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
	}
	
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}
	
	public String getLabel() {
		return "GenesisModClientTickHandler";
	}

}
