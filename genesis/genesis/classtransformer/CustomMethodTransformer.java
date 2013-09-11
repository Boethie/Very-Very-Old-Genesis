package genesis.genesis.classtransformer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public abstract class CustomMethodTransformer extends MethodVisitor {
	
	protected CustomMethodTransformer() {
		super(Opcodes.ASM4, null);
	}
	
	final void init(MethodVisitor mv) {
		this.mv = mv;
	}
	
}

