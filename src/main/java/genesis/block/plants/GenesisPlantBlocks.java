package genesis.block.plants;

import genesis.item.itemblock.ItemBlockAsteroxylon;
import genesis.item.itemblock.ItemBlockGenesisFern;
import genesis.item.itemblock.ItemBlockGenesisFlower;
import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenesisPlantBlocks {
    //public static Block flower_pot;
    public static Block calamites;
    public static Block calamites_block;
    public static Block plants;
    public static BlockZingiberopsisBase zingiberopsis;
    public static Block zingiberopsis_top;
    public static Block ferns;
    public static BlockAsteroxylon asteroxylon;
    public static Block asteroxylon_top;
    public static BlockSphenophyllumBase sphenophyllum;
    public static Block sphenophyllum_top;
    public static Block flowers;
    public static Block archaeomarasmius;
    public static Block mycelia;
    public static Block prototaxites;

    public static void init() {
        //flower_pot = new BlockGenesisFlowerPot().setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

        calamites = new BlockCalamitesPlant().setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
        calamites_block = new BlockCalamitesStorage().setBlockTextureName("calamites_block").setBlockName(Names.blockCalamitesBlock);

        plants = new BlockGenesisTerrestrialPlant().setBlockName(Names.blockPlant);

        zingiberopsis = (BlockZingiberopsisBase) new BlockZingiberopsisBase().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
        zingiberopsis_top = new BlockZingiberopsisTop().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");

        ferns = new BlockGenesisFern().setBlockName(Names.blockFern);

        asteroxylon = (BlockAsteroxylon) new BlockAsteroxylon().setBlockName(Names.blockAsteroxylon).setBlockTextureName("asteroxylon");
        asteroxylon_top = new BlockAsteroxylonTop().setBlockName(Names.blockAsteroxylon).setBlockTextureName("asteroxylon_top");

        sphenophyllum = (BlockSphenophyllumBase) new BlockSphenophyllumBase().setBlockName(Names.blockSphenophyllum).setBlockTextureName("sphenophyllum");
        sphenophyllum_top = new BlockSphenophyllumTop().setBlockName(Names.blockSphenophyllum).setBlockTextureName("sphenophyllum");

        flowers = new BlockGenesisFlower().setBlockName(Names.blockFlower);
        
        archaeomarasmius = new BlockArchaeomarasmius().register();
        
        mycelia = new BlockMycelia().setBlockName(Names.blockMycelia);
        prototaxites = new BlockPrototaxites().setBlockName(Names.blockPrototaxites);
    }

    public static void register() {
        GameRegistry.registerBlock(calamites, Names.Registry.blockCalamitesPlant);
        GameRegistry.registerBlock(calamites_block, Names.Registry.blockCalamitesBlock);

        GameRegistry.registerBlock(plants, ItemBlockGenesisPlant.class, Names.Registry.blockPlant);

        GameRegistry.registerBlock(zingiberopsis, Names.Registry.blockZingiberopsis);
        GameRegistry.registerBlock(zingiberopsis_top, Names.Registry.blockZingiberopsisTop);

        GameRegistry.registerBlock(ferns, ItemBlockGenesisFern.class, Names.Registry.blockFern);

        GameRegistry.registerBlock(asteroxylon, ItemBlockAsteroxylon.class, Names.Registry.blockAsteroxylon);
        GameRegistry.registerBlock(asteroxylon_top, ItemBlockAsteroxylon.class, Names.Registry.blockAsteroxylonTop);

        GameRegistry.registerBlock(sphenophyllum, Names.Registry.blockSphenophyllum);
        GameRegistry.registerBlock(sphenophyllum_top, Names.Registry.blockSphenophyllumTop);

        GameRegistry.registerBlock(flowers, ItemBlockGenesisFlower.class, Names.Registry.blockFlower);
        
        GameRegistry.registerBlock(archaeomarasmius, Names.Registry.blockArchaeomarasmius);
        
        GameRegistry.registerBlock(mycelia, Names.Registry.blockMycelia);
        GameRegistry.registerBlock(prototaxites, Names.Registry.blockPrototaxites);

        Blocks.fire.setFireInfo(plants, 60, 100);
        Blocks.fire.setFireInfo(ferns, 60, 100);
        Blocks.fire.setFireInfo(calamites, 30, 60);
        Blocks.fire.setFireInfo(calamites_block, 60, 20);
        Blocks.fire.setFireInfo(asteroxylon, 60, 100);
        Blocks.fire.setFireInfo(asteroxylon_top, 60, 100);
        Blocks.fire.setFireInfo(sphenophyllum, 60, 100);
        Blocks.fire.setFireInfo(sphenophyllum_top, 60, 100);
    }
}
