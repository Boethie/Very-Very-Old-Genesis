package genesis.classtransformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodTransformer extends ClassVisitor {
	String tgtMethodName;
	String tgtMethodDesc;
	String hookMethodOwner;
	String hookMethodName;
	boolean isConditionalReturn;
	CustomMethodTransformer template;
	
	MethodTransformer(ClassVisitor cv)
	{
		super(Opcodes.ASM4, cv);
	}
	
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if (name.equals(tgtMethodName) && desc.startsWith(tgtMethodDesc)) {
			if (template != null) {
				MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
				template.init(mv);
				return template;
			}
		}
		
		return cv.visitMethod(access, name, desc, signature, exceptions);
	}
	
}
