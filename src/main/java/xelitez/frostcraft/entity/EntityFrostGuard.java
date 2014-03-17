package xelitez.frostcraft.entity;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import xelitez.frostcraft.entity.ai.EntityAIAttackNearestAttackableTargetInCastle;
import xelitez.frostcraft.entity.ai.EntityAIAttackUsingWeapon;
import xelitez.frostcraft.entity.ai.EntityAIWalkBackToLastBlackfrost;
import xelitez.frostcraft.entity.ai.EntityAIWanderInCastle;
import xelitez.frostcraft.enums.EnumWeaponClass;
import xelitez.frostcraft.registry.IdMap;

public class EntityFrostGuard extends EntityCreature implements IRangedAttackMob
{
	public boolean loaded = false;
	
	public EntityFrostGuard(World par1World) 
	{
		super(par1World);
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 5;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackUsingWeapon(this, EntityPlayer.class, 1.0D, false, IdMap.itemCrossbow, EnumWeaponClass.RANGED, 20, 60, 15.0F));
        this.tasks.addTask(1, new EntityAIAttackUsingWeapon(this, EntityPlayer.class, 1.2D, false, IdMap.itemSpear, EnumWeaponClass.MELEE));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false));
        this.tasks.addTask(3, new EntityAIAttackUsingWeapon(this, EntityMob.class, 1.0D, false, IdMap.itemCrossbow, EnumWeaponClass.RANGED, 20, 60, 15.0F));
        this.tasks.addTask(3, new EntityAIAttackUsingWeapon(this, EntityMob.class, 1.2D, false, IdMap.itemSpear, EnumWeaponClass.MELEE));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityMob.class, 1.2D, false));
        this.tasks.addTask(3, new EntityAIAttackUsingWeapon(this, EntityAnimal.class, 1.0D, false, IdMap.itemCrossbow, EnumWeaponClass.RANGED, 20, 60, 15.0F));
        this.tasks.addTask(3, new EntityAIAttackUsingWeapon(this, EntityAnimal.class, 1.2D, false, IdMap.itemSpear, EnumWeaponClass.MELEE));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.2D, false));
        this.tasks.addTask(5, new EntityAIWanderInCastle(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWalkBackToLastBlackfrost(this, 1.1D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAIAttackNearestAttackableTargetInCastle(this, EntityPlayer.class, 0, false));
        this.targetTasks.addTask(3, new EntityAIAttackNearestAttackableTargetInCastle(this, EntityMob.class, 0, false));
        this.targetTasks.addTask(3, new EntityAIAttackNearestAttackableTargetInCastle(this, EntityAnimal.class, 0, false));
	}
	
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(160.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }
	
    protected boolean isAIEnabled()
    {
        return true;
    }
    
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
        if (this.isChild())
        {
            this.experienceValue = (int)((float)this.experienceValue * 2.5F);
        }

        return super.getExperiencePoints(par1EntityPlayer);
    }
    
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData)
    {
		this.setCurrentItemOrArmor(0, rand.nextFloat() < 0.67F ? new ItemStack(IdMap.itemSpear) : new ItemStack(IdMap.itemCrossbow));
		this.setEquipmentDropChance(0, 0.125F);
		this.setEquipmentDropChance(1, 0.025F);
		this.setEquipmentDropChance(2, 0.025F);
		this.setEquipmentDropChance(3, 0.025F);
		this.addRandomArmor();
        this.enchantEquipment();
        return super.onSpawnWithEgg(par1EntityLivingData);
    }
    
    protected void addRandomArmor()
    {
    	int i;
        if (this.rand.nextFloat() < (this.worldObj.difficultySetting == EnumDifficulty.HARD ? 0.05F : 0.01F))
        {
        	for(i = 0;i < 3;i++)
        	{
        		this.setCurrentItemOrArmor(i + 1, new ItemStack(getArmorItemForSlot(i + 1, 2)));
        	}
        }
    }
    
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();
        float f = this.getBrightness(1.0F);

        if (f > 0.5F)
        {
            this.entityAge += 2;
        }

        super.onLivingUpdate();
    }

    protected String getSwimSound()
    {
        return "game.hostile.swim";
    }

    protected String getSplashSound()
    {
        return "game.hostile.swim.splash";
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (super.attackEntityFrom(par1DamageSource, par2))
        {
            Entity entity = par1DamageSource.getEntity();

            if (this.riddenByEntity != entity && this.ridingEntity != entity)
            {
                if (entity != this)
                {
                    this.entityToAttack = entity;
                }

                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "game.hostile.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "game.hostile.die";
    }

    protected String func_146067_o(int p_146067_1_)
    {
        return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)par1Entity);
        }

        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                par1Entity.setFire(j * 4);
            }

            if (par1Entity instanceof EntityLivingBase)
            {
                EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, this);
            }

            EnchantmentHelper.func_151385_b(this, par1Entity);
        }

        return flag;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        }
    }


    protected boolean isValidLightLevel()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int l = this.worldObj.getBlockLightValue(i, j, k);

            if (this.worldObj.isThundering())
            {
                int i1 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                l = this.worldObj.getBlockLightValue(i, j, k);
                this.worldObj.skylightSubtracted = i1;
            }

            return l <= this.rand.nextInt(13);
        }
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) 
	{
		if(this.getHeldItem() != null && this.getHeldItem().getItem() != IdMap.itemCrossbow)
		{
			return;
		}
		NBTTagCompound compound = this.getHeldItem().getTagCompound();
		if(compound == null)
		{
			compound = new NBTTagCompound();
		}
		if(!compound.hasKey("loaded")) compound.setBoolean("loaded", false);
		if(!compound.hasKey("loadCooldown")) compound.setInteger("loadCooldown", 0);
		
		if(compound.getBoolean("loaded") && compound.getInteger("loadCooldown") == 0)
    	{
	        EntityCrossbowBolt entitycrossbowbolt = new EntityCrossbowBolt(this.worldObj, this, var1, 2.5F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
	        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
	        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
	        entitycrossbowbolt.setDamage((double)(var2 * 4.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));
	
	        if (i > 0)
	        {
	        	entitycrossbowbolt.setDamage(entitycrossbowbolt.getDamage() + (double)i * 0.5D + 0.5D);
	        }
	
	        if (j > 0)
	        {
	        	entitycrossbowbolt.setKnockbackStrength(j);
	        }
	
	        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
	        {
	        	entitycrossbowbolt.setFire(100);
	        }
	
	        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 1.2F) + 0.75F);
	        this.worldObj.spawnEntityInWorld(entitycrossbowbolt);
	        loaded = false;
			compound.setBoolean("loaded", false);
    	}
		else
		{
			loaded = true;
			compound.setBoolean("loaded", true);
		}
		this.getHeldItem().setTagCompound(compound);
	}
    
    public boolean getCanSpawnHere()
    {
    	if(this.isValidBlock(worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ))))
    	{
    		return super.getCanSpawnHere();
    	}
    	return false;
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
	
}
