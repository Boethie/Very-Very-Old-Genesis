package genesis.genesis.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import genesis.genesis.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	
	
	public void registerRender(){
		
	}
	public void preInit(){
		MinecraftForge.EVENT_BUS.register(this);
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

}
