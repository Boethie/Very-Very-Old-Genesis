package genesis.classtransformer;

import org.objectweb.asm.ClassWriter;

public class SuperClassWriter extends ClassWriter {
	
	String superClass;
	
	public SuperClassWriter(int flags, String superClass)
	{
		super(flags);
		
		this.superClass = superClass;
	}
	
    protected String getCommonSuperClass(final String type1, final String type2)
    {
    	return superClass;
    	//return super.getCommonSuperClass(type1, type2);
    }
	
}
