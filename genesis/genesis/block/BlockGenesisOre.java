package genesis.genesis.block;

import genesis.genesis.item.Items;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockGenesisOre extends BlockGenesisRock {

	protected ItemStack drop;
	protected int minDrop;
	protected int maxDrop;
	protected int minXP;
	protected int maxXP;
	
	public BlockGenesisOre(int blockID, Material mat, int harvestLevel, int minDrop, int maxDrop, int minXP, int maxXP) {
		super(blockID, mat, harvestLevel);
		
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
		this.minXP = minXP;
		this.maxXP = maxXP;
	}
	
	public BlockGenesisOre(int blockID, Material mat, int harvestLevel, int minDrop, int maxDrop) {
		super(blockID, mat, harvestLevel);

		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
	}
	
	public BlockGenesisOre(int blockID, Material mat, int harvestLevel) {
		super(blockID, mat, harvestLevel);

		this.minDrop = 1;
		this.maxDrop = 1;
	}
	
	public BlockGenesisOre setDrop(Object drop, int damage, float smeltXP) throws IllegalArgumentException
	{
		int itemID = 0;
		
		if (drop instanceof Block)
			itemID = ((Block)drop).blockID;
		else if (drop instanceof Item)
			itemID = ((Item)drop).itemID;
		else if (drop instanceof Integer)
			itemID = (Integer)drop;
		else
			throw new IllegalArgumentException("Invalid Genesis ore item drop: " + drop);
		
		ItemStack stack = new ItemStack(itemID, 1, damage);
		setDrop(stack);
		setSmelting(stack, smeltXP);
		
		return this;
	}
	
	public BlockGenesisOre setDrop(ItemStack drop)
	{
		this.drop = drop;
		
		return this;
	}
	
	public BlockGenesisOre setSmelting(ItemStack stack, float xp)
	{
		FurnaceRecipes.smelting().addSmelting(blockID, 0, stack, xp);
		
		return this;
	}
	
	public int quantityDropped(int metadata, int fortune, Random random)
	{
        int bonus = Math.max(random.nextInt(fortune + 2) - 1, 0);
        return MathHelper.getRandomIntegerInRange(random, minDrop, maxDrop) * (bonus + 1);
	}
	
    public int idDropped(int metdata, Random random, int fortune)
    {
        return drop.itemID;
    }
    
    public int damageDropped(int par1)
    {
        return drop.getItemDamage();
    }
    
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);

        if (minXP >= 0 && maxXP >= minXP)
        {
            int xp = MathHelper.getRandomIntegerInRange(world.rand, minXP, maxXP);
            dropXpOnBlockBreak(world, x, y, z, xp);
        }
    }
    
}
