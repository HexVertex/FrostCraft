package xelitez.frostcraft.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.MathHelper;
import xelitez.frostcraft.registry.IdMap;

public class EntityAIAttackNearestAttackableTargetInCastle extends EntityAITarget
{
    private final Class<?> targetClass;
    private final int targetChance;
    /**
     * Instance of EntityAINearestAttackableTargetSorter.
     */
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    /**
     * This filter is applied to the Entity search.  Only matching entities will be targetted.  (null -> no
     * restrictions)
     */
    private final IEntitySelector targetEntitySelector;
    private EntityLivingBase targetEntity;

    public EntityAIAttackNearestAttackableTargetInCastle(EntityCreature par1EntityCreature, Class<?> par2Class, int par3, boolean par4)
    {
        this(par1EntityCreature, par2Class, par3, par4, false);
    }

    public EntityAIAttackNearestAttackableTargetInCastle(EntityCreature par1EntityCreature, Class<?> par2Class, int par3, boolean par4, boolean par5)
    {
        this(par1EntityCreature, par2Class, par3, par4, par5, (IEntitySelector)null);
    }

    public EntityAIAttackNearestAttackableTargetInCastle(EntityCreature par1EntityCreature, Class<?> par2Class, int par3, boolean par4, boolean par5, final IEntitySelector par6IEntitySelector)
    {
        super(par1EntityCreature, par4, par5);
        this.targetClass = par2Class;
        this.targetChance = par3;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(par1EntityCreature);
        this.setMutexBits(1);
        this.targetEntitySelector = new IEntitySelector()
        {
            /**
             * Return whether the specified entity is applicable to this filter.
             */
            public boolean isEntityApplicable(Entity par1Entity)
            {
                return !(par1Entity instanceof EntityLivingBase) ? false : (par6IEntitySelector != null && !par6IEntitySelector.isEntityApplicable(par1Entity) ? false : EntityAIAttackNearestAttackableTargetInCastle.this.isSuitableTarget((EntityLivingBase)par1Entity, false));
            }
        };
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @SuppressWarnings("unchecked")
	public boolean shouldExecute()
    {
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
        {
            return false;
        }
        else
        {
            double d0 = this.getTargetDistance();
            List<?> list = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(d0, 4.0D, d0), this.targetEntitySelector);
            Collections.sort(list, this.theNearestAttackableTargetSorter);

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
            	for(int i = 0;i < list.size();i++)
            	{
            		EntityLivingBase entity = (EntityLivingBase)list.get(i);
            		if(this.isValidBlock(entity.worldObj.getBlock(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY) - 1, MathHelper.floor_double(entity.posZ))))
            		{
            			this.targetEntity = entity;
            			return true;
            		}
            	}
            	return false;
            }
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
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }

    public static class Sorter implements Comparator<Object>
    {
    	private final Entity theEntity;

    	public Sorter(Entity par1Entity)
    	{
    		this.theEntity = par1Entity;
    	}

    	public int compare(Entity par1Entity, Entity par2Entity)
    	{
    		double d0 = this.theEntity.getDistanceSqToEntity(par1Entity);
    		double d1 = this.theEntity.getDistanceSqToEntity(par2Entity);
    		return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
    	}

    	public int compare(Object par1Obj, Object par2Obj)
    	{
    		return this.compare((Entity)par1Obj, (Entity)par2Obj);
    	}
    }
}
