package xelitez.frostcraft.interfaces;

import net.minecraft.item.ItemStack;

public interface IChargeable 
{
	public int getChargeRate();
	
	public int getMaxCharge();
	
	public int charge(int var1, ItemStack var2);
}
