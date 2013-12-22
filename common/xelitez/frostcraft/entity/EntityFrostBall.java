package xelitez.frostcraft.entity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xelitez.frostcraft.damage.EntityDamageSourceFrostBall;

public class EntityFrostBall extends Entity
{
	private Entity castingEntity;
	
	private Entity target;
	
	private int ticksInAir = 0;
	
	public EntityFrostBall(World par1World) 
	{
		super(par1World);
        this.setSize(0.25F, 0.25F);
	}
	
	public EntityFrostBall(World par1World, Entity castingEntity, Entity target) 
	{
		super(par1World);
		this.castingEntity = castingEntity;
		this.target = target;
        this.setSize(0.25F, 0.25F);
        this.setPosition(this.posX, this.posY, this.posZ);
	}

	@Override
	protected void entityInit() 
	{
		
	}
	
    public void onUpdate()
    {    
        if (!this.worldObj.isRemote && (this.castingEntity != null && this.castingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
        {
            this.setDead();
        }
        if(!this.worldObj.isRemote)
    	{
    		++ticksInAir;
    		
            Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var3 = this.worldObj.clip(var15, var2);
            var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (var3 != null)
            {
                var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
            }

            Entity var4 = null;
            List<?> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;
            
            for (int var8 = 0; var8 < var5.size(); ++var8)
            {
                Entity var9 = (Entity)var5.get(var8);
                if (var9.canBeCollidedWith() && !var9.isEntityEqual(this.castingEntity) && !(var9 instanceof EntityFrostBall))
                {
                    float var10 = 0.3F;
                    AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
                    MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);

                    if (var12 != null)
                    {
                        double var13 = var15.distanceTo(var12.hitVec);

                        if (var13 < var6 || var6 == 0.0D)
                        {
                            var4 = var9;
                            var6 = var13;
                        }
                    }
                }
            }

            if (var4 != null)
            {
                var3 = new MovingObjectPosition(var4);
            }

            if (var3 != null)
            {
                this.onImpact(var3);
            }
            
            if(this.target != null && !this.target.isDead && this.ticksInAir > 20)
            {
	    		double dx = this.posX - this.target.posX;
	    		double dy = this.posY - (this.target.posY + (this.target.boundingBox.maxY - this.target.boundingBox.minY) * 0.5D);
	    		double dz = this.posZ - this.target.posZ;
	    		double dt = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
	    		double speed = 2.0D;
	    		this.motionX = dx / dt * -speed;
	    		this.motionY = dy / dt * -speed;
	    		this.motionZ = dz / dt * -speed;
            }
        	this.posX += this.motionX;
        	this.posY += this.motionY;
        	this.posZ += this.motionZ;
        	this.setPosition(this.posX, this.posY, this.posZ);
    	}
        if(!this.worldObj.isRemote && (this.target == null || (this.target != null && this.target.isDead) || this.ticksInAir == 40))
    	{
    		this.setDead();
    	}
    	super.onUpdate();
    }
    
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
        	if(par1MovingObjectPosition.entityHit != null)
        	{
	            DamageSource ds = null;
	            if(this.castingEntity != null)
	            {
	            	ds = new EntityDamageSourceFrostBall(this, this.castingEntity);
	            }
	            else
	            {
	            	ds = new EntityDamageSourceFrostBall(this, null);
	            }
	            
	            par1MovingObjectPosition.entityHit.attackEntityFrom(ds, 3);
        	}
            this.setDead();
        }
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) 
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) 
	{
		
	}
	
    public boolean canBeCollidedWith()
    {
        return true;
    }
    
    public float getCollisionBorderSize()
    {
        return 1.0F;
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

}
