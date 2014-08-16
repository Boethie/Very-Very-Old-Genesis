package genesis.fluid;

import genesis.fluid.block.BlockKomatiiticLava;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Fluid for {@link BlockKomatiiticLava}.
 *
 * @author rubensworks
 */
public class FluidKomatiiticLava extends Fluid {

    /**
     * Make a new instance.
     *
     * @param fluidName The fluid name that will be used inside the {@link FluidRegistry}.
     */
    public FluidKomatiiticLava(String fluidName) {
        super(fluidName);
        FluidRegistry.registerFluid(this);
        setDensity(1500); // How tick the fluid is, affects movement inside the liquid.
        setViscosity(1500); // How fast the fluid flows.
        setTemperature(1873); // 1600 degrees C
        setLuminosity(15); // Max light level
    }

}
