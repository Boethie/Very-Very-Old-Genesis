package genesis.genesis.common;

import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import genesis.genesis.lib.Blocks;
import genesis.genesis.lib.ConfigHandler;
import genesis.genesis.lib.LogHelper;
import genesis.genesis.packet.GenesisPacket;
import genesis.genesis.packet.PacketHandler;
import genesis.genesis.world.WorldGenSigillariaTree;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

	@Mod(modid = Genesis.modid, name = "Genesis", version = "0.0.1")
	@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels={GenesisPacket.CHANNEL}, packetHandler = PacketHandler.class)
public class Genesis {
	
	@Instance
	public static Genesis instance;
	
	@SidedProxy (clientSide = "genesis.genesis.client.ClientProxy", serverSide = "genesis.genesis.common.CommonProxy")
	public static CommonProxy proxy;
	
	public final static String modid = "genesis";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		LogHelper.init();
		ConfigHandler.init(evt.getSuggestedConfigurationFile());
		proxy.registerRender();
		proxy.preInit();
		LogHelper.log(Level.INFO, "Preparing Blocks");
		Blocks.init();
		Blocks.registerBlocks();
		Blocks.addNames();
		LogHelper.log(Level.INFO, "Blocks Loaded");
	}
	@EventHandler
	public void Init(FMLInitializationEvent evt)
	{
		Genesis.instance = this;
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		MinecraftForge.EVENT_BUS.register(new GenesisEventHandler());
		GameRegistry.registerWorldGenerator(new WorldGenSigillariaTree(false));
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabGenesis", "en_US", "Genesis");
	}
	public static CreativeTabs tabGenesis = new CreativeTabs("tabGenesis") {
        public ItemStack getIconItemStack() {
                return new ItemStack(Blocks.blockSaplingGenesis, 1, 2);
        }};	

}
