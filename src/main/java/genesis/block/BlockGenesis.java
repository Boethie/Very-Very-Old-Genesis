package genesis.block;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockGenesis extends Block {
    private ItemStack drop;
    private int rangeStart, rangeEnd;

    public BlockGenesis(Material material) {
        super(material);
        setCreativeTab(GenesisTabs.tabGenesisBlock);
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    public BlockGenesis setDrop(ItemStack drop) {
        return setDrop(drop, 1, 1);
    }

    public BlockGenesis setDrop(ItemStack dropStack, int startRange, int endRange) {
        drop = dropStack;
        rangeStart = startRange;
        rangeEnd = endRange;
        return this;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (drop != null) {
            ret.clear();
            int count = world.rand.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
            for (int i = 0; i < count; i++) {
                ret.add(drop.copy());
            }
        }

        return ret;
    }
}
