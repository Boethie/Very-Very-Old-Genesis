package genesis.block.aquatic;

import cpw.mods.fml.common.registry.GameRegistry;
import genesis.item.itemblock.ItemBlockCharnia;
import genesis.item.itemblock.ItemBlockGenesisAlgae;
import genesis.item.itemblock.ItemBlockGenesisCoral;
import genesis.item.itemblock.ItemBlockGenesisSponge;
import genesis.lib.Names;

/**
 * @author Arbiter
 */
public final class GenesisAquaticBlocks {
    public static BlockGenesisAlgae algae;
    public static BlockGenesisCoral coral;
    public static BlockGenesisSponge sponge;
    public static BlockPteridinium pteridinium;

    public static BlockCharnia charnia;
    public static BlockCharniaTop charnia_top;

    public static void initiate() {
        coral = (BlockGenesisCoral) new BlockGenesisCoral().setBlockName(Names.blockCoral);
        sponge = (BlockGenesisSponge) new BlockGenesisSponge().setBlockName(Names.blockSponge);
        algae = (BlockGenesisAlgae) new BlockGenesisAlgae().setBlockName(Names.blockAlgae);
        pteridinium = (BlockPteridinium) new BlockPteridinium().setBlockName(Names.blockPteridinium).setBlockTextureName("pteridinium");
        charnia = (BlockCharnia) new BlockCharnia().setBlockName(Names.blockCharnia).setBlockTextureName("charnia_bottom");
        charnia_top = (BlockCharniaTop) new BlockCharniaTop().setBlockName(Names.blockCharniaTop).setBlockTextureName("charnia_top");
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(algae, ItemBlockGenesisAlgae.class, Names.Registry.blockAlgae);
        GameRegistry.registerBlock(coral, ItemBlockGenesisCoral.class, Names.Registry.blockCoral);
        GameRegistry.registerBlock(sponge, ItemBlockGenesisSponge.class, Names.Registry.blockSponge);
        GameRegistry.registerBlock(pteridinium, Names.Registry.blockPteridinium);
        GameRegistry.registerBlock(charnia, ItemBlockCharnia.class, Names.Registry.blockCharnia);
        GameRegistry.registerBlock(charnia_top, Names.Registry.blockCharniaTop);
    }
}