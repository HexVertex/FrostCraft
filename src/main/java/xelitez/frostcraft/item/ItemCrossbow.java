package xelitez.frostcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xelitez.frostcraft.entity.EntityCrossbowBolt;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrossbow extends Item
{

	public ItemCrossbow() 
	{
        this.maxStackSize = 1;
		this.setMaxDamage(284);
		this.setCreativeTab(FrostcraftCreativeTabs.FCEquipment);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag == null)
		{
			tag = new NBTTagCompound();
		}
		boolean loaded = tag.hasKey("loaded") ? tag.getBoolean("loaded") : false;
		if(loaded) return Integer.MAX_VALUE;
        return 30;
    }
    
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag == null)
		{
			tag = new NBTTagCompound();
		}
		boolean loaded = tag.hasKey("loaded") ? tag.getBoolean("loaded") : false;
		if(loaded) return EnumAction.bow;
        return EnumAction.block;
    }
    
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		NBTTagCompound compound = par1ItemStack.getTagCompound();
		if(compound == null)
		{
			compound = new NBTTagCompound();
		}
		boolean loaded = compound.hasKey("loaded") ? compound.getBoolean("loaded") : false;
		if(!loaded && (par3EntityPlayer.inventory.hasItemStack(new ItemStack(IdMap.itemCraftingItems, 1, 10)) || par3EntityPlayer.capabilities.isCreativeMode))
		{
			if(!par3EntityPlayer.capabilities.isCreativeMode)
			{
				ItemHelper.consumeItemFromPlayer(par3EntityPlayer, new ItemStack(IdMap.itemCraftingItems, 1, 10));
			}
			compound.setBoolean("loaded", true);
			compound.setInteger("loadCooldown", 10);
		}
    	par1ItemStack.setTagCompound(compound);
        return par1ItemStack;
    }
    
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) 
    {
		NBTTagCompound compound = par1ItemStack.getTagCompound();
		if(compound == null)
		{
			compound = new NBTTagCompound();
		}
		if(!compound.hasKey("loaded")) compound.setBoolean("loaded", false);
		if(!compound.hasKey("loadCooldown")) compound.setInteger("loadCooldown", 0);
		
		if(compound.getBoolean("loaded") && compound.getInteger("loadCooldown") == 0)
    	{
    	    boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

    	    float f = 1.5F;

    	    EntityCrossbowBolt entitybolt = new EntityCrossbowBolt(par2World, par3EntityPlayer, f * 2.0F);
    	    
    	    entitybolt.setIsCritical(true);

    	    int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

    	    if (k > 0)
    	    {
    	    	entitybolt.setDamage(entitybolt.getDamage() + (double)k * 0.5D + 0.5D);
    	    }

    	    int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

    	    if (l > 0)
    	    {
    	    	entitybolt.setKnockbackStrength(l);
    	    }

    	    if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
    	    {
    	    	entitybolt.setFire(100);
    	    }

    	    par1ItemStack.damageItem(1, par3EntityPlayer);
    	    par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

    	    if (flag)
    	    {
    	    	entitybolt.canBePickedUp = 2;
    	    }

    	    if (!par2World.isRemote)
    	    {
    	    	par2World.spawnEntityInWorld(entitybolt);
    	    }
        	compound.setBoolean("loaded", false);
        	par1ItemStack.setTagCompound(compound);
    	}
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	NBTTagCompound compound = new NBTTagCompound();
    	compound.setBoolean("loaded", false);
    	compound.setInteger("loadCooldown", 0);
    	if(par1ItemStack.hasTagCompound());
    	{
    		compound = par1ItemStack.getTagCompound();
    		if(compound == null)
    		{
    			compound = new NBTTagCompound();
    		}
    		if(!compound.hasKey("loaded") || !compound.hasKey("loadCooldown"))
    		{
    	    	compound.setBoolean("loaded", false);
    	    	compound.setInteger("loadCooldown", 0);
    		}
    	}
    	
    	if (!compound.getBoolean("loaded") && (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItemStack(new ItemStack(IdMap.itemCraftingItems, 1, 10))))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }
    	else if(compound.getBoolean("loaded") && compound.getInteger("loadCooldown") == 0)
    	{
    		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
    	}

        return par1ItemStack;
    }
    
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	NBTTagCompound compound = new NBTTagCompound();
    	if(par1ItemStack.hasTagCompound());
    	{
    		compound = par1ItemStack.getTagCompound();
    		if(compound == null)
    		{
    			compound = new NBTTagCompound();
    		}
    	}
    	if(compound.hasKey("loadCooldown") && compound.getInteger("loadCooldown") > 0)
    	{
    		compound.setInteger("loadCooldown", compound.getInteger("loadCooldown") - 1);
    	}
    	par1ItemStack.setTagCompound(compound);
    }
    
    public int getItemEnchantability()
    {
        return 1;
    }
    
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return Item.getItemFromBlock(Blocks.planks) == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

}
