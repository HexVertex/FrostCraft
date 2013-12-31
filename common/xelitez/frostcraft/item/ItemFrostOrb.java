package xelitez.frostcraft.item;

import xelitez.frostcraft.world.WorldGenFrostWingTower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFrostOrb extends Item
{

	public ItemFrostOrb(int par1) 
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(100);
		this.setHasSubtypes(true);
	}

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par1ItemStack.hasTagCompound() && par1ItemStack.getItemDamage() == 0)
    	{
    		if(par1ItemStack.getTagCompound().hasKey("xCoord") && par1ItemStack.getTagCompound().hasKey("yCoord") && par1ItemStack.getTagCompound().hasKey("zCoord"))
    		{
    	    	par1ItemStack.setItemDamage(par1ItemStack.getMaxDamage());
	    		if(!par2World.isRemote)
	    		{
		    		int xCoord = par1ItemStack.getTagCompound().getInteger("xCoord");
		    		int yCoord = par1ItemStack.getTagCompound().getInteger("yCoord");
		    		int zCoord = par1ItemStack.getTagCompound().getInteger("zCoord");
		    		if(par1ItemStack.getTagCompound().hasKey("removed") && !par1ItemStack.getTagCompound().getBoolean("removed"))
		    		{
		    			par1ItemStack.getTagCompound().setBoolean("removed", true);
		    			WorldGenFrostWingTower.removeCylinder(par2World, xCoord, zCoord);
		    		}
		    		int posX = (int)Math.floor(par3EntityPlayer.posX);
		    		int posY = (int)Math.floor(par3EntityPlayer.posY);
		    		int posZ = (int)Math.floor(par3EntityPlayer.posZ);
	    			for(Object obj : par2World.playerEntities)
	    			{
	    				if(obj instanceof EntityPlayerMP)
	    				{
	    					EntityPlayerMP player = (EntityPlayerMP)obj;
	    					int dx = posX - (int)Math.floor(player.posX);
	    					int dy = posY - (int)Math.floor(player.posY);
	    					int dz = posZ - (int)Math.floor(player.posZ);
	    					if(Math.sqrt(dx * dx + dy * dy + dz * dz) < 10.0D)
	    					{
	    						player.mountEntity(null);
	    						player.addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 4));
	    						player.setPositionAndUpdate(xCoord + 0.5D, yCoord + 3, zCoord + 0.5D);
	    					}
	    				}
	    			}
	    		}
    		}

    	}
    	return par1ItemStack;
    }
    
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if(par1ItemStack.getMaxDamage() >= par1ItemStack.getItemDamage());
    	{
    		par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
    	}
    }
    
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
    	if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("removed") && !par1ItemStack.getTagCompound().getBoolean("removed"))
    	{
    		return EnumRarity.epic;
    	}
		return EnumRarity.uncommon;   	
    }
    
}
