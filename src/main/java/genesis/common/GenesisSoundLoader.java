package genesis.common;

import net.minecraftforge.client.event.sound.SoundLoadEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GenesisSoundLoader {

	public static final String[] soundNames = {
		"breakChablis",
		"dartHit1",
		"dartHit2",
		"dartHit3",
		"dartHit4",
		"dartLaunch1",
		"dartLaunch2",
		"dartLoad",
		"eggHarvest",
		"mossDig1",
		"mossDig2",
		"mossDig3",
		"mossDig4",
		"mossStep1",
		"mossStep2",
		"mossStep3",
		"mossStep4",
		"stepChablis1",
		"stepChablis2",
		"stepChablis3",
		"stepChablis4",
		"strikeChablis1",
		"strikeChablis2"
	};


	@SubscribeEvent
	public void onSound(SoundLoadEvent event) {

		/* TODO: Find out how to add custom sounds
		for(String s : soundNames)
			event.manager.addSound("genesis:" + s + ".ogg");
		*/
	}
}
