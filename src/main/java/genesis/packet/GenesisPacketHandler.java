package genesis.packet;

import ibxm.Player;

import java.util.logging.Logger;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import genesis.packet.GenesisPacket.ProtocolException;

public class GenesisPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		try {
			EntityPlayer entityPlayer = (EntityPlayer) player;
			ByteArrayDataInput input = ByteStreams.newDataInput(packet.data);

			GenesisPacket genesisPacket = GenesisPacket.constructPacket(input.readUTF());
			genesisPacket.read(input);
			genesisPacket.execute(entityPlayer.worldObj, entityPlayer);
		} catch (ProtocolException e) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
				Logger.getLogger("Genesis").warning("Player " + ((EntityPlayer) player).username + " caused a protocol exception when server recieved a packet!");
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}
