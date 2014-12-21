package genesis.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPermafrost extends BlockGenesis {
    public BlockPermafrost() {
        super(Material.ground);
        setHardness(0.5F);
        setStepSound(soundTypeGravel);
        setTickRandomly(true);
        setHarvestLevel("shovel", 0);
    }

    // TODO: Currently mimics behaviour of ice, change if necessary
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.getSavedLightValue(EnumSkyBlock.BLOCK, x, y + 1, z) > 8) {
            world.setBlock(x, y, z, Blocks.dirt);
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(Blocks.dirt));
        return list;
    }
}
