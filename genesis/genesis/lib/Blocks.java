package genesis.genesis.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import genesis.genesis.block.BlockGenesisLeaves;
import genesis.genesis.block.BlockGenesisSapling;
import genesis.genesis.block.BlockGenesisLog;
import genesis.genesis.common.Genesis;
import genesis.genesis.itemblock.ItemBlockGenesisLeaves;
import genesis.genesis.itemblock.ItemBlockGenesisLog;
import genesis.genesis.itemblock.ItemBlockGenesisSapling;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Blocks {
	
	public static Block blockLogGenesis;
	public static Block blockLogGenesis1;
	public static Block blockSaplingGenesis;
	public static Block blockSaplingGenesis1;
	public static Block blockLeavesGenesis;
	public static Block blockLeavesGenesis1;
	
	public static void init()
	{
		blockLogGenesis = new BlockGenesisLog(Ids.blockLogGenesisID_actual, 0)
				.setUnlocalizedName(Names.blockLogGenesis_unlocalizedName);
		blockLogGenesis1 = new BlockGenesisLog(Ids.blockLogGenesis1ID_actual, 1)
				.setUnlocalizedName(Names.blockLogGenesis1_unlocalizedName);
		blockSaplingGenesis = new BlockGenesisSapling(Ids.blockSaplingGenesisID_actual, 0)
				.setUnlocalizedName(Names.blockSaplingGenesis_unlocalizedName);
		blockSaplingGenesis1 = new BlockGenesisSapling(Ids.blockSaplingGenesis1ID_actual, 1)
				.setUnlocalizedName(Names.blockSaplingGenesis1_unlocalizedName);
		blockLeavesGenesis = new BlockGenesisLeaves(Ids.blockLeavesGenesisID_actual, 0)
				.setUnlocalizedName(Names.blockLeavesGenesis_unlocalizedName);
		blockLeavesGenesis1 = new BlockGenesisLeaves(Ids.blockLeavesGenesis1ID_actual, 1)
				.setUnlocalizedName(Names.blockLeavesGenesis1_unlocalizedName);
	}
	public static void addNames()
	{
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 0), Names.blockLogAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 1), Names.blockLogBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 2), Names.blockLogCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis, 1, 3), Names.blockLogFig_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 0), Names.blockLogLepidodendron_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 1), Names.blockLogSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockLogGenesis1, 1, 2), Names.blockLogTempskya_name);
		
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 0), Names.blockSaplingAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 1), Names.blockSaplingBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 2), Names.blockSaplingCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis, 1, 3), Names.blockSaplingFig_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 0), Names.blockSaplingLepidodendron_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 1), Names.blockSaplingSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockSaplingGenesis1, 1, 2), Names.blockSaplingTempskya_name);
		
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 0), Names.blockLeavesAraucarioxylon_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 1), Names.blockLeavesBjuvia_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 2), Names.blockLeavesCordaites_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis, 1, 3), Names.blockLeavesFig_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 0), Names.blockLeavesLepdodendron_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 1), Names.blockLeavesSigillaria_name);
		LanguageRegistry.addName(new ItemStack(blockLeavesGenesis1, 1, 2), Names.blockLeavesTempskya_name);
	}
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(blockLogGenesis1, ItemBlockGenesisLog.class, Genesis.modid + Names.blockLogGenesis1_name);
		GameRegistry.registerBlock(blockLogGenesis, ItemBlockGenesisLog.class, Genesis.modid + Names.blockLogGenesis_name);
		
		GameRegistry.registerBlock(blockSaplingGenesis, ItemBlockGenesisSapling.class, Genesis.modid + Names.blockSaplingGenesis_name);
		GameRegistry.registerBlock(blockSaplingGenesis1, ItemBlockGenesisSapling.class, Genesis.modid + Names.blockSaplingGenesis1_name);
		
		GameRegistry.registerBlock(blockLeavesGenesis, ItemBlockGenesisLeaves.class, Genesis.modid + Names.blockLeavesGenesis_name);
		GameRegistry.registerBlock(blockLeavesGenesis1, ItemBlockGenesisLeaves.class, Genesis.modid + Names.blockLeavesGenesis1_name);
		
	}
}
