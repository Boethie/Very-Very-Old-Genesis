package genesis.genesis.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockGenesisOre extends BlockGenesisRock {
	
	protected int[] dropItemIDs;
	protected int[] dropItemDamages;
	protected int minDrop;
	protected int maxDrop;
	protected int minXP;
	protected int maxXP;
	
	public BlockGenesisOre(int blockID, Object[] drops, int[] damages, int minDrop, int maxDrop, int minXP, int maxXP) {
		super(blockID);
		
		setDrops(drops, damages);
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
		this.minXP = minXP;
		this.maxXP = maxXP;
	}
	
	public BlockGenesisOre(int blockID, Object[] drops, int[] damages, int minDrop, int maxDrop) {
		super(blockID);

		setDrops(drops, damages);
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
	}
	
	public void setDrops(Object[] drops, int[] damages)
	{
		dropItemIDs = new int[drops.length];
		dropItemDamages = new int[dropItemIDs.length];
		
		for (int i = 0; i < drops.length; i++)
		{
			Object objDrop = drops[i];
			
			if (objDrop instanceof Block)
				dropItemIDs[i] = ((Block)objDrop).blockID;
			else if (objDrop instanceof Item)
				dropItemIDs[i] = ((Item)objDrop).itemID;
			else if (objDrop instanceof Integer)
				dropItemIDs[i] = (Integer)objDrop;
			
			int damage = 0;
			
			if (damages != null && i < damages.length)
				damage = damages[i];
			
			dropItemDamages[i] = damage;
		}
	}
	
	public int quantityDropped(int metadata, int fortune, Random random)
	{
        int bonus = Math.max(random.nextInt(fortune + 2) - 1, 0);
        return MathHelper.getRandomIntegerInRange(random, minDrop, maxDrop) * (bonus + 1);
	}
	
    public int dropIndex(int metadata, Random random, int fortune)
    {
        return random.nextInt(dropItemIDs.length);
    }
	
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        
        for(int i = 0; i < count; i++)
        {
            int dropIndex = dropIndex(metadata, world.rand, fortune);
            int dropItemID = dropItemIDs[dropIndex];
            int dropDamage = dropItemDamages[dropIndex];
            
            ret.add(new ItemStack(dropItemID, 1, dropDamage));
        }
        
        return ret;
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
