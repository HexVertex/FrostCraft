package xelitez.frostcraft.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

public class ItemFrostHoe extends ItemHoe
{

	public ItemFrostHoe(ToolMaterial par1ToolMaterial) {
		super(par1ToolMaterial);
        this.setCreativeTab(FrostcraftCreativeTabs.FCEquipment);
	}
	
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(2, par3EntityLiving);
        int var1 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.freeze.effectId, par1ItemStack);
        if(var1 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.freeze.id, 10 * var1, 0), par3EntityLiving);
        }
        int var2 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.frostburn.effectId, par1ItemStack);
        if(var2 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.frostburn.id, 60, var2 - 1), par3EntityLiving);
        }
        return true;
    }
}
