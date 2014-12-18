package genesis.block.aquatic;

import genesis.item.itemblock.ItemBlockCharnia;
import genesis.item.itemblock.ItemBlockGenesisAlgae;
import genesis.item.itemblock.ItemBlockGenesisCoral;
import genesis.item.itemblock.ItemBlockGenesisSponge;
import genesis.lib.Names;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Arbiter
 */
public final class GenesisAquaticBlocks {
    public static Block algae;
    public static Block coral;
    public static Block sponge;
    public static Block pteridinium;

    public static Block charnia;
    public static Block charnia_top;

    public static void init() {
        algae = new BlockGenesisAlgae().setBlockName(Names.blockAlgae);
        coral = new BlockGenesisCoral().setBlockName(Names.blockCoral);
        sponge = new BlockGenesisSponge().setBlockName(Names.blockSponge);
        pteridinium = new BlockPteridinium().setBlockName(Names.blockPteridinium).setBlockTextureName("pteridinium");
        charnia = new BlockCharnia().setBlockName(Names.blockCharnia).setBlockTextureName("charnia_bottom");
        charnia_top = new BlockCharniaTop().setBlockName(Names.blockCharnia).setBlockTextureName("charnia_top");
    }

    public static void register() {
        GameRegistry.registerBlock(algae, ItemBlockGenesisAlgae.class, Names.Registry.blockAlgae);
        GameRegistry.registerBlock(coral, ItemBlockGenesisCoral.class, Names.Registry.blockCoral);
        GameRegistry.registerBlock(sponge, ItemBlockGenesisSponge.class, Names.Registry.blockSponge);
        GameRegistry.registerBlock(pteridinium, Names.Registry.blockPteridinium);
        GameRegistry.registerBlock(charnia, ItemBlockCharnia.class, Names.Registry.blockCharnia);
        GameRegistry.registerBlock(charnia_top, Names.Registry.blockCharniaTop);
    }
}