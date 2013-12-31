package xelitez.frostcraft.item;

import java.util.List;

import xelitez.frostcraft.registry.CreativeTabs;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemIcePop extends Item
{
	Integer[] healValues = new Integer[] {1, 5, 3};
	String[] name = new String[] {"Ice Pop", "Apple Ice Pop", "Chocolate Ice Pop"};
	Icon[] icons;

	public ItemIcePop(int par1) {
		super(par1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.FCMiscItems);
	}
	
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	--par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(healValues[par1ItemStack.getItemDamage()], 0.2F);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        if(!par2World.isRemote)
        {
	        EntityItem entityitem = par3EntityPlayer.dropPlayerItem(new ItemStack(Item.stick));
	        entityitem.delayBeforeCanPickup = 0;
        }
        return par1ItemStack;
    }
	
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 20;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par3EntityPlayer.canEat(false))
    	{
    		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
    	}
        return par1ItemStack;
    }
    
    @Override
    public int getMetadata(int par1)
    {
    	return par1;
    }
    
    @Override
    public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	icons = new Icon[3];
    	icons[0] = par1IconRegister.registerIcon("FrostCraft:ice_pop");
    	icons[1] = par1IconRegister.registerIcon("FrostCraft:ice_pop_apple");
    	icons[2] = par1IconRegister.registerIcon("FrostCraft:ice_pop_chocolate");
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public void getSubItems(int par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0;i < 3;i++)
    	{
    		par3List.add(new ItemStack(par1, 1, i));
    	}
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
    	return "XFC." + this.name[par1ItemStack.getItemDamage()];
    }
}
