package genesis.genesis.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import genesis.genesis.block.BlockMossRenderer;
import genesis.genesis.block.BlockTikiTorchRenderer;
import genesis.genesis.block.plants.BlockGenesisPlantRenderer;
import genesis.genesis.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	
	public void registerRenderers(){
		super.registerRenderers();

		RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
		RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
		RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
		
		LanguageLoader.loadLanguages();
	}
	
	public void preInit(){
		super.preInit();
		
		MinecraftForge.EVENT_BUS.register(this);

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

}
