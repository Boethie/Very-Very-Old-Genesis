package genesis.genesis.classtransformer;

import net.minecraft.block.Block;

public class ObfuscationTable {

	public static String ClassBlock = Block.class.getName();
	public static String ClassBlockPath = ClassBlock.replace('.', '/');
	public static String FieldBlockID;
	
	public static String ClassEntityDiggingFX;
	public static String ClassEntityDiggingFXPath;
	public static String MethodApplyColourMultiplier;
	public static String MethodApplyColourMultiplierDesc;
	public static String FieldBlockInstance;
	public static String FieldSide;

	public static String ClassRenderBlocks;
	public static String ClassRenderBlocksPath;
	public static String MethodRenderBlockAsItem;
	public static String MethodRenderBlockAsItemDesc;

	public static String ClassItemHoe;
	public static String ClassItemHoePath;
	public static String MethodOnItemUse;
	public static String MethodOnItemUseDesc;
	
}
