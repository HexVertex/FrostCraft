package xelitez.frostcraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import xelitez.frostcraft.enums.EnumWeaponClass;
import xelitez.frostcraft.registry.IdMap;

public class EntityAIAttackUsingWeapon extends EntityAIBase
{
    EntityCreature attacker;
    /**
     * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
     */
    int attackTick;
    /**
     * The speed with which the mob will approach the target
     */
    double speedTowardsTarget;
    /**
     * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
     */
    boolean longMemory;
    /**
     * The PathEntity of our entity.
     */
    
    int followTicks = 0;
    PathEntity entityPathEntity;
    Class<?> classTarget;
    private int field_75445_i;
    private double field_151497_i;
    private double field_151495_j;
    private double field_151496_k;

    private int failedPathFindingPenalty;
    
    private Item WeaponItem;
    private EnumWeaponClass weaponClass;
    
    private EntityLivingBase attackTarget;
    private int field_75318_f;
    private int field_96561_g;
    private int maxRangedAttackTime;
    private float field_96562_i;
    private float field_82642_h;

    public EntityAIAttackUsingWeapon(EntityCreature par1EntityCreature, Class<?> par2Class, double par3, boolean par5, Item weaponItem, EnumWeaponClass weaponClass, Object... rangedData)
    {
        this(par1EntityCreature, par3, par5, weaponItem, weaponClass, rangedData);
        this.classTarget = par2Class;
    }

    public EntityAIAttackUsingWeapon(EntityCreature par1EntityCreature, double par2, boolean par4, Item weaponItem, EnumWeaponClass weaponClass, Object... rangedData)
    {
        this.attacker = par1EntityCreature;
        this.WeaponItem = weaponItem;
        this.speedTowardsTarget = par2;
        this.longMemory = par4;
        this.setMutexBits(3);
        this.weaponClass = weaponClass;
        if(weaponClass == EnumWeaponClass.RANGED && !(par1EntityCreature instanceof IRangedAttackMob))
        {
            throw new IllegalArgumentException("RANGED attack entities require Mob implements RangedAttackMob");
        }
        if(this.weaponClass == EnumWeaponClass.RANGED)
        {
	        this.field_96561_g = (Integer) rangedData[0];
	        this.maxRangedAttackTime = (Integer) rangedData[1];
	        this.field_96562_i = (Float) rangedData[2];
	        this.field_82642_h = (Float)rangedData[2] * (Float)rangedData[2];
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if(this.WeaponItem != null && this.WeaponItem != attacker.getHeldItem().getItem())
        {
        	return false;
        }
        if(this.weaponClass == EnumWeaponClass.MELEE)
        {
	        if (entitylivingbase == null)
	        {
	            return false;
	        }
	        else if (!entitylivingbase.isEntityAlive())
	        {
	            return false;
	        }
	        else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass()))
	        {
	            return false;
	        }
	        else
	        {
	            if (-- this.field_75445_i <= 0)
	            {
	                this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
	               this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
	                return this.entityPathEntity != null;
	            }
	            else
	            {
	                return true;
	            }
	        }
        }
        else
        {
            if (entitylivingbase == null || entitylivingbase == attacker)
            {
                return false;
            }
            else
            {
            	if(this.attackTarget != entitylivingbase)
            	{
            		followTicks = 0;
            	}
        		this.attackTarget = entitylivingbase;
        		return true;
            }
        }
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	followTicks++;
    	System.out.println(this.followTicks);
    	if(this.attacker.getAttackTarget() != null && this.isValidBlock(this.attacker.getAttackTarget().worldObj.getBlock((int)this.attacker.getAttackTarget().posX, (int)this.attacker.getAttackTarget().posY - 1, (int)this.attacker.getAttackTarget().posZ)))
    	{
    		followTicks = 0;
    	}
    	if(followTicks > 400)
    	{
    		return false;
    	}
    	if(this.weaponClass == EnumWeaponClass.MELEE)
    	{
    		EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
    		return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : (!this.longMemory ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistance(MathHelper.floor_double(entitylivingbase.posX), MathHelper.floor_double(entitylivingbase.posY), MathHelper.floor_double(entitylivingbase.posZ))));
    	}
    	else
    	{
            return this.shouldExecute() || !this.attacker.getNavigator().noPath();
    	}
    }
    
    private boolean isValidBlock(Block block)
    {
    	if(block == IdMap.blockBlackFrost) return true;
    	if(block == IdMap.blockBlackFrostStair) return true;
    	if(block == IdMap.blockBlackFrostStairBrick) return true;
    	if(block == IdMap.blockBlackFrostStairCobble) return true;
    	if(block == IdMap.blockBlackFrostSingleSlabSet) return true;
    	if(block == IdMap.blockBlackFrostDoubleSlabSet) return true;
    	return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
        this.field_75445_i = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attacker.getNavigator().clearPathEntity();
        this.attacker.setAttackTarget(null);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	if(this.weaponClass == EnumWeaponClass.MELEE)
    	{
	        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
	        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
	        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ);
	        double d1 = (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F + entitylivingbase.width);
	        --this.field_75445_i;
	
	        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.field_75445_i <= 0 && (this.field_151497_i == 0.0D && this.field_151495_j == 0.0D && this.field_151496_k == 0.0D || entitylivingbase.getDistanceSq(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
	        {
	            this.field_151497_i = entitylivingbase.posX;
	            this.field_151495_j = entitylivingbase.boundingBox.minY;
	            this.field_151496_k = entitylivingbase.posZ;
	            this.field_75445_i = failedPathFindingPenalty + 4 + this.attacker.getRNG().nextInt(7);
	
	            if (this.attacker.getNavigator().getPath() != null)
	            {
	                PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
	                if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
	                {
	                    failedPathFindingPenalty = 0;
	                }
	                else
	                {
	                    failedPathFindingPenalty += 10;
	                }
	            }
	            else
	            {
	                failedPathFindingPenalty += 10;
	            }
	
	            if (d0 > 1024.0D)
	            {
	                this.field_75445_i += 10;
	            }
	            else if (d0 > 256.0D)
	            {
	                this.field_75445_i += 5;
	            }
	
	            if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget))
	            {
	                this.field_75445_i += 15;
	            }
	        }
	
	        this.attackTick = Math.max(this.attackTick - 1, 0);
	
	        if (d0 <= d1 && this.attackTick <= 20)
	        {
	            this.attackTick = 20;
	
	            if (this.attacker.getHeldItem() != null)
	            {
	                this.attacker.swingItem();
	            }
	
	            this.attacker.attackEntityAsMob(entitylivingbase);
	        }
	    }
    	else
    	{
            double d0 = this.attacker.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
            boolean flag = this.attacker.getEntitySenses().canSee(this.attackTarget);

            if (flag)
            {
                ++this.field_75318_f;
            }
            else
            {
                this.field_75318_f = 0;
            }

            if (d0 <= (double)this.field_82642_h && this.field_75318_f >= 20)
            {
                this.attacker.getNavigator().clearPathEntity();
            }
            else
            {
                this.attacker.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.speedTowardsTarget);
            }

            this.attacker.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
            float f;

            if (--this.attackTick == 0)
            {
                if (d0 > (double)this.field_82642_h || !flag)
                {
                    return;
                }

                f = MathHelper.sqrt_double(d0) / this.field_96562_i;
                float f1 = f;

                if (f < 0.1F)
                {
                    f1 = 0.1F;
                }

                if (f1 > 1.0F)
                {
                    f1 = 1.0F;
                }

                ((IRangedAttackMob) this.attacker).attackEntityWithRangedAttack(this.attackTarget, f1);
                this.attackTick = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
            }
            else if (this.attackTick < 0)
            {
                f = MathHelper.sqrt_double(d0) / this.field_96562_i;
                this.attackTick = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
            }
    	}
    }

}
