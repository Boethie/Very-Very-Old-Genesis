package genesis.genesis.classtransformer;

import java.util.logging.Level;

import genesis.genesis.lib.LogHelper;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {
	
	//Injects code into the class files before they load
	
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (name.equals(ObfuscationTable.ClassEntityDiggingFX))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new DiggingFXColorTransformer(),
					ObfuscationTable.MethodApplyColourMultiplier, ObfuscationTable.MethodApplyColourMultiplierDesc,
					ObfuscationTable.ClassEntityDiggingFXPath);
			
			return returnVal;
		}
		else if (name.equals(ObfuscationTable.ClassRenderBlocks))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new MossBlockInventoryRenderTransformer(),
					ObfuscationTable.MethodRenderBlockAsItem, ObfuscationTable.MethodRenderBlockAsItemDesc,
					ObfuscationTable.ClassRenderBlocksPath);
			
			return returnVal;
		}
		else if (name.equals(ObfuscationTable.ClassItemHoe))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new HoeOnMossBlockTransformer(),
					ObfuscationTable.MethodOnItemUse, ObfuscationTable.MethodOnItemUseDesc,
					ObfuscationTable.ClassItemHoePath);
			
			return returnVal;
		}
		
		return bytes;
	}
	
	private class DiggingFXColorTransformer extends CustomMethodTransformer {
		
		private boolean injected = false;
		
		@Override
		public void visitCode() {
			mv.visitCode();
			
			if (!injected)
			{
				try
				{
					String block = "L" + ObfuscationTable.ClassBlockPath + ";";
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);	// First hook parameter
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);
					mv.visitFieldInsn(Opcodes.GETFIELD,
							ObfuscationTable.ClassEntityDiggingFXPath,
							ObfuscationTable.FieldBlockInstance, block);	// Second parameter
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);
					mv.visitFieldInsn(Opcodes.GETFIELD,
							ObfuscationTable.ClassEntityDiggingFXPath, ObfuscationTable.FieldSide, "I");	// Last parameter
					
					mv.visitMethodInsn(Opcodes.INVOKESTATIC,
							"genesis/genesis/hooks/MossHooks",
							"handleParticleColors", "(L" + ObfuscationTable.ClassEntityDiggingFXPath + ";" + block + "I)Z");
					
					Label label = new Label();
					mv.visitJumpInsn(Opcodes.IFNE, label);
					mv.visitVarInsn(Opcodes.ALOAD, 0);
					mv.visitInsn(Opcodes.ARETURN);
					mv.visitLabel(label);
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
				
				injected = true;
			}
		}
		
	}
	
	private class MossBlockInventoryRenderTransformer extends CustomMethodTransformer {
		
		private boolean injected = false;
		
		@Override
		public void visitVarInsn(int opcode, int var) {
			mv.visitVarInsn(opcode, var);
			
			if (!injected && opcode == Opcodes.ISTORE && var == 5)
			{
				try
				{
					mv.visitVarInsn(Opcodes.ALOAD, 1);
					mv.visitVarInsn(Opcodes.ILOAD, 5);
					mv.visitMethodInsn(Opcodes.INVOKESTATIC,
							"genesis/genesis/hooks/MossHooks",
							"shouldGrasslikeRender",
							"(L" + ObfuscationTable.ClassBlockPath + ";Z)Z");
					mv.visitVarInsn(Opcodes.ISTORE, 5);
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
				
				injected = true;
			}
		}
		
	}
	
	private class HoeOnMossBlockTransformer extends CustomMethodTransformer {
		
		private boolean found = false;
		
		@Override
		public void visitFieldInsn(int opcode, String owner, String name, String desc)
		{
			mv.visitFieldInsn(opcode, owner, name, desc);
			
			if (opcode == Opcodes.GETFIELD &&
					owner.equals(BlockGrass.class.getName().replace('.', '/')) &&
					name.equals(ObfuscationTable.FieldBlockID))
			{
				found = true;
			}
		}
		
		@Override
		public void visitJumpInsn(int opcode, Label label)
		{
			mv.visitJumpInsn(opcode, label);
			
			if (found && opcode == Opcodes.IF_ICMPEQ)
			{
				try
				{
					mv.visitVarInsn(Opcodes.ILOAD, 12);
					mv.visitFieldInsn(Opcodes.GETSTATIC, "genesis/genesis/block/Blocks",
							"moss",
							"L" + ObfuscationTable.ClassBlockPath + ";");
					mv.visitFieldInsn(Opcodes.GETFIELD,
							ObfuscationTable.ClassBlockPath,
							ObfuscationTable.FieldBlockID, "I");
					mv.visitJumpInsn(Opcodes.IF_ICMPEQ, label);
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
