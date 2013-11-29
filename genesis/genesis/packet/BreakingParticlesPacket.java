package genesis.genesis.packet;

import genesis.genesis.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class BreakingParticlesPacket extends GenesisPacket {
	
	public int x, y, z;
	public int blockID;
	public int metadata;
	
	public BreakingParticlesPacket() { }
	
	public BreakingParticlesPacket(int x, int y, int z, int blockID, int metadata)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.blockID = blockID;
		this.metadata = metadata;
	}
	
	public BreakingParticlesPacket(int x, int y, int z, World world)
	{
		this(x, y, z, world.getBlockId(x, y, z), world.getBlockMetadata(x, y, z));
	}
	
	@Override
	public void write(ByteArrayDataOutput out)
	{
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(z);
		out.writeInt(blockID);
		out.writeInt(metadata);
	}
	
	@Override
	public void read(ByteArrayDataInput in)
	{
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
		blockID = in.readInt();
		metadata = in.readInt();
	}
	
	@Override
	public void execute(World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			Minecraft mc = ClientProxy.getMC();
			
			if (world == mc.theWorld)
			{
				mc.effectRenderer.addBlockDestroyEffects(x, y, z,
						blockID, metadata);
			}
		}
	}
	
}
