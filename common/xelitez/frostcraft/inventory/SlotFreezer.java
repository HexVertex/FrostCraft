package xelitez.frostcraft.inventory;

import xelitez.frostcraft.registry.RecipeRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotFreezer extends Slot
{
	private EntityPlayer player;
	private int field_75228_b;
	
	public SlotFreezer(EntityPlayer player, IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
		this.player = player;
	}
	
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
    
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onCrafting(par2ItemStack);
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }

    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.field_75228_b += par2;
        this.onCrafting(par1ItemStack);
    }

    protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.player.worldObj, this.player, this.field_75228_b);

        if (!this.player.worldObj.isRemote)
        {
            int var2 = this.field_75228_b;
            float var3 = RecipeRegistry.registry().getFreezerExperience(par1ItemStack);
            int var4;

            if (var3 == 0.0F)
            {
                var2 = 0;
            }
            else if (var3 < 1.0F)
            {
                var4 = MathHelper.floor_float((float)var2 * var3);

                if (var4 < MathHelper.ceiling_float_int((float)var2 * var3) && (float)Math.random() < (float)var2 * var3 - (float)var4)
                {
                    ++var4;
                }

                var2 = var4;
            }

            while (var2 > 0)
            {
                var4 = EntityXPOrb.getXPSplit(var2);
                var2 -= var4;
                this.player.worldObj.spawnEntityInWorld(new EntityXPOrb(this.player.worldObj, this.player.posX, this.player.posY + 0.5D, this.player.posZ + 0.5D, var4));
            }
        }

        this.field_75228_b = 0;
    }

}
