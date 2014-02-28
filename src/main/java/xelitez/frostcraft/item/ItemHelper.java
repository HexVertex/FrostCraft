package xelitez.frostcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemHelper 
{

    public static boolean consumeItemFromPlayer(EntityPlayer player, ItemStack item)
    {
        int var2 = getInventorySlotContainItem(player, item);

        if (var2 < 0)
        {
            return false;
        }
        else
        {
            if (--player.inventory.mainInventory[var2].stackSize <= 0)
            {
            	player.inventory.mainInventory[var2] = null;
            }

            return true;
        }
    }
    
    private static int getInventorySlotContainItem(EntityPlayer player, ItemStack item)
    {
        for (int i = 0; i < player.inventory.mainInventory.length; ++i)
        {
            if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].isItemEqual(item))
            {
                return i;
            }
        }

        return -1;
    }
    
    public static boolean hasPlayerItem(EntityPlayer player, ItemStack item)
    {
        int var2 = getInventorySlotContainItem(player, item);
        return var2 >= 0;
    }
	
}
