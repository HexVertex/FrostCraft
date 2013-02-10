package xelitez.frostcraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.frostcraft.tileentity.TileEntityFreezer;
import xelitez.frostcraft.tileentity.TileEntityFrostFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerFreezer extends Container
{
    private TileEntityFreezer tile;
    private int lastFreezeTime = 0;
    private int lastStorage = 0;
    
    public ContainerFreezer(InventoryPlayer par1InventoryPlayer, TileEntityFreezer par2TileEntityFreezer)
    {
        this.tile = par2TileEntityFreezer;
        this.addSlotToContainer(new Slot(par2TileEntityFreezer, 0, 53, 17));
        this.addSlotToContainer(new SlotFreezer(par1InventoryPlayer.player, par2TileEntityFreezer, 1, 107, 17));
        this.addSlotToContainer(new SlotContainer(par2TileEntityFreezer, 2, 107, 53));
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
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.freezeTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.storage);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastFreezeTime != this.tile.freezeTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.tile.freezeTime);
            }

            if (this.lastStorage != this.tile.storage)
            {
                var2.sendProgressBarUpdate(this, 1, this.tile.storage);
            }

        }

        this.lastFreezeTime = this.tile.freezeTime;
        this.lastStorage = this.tile.storage;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.freezeTime = par2;
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

            if (par2 == 1 || par2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 0)
            {
                if (RecipeRegistry.registry().getFreezingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 3, 30, false))
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
