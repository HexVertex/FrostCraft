package xelitez.frostcraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class EntityFrostWingIcicle extends Entity
{
	protected Entity castingEntity;
	
	public EntityFrostWingIcicle(World par1World, Entity entity) 
	{
		super(par1World);
		this.setSize(1.25F, 1.25F);
		this.ignoreFrustumCheck = true;
		this.castingEntity = entity;
	}

}
