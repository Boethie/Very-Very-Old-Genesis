package genesis.genesis.plants.items;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import genesis.genesis.plants.blocks.PlantBlocks;
import net.minecraft.item.Item;

public class PlantItems {
	
	public static Item calamites;
	
	public static void init()
	{
		calamites = new ItemPlant(IDs.itemCalamitesID, PlantBlocks.blockCalamites).setTextureName("calamites")
				.setUnlocalizedName(Names.itemCalamites_unloc);
	}
	
	

}
