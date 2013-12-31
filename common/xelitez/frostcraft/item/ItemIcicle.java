package xelitez.frostcraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.registry.CreativeTabs;

public class ItemIcicle extends Item
{

	public ItemIcicle(int par1) 
	{
		super(par1);
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.FCMiscItems);
	}
	
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        if(par3EntityLivingBase instanceof EntityPlayer)
        {
        	((EntityPlayer) par3EntityLivingBase).inventory.consumeInventoryItem(par1ItemStack.itemID);
        }
        EffectTicker.addEffect(par2EntityLivingBase, new PotionEffect(FCPotion.freeze.id, 40));
        EffectTicker.addEffect(par2EntityLivingBase, new PotionEffect(FCPotion.frostburn.id, 40), par3EntityLivingBase);
        return true;
    }
    
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 2;
    }
    
    public boolean isFull3D()
    {
        return true;
    }
}
