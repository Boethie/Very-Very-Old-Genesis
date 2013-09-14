package genesis.genesis.item.plants;

import genesis.genesis.block.plants.PlantBlocks;
import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.item.Item;

public class PlantItems {
	
	public static Item calamites;
	
	public static void init()
	{
		calamites = new ItemPlant(IDs.itemCalamitesID, PlantBlocks.blockCalamites).setTextureName("calamites")
				.setUnlocalizedName(Names.itemCalamites_unloc);
	}
	
	

}
