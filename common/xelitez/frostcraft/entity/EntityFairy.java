package xelitez.frostcraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityFairy extends EntityLiving
{

	public EntityFairy(World par1World) 
	{
		super(par1World);
	}

	@Override
	public int getMaxHealth() 
	{
		return 7;
	}

}
