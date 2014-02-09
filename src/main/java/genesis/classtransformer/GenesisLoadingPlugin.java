package genesis.classtransformer;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({"genesis.genesis.classtransformer"})
@MCVersion("1.6.4")
public class GenesisLoadingPlugin implements IFMLLoadingPlugin {
	 
	public String[] getLibraryRequestClass() {
		return null;
	}

	public String[] getASMTransformerClass() {
		boolean isObfuscated = !World.class.getSimpleName().equals("World");

		GenesisObfTable.ClassBlock = Block.class.getName();
		GenesisObfTable.ClassBlockPath = GenesisObfTable.ClassBlock.replace('.', '/');
		GenesisObfTable.ClassBlockFlowerPot = isObfuscated ? "aoj" : "net.minecraft.block.BlockFlowerPot";
		GenesisObfTable.FieldBlockID = isObfuscated ? "field_71990_ca" : "blockID";
		GenesisObfTable.MethodOnBlockActivated = isObfuscated ? "a" : "onBlockActivated";
		GenesisObfTable.MethodOnBlockActivatedDesc = isObfuscated ? "(Labw;IIILuf;IFFF)Z" : "(Lnet/minecraft/world/World;IIILnet/minecraft/entity/player/EntityPlayer;IFFF)Z";

		GenesisObfTable.ClassEntityDiggingFX = isObfuscated ? "bes" : "net.minecraft.client.particle.EntityDiggingFX";
		GenesisObfTable.ClassEntityDiggingFXPath = GenesisObfTable.ClassEntityDiggingFX.replace('.', '/');
		GenesisObfTable.MethodApplyColourMultiplier = isObfuscated ? "a" : "applyColourMultiplier";
		GenesisObfTable.MethodApplyColourMultiplierDesc = "(III)L" + GenesisObfTable.ClassEntityDiggingFX.replace('.', '/') + ";";
		GenesisObfTable.FieldBlockInstance = isObfuscated ? "field_70597_a" : "blockInstance";
		GenesisObfTable.FieldSide = "side";

		GenesisObfTable.ClassRenderBlocks = isObfuscated ? "bfr" : "net.minecraft.client.renderer.RenderBlocks";
		GenesisObfTable.ClassRenderBlocksPath = GenesisObfTable.ClassRenderBlocks.replace('.', '/');
		GenesisObfTable.MethodRenderBlockAsItem = isObfuscated ? "a" : "renderBlockAsItem";
		GenesisObfTable.MethodRenderBlockAsItemDesc = "(L" + Block.class.getName().replace('.', '/') + ";IF)V";

		GenesisObfTable.ClassItemHoe = isObfuscated ? "yb" : "net.minecraft.item.ItemHoe";
		GenesisObfTable.ClassItemHoePath = GenesisObfTable.ClassItemHoe.replace('.', '/');
		GenesisObfTable.MethodOnItemUse = isObfuscated ? "a" : "onItemUse";
		GenesisObfTable.MethodOnItemUseDesc = isObfuscated ? "(Lye;Luf;Labw;IIIIFFF)Z" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIIIFFF)Z";
		
		return new String[] {"genesis.genesis.classtransformer.GenesisClassTransformer"};
	}

	public String getModContainerClass() {
		return null;
	}

	public String getSetupClass() {
		return null;
	}

	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
