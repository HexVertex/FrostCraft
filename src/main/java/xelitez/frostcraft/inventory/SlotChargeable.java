package xelitez.frostcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xelitez.frostcraft.interfaces.IChargeable;

public class SlotChargeable extends Slot{

	public SlotChargeable(IInventory par1iInventory, int par2, int par3,
			int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

    public boolean isItemValid(ItemStack par1ItemStack)
    {
    	if(par1ItemStack != null && par1ItemStack.getItem() instanceof IChargeable)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
