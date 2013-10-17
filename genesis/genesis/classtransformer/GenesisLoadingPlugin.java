package genesis.genesis.classtransformer;

import java.util.Map;


import net.minecraft.block.Block;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@TransformerExclusions({"genesis.genesis.classtransformer"})
@MCVersion("1.6.4")
public class GenesisLoadingPlugin implements IFMLLoadingPlugin {
	 
	public String[] getLibraryRequestClass() {
		return null;
	}

	public String[] getASMTransformerClass() {
		boolean isObfuscated = !World.class.getSimpleName().equals("World");
		
		ObfuscationTable.FieldBlockID = isObfuscated ? "field_71990_ca" : "blockID";

		ObfuscationTable.ClassEntityDiggingFX = isObfuscated ? "bep" : "net.minecraft.client.particle.EntityDiggingFX";
		ObfuscationTable.ClassEntityDiggingFXPath = ObfuscationTable.ClassEntityDiggingFX.replace('.', '/');
		ObfuscationTable.MethodApplyColourMultiplier = isObfuscated ? "a" : "applyColourMultiplier";
		ObfuscationTable.MethodApplyColourMultiplierDesc = "(III)L" + ObfuscationTable.ClassEntityDiggingFX.replace('.', '/') + ";";
		ObfuscationTable.FieldBlockInstance = isObfuscated ? "field_70597_a" : "blockInstance";
		ObfuscationTable.FieldSide = "side";

		ObfuscationTable.ClassRenderBlocks = isObfuscated ? "bfo" : "net.minecraft.client.renderer.RenderBlocks";
		ObfuscationTable.ClassRenderBlocksPath = ObfuscationTable.ClassRenderBlocks.replace('.', '/');
		ObfuscationTable.MethodRenderBlockAsItem = isObfuscated ? "a" : "renderBlockAsItem";
		ObfuscationTable.MethodRenderBlockAsItemDesc = "(L" + Block.class.getName().replace('.', '/') + ";IF)V";

		ObfuscationTable.ClassItemHoe = isObfuscated ? "ya" : "net.minecraft.item.ItemHoe";
		ObfuscationTable.ClassItemHoePath = ObfuscationTable.ClassItemHoe.replace('.', '/');
		ObfuscationTable.MethodOnItemUse = isObfuscated ? "a" : "onItemUse";
		ObfuscationTable.MethodOnItemUseDesc = isObfuscated ? "(Lyd;Lue;Labv;IIIIFFF)Z" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIIIFFF)Z";
		
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
	
}
