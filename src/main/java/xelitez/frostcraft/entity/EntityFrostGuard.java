package xelitez.frostcraft.entity;

import xelitez.frostcraft.registry.IdMap;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityFrostGuard extends EntityCreature
{
	
	public EntityFrostGuard(World par1World) 
	{
		super(par1World);
		this.setCurrentItemOrArmor(0, rand.nextInt(1) == 0 ? new ItemStack(IdMap.itemSpear) : new ItemStack(IdMap.itemCrossbow));
	}
	
	
}
