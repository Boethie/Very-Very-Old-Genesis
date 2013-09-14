package genesis.genesis.block.plants;

import genesis.genesis.lib.IDs;
import genesis.genesis.lib.Names;
import net.minecraft.block.Block;

public class PlantBlocks {
	
	public static Block blockCalamites;
	
	public static void init()
	{
		blockCalamites = new BlockCalamitesPlant(IDs.blockCalamitesPlantID).setTextureName("calamites")
				.setUnlocalizedName(Names.blockCalamites_unloc);
	}

}
