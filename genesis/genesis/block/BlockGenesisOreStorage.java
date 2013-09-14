package genesis.genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class BlockGenesisOreStorage extends BlockGenesisRock {
	
	public BlockGenesisOreStorage(int blockID, Material mat, int harvestLevel) {
		super(blockID, mat, harvestLevel);
		
        setStepSound(soundMetalFootstep);
	}
	
	public void setRecipe(Object storingItem, boolean craftFour)
	{
        if (craftFour)
        {
        	CraftingManager.getInstance().addRecipe(new ItemStack(this),
        			"mm",
        			"mm",
        			'm', storingItem);
        }
        else
        {
        	CraftingManager.getInstance().addRecipe(new ItemStack(this),
        			"mmm",
        			"mmm",
        			"mmm",
        			'm', storingItem);
        }
	}
	
}
