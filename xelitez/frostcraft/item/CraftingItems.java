package xelitez.frostcraft.item;

import java.util.List;

import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import net.minecraft.item.ItemStack;

public class CraftingItems extends ItemBase
{
	String[] name = new String[] {"Iceball", "Ice-Covered String"};
	
	public CraftingItems(int par1) 
	{
		super(par1);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.FCMiscItems);
        this.setHasSubtypes(true);
	}
	
    public void getSubItems(int par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0;i < 2;i++)
    	{
    		par3List.add(new ItemStack(par1, 1, i));
    	}
    }
	
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    public int getIconFromDamage(int par1)
    {
        switch(par1)
        {
        case 0:
        	return 15;
        case 1:
        	return 14;
        default:
        	return 0;
        }
    }
    
    public String getItemNameIS(ItemStack par1ItemStack)
    {
        return "XFC." + this.name[par1ItemStack.getItemDamage()];
    }

}
