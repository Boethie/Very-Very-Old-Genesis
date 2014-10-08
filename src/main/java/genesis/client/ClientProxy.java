package genesis.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import genesis.Genesis;
import genesis.client.model.entity.ModelEryops;
import genesis.client.renderer.BlockCampfireRenderer;
import genesis.client.renderer.ItemPolissoirRender;
import genesis.client.renderer.TileEntityCampfireRenderer;
import genesis.client.renderer.TileEntityPolissoirRenderer;
import genesis.client.renderer.block.*;
import genesis.client.renderer.entity.RenderEryops;
import genesis.common.CommonProxy;
import genesis.entity.EntityEryops;
import genesis.managers.GenesisModBlocks;
import genesis.tileentity.TileEntityCampfire;
import genesis.tileentity.TileEntityPolissoir;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    public static int polissoirRenderID;

    @Override
    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(new GenesisClientEventHandler());
    }

    @Override
    public void registerRenderers() {
        polissoirRenderID = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerEntityRenderingHandler(EntityEryops.class, new RenderEryops(new ModelEryops(), 0.5F));

        RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
        RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
        RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
        RenderingRegistry.registerBlockHandler(new BlockBjuviaConeRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GenesisModBlocks.polissoir), new ItemPolissoirRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPolissoir.class, new TileEntityPolissoirRenderer());

    }

    @Override
    public boolean areLeavesOpaque() {
        return FMLCommonHandler.instance().getEffectiveSide() != Side.SERVER && !FMLClientHandler.instance().getClient().gameSettings.fancyGraphics;
    }

    @Override
    public void playSound(double x, double y, double z, String sound, float volume, float frequency) {
        ResourceLocation soundLocation = new ResourceLocation(Genesis.ASSETS + sound);
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(soundLocation, volume, frequency, (float) x, (float) y, (float) z));
    }
}
