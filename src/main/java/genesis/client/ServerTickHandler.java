package genesis.client;

import java.util.EnumSet;

public class ServerTickHandler implements ITickHandler {
	
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}
	
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.contains(TickType.WORLD))
		{
			/*World world = (World)tickData[0];
			
			for (Object playerObj : world.playerEntities)
			{
				EntityPlayer player = (EntityPlayer)playerObj;

		        Vec3 vec3 = player.getPosition(1);
		        vec3.yCoord += player.getEyeHeight();
		        Vec3 vec31 = player.getLook(1);
		        Vec3 vec32 = vec3.addVector(vec31.xCoord * 100, vec31.yCoord * 100, vec31.zCoord * 100);
		        MovingObjectPosition lookPos = world.clip(vec3, vec32);
	
		        if (lookPos != null && lookPos.typeOfHit == EnumMovingObjectType.TILE)
		        {
		            int hitX = lookPos.blockX;
		            int hitY = lookPos.blockY;
		            int hitZ = lookPos.blockZ;
		            
		            int blockID = world.getBlockId(hitX, hitY, hitZ);
		            int lightOpac = world.getBlockLightOpacity(hitX, hitY, hitZ);
		            
		            ForgeDirection hitDir = ForgeDirection.getOrientation(lookPos.sideHit);
		
		            hitX += hitDir.offsetX;
		            hitY += hitDir.offsetY;
		            hitZ += hitDir.offsetZ;
		            
		            int lightVal = world.getBlockLightValue(hitX, hitY, hitZ);
		            int fullLightVal = world.getFullBlockLightValue(hitX, hitY, hitZ);
		            
		            System.out.println(player.getEntityName() + String.format(" bi: %d, lv: %d, flv: %d, lo: %d", blockID, lightVal, fullLightVal, lightOpac));
		        }
			}*/
		}
	}
	
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}
	
	public String getLabel() {
		return "GenesisModServerTickHandler";
	}

}
