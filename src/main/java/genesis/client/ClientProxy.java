package genesis.client;

import genesis.block.gui.TileEntityCampfire;
import genesis.client.event.GuiEventHandler;
import genesis.client.event.TextureStitchEventHandler;
import genesis.client.renderer.BlockBjuviaConeRenderer;
import genesis.client.renderer.BlockCampfireRenderer;
import genesis.client.renderer.BlockGenesisFlowerPotRenderer;
import genesis.client.renderer.BlockGenesisPlantRenderer;
import genesis.client.renderer.BlockMossRenderer;
import genesis.client.renderer.BlockTikiTorchRenderer;
import genesis.client.renderer.TileEntityCampfireRenderer;
import genesis.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() 
    {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new TextureStitchEventHandler());
    }
    
	@Override
	public void registerRenderers() {
		super.registerRenderers();

		RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
		RenderingRegistry.registerBlockHandler(new BlockBjuviaConeRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());

		LanguageLoader.loadLanguages();
	}
	@Override
	public boolean areLeavesOpaque(){
		return FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER?false:!Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}
}
