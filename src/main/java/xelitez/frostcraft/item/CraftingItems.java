package xelitez.frostcraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

public class CraftingItems extends Item
{
	String[] name = new String[] {"Iceball", "Ice-Covered String", "CFU Handler",
			"Frost Transformer", "Compressor", "Frost Sprayer", "CFU Storage Handler",
			"Stick in Water", "Stick in Apple Juice", "Stick in Chocolate", "Crossbow Bolt",
			"Empty Augment"};
	IIcon[] icons;
	
	public CraftingItems() 
	{
		super();
        this.setMaxDamage(0);
        this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
        this.setHasSubtypes(true);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public void getSubItems(Item par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0;i < name.length;i++)
    	{
    		par3List.add(new ItemStack(par1, 1, i));
    	}
    }
	
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
    
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	icons = new IIcon[name.length];
    	icons[0] = par1IconRegister.registerIcon("Frostcraft:iceball");
    	icons[1] = par1IconRegister.registerIcon("Frostcraft:ice_covered_string");
    	icons[2] = par1IconRegister.registerIcon("Frostcraft:cfu_handler");
    	icons[3] = par1IconRegister.registerIcon("Frostcraft:frost_transformer");
    	icons[4] = par1IconRegister.registerIcon("Frostcraft:compressor");
    	icons[5] = par1IconRegister.registerIcon("Frostcraft:frost_sprayer");
    	icons[6] = par1IconRegister.registerIcon("Frostcraft:cfu_storage_handler");
    	icons[7] = par1IconRegister.registerIcon("Frostcraft:stick_in_water");
    	icons[8] = par1IconRegister.registerIcon("Frostcraft:stick_in_applejuice");
    	icons[9] = par1IconRegister.registerIcon("Frostcraft:stick_in_chocolate");
    	icons[10] = par1IconRegister.registerIcon("Frostcraft:crossbow_bolt");
    	icons[11] = par1IconRegister.registerIcon("Frostcraft:empty_augment");
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
    	return "XFC." + this.name[par1ItemStack.getItemDamage()];
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
    	if(itemStack.getItemDamage() >= 7 && itemStack.getItemDamage() <= 9)
    	{
    		return new ItemStack(Items.bucket);
    	}
    	return null;
    }

}
