package xelitez.frostcraft.effect;

import java.util.List;

import xelitez.frostcraft.entity.EntityFrostShot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FrostExplosion 
{
    private World worldObj;
	private Entity exploder;
	private float explosionSize;
	
	private double explosionX;
	private double explosionY;
	private double explosionZ;

	public FrostExplosion(World par1World, Entity par2Entity, double par3, double par5, double par7, float par9)
    {
        this.worldObj = par1World;
        this.exploder = par2Entity;
        this.explosionSize = par9;
        this.explosionX = par3;
        this.explosionY = par5;
        this.explosionZ = par7;
    }
	
	public void doExplosion()
	{
        int var3 = MathHelper.floor_double(this.explosionX - (double)this.explosionSize - 1.0D);
        int var4 = MathHelper.floor_double(this.explosionX + (double)this.explosionSize + 1.0D);
        int var5 = MathHelper.floor_double(this.explosionY - (double)this.explosionSize - 1.0D);
        int var29 = MathHelper.floor_double(this.explosionY + (double)this.explosionSize + 1.0D);
        int var7 = MathHelper.floor_double(this.explosionZ - (double)this.explosionSize - 1.0D);
        int var30 = MathHelper.floor_double(this.explosionZ + (double)this.explosionSize + 1.0D);
        List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));

        for (int var11 = 0; var11 < var9.size(); ++var11)
        {
            Entity var32 = (Entity)var9.get(var11);
            double var13 = var32.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)this.explosionSize;

            if (var13 <= 1.0D)
            {
                double var15 = var32.posX - this.explosionX;
                double var17 = var32.posY + (double)var32.getEyeHeight() - this.explosionY;
                double  var19 = var32.posZ - this.explosionZ;
                double var34 = (double)MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);

                if (var34 != 0.0D)
                {
                    var15 /= var34;
                    var17 /= var34;
                    var19 /= var34;
                    EffectTicker.addEffect(var32, new PotionEffect(FCPotion.freeze.id, var32 instanceof EntityPlayer ? 20 : 40));
                    if(this.exploder instanceof EntityFrostShot)
                    {
                    	if(((EntityFrostShot) this.exploder).shootingEntity != null)
                    	{
                    		EffectTicker.addEffect(var32, new PotionEffect(FCPotion.frostburn.id, 80, 1), this.exploder, ((EntityFrostShot) this.exploder).shootingEntity);
                    	}
                    	else
                    	{
                    		EffectTicker.addEffect(var32, new PotionEffect(FCPotion.frostburn.id, 80, 1), this.exploder, this.exploder);
                    	}
                    }
                    else
                    {
                    	EffectTicker.addEffect(var32, new PotionEffect(FCPotion.frostburn.id, 80, 1), this.exploder, this.exploder);
                    }
                }
            }
        }
	}
}
