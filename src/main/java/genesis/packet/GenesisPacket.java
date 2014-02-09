package genesis.packet;

import java.util.HashMap;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public abstract class GenesisPacket {

	public final static String CHANNEL = "genesis";

	private static final HashMap<String, Class<? extends GenesisPacket>> classMap = new HashMap();

	public static GenesisPacket constructPacket(String packetClass) throws ProtocolException, ReflectiveOperationException {
		if (!classMap.containsKey(packetClass))
			try {
				classMap.put(packetClass, (Class<? extends GenesisPacket>) Class.forName(packetClass));
			} catch (ClassCastException ex) {
				throw new ProtocolException("Intended packet class " + packetClass + " does not extend GenesisPacket!", ex);
			} catch (ClassNotFoundException ex) {
				throw new ProtocolException("Could not find packet class " + packetClass + "!", ex);
			}

		Class<? extends GenesisPacket> clazz = classMap.get(packetClass);

		return clazz.newInstance();
	}

	public static class ProtocolException extends Exception {

		public ProtocolException() {}

		public ProtocolException(String message, Throwable cause) {
			super(message, cause);
		}

		public ProtocolException(String message) {
			super(message);
		}

		public ProtocolException(Throwable cause) {
			super(cause);
		}
	}

	public final String getPacketID() {
		return getClass().getName();
	}

	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(getPacketID());

		write(out);

		return PacketDispatcher.getPacket(CHANNEL, out.toByteArray());
	}

	public abstract void write(ByteArrayDataOutput out);

	public abstract void read(ByteArrayDataInput in) throws ProtocolException;

	public abstract void execute(World world, EntityPlayer player) throws ProtocolException;

}
