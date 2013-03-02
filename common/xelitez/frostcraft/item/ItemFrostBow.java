package xelitez.frostcraft.item;

import java.util.List;

import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.entity.EntityFrostArrow;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemFrostBow extends ItemBow
{
	public ItemFrostBow(int par1) 
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(284);
		this.setCreativeTab(CreativeTabs.FCEquipment);
	}
	
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || (par3EntityPlayer.inventory.hasItem(Item.arrow.itemID) && this.hasPlayerItem(par3EntityPlayer, new ItemStack(IdMap.itemCraftingItems.itemID, 1, 0))))
        {
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityFrostArrow var8 = new EntityFrostArrow(par2World, par3EntityPlayer, var7 * 2.0F);

            if (var7 == 1.0F)
            {
                var8.setIsCritical(true);
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }
            
            int var11 = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack);

            if (var11 > 0)
            {
                var8.setCanFreeze(false);
            }
            
            int var12 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.freeze.effectId, par1ItemStack);
            
            if(var12 > 0)
            {
            	var8.setFreezeLevel(var12);
            }
            
            int var13 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.frostburn.effectId, par1ItemStack);
            
            if(var13 > 0)
            {
            	var8.setFrost(var13);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (var5)
            {
                var8.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
                this.consumeItemFromPlayer(par3EntityPlayer, new ItemStack(IdMap.itemCraftingItems.itemID, 1 ,0));
            }

            if (!par2World.isRemote)
            {
            	par2World.spawnEntityInWorld(var8);
            }
        }
    }
    
    private boolean hasPlayerItem(EntityPlayer player, ItemStack item)
    {
        int var2 = this.getInventorySlotContainItem(player, item);
        return var2 >= 0;
    }
    
    private boolean consumeItemFromPlayer(EntityPlayer player, ItemStack item)
    {
        int var2 = this.getInventorySlotContainItem(player, item);

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
    
    private int getInventorySlotContainItem(EntityPlayer player, ItemStack item)
    {
        for (int var2 = 0; var2 < player.inventory.mainInventory.length; ++var2)
        {
            if (player.inventory.mainInventory[var2] != null && player.inventory.mainInventory[var2].itemID == item.itemID && player.inventory.mainInventory[var2].getItemDamage() == item.getItemDamage())
            {
                return var2;
            }
        }

        return -1;
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.capabilities.isCreativeMode || (par3EntityPlayer.inventory.hasItem(Item.arrow.itemID) && this.hasPlayerItem(par3EntityPlayer, new ItemStack(IdMap.itemCraftingItems.itemID, 1, 0))))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    
    public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem != null && usingItem.getItem().itemID == IdMap.itemFrostBow.itemID)
        {
            int k = usingItem.getMaxItemUseDuration() - useRemaining;
            if (k >= 18) return 49;
            if (k >  13) return 33;
            if (k >   0) return 17;
        }
        return getIconIndex(stack);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
    	{
    		par3List.add("\u00a7oWhat a waste of a Frost Bow...");
    	}
    }
    
    public boolean isFull3D()
    {
    	return true;
    }
    
    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
    	return false;
    }
    
    public String getTextureFile()
    {
        return "/xelitez/frostcraft/textures/Items_0.png";
    }
}
