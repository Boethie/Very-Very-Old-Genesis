package genesis.block.aquatic;

import genesis.item.itemblock.ItemBlockGenesisAlgae;
import genesis.item.itemblock.ItemBlockGenesisCoral;
import genesis.item.itemblock.ItemBlockGenesisSponge;
import genesis.lib.Names;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Arbiter
 **/
public final class AquaticBlocks
{
	public static final Material aquatic = (new Material(MapColor.grassColor));
	
	public static BlockGenesisAlgae algae;
	public static BlockGenesisCoral coral;
	public static BlockGenesisSponge sponge;
	public static BlockPteridinium pteridinium;
	
	private AquaticBlocks() {}
	
	public static void init()
	{		
		coral = (BlockGenesisCoral) new BlockGenesisCoral().setBlockName(Names.blockCoral);
		
		sponge = (BlockGenesisSponge) new BlockGenesisSponge().setBlockName(Names.blockSponge);

		algae = (BlockGenesisAlgae) new BlockGenesisAlgae().setBlockName(Names.blockAlgae);
		
		pteridinium = (BlockPteridinium) new BlockPteridinium().setBlockName(Names.blockPteridinium).setBlockTextureName("pteridinium");
	}
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(algae, ItemBlockGenesisAlgae.class, Names.blockAlgae);
		
		GameRegistry.registerBlock(coral, ItemBlockGenesisCoral.class, Names.blockCoral);
		
		GameRegistry.registerBlock(sponge, ItemBlockGenesisSponge.class, Names.blockSponge);
		
		GameRegistry.registerBlock(pteridinium, Names.blockPteridinium);
	}
}