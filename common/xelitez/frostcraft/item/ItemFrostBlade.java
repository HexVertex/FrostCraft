package xelitez.frostcraft.item;

import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFrostBlade extends ItemSword
{

    public ItemFrostBlade(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.setCreativeTab(CreativeTabs.FCEquipment);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(1, par3EntityLiving);
        int var1 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.freeze.effectId, par1ItemStack);
        if(var1 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.freeze.id, 5 * var1, 0), par3EntityLiving);
        }
        int var2 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.frostburn.effectId, par1ItemStack);
        if(var2 > 0)
        {
        	EffectTicker.addEffect(par2EntityLiving, new PotionEffect(FCPotion.frostburn.id, 60, var2 - 1), par3EntityLiving);
        }
        return true;
    }
    
    public String getTextureFile()
    {
        return "/xelitez/frostcraft/textures/Items_0.png";
    }

}
