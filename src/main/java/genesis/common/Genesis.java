package genesis.common;

import java.util.HashMap;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

import genesis.block.ModBlocks;
import genesis.block.gui.TileEntityCampfire;
import genesis.block.trees.TreeBlocks;
import genesis.block.trees.TreeBlocks.TreeBlockType;
import genesis.block.trees.TreeBlocks.TreeType;
import genesis.item.ModItems;
import genesis.lib.ConfigHandler;
import genesis.lib.LogHelper;

@Mod(modid = Genesis.MOD_ID, name = "Project Genesis", version = Genesis.MOD_VERSION)
public class Genesis {

	@Instance(Genesis.MOD_ID)
	public static Genesis instance;

	@SidedProxy (clientSide = "genesis.client.ClientProxy", serverSide = "genesis.common.CommonProxy")
	public static CommonProxy proxy;

	public static final String MOD_ID = "genesis";
	public static final String MOD_VERSION = "0.0.1";
	
	public static HashMap<Class<?>, String> teClassToNameMap;
	public static CreativeTabs tabGenesis = new CreativeTabs("tabGenesis") {

		@Override
		public ItemStack getIconItemStack() {
			return TreeBlocks.getBlockForType(TreeBlockType.SAPLING, TreeType.SIGILLARIA.getName()).getStack();
		}

		@Override
		public Item getTabIconItem() {
			return getIconItemStack().getItem();
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		Genesis.instance = this;

		LogHelper.init();

		ConfigHandler.init(evt.getSuggestedConfigurationFile());

		proxy.registerRenderers();
		proxy.preInit();

		LogHelper.log(Level.INFO, "Preparing Blocks and Items");
		ModBlocks.init();
		ModItems.init();

		ModBlocks.registerBlocks();
		ModItems.registerItems();
		LogHelper.log(Level.INFO, "Blocks and Items Loaded");

		teClassToNameMap = ReflectionHelper.getPrivateValue(TileEntity.class, new TileEntity(), "classToNameMap", "field_70323_b");

		GameRegistry.registerTileEntity(TileEntityCampfire.class, MOD_ID + ".TileEntityCampfire");

		MinecraftForge.EVENT_BUS.register(new GenesisSoundLoader());
	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Genesis.instance, new GenesisGuiHandler());

		MinecraftForge.EVENT_BUS.register(new GenesisEventHandler());

		proxy.init();
	}
}
