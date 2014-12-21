package genesis.fluid;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This will take care of the logic of custom buckets, so they can be filled like other buckets.
 *
 * @author rubensworks
 */
public class BucketHandler {
    private static final BucketHandler INSTANCE = new BucketHandler();

    /**
     * The map that will map the fluid block to the respective bucket that is capable
     * to hold the fluid of that block.
     */
    public Map<Block, Item> buckets = new HashMap<Block, Item>();

    private BucketHandler() {
    }

    /**
     * Get the unique instance.
     *
     * @return The unique instance.
     */
    public static BucketHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Called when player right clicks with an empty bucket on a fluid block.
     *
     * @param event The Forge event required for this.
     */
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        ItemStack result = fillCustomBucket(event.world, event.target);

        if (result != null) {
            event.result = result;
            event.setResult(Result.ALLOW);
        }
    }

    public void bindBucketToFluid(Item item, Block fluidBlock, Fluid fluid) {
        FluidStack fluidStack = FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME);
        FluidContainerRegistry.registerFluidContainer(fluidStack, new ItemStack(item), new ItemStack(Items.bucket));
        buckets.put(fluidBlock, item);
    }

    private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

        Item bucket = buckets.get(block);
        if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
            world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
            return new ItemStack(bucket);
        } else {
            return null;
        }
    }
}
