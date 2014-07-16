package genesis.common;

import net.minecraft.block.Block;

public class GenesisSoundHandler {
	
	public static final GenesisSoundType soundTypeMoss = new GenesisSoundType("moss", 10.0F, 1.0F);
	public static final GenesisSoundType soundTypeRottenLog = new GenesisSoundType("rottenLog", 10.0F, 1.0F);
	public static final GenesisSoundType soundTypeFern = new GenesisSoundType("fern", 10.0F, 1.0F);
	public static final GenesisSoundType soundTypeCalamites = new GenesisSoundType("calamites", 10.0F, 1.0F);

	public static class GenesisSoundType extends Block.SoundType
    {
        public final String soundName;
        public final float volume;
        public final float frequency;
        private static final String __OBFID = "CL_00000203";

        public GenesisSoundType(String p_i45393_1_, float p_i45393_2_, float p_i45393_3_)
        {
        	super(p_i45393_1_, p_i45393_2_, p_i45393_3_);
            this.soundName = p_i45393_1_;
            this.volume = p_i45393_2_;
            this.frequency = p_i45393_3_;
            
        }
        
        @Override
        public String getStepResourcePath(){
            return Genesis.MOD_ID+":step."+this.soundName;
        }
        
        @Override
        public String getBreakSound()
        {
            return Genesis.MOD_ID+":dig."+this.soundName;
        }

    }
}
