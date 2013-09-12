package genesis.genesis.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import genesis.genesis.block.BlockMossRenderer;
import genesis.genesis.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	
	public void registerRenderers(){
		RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
		
		super.registerRenderers();
	}
	
	public void preInit(){
		MinecraftForge.EVENT_BUS.register(this);

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		super.preInit();
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

}
