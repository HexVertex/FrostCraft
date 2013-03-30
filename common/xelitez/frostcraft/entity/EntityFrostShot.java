package xelitez.frostcraft.entity;

import java.util.List;

import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.effect.FrostExplosion;
import xelitez.frostcraft.network.PacketSendManagerServer;
import xelitez.util.DegMath;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFrostShot extends Entity
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public EntityLiving shootingEntity;
    private int ticksAlive;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
	private int tickCounter;

    public EntityFrostShot(World par1World)
    {
        super(par1World);
        this.setSize(0.2F, 0.2F);
    }

    protected void entityInit() {}

    @SideOnly(Side.CLIENT)

    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
     * length * 64 * renderDistanceWeight Args: distance
     */
    public boolean isInRangeToRenderDist(double par1)
    {
        double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        var3 *= 64.0D;
        return par1 < var3 * var3;
    }

    public EntityFrostShot(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World);
        this.setSize(0.2F, 0.2F);
        this.setLocationAndAngles(par2, par4, par6, this.rotationYaw, this.rotationPitch);
        this.setPosition(par2, par4, par6);
        double var14 = (double)MathHelper.sqrt_double(par8 * par8 + par10 * par10 + par12 * par12);
        this.accelerationX = par8 / var14 * 0.1D;
        this.accelerationY = par10 / var14 * 0.1D;
        this.accelerationZ = par12 / var14 * 0.1D;
    }

    public EntityFrostShot(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7)
    {
        super(par1World);
        this.shootingEntity = par2EntityLiving;
        this.setSize(0.2F, 0.2F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        par3 += this.rand.nextGaussian() * 0.4D;
        par5 += this.rand.nextGaussian() * 0.4D;
        par7 += this.rand.nextGaussian() * 0.4D;
        double var9 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / var9 * 0.1D;
        this.accelerationY = par5 / var9 * 0.1D;
        this.accelerationZ = par7 / var9 * 0.1D;
    }
    
    public EntityFrostShot(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World);
        this.shootingEntity = par2EntityLiving;
        this.setSize(0.2F, 0.2F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        float var3 = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var3);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var3);
        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * var3);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);
    }
    
    protected float func_70183_g()
    {
        return 0.0F;
    }
    
    protected float func_70182_d()
    {
        return 0.75F;
    }
    
    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var10) * 180.0D / Math.PI);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        spawnParticles();
        if (!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
        {
            this.setDead();
        }
        else
        {
            if(this.isBurning())
            {
            	this.setDead();
            }

            super.onUpdate();

            if (this.inGround)
            {
                int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

                if (var1 == this.inTile)
                {
                    ++this.ticksAlive;

                    if (this.ticksAlive == 600)
                    {
                        this.setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
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

                if (var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25))
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
 
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            spawnParticles();
            this.getMotionFactor();

            if (this.isInWater())
            {
                for (int var19 = 0; var19 < 4; ++var19)
                {
                    float var18 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var18, this.posY - this.motionY * (double)var18, this.posZ - this.motionZ * (double)var18, this.motionX, this.motionY, this.motionZ);
                }
            }
            this.setPosition(this.posX, this.posY, this.posZ);
        }
        if(!worldObj.isRemote && this.tickCounter == 1)
        {
        	PacketSendManagerServer.sendEntityData(this);
        	tickCounter = 2;
        }
        if(!worldObj.isRemote && this.tickCounter >= 3)
        {
        	PacketSendManagerServer.sendEntityData(this);
        	tickCounter = 2;
        }
        if(!worldObj.isRemote)
        {
        	tickCounter++;
        }
        spawnParticles();
        this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.blaze.breathe", 0.15F, 20F);
    }
    
    private void spawnParticles()
    {
    	double var1 = 0.1D;
    	this.worldObj.spawnParticle("snow", this.posX, this.posY, this.posZ, (double)DegMath.cos(-(this.rotationYaw + 90.0f)) * (double)DegMath.cos(-this.rotationPitch + 90.0f) * var1, (double)DegMath.sin(-this.rotationPitch + 90.0f) * var1, (double)DegMath.sin(-(this.rotationYaw + 90.0f)) * (double)DegMath.cos(-this.rotationPitch + 90.0f) * var1);
    	this.worldObj.spawnParticle("snow", this.posX, this.posY, this.posZ, (double)DegMath.cos(-(this.rotationYaw + 90.0f)) * (double)DegMath.cos(-this.rotationPitch - 90.0f) * var1, (double)DegMath.sin(-this.rotationPitch - 90.0f) * var1, (double)DegMath.sin(-(this.rotationYaw + 90.0f)) * (double)DegMath.cos(-this.rotationPitch - 90.0f) * var1);
    	this.worldObj.spawnParticle("snow", this.posX, this.posY, this.posZ, (double)DegMath.cos(-(this.rotationYaw)) * var1, 0.0D, (double)DegMath.sin(-(this.rotationYaw)) * var1);
    	this.worldObj.spawnParticle("snow", this.posX, this.posY, this.posZ, (double)DegMath.cos(-(this.rotationYaw + 180.0f)) * var1, 0.0D, (double)DegMath.sin(-(this.rotationYaw + 180.0f)) * var1);
    	this.worldObj.spawnParticle("snow", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return 0.95F;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
            if (par1MovingObjectPosition.entityHit != null)
            {
                EffectTicker.addEffect(par1MovingObjectPosition.entityHit, new PotionEffect(FCPotion.freeze.id, par1MovingObjectPosition.entityHit instanceof EntityPlayer ? 20 : 40));
                if(this.shootingEntity != null)
                {
                	EffectTicker.addEffect(par1MovingObjectPosition.entityHit, new PotionEffect(FCPotion.frostburn.id, 80, 1), this, this.shootingEntity);
                }
                else
                {
                	EffectTicker.addEffect(par1MovingObjectPosition.entityHit, new PotionEffect(FCPotion.frostburn.id, 80, 1), this, this);
                }
            }
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 1.25F, 1.5F);
            new FrostExplosion(this.worldObj, this, this.posX, this.posY, this.posZ, 4.0f).doExplosion();
            this.setDead();
        }
        this.worldObj.spawnParticle("frostExplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("direction"))
        {
            NBTTagList var2 = par1NBTTagCompound.getTagList("direction");
            this.motionX = ((NBTTagDouble)var2.tagAt(0)).data;
            this.motionY = ((NBTTagDouble)var2.tagAt(1)).data;
            this.motionZ = ((NBTTagDouble)var2.tagAt(2)).data;
        }
        else
        {
            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            this.setBeenAttacked();
            return false;
        }
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
