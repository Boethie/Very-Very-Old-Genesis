package genesis.classtransformer;

import org.objectweb.asm.ClassWriter;

public class SuperClassWriter extends ClassWriter {

    private String superClass;

    public SuperClassWriter(int flags, String superClassString) {
        super(flags);
        superClass = superClassString;
    }

    @Override
    protected String getCommonSuperClass(final String type1, final String type2) {
        return superClass;
        //return super.getCommonSuperClass(type1, type2);
    }
}
