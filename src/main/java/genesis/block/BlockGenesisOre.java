package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGenesisOre extends BlockGenesisRock {
    protected int minDrop;
    protected int maxDrop;
    protected int minXP;
    protected int maxXP;

    public BlockGenesisOre(Material material, int harvestLevel, int minDrop, int maxDrop, int minXP, int maxXP) {
        super(material, harvestLevel);

        this.minDrop = minDrop;
        this.maxDrop = maxDrop;
        this.minXP = minXP;
        this.maxXP = maxXP;
    }

    public BlockGenesisOre(Material material, int harvestLevel, int minDrop, int maxDrop) {
        this(material, harvestLevel, minDrop, maxDrop, 1, 1);
    }

    public BlockGenesisOre(Material material, int harvestLevel) {
        this(material, harvestLevel, 1, 1);
    }

    public BlockGenesisOre setDropAndSmelting(Object drop, int damage, float smeltXP) throws IllegalArgumentException {
        ItemStack stack;

        if (drop instanceof Block)
            stack = new ItemStack((Block) drop, 1, damage);
        else if (drop instanceof Item)
            stack = new ItemStack((Item) drop, 1, damage);
        else
            throw new IllegalArgumentException("Invalid Genesis ore item drop: " + drop);

        setDrop(stack);
        setSmelting(stack, smeltXP);

        return this;
    }

    public BlockGenesisOre setSmelting(ItemStack stack, float xp) {
        // FurnaceRecipes.smelting().addSmelting(block, stack, xp);
        FurnaceRecipes.smelting().func_151393_a(this, stack, xp);

        return this;
    }

    @Override
    public int quantityDropped(int metadata, int fortune, Random random) {
        int bonus = Math.max(random.nextInt(fortune + 2) - 1, 0);
        return MathHelper.getRandomIntegerInRange(random, minDrop, maxDrop) * (bonus + 1);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);

        if (minXP >= 0 && maxXP >= minXP) {
            int xp = MathHelper.getRandomIntegerInRange(world.rand, minXP, maxXP);
            dropXpOnBlockBreak(world, x, y, z, xp);
        }
    }
}
