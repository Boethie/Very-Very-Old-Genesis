package genesis.block.plants;

import genesis.common.Genesis;
import genesis.item.itemblock.ItemBlockAsteroxylon;
import genesis.item.itemblock.ItemBlockGenesisFern;
import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.lib.Names;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class PlantBlocks 
{
	public static BlockGenesisFlowerPot flowerPot;
	public static Block calamitesPlant;
	public static BlockCalamitesStorage calamitesBlock;
	public static BlockGenesisTerrestrialPlant plants;
	public static BlockZingiberopsisBase zingiberopsis;
	public static BlockZingiberopsisTop zingTop;
	public static BlockGenesisFern ferns;
	public static BlockAsteroxylon asteroxylon;
	public static BlockAsteroxylonTop asterTop;
	public static BlockHausmanniaTop hausTop;
	
	public static void init() {
		flowerPot = (BlockGenesisFlowerPot) new BlockGenesisFlowerPot().setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

		// calamitesPlant = new BlockCalamitesPlant().setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage) new BlockCalamitesStorage().setBlockTextureName("calamites_block").setBlockName("storage." + Names.blockCalamitesPlant);

		plants = (BlockGenesisTerrestrialPlant) new BlockGenesisTerrestrialPlant().setBlockName(Names.blockPlant);

		zingiberopsis = (BlockZingiberopsisBase) new BlockZingiberopsisBase().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		zingTop = (BlockZingiberopsisTop) new BlockZingiberopsisTop().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		ferns = (BlockGenesisFern) new BlockGenesisFern().setBlockName(Names.blockFern);
		hausTop = (BlockHausmanniaTop) new BlockHausmanniaTop();
		
		asteroxylon = (BlockAsteroxylon) new BlockAsteroxylon().setBlockName(Names.blockAsteroxylon);
		
		asterTop = (BlockAsteroxylonTop) new BlockAsteroxylonTop().setBlockName(Names.blockAsteroxylon + ".top").setBlockTextureName(Genesis.MOD_ID + ":asteroxylon_top");
	}

	public static void registerBlocks() {
		// GameRegistry.registerBlock(calamitesPlant, Names.blockCalamitesPlant);
		GameRegistry.registerBlock(calamitesBlock, "storage." + Names.blockCalamitesPlant);
		GameRegistry.registerBlock(plants, ItemBlockGenesisPlant.class, Names.blockPlant);
		
		GameRegistry.registerBlock(zingiberopsis, Names.blockZingiberopsis);
		
		GameRegistry.registerBlock(zingTop, Names.blockZingiberopsis + ".top");
		
		GameRegistry.registerBlock(ferns, ItemBlockGenesisFern.class, Names.blockFern);
		GameRegistry.registerBlock(hausTop, Names.blockFern + ".top");
		
		GameRegistry.registerBlock(asteroxylon, ItemBlockAsteroxylon.class, Names.blockAsteroxylon);
		
		GameRegistry.registerBlock(asterTop, Names.blockAsteroxylon + ".top");

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), "CCC", "CCC", "CCC", 'C', calamitesPlant);

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), "C", 'C', calamitesBlock);
	}

}
