package genesis.common;

import cpw.mods.fml.relauncher.Side;

import genesis.client.ServerTickHandler;

public class CommonProxy {

	public void registerRenderers() {}

	public void preInit() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}

	public void init() {}

}
