package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class BlockGenesisOreStorage extends BlockGenesisRock {

	public BlockGenesisOreStorage(Material material, int harvestLevel) {
		super(material, harvestLevel);

		setStepSound(soundTypeMetal);
	}

	public void setRecipe(Object storingItem, boolean craftFour) {
		ItemStack storingStack = null;

		if (storingItem instanceof Item)
			storingStack = new ItemStack((Item) storingItem);
		else if (storingItem instanceof Block)
			storingStack = new ItemStack((Block) storingItem);
		else if (storingItem instanceof ItemStack)
			storingStack = (ItemStack) storingItem;

		if (storingStack != null) {
			ItemStack output = storingStack.copy();

			if (craftFour) {
				CraftingManager.getInstance().addRecipe(new ItemStack(this), "mm", "mm", 'm', storingItem);

				output.stackSize = 4;
			} else {
				CraftingManager.getInstance().addRecipe(new ItemStack(this), "mmm", "mmm", "mmm", 'm', storingItem);

				output.stackSize = 9;
			}

			CraftingManager.getInstance().addRecipe(output, "m", 'm', this);
		}
	}

}
