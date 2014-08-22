package genesis.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import genesis.block.GenesisModBlocks;
import genesis.block.gui.TileEntityCampfire;
import genesis.block.gui.TileEntityPolissoir;
import genesis.entity.ModEntities;
import genesis.fluid.BucketHandler;
import genesis.item.GenesisModItems;
import genesis.item.Recipes;
import genesis.lib.ConfigHandler;
import genesis.lib.GenesisVersion;
import genesis.lib.LogHelper;
import genesis.lib.Names;
import genesis.world.WorldProviderGenesis;
import genesis.world.WorldTypeGenesis;
import genesis.world.biome.GenesisBiomes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;

import java.util.HashMap;

@Mod(modid = Genesis.MOD_ID, name = "Project Genesis", version = Genesis.MOD_VERSION, dependencies = "required-after:Forge")
public class Genesis {

    public static final String MOD_ID = "genesis";
    public static final String ASSETS = MOD_ID + ":";
    public static final String MOD_VERSION = "@VERSION@";
    @Instance(Genesis.MOD_ID)
    public static Genesis instance;
    @SidedProxy(clientSide = "genesis.client.ClientProxy", serverSide = "genesis.common.CommonProxy")
    public static CommonProxy proxy;
    @Metadata(Genesis.MOD_ID)
    public static ModMetadata metadata;
    public static HashMap<Class<? extends TileEntity>, String> teClassToNameMap;

    public static int dimensionID = DimensionManager.getNextFreeDimId();

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        Genesis.instance = this;

        LogHelper.init();

        GenesisVersion.performVersioning();

        ConfigHandler.init(evt.getSuggestedConfigurationFile());

        proxy.preInit();

        LogHelper.log(Level.INFO, "Preparing Blocks and Items");
        GenesisModBlocks.initiate();
        GenesisModItems.initiate();

        GenesisModBlocks.registerBlocks();
        GenesisModItems.registerItems();

        Recipes.registerRecipes();

        GenesisBiomes.config();

        LogHelper.log(Level.INFO, "Blocks and Items Loaded");

        ModEntities.init();
        LogHelper.log(Level.INFO, "Registered Entities");

        GameRegistry.registerTileEntity(TileEntityCampfire.class, Names.mod + "Campfire");
        GameRegistry.registerTileEntity(TileEntityPolissoir.class, Names.mod + "Polissoir");
    }

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Genesis.instance, new GenesisGuiHandler());

        //MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new GenesisEventHandler());
        MinecraftForge.EVENT_BUS.register(BucketHandler.getInstance());

        GenesisBiomes.init();

        DimensionManager.registerProviderType(dimensionID, WorldProviderGenesis.class, true);
        DimensionManager.registerDimension(dimensionID, dimensionID);
        WorldTypeGenesis genesisTestType = new WorldTypeGenesis("genesisTestType");

        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        /* When retrieving static fields via reflection, it is unnecessary to pass an actual instance of the class */
        teClassToNameMap = ReflectionHelper.getPrivateValue(TileEntity.class, null, "classToNameMap", "field_70323_b");
    }
}
