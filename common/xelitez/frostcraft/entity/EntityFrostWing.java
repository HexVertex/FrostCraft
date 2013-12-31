package xelitez.frostcraft.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.registry.IdMap;

public class EntityFrostWing extends EntityCreature implements IBossDisplayData, IMob
{
	public List<Object[]> rotationList = new ArrayList<Object[]>();
	
    public List<Object[]> rotationListModel = new ArrayList<Object[]>();
	
    /** ticks until heightOffset is randomized */
    private int heightOffsetUpdateTime;
    
    public boolean isFlying;
    
    private int flyingTime = 0;
    
    private int amountOfAttacks = 5;
    private int attackCooldown = 0;
    
    private boolean pathSet = false;
    private Vec3 path = null;
    
    private ChunkCoordinates ck = new ChunkCoordinates(0, 0, 0);
    
	public EntityFrostWing() 
	{
		this(null);
	}
	
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, 0);
        this.dataWatcher.addObject(17, 0);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3D);
    }
	
	public EntityFrostWing(World par1World) 
	{
		super(par1World);
		if(par1World != null)
		{
	        this.tasks.addTask(1, new EntityAISwimming(this));
			this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
			this.tasks.addTask(2, new EntityAILookIdle(this));
	        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
			this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
			this.yOffset *= IdMap.FrostWingSize;
			this.setSize(0.8F, 1.3F);
			this.setSize(this.width * IdMap.FrostWingSize, this.height * IdMap.FrostWingSize);
			this.addRotationOverTime(0, 0, -0.3F, 1);
			this.addRotationOverTime(8, 0, -0.3F, 1);
			this.addRotationOverTime(11, 0, 0.3F, 1);
			this.addRotationOverTime(12, 0, -0.4F, 1);
			this.addRotationOverTime(15, 0, -0.4F, 1);
			this.addRotationOverTime(18, 0, 0.1F, 1);
			this.addRotationOverTime(21, 0, 0.3F, 1);
			this.addRotationOverTime(24, 0, 0.3F, 1);
			this.jumpMovementFactor = 0.15F;
		}
	}
	
	public EntityFrostWing(World par1World, int statX, int statY, int statZ)
	{
		this(par1World);
		ck = new ChunkCoordinates(statX, statY, statZ);
	}
    
    protected void jump()
    {
    	this.motionY = 0.1D;
    }
	
	public void addRotationOverTime(int identifier, int move, float rotateAngle, long milliseconds)
	{
		this.removeRotations(identifier);
		rotationList.add(new Object[] {identifier, move, rotateAngle, milliseconds, System.currentTimeMillis()});
	}
	
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
        	if(this.posY <= 0.0D) this.setDead();
        	if(this.ticksExisted == Integer.MAX_VALUE) this.ticksExisted = 0;
        	++this.ticksExisted;
        	if(this.ticksExisted % 50 == 0 && !this.isDead && this.getHealth() > 0.0F)
        	{
        		this.setHealth(this.getHealth() + 1.0F);
        	}
            --this.heightOffsetUpdateTime;

            if (this.heightOffsetUpdateTime <= 0)
            {
                this.heightOffsetUpdateTime = 100;
            }

        }
        
        if (!this.onGround && this.motionY < 0.0D && (!(!this.getNavigator().noPath() && this.getNavigator().getPath().getPosition(this).yCoord > this.posY) || !(this.path != null && this.path.yCoord > this.posY)))
        {
            this.motionY *= 0.25D;
        }
        
        if(this.motionY < 0.0D && (!(!this.getNavigator().noPath() && this.getNavigator().getPath().getPosition(this).yCoord > this.posY) || !(this.path != null && this.path.yCoord > this.posY)))
        {
        	this.motionY *= 2.0D;
        }
        
        if((!this.getNavigator().noPath() && this.getNavigator().getPath().getPosition(this).yCoord > this.posY) || (this.path != null && this.path.yCoord > this.posY))
        {
        	this.motionY = 0.1D;
        }
        
        if(!this.isFlying && !this.onGround && (this.getAttackTime() == 0 || this.getAttack() != 1))
        {
            this.addRotationOverTime(12, 0, -0.4F, 100);
            this.addRotationOverTime(15, 0, -0.4F, 100);
			this.addRotationOverTime(8, 1, -0.7F, 100);
			this.addRotationOverTime(11, 1, 0.7F, 100);
			this.addRotationOverTime(14, 1, -0.2F, 100);
			this.addRotationOverTime(17, 1, 0.2F, 100);
	        this.isFlying = !this.onGround;
        }
        if(this.isFlying && this.onGround && (this.getAttackTime() == 0  || this.getAttack() != 1))
        {
	        this.addRotationOverTime(12, 1, 0.0F, 100);
	        this.addRotationOverTime(15, 1, 0.0F, 100);
			this.addRotationOverTime(8, 2, 0.0F, 100);
			this.addRotationOverTime(11, 2, 0.0F, 100);
			this.addRotationOverTime(14, 2, 0.0F, 100);
			this.addRotationOverTime(17, 2, 0.0F, 100);
			this.addRotationOverTime(0, 1, -0.3F, 25);
	        this.isFlying = !this.onGround;
        }
        
        if(this.isFlying)
        {
        	++this.flyingTime;
        }
        else
        {
        	this.flyingTime = 0;
        }
        
        this.executeAttack();
        if(this.getAttackTarget() != null)
        {
        	if(this.getAttackTarget().getDistanceToEntity(this) < 14.0F)
        	{
        		if(this.amountOfAttacks > 0 && this.attackCooldown == 0 && this.getAttack() == 0)
        		{
	        		this.getNavigator().clearPathEntity();
	        		this.path = null;
		        	performAttack(this.getAttackByChance(rand.nextInt(this.getTotalAttackChance() + 1)));
		        	this.attackCooldown = 10;
        		}
        	}
        	else if(!this.isFlying && this.getNavigator().noPath())
        	{
        		this.getNavigator().setPath(this.worldObj.getPathEntityToEntity(this, this.getAttackTarget(), (float)this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue(), true, true, true, true), 1.0D);
        	}
        	else if(this.isFlying)
        	{
        		if(this.path != null && this.getDistance(this.path.xCoord, this.path.yCoord, this.path.zCoord) < 14.0F)
        		{
        			this.path = null;
        		}
        		if(this.path == null)
        		{
        			this.path = Vec3.createVectorHelper(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ);
        		}
        		this.getMoveHelper().setMoveTo(path.xCoord, path.yCoord, path.zCoord, 1.0D);
        	}
        }
        else if(this.path != null)
        {
        	this.path = null;
    		this.getNavigator().clearPathEntity();
        }
        if(this.attackCooldown > 0 && !this.worldObj.isRemote) this.attackCooldown--;
        
        super.onLivingUpdate();
        
        if (this.isJumping)
        {
            if (!this.isInWater() && !this.handleLavaMovement())
            {
                if (!this.onGround)
                {
                    this.jump();
                }
            }
            else
            {
                this.motionY += 0.03999999910593033D;
            }
        }
        
    }
    
    private void executeAttack()
    {
    	switch(this.getAttack())
    	{
    	case 1:
    		this.motionX = 0;
    		this.motionY = 0;
    		this.motionZ = 0;
    		if(this.getAttackTarget() != null) this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		if(this.getAttackTime() > 57)
    		{
    			this.addRotationOverTime(0, 3, -0.5F, 200);
    			this.addRotationOverTime(8, 3, -0.5F, 200);
    			this.addRotationOverTime(11, 3, 0.5F, 200);
    			this.addRotationOverTime(6, 3, -0.05F, 200);
    			this.addRotationOverTime(9, 3, -0.05F, 200);
    			this.addRotationOverTime(12, 3, -0.5F, 200);
    			this.addRotationOverTime(15, 3, -0.5F, 200);
    		}
    		if(this.getAttackTime() % 5 == 0 && !this.worldObj.isRemote)
    		{
    			double size = 17.5D;
            	int var3 = MathHelper.floor_double(this.posX - size - 1.0D);
                int var4 = MathHelper.floor_double(this.posX + size + 1.0D);
                int var5 = MathHelper.floor_double(this.posY - size - 1.0D);
                int var29 = MathHelper.floor_double(this.posY + size + 1.0D);
                int var7 = MathHelper.floor_double(this.posZ - size - 1.0D);
                int var30 = MathHelper.floor_double(this.posZ + size + 1.0D);
                List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));

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
                        	if(!var32.isEntityInvulnerable() && ((var32 instanceof EntityPlayer && (!((EntityPlayer)var32).capabilities.isCreativeMode)) || var32 == this.getAttackTarget() || var32 instanceof EntityAnimal || var32 instanceof EntityMob))
                        	{
	                        	EntityFrostWingIcicleDropping icicle = new EntityFrostWingIcicleDropping(this.worldObj, this);
	        					icicle.posX = var32.posX;
	        					icicle.posY = var32.posY + 17.5D;
	        					icicle.posZ = var32.posZ;
	        					this.worldObj.spawnEntityInWorld(icicle);
                        	}
                        }
                    }
                }
    		}
    		if(this.getAttackTime() == 1)
    		{
    			this.resetRotations(300);
    			this.attackCooldown = 40;
    		}
    		break;
    	case 2:
    		if(!this.worldObj.isRemote) this.tryToEvadeTarget();
    		if(this.getAttackTime() == 1)
    		{
    			this.attackCooldown = 40;
    		}
    		break;
    	case 3:
    		if(this.getAttackTarget() != null && (this.getAttackTarget().getDistanceToEntity(this) < 12 || MathHelper.floor_double(this.getAttackTarget().posY) > MathHelper.floor_double(this.posY)))
    		{
    			this.moveEntity(0.0D, 0.25D, 0.0D);
    		}
    		else
    		{
    			this.attackCooldown = 0;
    			this.dataWatcher.updateObject(17, 0);
    		}
    		break;
    	case 4:
    		if(this.motionY < 0.0D)
    		{
    			this.motionY = 0.0D;
    		}
    		if(this.getAttackTime() % 20 == 10 && !this.worldObj.isRemote)
    		{
    			double size = 17.5D;
            	int var3 = MathHelper.floor_double(this.posX - size - 1.0D);
                int var4 = MathHelper.floor_double(this.posX + size + 1.0D);
                int var5 = MathHelper.floor_double(this.posY - size - 1.0D);
                int var29 = MathHelper.floor_double(this.posY + size + 1.0D);
                int var7 = MathHelper.floor_double(this.posZ - size - 1.0D);
                int var30 = MathHelper.floor_double(this.posZ + size + 1.0D);
                List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));
                
                List<Entity> var10 = new ArrayList<Entity>();
                
                for (int i = 0; i < var9.size(); ++i)
                {
                    Entity entity = (Entity)var9.get(i);
                    if((entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) || (this.getAttackTarget() != null && entity.isEntityEqual(this.getAttackTarget())))
                    {
                    	var10.add(entity);
                    }
                }

                for (int var11 = 0; var11 < 5; ++var11)
                {
                	Entity var32 = null;
                	
                	if(var10.size() > 1)
                	{
                		var32 = (Entity)var10.get(this.rand.nextInt(var10.size()));
                	}
                	else if(var10.size() == 1)
                	{
                		var32 = (Entity)var10.get(0);
                	}
                	if(var32 != null)
                	{
	                	double var13 = var32.getDistance(this.posX, this.posY, this.posZ) / (double)size;
	
	                	if (var13 <= 1.0D)
	                	{
	                		double var15 = var32.posX - this.posX;
	                		double var17 = var32.posY + (double)var32.getEyeHeight() - this.posY;
	                		double  var19 = var32.posZ - this.posZ;
	                		double var34 = (double)MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
	
	                		if (var34 != 0.0D)
	                		{
	                			if(!var32.isEntityInvulnerable() && ((var32 instanceof EntityPlayer && (!((EntityPlayer)var32).capabilities.isCreativeMode)) || var32 == this.getAttackTarget()))
	                			{
	                				EntityFrostBall entity = new EntityFrostBall(this.worldObj, this, var32);
	                				entity.posX = this.posX;
	                				entity.posY = this.posY + 1.0D;
	                				entity.posZ = this.posZ;
	                				entity.motionX = (double)MathHelper.sin((float)var11 * 2.0F * (float)Math.PI / 5.0F) * 0.25D;
	                				entity.motionZ = (double)MathHelper.cos((float)var11 * 2.0F * (float)Math.PI / 5.0F) * 0.25D;
	                				this.worldObj.spawnEntityInWorld(entity);
	                			}
	                		}
	                	}
                	}
                }
    		}
    		break;
    	case 5:
    		if(!this.onGround)
    		{
    			this.motionX = 0.0D;
    			this.motionY = -1.0D;
    			this.motionZ = 0.0D;
    		}
    		else
    		{
    			double size = 7.5D;
            	int var3 = MathHelper.floor_double(this.posX - size - 1.0D);
                int var4 = MathHelper.floor_double(this.posX + size + 1.0D);
                int var5 = MathHelper.floor_double(this.posY - size - 1.0D);
                int var29 = MathHelper.floor_double(this.posY + size + 1.0D);
                int var7 = MathHelper.floor_double(this.posZ - size - 1.0D);
                int var30 = MathHelper.floor_double(this.posZ + size + 1.0D);
                List<?> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));

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
                        	if(!var32.isEntityInvulnerable() && ((var32 instanceof EntityPlayer && (!((EntityPlayer)var32).capabilities.isCreativeMode)) || var32 == this.getAttackTarget() || var32 instanceof EntityAnimal || var32 instanceof EntityMob))
                        	{
                                EffectTicker.addEffect(var32, new PotionEffect(FCPotion.freeze.id, var32 instanceof EntityPlayer ? 20 : 40));
                            	EffectTicker.addEffect(var32, new PotionEffect(FCPotion.frostburn.id, 80), this);
                        	}
                        }
                    }
                }
    		}	
    	}
    	
    	if(this.getAttackTime() > 0) 
    	{
    		if(!this.worldObj.isRemote) this.dataWatcher.updateObject(17, this.getAttackTime() - 1);
    	}
    	else
    	{
    		if(!this.worldObj.isRemote && this.getAttack() != 0) this.dataWatcher.updateObject(16, 0);
    	}
    	
    }
    
    private void tryToEvadeTarget()
    {
    	if(this.getAttackTarget() != null && !this.pathSet &&this.getNavigator().noPath() && this.getAttackTime() > 5 && !this.isFlying)
    	{
	    	for(int i = 0;i < 15;i++)
	    	{
	    		int x = this.rand.nextInt(10) - 5 + (int)this.posX;
	    		int z = this.rand.nextInt(10) - 5 + (int)this.posZ;
	    		int y = this.rand.nextInt(10) - 5 + (int)this.posY;
	    		if(this.getAttackTarget().getDistance(this.posX, this.posY, this.posZ)  + 3.0D < this.getAttackTarget().getDistance((double)x, (double)y, (double)z) && this.worldObj.getBlockId(x, y, z) == 0)
	    		{
	    			this.getNavigator().setPath(this.worldObj.getEntityPathToXYZ(this, MathHelper.floor_double(x), (int)y, MathHelper.floor_double(z), (float)this.getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue(), true, true, true, true), 1.0D);
	    			if(!this.getNavigator().noPath()) 
	    			{
		    			this.attackCooldown = this.getAttackTime();
		    			this.pathSet = true;
	    				break;
	    			}
	    		}
	    	}
    	}
    	else if(this.getAttackTime() >= 5 && this.isFlying && !this.pathSet)
    	{
    		if(!this.getNavigator().noPath()) this.getNavigator().clearPathEntity();
    		for(int i = 0;i < 15;i++)
    		{
	    		int x = this.rand.nextInt(10) - 5 + (int)this.posX;
	    		int z = this.rand.nextInt(10) - 5 + (int)this.posZ;
	    		int y = this.rand.nextInt(10) - 5 + (int)this.posY;
	    		if(this.getAttackTarget().getDistance(this.posX, this.posY, this.posZ) + 3.0D < this.getAttackTarget().getDistance((double)x, (double)y, (double)z) && this.worldObj.getBlockId(x, y, z) == 0)
	    		{
	    			this.path = Vec3.createVectorHelper(x, y, z);
	    			if(this.path != null) 
	    			{
	    				this.attackCooldown = this.getAttackTime();
	    				this.pathSet = true;
	    				break;
	    			}
	    		}
	    	}
    	}
    	if(this.path != null && this.pathSet)
    	{
    		this.getMoveHelper().setMoveTo(path.xCoord, path.yCoord, path.zCoord, 1.0D);
    	}
    	if(((this.getNavigator().getPath() != null && this.getNavigator().getPath().isFinished()) || (this.path != null && MathHelper.floor_double(this.path.xCoord) == MathHelper.floor_double(this.posX) && MathHelper.floor_double(this.path.yCoord) == MathHelper.floor_double(this.posY) && MathHelper.floor_double(this.path.zCoord) == MathHelper.floor_double(this.posZ))) && this.getAttackTime() > 5)
    	{
    		this.attackCooldown = 0;
    		this.dataWatcher.updateObject(17, 5);
    	}
    	if(this.getAttackTarget() != null && this.getAttackTarget().getDistanceToEntity(this) > 19.0D)
    	{
    		this.attackCooldown = 0;
    		this.dataWatcher.updateObject(17, 5);
    	}
    	if(this.getAttackTime() <= 5)
    	{
    		this.attackCooldown = 0;
    		this.getNavigator().clearPathEntity();
    		this.pathSet = false;
    		this.path = null;
    	}
    }
    
    private void performAttack(int attack)
    {
    	switch(attack)
    	{
    	case 1:
    		this.dataWatcher.updateObject(17, 60);
    		this.dataWatcher.updateObject(16, attack);
    		break;
    	case 2:
    		this.dataWatcher.updateObject(17, 100);
    		this.dataWatcher.updateObject(16, attack);
    		break;
    	case 3:
    		this.dataWatcher.updateObject(17, 80);
    		this.dataWatcher.updateObject(16, attack);
    		break;
    	case 4:
    		this.dataWatcher.updateObject(17, 60);
    		this.dataWatcher.updateObject(16, attack);
    		break;
    	case 5:
    		this.dataWatcher.updateObject(17, 30);
    		this.dataWatcher.updateObject(16, attack);
    		break;
    	}
    }
    
    private int getTotalAttackChance()
    {
    	int total = 0;
    	for(int i = 0;i < this.amountOfAttacks;i++)
    	{
    		total += this.getAttackChance(i + 1);
    	}
    	return total;
    }
    
    private int getAttackChance(int attack)
    {
    	switch(attack)
    	{
    	case 1:
    		return 2;
    	case 2:
    		if(this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityAnimal) return 0;
    		if(this.getHealth() / this.getMaxHealth() < 0.25F) return 3;
    		if(this.getHealth() / this.getMaxHealth() < 0.5F) return 2;
    		return 1;
    	case 3:
    		if(this.getAttackTarget() != null && this.getAttackTarget().posY > this.posY && this.getAttackTarget().getDistanceToEntity(this) > 10) return 7;
    		if(this.getAttackTarget() != null && this.getAttackTarget().getDistanceToEntity(this) < 5) return 4;
    		return 3;
    	case 4:
    		return 2;
    	case 5:
    		if(this.isFlying && this.flyingTime > 70 && this.getAttackTarget() != null && this.getAttackTarget().onGround && this.getAttackTarget().getDistance(this.posX, this.getAttackTarget().posY, this.posY) < 5.0D) return 3;
    		if(this.isFlying && this.flyingTime > 70)return 2;
    		return 0;
    	default:
    		return 0;
    	}
    }
    
    private int getAttackByChance(int chance)
    {
    	int counter = 0;
    	for(int i = 0;i < this.amountOfAttacks;i++)
    	{
    		counter += this.getAttackChance(i + 1);
    		if(chance <= counter)
    		{
    			return i + 1;
    		}
    	}
    	return 0;
    }
    
    private int getAttack()
    {
    	return this.dataWatcher.getWatchableObjectInt(16);
    }
    
    private int getAttackTime()
    {
    	return this.dataWatcher.getWatchableObjectInt(17);
    }
	
	public void onRotationFinish(int identifier, int move)
	{
		switch(identifier)
		{
		case 6:
			switch(move)
			{
			case 2:
				if(this.getAttackTime() > 1)
				{
	    			this.addRotationOverTime(6, 3, -0.05F, 75);
	    			this.addRotationOverTime(9, 3, -0.05F, 75);
	    			this.addRotationOverTime(12, 3, -0.55F, 75);
	    			this.addRotationOverTime(15, 3, -0.55F, 75);
				}
    			break;
			case 3:
				if(this.getAttackTime() > 1)
				{
	    			this.addRotationOverTime(6, 2, -0.0F, 75);
	    			this.addRotationOverTime(9, 2, -0.0F, 75);
	    			this.addRotationOverTime(12, 2, -0.5F, 75);
	    			this.addRotationOverTime(15, 2, -0.5F, 75);
				}
    			break;
			}
			break;
		case 8:
			switch(move)
			{
			case 0:
				this.addRotationOverTime(8, 1, -0.7F, 500);
				this.addRotationOverTime(11, 1, 0.7F, 500);
				this.addRotationOverTime(14, 1, -0.2F, 500);
				this.addRotationOverTime(17, 1, 0.2F, 500);
				this.addRotationOverTime(0, 1, -0.33F, 500);
				break;
			case 1:
				this.addRotationOverTime(8, 0, -0.1F, 250);
				this.addRotationOverTime(11, 0, 0.1F, 250);
				this.addRotationOverTime(14, 0, 0.2F, 250);
				this.addRotationOverTime(17, 0, -0.2F, 250);
				this.addRotationOverTime(0, 0, -0.27F, 250);
				break;
			}
			break;
		}
		cleanupRotations();
	}
	
	public void resetRotations(int time)
	{
		this.addRotationOverTime(0, 0, -0.3F, 1);
		this.addRotationOverTime(1, 0, 0.0F, time);
		this.addRotationOverTime(2, 0, 0.0F, time);
		this.addRotationOverTime(3, 0, 0.0F, time);
		this.addRotationOverTime(4, 0, 0.0F, time);
		this.addRotationOverTime(5, 0, 0.0F, time);
		this.addRotationOverTime(6, 0, 0.0F, time);
		this.addRotationOverTime(7, 0, 0.0F, time);
		this.addRotationOverTime(9, 0, 0.0F, time);
		this.addRotationOverTime(10, 0, 0.0F, time);
		this.addRotationOverTime(13, 0, 0.0F, time);
		this.addRotationOverTime(16, 0, 0.0F, time);
		this.addRotationOverTime(18, 0, 0.1F, time);
		this.addRotationOverTime(19, 0, 0.0F, time);
		this.addRotationOverTime(20, 0, 0.0F, time);
		this.addRotationOverTime(21, 0, 0.3F, time);
		this.addRotationOverTime(22, 0, 0.0F, time);
		this.addRotationOverTime(23, 0, 0.0F, time);
		this.addRotationOverTime(24, 0, 0.3F, time);
		this.addRotationOverTime(25, 0, 0.0F, time);
		this.addRotationOverTime(26, 0, 0.0F, time);
		if(this.isFlying)
		{
            this.addRotationOverTime(12, 0, -0.4F, 100);
            this.addRotationOverTime(15, 0, -0.4F, 100);
			this.addRotationOverTime(8, 1, -0.7F, 100);
			this.addRotationOverTime(11, 1, 0.7F, 100);
			this.addRotationOverTime(14, 1, -0.2F, 100);
			this.addRotationOverTime(17, 1, 0.2F, 100);
		}
		else
		{
	        this.addRotationOverTime(12, 1, 0.0F, 100);
	        this.addRotationOverTime(15, 1, 0.0F, 100);
			this.addRotationOverTime(8, 2, 0.0F, 100);
			this.addRotationOverTime(11, 2, 0.0F, 100);
			this.addRotationOverTime(14, 2, 0.0F, 100);
			this.addRotationOverTime(17, 2, 0.0F, 100);
			this.addRotationOverTime(0, 1, -0.3F, 25);
		}
	}
	
	private void removeRotations(int identifier)
	{
		for(int var1 = 0;var1 < rotationList.size();var1++)
		{
			if((Integer)rotationList.get(var1)[0] == identifier)
			{
				rotationList.remove(var1);
				removeRotations(identifier);
				return;
			}
		}
	}
	
	private void cleanupRotations()
	{
		for(int var1 = 0;var1 < rotationList.size();var1++)
		{
			Object[] obj = rotationList.get(var1);
			if((Long)obj[3] + (Long)obj[4] < System.currentTimeMillis())
			{
				rotationList.remove(var1);
				cleanupRotations();
				return;
			}
		}
	}
	
	public Object[] getList(int identifier)
	{
		for(Object[] list : rotationList)
		{
			if((Integer)list[0] == identifier)
			{
				return list;
			}
		}
		return null;
	}
	
	public Object[] getList(int identifier, int move)
	{
		for(Object[] list : rotationList)
		{
			if((Integer)list[0] == identifier && (Integer)list[1] == move)
			{
				return list;
			}
		}
		return null;
	}
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
    	if(this.getAttack() == 1 && this.isFlying)
    	{
    		return false;
    	}
        return true;
    }
    
    protected boolean isMovementCeased()
    {
    	if(this.getAttack() == 1)
    	{
    		return true;
    	}
        return false;
    }
    
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }
    
    /**
     * knocks back this entity
     */
    public void knockBack(Entity par1Entity, float par2, double par3, double par5)
    {
        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
        {
            this.isAirBorne = true;
            float f1 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
            float f2 = 0.4F;
            this.motionX /= 2.0D;
            this.motionY /= 2.0D;
            this.motionZ /= 2.0D;
            this.motionX -= par3 / (double)f1 * (double)f2;
            this.motionY += (double)f2;
            this.motionZ -= par5 / (double)f1 * (double)f2;

            if (this.motionY > 0.4000000059604645D)
            {
                this.motionY = 0.4000000059604645D;
            }
            if(this.isFlying)
            {
            	this.motionY = -0.7D;
            	this.attackCooldown = 20;
            }
        }
    }
    
    public int getTotalArmorValue()
    {
    	return 10;
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}

	@Override
	public ItemStack[] getLastActiveItems() 
	{
		return new ItemStack[0];
	}
	
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setInteger("Attack", this.getAttack());
    	par1NBTTagCompound.setInteger("ATime", this.getAttackTime());
    	par1NBTTagCompound.setInteger("AttackCooldown", this.attackCooldown);
    	
    	par1NBTTagCompound.setInteger("StatueX", this.ck.posX);
    	par1NBTTagCompound.setInteger("StatueY", this.ck.posY);
    	par1NBTTagCompound.setInteger("StatueZ", this.ck.posZ);
    }
    
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	
    	this.dataWatcher.updateObject(16, par1NBTTagCompound.getInteger("Attack"));
    	this.dataWatcher.updateObject(17, par1NBTTagCompound.getInteger("ATime"));
    	this.attackCooldown = par1NBTTagCompound.getInteger("AttackCooldown");
    	
    	int x = par1NBTTagCompound.getInteger("StatueX");
    	int y = par1NBTTagCompound.getInteger("StatueY");
    	int z = par1NBTTagCompound.getInteger("StatueZ");
    	
    	this.ck = new ChunkCoordinates(x, y, z);
    }
    
    protected void dropFewItems(boolean par1, int par2)
    {
    	if(!(this.ck.posX == 0 && this.ck.posY == 0 && ck.posZ == 0))
    	{
    		ItemStack itemOrb = new ItemStack(IdMap.itemFrostOrb);
    		if(itemOrb.stackTagCompound == null)
    		{
    			itemOrb.stackTagCompound = new NBTTagCompound("tag");
    		}
    		itemOrb.getTagCompound().setInteger("xCoord", ck.posX);
    		itemOrb.getTagCompound().setInteger("yCoord", ck.posY);
    		itemOrb.getTagCompound().setInteger("zCoord", ck.posZ);
    		itemOrb.getTagCompound().setBoolean("removed", false);
    		this.entityDropItem(itemOrb, 0.0F);
    	}
    }

}