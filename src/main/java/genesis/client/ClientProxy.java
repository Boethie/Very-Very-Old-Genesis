package genesis.client;

import genesis.block.gui.TileEntityCampfire;
import genesis.client.renderer.BlockCampfireRenderer;
import genesis.client.renderer.BlockGenesisFlowerPotRenderer;
import genesis.client.renderer.BlockGenesisPlantRenderer;
import genesis.client.renderer.BlockHausmanniaRenderer;
import genesis.client.renderer.BlockMossRenderer;
import genesis.client.renderer.BlockTikiTorchRenderer;
import genesis.client.renderer.TileEntityCampfireRenderer;
import genesis.common.CommonProxy;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		super.registerRenderers();

		RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockHausmanniaRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());

		LanguageLoader.loadLanguages();
	}
}
