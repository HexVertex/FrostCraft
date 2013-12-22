package xelitez.frostcraft.entity;

import java.util.List;

import xelitez.frostcraft.damage.EntityDamageSourceIcicle;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFrostWingIcicleDropping extends EntityFrostWingIcicle
{	
	public EntityFrostWingIcicleDropping(World par1World, Entity entity) 
	{
		super(par1World, entity);
	}
	
	public EntityFrostWingIcicleDropping(World par1World) 
	{
		super(par1World, null);
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
    	if(this.worldObj.getBlockId((int)this.posX, (int)this.posY, (int)this.posZ) != 0)
    	{
        	double size = 2.0D;
        	int var3 = MathHelper.floor_double(this.posX - size - 1.0D);
            int var4 = MathHelper.floor_double(this.posX + size + 1.0D);
            int var5 = MathHelper.floor_double(this.posY - size - 1.0D);
            int var29 = MathHelper.floor_double(this.posY + size + 1.0D);
            int var7 = MathHelper.floor_double(this.posZ - size - 1.0D);
            int var30 = MathHelper.floor_double(this.posZ + size + 1.0D);
            List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.castingEntity, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));

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
    	super.onUpdate();
    	this.motionY -= 0.1D;
    	this.posY += this.motionY;
    }

}
