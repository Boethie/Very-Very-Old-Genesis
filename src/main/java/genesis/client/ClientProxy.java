package genesis.client;

import net.minecraft.client.Minecraft;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;

import genesis.block.BlockMossRenderer;
import genesis.block.BlockTikiTorchRenderer;
import genesis.block.gui.BlockCampfireRenderer;
import genesis.block.gui.TileEntityCampfire;
import genesis.block.gui.TileEntityCampfireRenderer;
import genesis.block.plants.BlockGenesisFlowerPotRenderer;
import genesis.block.plants.BlockGenesisPlantRenderer;
import genesis.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	private static Minecraft mc;
	
	public void registerRenderers()
	{
		super.registerRenderers();

		RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
		RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
		
		LanguageLoader.loadLanguages();
	}
	
	@Override
	public void preInit()
	{
		super.preInit();

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
	
	@Override
	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new GenesisClientEventHandler());
	}
	
	public static Minecraft getMC()
	{
		if (mc == null)
			mc = Minecraft.getMinecraft();
		
		return mc;
	}

}
