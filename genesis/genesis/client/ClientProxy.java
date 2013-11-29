package genesis.genesis.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import genesis.genesis.block.BlockMossRenderer;
import genesis.genesis.block.BlockTikiTorchRenderer;
import genesis.genesis.block.gui.BlockCampfireRenderer;
import genesis.genesis.block.gui.TileEntityCampfire;
import genesis.genesis.block.gui.TileEntityCampfireRenderer;
import genesis.genesis.block.plants.BlockGenesisFlowerPotRenderer;
import genesis.genesis.block.plants.BlockGenesisPlantRenderer;
import genesis.genesis.common.CommonProxy;
import genesis.genesis.common.GenesisEventHandler;

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
