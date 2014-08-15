package xelitez.frostcraft.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xelitez.frostcraft.damage.EntityDamageSourceIcicle;

public class EntityFrostWingIcicleDropping extends EntityFrostWingIcicle
{	
	public EntityFrostWingIcicleDropping(World par1World, Entity entity) 
	{
		super(par1World, entity);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityFrostWingIcicleDropping(World par1World) 
	{
		super(par1World, null);
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected void entityInit() 
	{
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) 
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) 
	{
	}
	
    /**
     * Gets called every tick from main Entity class
     */
    public void onUpdate()
    {      
    	if(!this.worldObj.isRemote)
    	{
            Vec3 vec1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var1 = this.worldObj.rayTraceBlocks(vec1, var2);          
            var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (var1 != null)
            {
            	double size = 2.0D;
            	int var3 = MathHelper.floor_double(this.posX - size - 1.0D);
                int var4 = MathHelper.floor_double(this.posX + size + 1.0D);
                int var5 = MathHelper.floor_double(this.posY - size - 1.0D);
                int var29 = MathHelper.floor_double(this.posY + size + 1.0D);
                int var7 = MathHelper.floor_double(this.posZ - size - 1.0D);
                int var30 = MathHelper.floor_double(this.posZ + size + 1.0D);
                List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.castingEntity, AxisAlignedBB.getBoundingBox((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));

                for (int var11 = 0; var11 < var9.size(); ++var11)
                {
                    Entity var32 = (Entity)var9.get(var11);
                    double var13 = var32.getDistance(this.posX, this.posY, this.posZ) / (double)size;

                    if (var13 <= 1.0D)
                    {
                        double var15 = var32.posX - this.posX;
                        double var17 = var32.posY + (double)var32.getEyeHeight() - this.posY;
                        double  var19 = var32.posZ - this.posZ;
                        double var34 = (double)MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);

                        if (var34 != 0.0D)
                        {
                            var15 /= var34;
                            var17 /= var34;
                            var19 /= var34;
                            
                            DamageSource ds = null;
                            if(this.castingEntity != null)
                            {
                            	ds = new EntityDamageSourceIcicle(this, this.castingEntity);
                            }
                            else
                            {
                            	ds = new EntityDamageSourceIcicle(this, null);
                            }
                            
                            var32.attackEntityFrom(ds, 6);
                        }
                    }
                }
        		this.setDead();
            }
    	}
    	this.motionY -= 0.1D;
    	this.posY += this.motionY;
    	this.setPosition(posX, posY, posZ);
    	super.onUpdate();
    }

}
