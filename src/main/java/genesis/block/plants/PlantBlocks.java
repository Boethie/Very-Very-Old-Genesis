package genesis.block.plants;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import cpw.mods.fml.common.registry.GameRegistry;

import genesis.common.Genesis;
import genesis.item.ModItems;
import genesis.item.itemblock.ItemBlockAsteroxylon;
import genesis.item.itemblock.ItemBlockGenesisAlgae;
import genesis.item.itemblock.ItemBlockGenesisCoral;
import genesis.item.itemblock.ItemBlockGenesisFern;
import genesis.item.itemblock.ItemBlockGenesisPlant;
import genesis.item.itemblock.ItemBlockGenesisSponge;
import genesis.lib.Names;

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
	public static BlockGenesisCoral coral; 
	public static BlockGenesisSponge sponge;
	public static BlockGenesisAlgae algae;
	public static BlockHausmanniaTop hausTop;
	
	public static void init() {
		flowerPot = (BlockGenesisFlowerPot) new BlockGenesisFlowerPot().setBlockTextureName("flower_pot").setBlockName(Names.blockFlowerPot);

		// calamitesPlant = new BlockCalamitesPlant().setBlockTextureName("calamites").setBlockName(Names.blockCalamitesPlant);
		calamitesBlock = (BlockCalamitesStorage) new BlockCalamitesStorage().setBlockTextureName("calamites_block").setBlockName("storage." + Names.blockCalamitesPlant);

		plants = (BlockGenesisTerrestrialPlant) new BlockGenesisTerrestrialPlant().setBlockName(Names.blockPlant);

		zingiberopsis = (BlockZingiberopsisBase) new BlockZingiberopsisBase().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		zingTop = (BlockZingiberopsisTop) new BlockZingiberopsisTop().setBlockName(Names.blockZingiberopsis).setBlockTextureName("zingiberopsis");
		
		ferns = (BlockGenesisFern) new BlockGenesisFern().setBlockName(Names.blockFern);
		//hausTop = (BlockHausmanniaTop) new BlockHausmanniaTop();
		
		coral = (BlockGenesisCoral) new BlockGenesisCoral().setBlockName(Names.blockCoral);
		
		sponge = (BlockGenesisSponge) new BlockGenesisSponge().setBlockName(Names.blockSponge);
		algae = (BlockGenesisAlgae) new BlockGenesisAlgae().setBlockName(Names.blockAlgae);
		
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
		//GameRegistry.registerBlock(hausTop, Names.blockFern);
		
		GameRegistry.registerBlock(coral, ItemBlockGenesisCoral.class, Names.blockCoral);
		
		GameRegistry.registerBlock(sponge, ItemBlockGenesisSponge.class, Names.blockSponge);
		GameRegistry.registerBlock(algae, ItemBlockGenesisAlgae.class, Names.blockAlgae);
		
		GameRegistry.registerBlock(asteroxylon, ItemBlockAsteroxylon.class, Names.blockAsteroxylon);
		
		GameRegistry.registerBlock(asterTop, Names.blockAsteroxylon + ".top");

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesBlock), "CCC", "CCC", "CCC", 'C', calamitesPlant);

		//CraftingManager.getInstance().addRecipe(new ItemStack(calamitesPlant, 9), "C", 'C', calamitesBlock);
	}

}
