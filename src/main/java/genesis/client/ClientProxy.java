package genesis.client;

import genesis.block.gui.TileEntityCampfire;
import genesis.client.renderer.BlockBjuviaConeRenderer;
import genesis.client.renderer.BlockCampfireRenderer;
import genesis.client.renderer.BlockGenesisFlowerPotRenderer;
import genesis.client.renderer.BlockGenesisPlantRenderer;
import genesis.client.renderer.BlockMossRenderer;
import genesis.client.renderer.BlockTikiTorchRenderer;
import genesis.client.renderer.TileEntityCampfireRenderer;
import genesis.common.CommonProxy;
import genesis.common.Genesis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(new GenesisClientEventHandler());
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new BlockMossRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisPlantRenderer());
        RenderingRegistry.registerBlockHandler(new BlockGenesisFlowerPotRenderer());
        RenderingRegistry.registerBlockHandler(new BlockTikiTorchRenderer());
        RenderingRegistry.registerBlockHandler(new BlockCampfireRenderer());
        RenderingRegistry.registerBlockHandler(new BlockBjuviaConeRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
    }

    @Override
    public boolean areLeavesOpaque() {
        return FMLCommonHandler.instance().getEffectiveSide() != Side.SERVER && !Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }
    
    @Override
    public void playSound(double x, double y, double z, String sound, float volume, float frequency) {
    	ResourceLocation soundLocation = new ResourceLocation(Genesis.MOD_ID, sound);
    	FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(soundLocation, volume, frequency, (float) x, (float) y, (float) z));
    }
}
