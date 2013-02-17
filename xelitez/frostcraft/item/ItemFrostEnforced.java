package xelitez.frostcraft.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.enums.EnumRenderType;
import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class ItemFrostEnforced extends Item
{
	private Item parentItem;
	private EnumRenderType renderType;
	
	public ItemFrostEnforced(int id, Item parent, int customTexture, EnumRenderType type)
	{
		super(id);
		parentItem = parent;
		this.maxStackSize = 1;
		if(customTexture != -1)
		{
			this.setIconIndex(customTexture);
		}
		else
		{
			this.setIconIndex(parent.getIconIndex(new ItemStack(id, 1, 0)));
		}
		this.setMaxDamage(parent.getMaxDamage() + 400);
		this.setCreativeTab(CreativeTabs.FCEquipment);
		this.renderType = type;
	}
	
	public ItemFrostEnforced(int id, Item parent, EnumRenderType type)
	{
		this(id, parent, -1, type);
	}
	
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
    	return parentItem.getStrVsBlock(par1ItemStack, par2Block) + parentItem.getStrVsBlock(par1ItemStack, par2Block) / 10.0f;
    }
    
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
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
    	return parentItem.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
    }
    
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
    	return parentItem.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
    }
 
    public int getDamageVsEntity(Entity par1Entity)
    {
    	return parentItem.getDamageVsEntity(par1Entity) + parentItem.getDamageVsEntity(par1Entity) / 10;
    }
    
    public boolean isFull3D()
    {
    	return parentItem.isFull3D();
    }
    
    public int getItemEnchantability()
    {
    	return parentItem.getItemEnchantability() + parentItem.getItemEnchantability() / 10;
    }
    
    public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
    	return parentItem.getStrVsBlock(stack, block, meta) + parentItem.getStrVsBlock(stack, block, meta) / 10.0f;
    }
    
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	return parentItem.getItemUseAction(par1ItemStack);
    }
    
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
    	return parentItem.getMaxItemUseDuration(par1ItemStack);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return parentItem.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
    
    public boolean canHarvestBlock(Block par1Block)
    {
    	return parentItem.canHarvestBlock(par1Block);
    }
    
    public EnumRenderType getRenderType()
    {
    	return renderType;
    }
    
    public String getTextureFile()
    {
    	if(parentItem == null)return "/gui/items.png";
    	return parentItem.getTextureFile();
    }
}
