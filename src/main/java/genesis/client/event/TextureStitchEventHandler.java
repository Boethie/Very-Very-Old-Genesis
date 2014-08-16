package genesis.client.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Event handler for {@link TextureStitchEvent}.
 *
 * @author rubensworks
 */
public class TextureStitchEventHandler {

    /**
     * The mapping of {@link Fluid} to {@link BlockFluidBase}.
     */
    public static Map<Fluid, BlockFluidBase> fluidMap = new HashMap<Fluid, BlockFluidBase>();

    /**
     * After the texture stitching.
     *
     * @param event The received event.
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void postStitch(TextureStitchEvent.Post event) {
        for (Entry<Fluid, BlockFluidBase> fluids : fluidMap.entrySet()) {
            fluids.getKey().setIcons(fluids.getValue().getBlockTextureFromSide(0), fluids.getValue().getBlockTextureFromSide(1));
        }
    }
}
