package xelitez.frostcraft.item;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.entity.EntityFrostShot;
import xelitez.frostcraft.interfaces.IChargeable;
import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.registry.IdMap;

public class ItemFrostGun extends ItemBase implements IChargeable
{

	public ItemFrostGun(int par1) 
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(1024);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.FCEquipment);
	}
	
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
        if (var6 >= 10 && par1ItemStack.getItemDamage() <= par1ItemStack.getMaxDamage() - 16)
        {
            EntityFrostShot var8 = new EntityFrostShot(par2World, par3EntityPlayer);

            par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() + 16);
            par2World.playSoundAtEntity(par3EntityPlayer, "mob.ghast.fireball", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.75F);


            if (!par2World.isRemote)
            {
            	par2World.spawnEntityInWorld(var8);
            }
        }
    }
	
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }
    
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par1ItemStack.getItemDamage() <= par1ItemStack.getMaxDamage() - 16)
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
    
    @Override
    public void getSubItems(int par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1024));
        for(Enchantment enchant : Enchantment.enchantmentsList)
        {
        	if(enchant != null && enchant instanceof FrostEnchantment)
        	{
        		par3List.add(Item.field_92053_bW.func_92057_a(new EnchantmentData(enchant, enchant.getMaxLevel())));
        	}
        }
    }
    
    @Override
    public boolean isFull3D()
    {
    	return true;
    }

	@Override
	public int getChargeRate() 
	{
		return 4;
	}

	@Override
	public int getMaxCharge() 
	{
		return this.getMaxDamage();
	}

	@Override
	public int charge(int i, ItemStack var2) 
	{
		if(var2.getItemDamage() > 0)
		{
			int currentDamage = var2.getItemDamage();
			int damageToAdd = i;
			if(currentDamage - damageToAdd < 0)
			{
				damageToAdd = currentDamage;
			}
			var2.setItemDamage(currentDamage - damageToAdd);
			return damageToAdd;
		}
		else
		{
			return 0;
		}
	}
}
