package genesis.block.plants;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
import genesis.lib.Names;
import net.minecraft.block.BlockMushroom;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockArchaeomarasmius extends BlockMushroom
{
	public BlockArchaeomarasmius()
	{
		super();
		setHardness(0.0f);
		setResistance(0.0f);
		setStepSound(soundTypeGrass);
		setCreativeTab(GenesisTabs.tabGenesisDecoration);
	}
	
	protected BlockArchaeomarasmius register()
	{
		setBlockName(Names.blockArchaeomarasmius);
		setBlockTextureName(Genesis.MOD_ID + ":archaeomarasmius");
		return this;
	}
}