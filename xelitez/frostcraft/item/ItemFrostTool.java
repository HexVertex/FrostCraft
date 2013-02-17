package xelitez.frostcraft.item;

import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;

public class ItemFrostTool extends ItemTool
{

	protected ItemFrostTool(int par1, int par2,
			EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) 
	{
		super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
        this.setCreativeTab(CreativeTabs.FCEquipment);
	}
	
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(2, par3EntityLiving);
        int var1 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.freeze.effectId, par1ItemStack);
        if(var1 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.freeze.id, 5 * var1, 0), par3EntityLiving);
        }
        int var2 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.frostBurn.effectId, par1ItemStack);
        if(var2 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.frostBurn.id, 60, var2 - 1), par3EntityLiving);
        }
        return true;
    }
	
    public String getTextureFile()
    {
        return "/xelitez/frostcraft/textures/Items_0.png";
    }

}
