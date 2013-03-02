package xelitez.frostcraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xelitez.frostcraft.interfaces.IChargeable;
import xelitez.frostcraft.tileentity.TileEntityThermalPump;

public class ContainerThermalPump extends Container
{
	private TileEntityThermalPump tile;
	private int lastValue;
	private int lastStorage;
	
    public ContainerThermalPump(InventoryPlayer par1InventoryPlayer, TileEntityThermalPump par2TileEntityThermalPump)
    {
        this.tile = par2TileEntityThermalPump;
        this.addSlotToContainer(new SlotChargeable(par2TileEntityThermalPump, 0, 80, 17));
    	
        int var6;
        int var7;
        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, (int)this.tile.value);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.storage);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            
            if (this.lastValue != (int)this.tile.value)
            {
                var2.sendProgressBarUpdate(this, 0, (int)this.tile.value);
            }
            if (this.lastStorage != this.tile.storage)
            {
                var2.sendProgressBarUpdate(this, 1, this.tile.storage);
            }
        }
        this.lastValue = (int)this.tile.value;
        this.lastStorage = this.tile.storage;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.value = (double)par2;
        }
        if (par1 == 1)
        {
            this.tile.storage = par2;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(var5, 1, 37, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 0)
            {
                if (var5.getItem() != null && var5.getItem() instanceof IChargeable)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 2 && par2 < 29)
                {
                    if (!this.mergeItemStack(var5, 28, 37, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 1, 28, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 1, 28, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
    
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) 
	{
        return this.tile.isUseableByPlayer(par1EntityPlayer);
	}
}
