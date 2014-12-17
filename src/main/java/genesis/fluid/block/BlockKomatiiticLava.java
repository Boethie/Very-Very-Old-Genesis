package genesis.fluid.block;

import genesis.Genesis;
import genesis.client.GenesisClientEventHandler;
import genesis.fluid.FluidKomatiiticLava;
import genesis.managers.GenesisModBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Fluid block for {@link FluidKomatiiticLava}.
 *
 * @author rubensworks
 */
public class BlockKomatiiticLava extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    protected IIcon[] icons;

    /**
     * Make a new instance.
     * This will bind the fluid to this block and vice-versa.
     *
     * @param fluid    The fluid to bind with.
     * @param material The material of the block.
     */
    public BlockKomatiiticLava(Fluid fluid, Material material) {
        super(fluid, material);
        disableStats();
        setLightLevel(1.0F);
        setTickRandomly(true);

        // Check if the fluid already has a block, to comply with the FluidDict way of working.
        if (fluid.getBlock() == null) {
            fluid.setBlock(this);
            GenesisClientEventHandler.fluidMap.put(fluid, this);
        }
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon(getTextureName() + "_still");
        icons[1] = iconRegister.registerIcon(getTextureName() + "_flow");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? icons[1] : icons[0];
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        // Check for surrounding water and replace this block with a stone if so.
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            int tx = x + direction.offsetX;
            int ty = y + direction.offsetY;
            int tz = z + direction.offsetZ;
            if (world.getBlock(tx, ty, tz) == Blocks.water) {
                world.setBlock(x, y, z, GenesisModBlocks.komatiite);
                return;
            }
        }

        super.updateTick(world, x, y, z, random);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!entity.isImmuneToFire()) {
            // This damage stack with the default lava material damage all entities take.
            entity.attackEntityFrom(DamageSource.lava, 4.0F);
            // The default fire duration is extended to 30 ticks instead of the normal 15 ticks.
            entity.setFire(30);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (world.getBlock(x, y + 1, z).getMaterial() == Material.air && !world.getBlock(x, y + 1, z).isOpaqueCube()) {
            // Random lava popping particles
            if (random.nextInt(45) == 0) {
                double px, py, pz;
                px = (double) ((float) x + random.nextFloat());
                py = (double) y + maxY;
                pz = (double) ((float) z + random.nextFloat());
                world.spawnParticle("lava", px, py, pz, 0.0D, 0.0D, 0.0D);
                world.playSound(px, py, pz, "liquid.lavapop", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(100) == 0) {
                world.playSound((double) x, (double) y, (double) z, "liquid.lava", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }

        // Drop lava particles from the bottom of the block
        if (random.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement()) {
            double px, py, pz;
            px = (double) ((float) x + random.nextFloat());
            py = (double) y - 1.05D;
            pz = (double) ((float) z + random.nextFloat());

            world.spawnParticle("dripLava", px, py, pz, 0.0D, 0.0D, 0.0D);
        }
    }
}
