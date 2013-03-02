package xelitez.frostcraft.inventory;

import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.frostcraft.tileentity.TileEntityFrostEnforcer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerFrostEnforcer extends Container
{
    private TileEntityFrostEnforcer enforcer;
    private int lastEnforceTime = 0;
    private int lastStorage = 0;

    public ContainerFrostEnforcer(InventoryPlayer par1InventoryPlayer, TileEntityFrostEnforcer par2TileEntityFrostEnforcer)
    {
        this.enforcer = par2TileEntityFrostEnforcer;
        this.addSlotToContainer(new Slot(par2TileEntityFrostEnforcer, 0, 56, 17));
        this.addSlotToContainer(new SlotEnforcer(par2TileEntityFrostEnforcer, 1, 116, 35));
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
        par1ICrafting.sendProgressBarUpdate(this, 0, this.enforcer.enforceTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.enforcer.storage);
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastEnforceTime != this.enforcer.enforceTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.enforcer.enforceTime);
            }

            if (this.lastStorage != this.enforcer.storage)
            {
                var2.sendProgressBarUpdate(this, 1, this.enforcer.storage);
            }

        }

        this.lastEnforceTime = this.enforcer.enforceTime;
        this.lastStorage = this.enforcer.storage;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.enforcer.enforceTime = par2;
        }

        if (par1 == 1)
        {
            this.enforcer.storage = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.enforcer.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 1)
            {
                if (!this.mergeItemStack(var5, 2, 38, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 0)
            {
                if (RecipeRegistry.registry().getEnforcingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
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
                else if (par2 >= 29 && par2 < 39 && !this.mergeItemStack(var5, 2, 29, false))
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
    
}
