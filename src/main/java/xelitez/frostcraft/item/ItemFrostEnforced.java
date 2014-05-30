package xelitez.frostcraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.enums.EnumRenderType;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFrostEnforced extends Item
{
	private Item parentItem;
	private EnumRenderType renderType;
	
	public static final IIcon[] overlays = new IIcon[5];
	private final String[] overlayNames = new String[] {"Frostcraft:overlay_sword", "Frostcraft:overlay_spade", "Frostcraft:overlay_pickaxe", "Frostcraft:overlay_axe", "Frostcraft:overlay_hoe"};
	
	public ItemFrostEnforced(Item parent, EnumRenderType type)
	{
		super();
		parentItem = parent;
		this.maxStackSize = 1;
		this.setMaxDamage(parent.getMaxDamage() + 400);
		this.setCreativeTab(FrostcraftCreativeTabs.FCEquipment);
		this.renderType = type;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	itemIcon = parentItem.getIconIndex(new ItemStack(this, 1, 0));
    	for(int i = 0;i < overlayNames.length;i++)
    	{
    		overlays[i] = par1IconRegister.registerIcon(overlayNames[i]);
    	}
    }
	
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	return parentItem.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }
	
    @Override
    public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
    {
    	return parentItem.func_150893_a(par1ItemStack, par2Block) + parentItem.func_150893_a(par1ItemStack, par2Block) / 10.0f;
    }
    
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        int var1 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.freeze.effectId, par1ItemStack);
        if(var1 > 0)
        {
        	EffectTicker.addEffect(par2EntityLivingBase, new PotionEffect(FCPotion.freeze.id, 5 * var1, 0), par3EntityLivingBase);
        }
        int var2 = EnchantmentHelper.getEnchantmentLevel(FrostEnchantment.frostburn.effectId, par1ItemStack);
        if(var2 > 0)
        {
        	EffectTicker.addEffect(par2EntityLivingBase, new PotionEffect(FCPotion.frostburn.id, 60, var2 - 1), par3EntityLivingBase);
        }
    	return parentItem.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3Block, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
    	return parentItem.onBlockDestroyed(par1ItemStack, par2World, par3Block, par4, par5, par6, par7EntityLivingBase);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getAttributeModifiers(ItemStack item)
    {
        Multimap multimap = parentItem.getAttributeModifiers(new ItemStack(this.parentItem));
        AttributeModifier am = null;
        Multimap mm = super.getAttributeModifiers(item);
        if(!multimap.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).isEmpty())
        {
        	am = (AttributeModifier) multimap.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray()[0];
            double damage = am.getAmount();
            damage += damage / 4;
            mm.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", damage, 0));
        }
        return mm;
    }
    
    @Override
    public boolean isFull3D()
    {
    	return parentItem.isFull3D();
    }
    
    @Override
    public int getItemEnchantability()
    {
    	return parentItem.getItemEnchantability() + parentItem.getItemEnchantability() / 10;
    }
    
    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) 
    {
    	return parentItem.getDigSpeed(stack, block, meta) + parentItem.getDigSpeed(stack, block, meta) / 10.0f;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	return parentItem.getItemUseAction(par1ItemStack);
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
    	return parentItem.getMaxItemUseDuration(par1ItemStack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return parentItem.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
    
    @Override
    public boolean canHarvestBlock(Block par1Block, ItemStack par2ItemStack)
    {
    	return parentItem.canHarvestBlock(par1Block, par2ItemStack);
    }
    
    public EnumRenderType getRenderType()
    {
    	return renderType;
    }
    
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
    	return this.parentItem.getIsRepairable(par1ItemStack, par2ItemStack);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack item)
    {
    	return "Enforced " + StatCollector.translateToLocal(this.parentItem.getUnlocalizedNameInefficiently(new ItemStack(this.parentItem)) + ".name");
    }
    
}
