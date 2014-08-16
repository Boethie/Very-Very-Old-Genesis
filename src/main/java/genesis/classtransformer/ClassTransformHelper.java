package genesis.classtransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassTransformHelper {

    public static byte[] injectCustomHook(
            byte[] byteCode,
            CustomMethodTransformer template,
            String tgtMethodName,
            String tgtMethodDesc,
            String superClass) {
        ClassWriter classWriter = new SuperClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, superClass);

        MethodTransformer transformer = new MethodTransformer(classWriter);
        transformer.tgtMethodName = tgtMethodName;
        transformer.tgtMethodDesc = tgtMethodDesc;
        transformer.template = template;

        ClassReader classReader = new ClassReader(byteCode);
        classReader.accept(transformer, 0);

        return classWriter.toByteArray();
    }

}
