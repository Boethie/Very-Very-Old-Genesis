package genesis.classtransformer;

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

