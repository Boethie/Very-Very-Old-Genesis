package genesis.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockGenesisOre extends BlockGenesisRock {
    protected int dropMin, dropMax;
    protected int xpMin, xpMax;

    public BlockGenesisOre(int harvestLevel) {
        this(harvestLevel, 1, 1);
    }

    public BlockGenesisOre(int harvestLevel, int minDrop, int maxDrop) {
        this(harvestLevel, minDrop, maxDrop, 1, 1);
    }

    public BlockGenesisOre(int harvestLevel, int minDrop, int maxDrop, int minXP, int maxXP) {
        super(Material.rock, harvestLevel);
        setHardness(3.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 1);
        dropMin = minDrop;
        dropMax = maxDrop;
        xpMin = minXP;
        xpMax = maxXP;
    }

    public BlockGenesisOre setDropAndSmelting(Object drop, int damage, float smeltXP) {
        ItemStack stack = null;

        if (drop instanceof Block) {
            stack = new ItemStack((Block) drop, 1, damage);
        } else if (drop instanceof Item) {
            stack = new ItemStack((Item) drop, 1, damage);
        } else {
            new IllegalArgumentException("Invalid Genesis ore item drop: " + drop).printStackTrace();
        }

        if (stack != null) {
            setDrop(stack);
            setSmelting(stack, smeltXP);
        }

        return this;
    }

    public BlockGenesisOre setSmelting(ItemStack stack, float xp) {
        GameRegistry.addSmelting(this, stack, xp);
        return this;
    }

    @Override
    public int quantityDropped(int metadata, int fortune, Random random) {
        int bonus = Math.max(random.nextInt(fortune + 2) - 1, 0);
        return MathHelper.getRandomIntegerInRange(random, dropMin, dropMax) * (bonus + 1);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);

        if (xpMin >= 0 && xpMax >= xpMin) {
            int xp = MathHelper.getRandomIntegerInRange(world.rand, xpMin, xpMax);
            dropXpOnBlockBreak(world, x, y, z, xp);
        }
    }
}
