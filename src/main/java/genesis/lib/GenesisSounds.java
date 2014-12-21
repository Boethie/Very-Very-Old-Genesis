package genesis.lib;

import genesis.Genesis;
import net.minecraft.block.Block;

public class GenesisSounds {

    public static final GenesisSoundType soundTypeMoss = new GenesisSoundType("moss", 10.0F, 1.0F);
    public static final GenesisSoundType soundTypeRottenLog = new GenesisSoundType("rottenLog", 10.0F, 1.0F);
    public static final GenesisSoundType soundTypeFern = new GenesisSoundType("fern", 10.0F, 1.0F);
    public static final GenesisSoundType soundTypeCalamites = new GenesisSoundType("calamites", 10.0F, 1.0F);

    public static class GenesisSoundType extends Block.SoundType {
        public final String soundName;
        public final float volume;
        public final float frequency;

        public GenesisSoundType(String nameOfSound, float soundVolume, float soundFrequency) {
            super(nameOfSound, soundVolume, soundFrequency);
            soundName = nameOfSound;
            volume = soundVolume;
            frequency = soundFrequency;
        }

        @Override
        public String getStepSound() {
            return Genesis.ASSETS + "step." + soundName;
        }

        @Override
        public String getBreakSound() {
            return Genesis.ASSETS + "dig." + soundName;
        }
    }
}
