package genesis.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import genesis.block.GenesisModBlocks;
import genesis.block.gui.TileEntityCampfire;
import genesis.block.gui.TileEntityPolissoir;
import genesis.client.model.ModelEryops;
import genesis.client.renderer.*;
import genesis.client.renderer.entity.RenderEryops;
import genesis.common.CommonProxy;
import genesis.common.Genesis;
import genesis.entity.EntityEryops;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(new GenesisClientEventHandler());
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEryops.class, new RenderEryops(new ModelEryops(), 0.5F));

        RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
        RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
        RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
        RenderingRegistry.registerBlockHandler(new BlockBjuviaConeRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPolissoir.class, new TileEntityPolissoirRenderer());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GenesisModBlocks.polissoir), new ItemPolissoirRender());
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
