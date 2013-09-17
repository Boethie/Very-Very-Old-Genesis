package genesis.genesis.common;

import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.trees.TreeBlocks;
import genesis.genesis.block.trees.TreeBlocks.TreeBlockType;
import genesis.genesis.item.Items;
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

@Mod(modid = Genesis.MOD_ID, name = "Genesis", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels={GenesisPacket.CHANNEL}, packetHandler = PacketHandler.class)
public class Genesis {
	
	@Instance(Genesis.MOD_ID)
	public static Genesis instance;
	
	@SidedProxy (clientSide = "genesis.genesis.client.ClientProxy", serverSide = "genesis.genesis.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static final String MOD_ID = "genesis";
	
	public static CreativeTabs tabGenesis = new CreativeTabs("tabGenesis") {
        public ItemStack getIconItemStack() {
                return TreeBlocks.getBlockForType(TreeBlockType.SAPLING, TreeBlocks.SIGIL_NAME).getStack();
        }
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		Genesis.instance = this;
		
		LogHelper.init();
		
		ConfigHandler.init(evt.getSuggestedConfigurationFile());
		
		proxy.registerRenderers();
		proxy.preInit();
		
		LogHelper.log(Level.INFO, "Preparing Blocks and Items");
		Blocks.init();
		Items.init();
		
		Blocks.registerBlocks();
		Items.registerItems();
		LogHelper.log(Level.INFO, "Blocks and Items Loaded");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent evt)
	{
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		MinecraftForge.EVENT_BUS.register(new GenesisEventHandler());
		
		GameRegistry.registerWorldGenerator(new WorldGenSigillariaTree(false));
	}

}
