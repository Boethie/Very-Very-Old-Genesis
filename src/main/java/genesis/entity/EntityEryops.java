package genesis.entity;

import genesis.managers.GenesisModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEryops extends EntityMob {

    public float mouthTick = 0.0F;
	
    public EntityEryops(World world) {
        super(world);
        this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        setSize(0.85F, 0.85F);
    }

    protected String getHurtSound() {
        return "mob.eryops.hurt";
    }

    protected String getDeathSound() {
        return "mob.eryops.death";
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (!this.hasBitten()) {
            this.mouthTick = 20;
            // SEND PKT TO CLIENT.
            return super.attackEntityAsMob(entity);
        } else {
            return false;
        }
    }

    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isRemote) {
            System.out.println("Client - this.mouthTick " + this.mouthTick);
        } else {
            System.out.println("Server - this.mouthTick " + this.mouthTick);
        }
        if (this.hasBitten()) {
            this.mouthTick--;
        }
        super.onLivingUpdate();
    }

    /** Returns true if the Eryops has attacked recently (20 ticks). */
    public boolean hasBitten() {
        return this.mouthTick > 0;
    }

    /** Returns a "percentage" of the mouth movement depending on the mouth tick. */
    @SideOnly(Side.CLIENT)
    public float getMouthAngle() {
        if (this.attackTime > 0) {
            this.mouthTick = this.mouthTick / 2;
            return (float) (-0.0006 * Math.pow(this.attackTime, 3) + 0.0097 * Math.pow(this.attackTime, 2) + 0.0593 * this.attackTime);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5F);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected Item getDropItem() {
        return GenesisModItems.raw_eryops;
    }

    /** Drops 1 and 2 raw legs by default + a bonus from looting enchant. Maximum of 4 raw legs. */
    @Override
    protected void dropFewItems(boolean recentlyBeenHit, int enchantBonus) {
        Item item = this.getDropItem();
        if (item != null) {
            int drops = 1 + this.rand.nextInt(2);
            if (enchantBonus > 0) {
                drops += this.rand.nextInt(enchantBonus + 1);
                if (drops > 4) {
                    drops = 4;
                }
            }
            for (int count = 0; count < drops; count++) {
                this.dropItem(item, 1);
            }
        }
    }
}
