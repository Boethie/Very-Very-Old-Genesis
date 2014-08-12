package genesis.fluid.item;

import genesis.common.Genesis;
import genesis.fluid.BucketHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Generic class for fluid buckets.
 * @author rubensworks
 *
 */
public class ItemGenesisBucket extends ItemBucket {

	protected Block fluidBlock;
	protected Fluid fluid;
	
	public ItemGenesisBucket(Block block, Fluid fluid) {
        super(block);
        this.fluidBlock = block;
        this.fluid = fluid;
        setContainerItem(Items.bucket);
    }
	
	@Override
	public Item setUnlocalizedName(String unlocName) {
		registerItem(unlocName);
		return super.setUnlocalizedName(unlocName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getIconString());
	}
	
	protected void registerItem(String name) {
		GameRegistry.registerItem(this, name);
		bindToFluid(fluid);
	}
	
	/**
	 * Called after registering this bucket to bind it to a fluid.
	 * @param fluid The fluid to bind to.
	 */
	protected void bindToFluid(Fluid fluid) {
        Item item = this;
        FluidStack fluidStack = FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME);
        FluidContainerRegistry.registerFluidContainer(
                fluidStack,
                new ItemStack(item),
                new ItemStack(Items.bucket)
        );
        BucketHandler.getInstance().buckets.put(fluidBlock, item);
    }
	
}
