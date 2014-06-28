package genesis.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityEryops extends EntityMob 
{
	public EntityEryops(World par1World) 
	{
		super(par1World);
	}
	
	protected String getHurtSound()
    {
        return "mob.eryops.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.eryops.death";
    }
}