package xelitez.frostcraft.inventory;

import xelitez.frostcraft.interfaces.IChargeable;
import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.frostcraft.tileentity.TileEntityFrostGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFrostGenerator extends Container
{
	private TileEntityFrostGenerator tile;
	
	private int lastStorage = 0;
	private int lastBurnTime = 0;
	private int lastItemValue = 0;
	
	public ContainerFrostGenerator(InventoryPlayer par1InventoryPlayer, TileEntityFrostGenerator par2TileEntityFrostGenerator)
	{
		this.tile = par2TileEntityFrostGenerator;
        this.addSlotToContainer(new SlotChargeable(par2TileEntityFrostGenerator, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntityFrostGenerator, 1, 56, 53));
        
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
	}
	
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.generatorBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.currentItemBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tile.storage);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            
            if (this.lastBurnTime != this.tile.generatorBurnTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.tile.generatorBurnTime);
            }
            if(this.lastItemValue != this.tile.currentItemBurnTime)
            {
            	var2.sendProgressBarUpdate(this, 1, this.tile.currentItemBurnTime);
            }
            if (this.lastStorage != this.tile.storage)
            {
                var2.sendProgressBarUpdate(this, 2, this.tile.storage);
            }
        }
        this.lastBurnTime = this.tile.generatorBurnTime;
        this.lastItemValue = this.tile.currentItemBurnTime;
        this.lastStorage = this.tile.storage;
    }
    
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.generatorBurnTime = par2;
        }

        if (par1 == 1)
        {
            this.tile.currentItemBurnTime = par2;
        }

        if (par1 == 2)
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

            if (par2 != 1 && par2 != 0)
            {
                if (var5.getItem() instanceof IChargeable)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (RecipeRegistry.registry().getFrostTime(var5) != 0)
                {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 2 && par2 < 29)
                {
                    if (!this.mergeItemStack(var5, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 29 && par2 < 38 && !this.mergeItemStack(var5, 2, 29, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 2, 29, false))
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
	public boolean canInteractWith(EntityPlayer var1) 
	{
		return this.tile.isUseableByPlayer(var1);
	}

}
