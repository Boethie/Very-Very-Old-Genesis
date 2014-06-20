package genesis.block.aquatic;

import genesis.item.itemblock.ItemBlockGenesisAlgae;
import genesis.item.itemblock.ItemBlockGenesisCoral;
import genesis.item.itemblock.ItemBlockGenesisSponge;
import genesis.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author Arbiter
 *
 */
public final class AquaticBlocks
{
	public static BlockGenesisAlgae algae;
	public static BlockGenesisCoral coral;
	public static BlockGenesisSponge sponge;
	
	private AquaticBlocks() {}
	
	public static void init()
	{		
		coral = (BlockGenesisCoral) new BlockGenesisCoral().setBlockName(Names.blockCoral);
		
		sponge = (BlockGenesisSponge) new BlockGenesisSponge().setBlockName(Names.blockSponge);

		algae = (BlockGenesisAlgae) new BlockGenesisAlgae().setBlockName(Names.blockAlgae);
	}
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(algae, ItemBlockGenesisAlgae.class, Names.blockAlgae);
		
		GameRegistry.registerBlock(coral, ItemBlockGenesisCoral.class, Names.blockCoral);
		
		GameRegistry.registerBlock(sponge, ItemBlockGenesisSponge.class, Names.blockSponge);
	}
}