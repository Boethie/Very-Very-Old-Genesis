package genesis.genesis.classtransformer;

import java.util.logging.Level;

import genesis.genesis.lib.LogHelper;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.world.World;

public class GenesisClassTransformer implements IClassTransformer {
	
	//Injects code into the class files before they load
	
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if (name.equals(GenesisObfTable.ClassEntityDiggingFX))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new DiggingFXColorTransformer(),
					GenesisObfTable.MethodApplyColourMultiplier, GenesisObfTable.MethodApplyColourMultiplierDesc,
					GenesisObfTable.ClassEntityDiggingFXPath);
			
			return returnVal;
		}
		else if (name.equals(GenesisObfTable.ClassRenderBlocks))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new MossBlockInventoryRenderTransformer(),
					GenesisObfTable.MethodRenderBlockAsItem, GenesisObfTable.MethodRenderBlockAsItemDesc,
					GenesisObfTable.ClassRenderBlocksPath);
			
			return returnVal;
		}
		else if (name.equals(GenesisObfTable.ClassItemHoe))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new HoeOnMossBlockTransformer(),
					GenesisObfTable.MethodOnItemUse, GenesisObfTable.MethodOnItemUseDesc,
					GenesisObfTable.ClassItemHoePath);
			
			return returnVal;
		}
		else if (name.equals(GenesisObfTable.ClassBlockFlowerPot))
		{
			byte[] returnVal = ClassTransformHelper.injectCustomHook(bytes, new FlowerPotRightClickTransformer(),
					GenesisObfTable.MethodOnBlockActivated, GenesisObfTable.MethodOnBlockActivatedDesc,
					GenesisObfTable.ClassBlockFlowerPot);
			
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
					String block = "L" + GenesisObfTable.ClassBlockPath + ";";
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);	// First hook parameter
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);
					mv.visitFieldInsn(Opcodes.GETFIELD,
							GenesisObfTable.ClassEntityDiggingFXPath,
							GenesisObfTable.FieldBlockInstance, block);	// Second parameter
					
					mv.visitVarInsn(Opcodes.ALOAD, 0);
					mv.visitFieldInsn(Opcodes.GETFIELD,
							GenesisObfTable.ClassEntityDiggingFXPath, GenesisObfTable.FieldSide, "I");	// Last parameter
					
					mv.visitMethodInsn(Opcodes.INVOKESTATIC,
							"genesis/genesis/hooks/MossHooks",
							"handleParticleColors", "(L" + GenesisObfTable.ClassEntityDiggingFXPath + ";" + block + "I)Z");
					
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
							"(L" + GenesisObfTable.ClassBlockPath + ";Z)Z");
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
					name.equals(GenesisObfTable.FieldBlockID))
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
							"L" + GenesisObfTable.ClassBlockPath + ";");
					mv.visitFieldInsn(Opcodes.GETFIELD,
							GenesisObfTable.ClassBlockPath,
							GenesisObfTable.FieldBlockID, "I");
					mv.visitJumpInsn(Opcodes.IF_ICMPEQ, label);
				}
				catch (Throwable e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}

	private class FlowerPotRightClickTransformer extends CustomMethodTransformer {
		
		private int matches = 0;
		
		@Override
		public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack)
		{
			mv.visitFrame(type, nLocal, local, nStack, stack);

			// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			if (type == Opcodes.F_SAME && nLocal == 0 && nStack == 0)
			{
				matches++;
				
				if (matches >= 2)
				{
					try
					{
						String worldDesc = World.class.getName().replace('.', '/');
						String entityPlayerDesc = EntityPlayer.class.getName().replace('.', '/');
						
						mv.visitVarInsn(Opcodes.ALOAD, 1);
						mv.visitVarInsn(Opcodes.ILOAD, 2);
						mv.visitVarInsn(Opcodes.ILOAD, 3);
						mv.visitVarInsn(Opcodes.ILOAD, 4);
						mv.visitVarInsn(Opcodes.ALOAD, 5);
						mv.visitVarInsn(Opcodes.ILOAD, 6);
						mv.visitVarInsn(Opcodes.FLOAD, 7);
						mv.visitVarInsn(Opcodes.FLOAD, 8);
						mv.visitVarInsn(Opcodes.FLOAD, 9);
						mv.visitMethodInsn(Opcodes.INVOKESTATIC, "genesis/genesis/hooks/FlowerPotHooks",
								"checkSwitchFlowerPotBlock",
								"(L" + worldDesc + ";IIIL" + entityPlayerDesc + ";IFFF)Z");
						Label label = new Label();
						mv.visitJumpInsn(Opcodes.IFEQ, label);
						mv.visitInsn(Opcodes.ICONST_1);
						mv.visitInsn(Opcodes.IRETURN);
						mv.visitLabel(label);
					}
					catch (Throwable e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
}
