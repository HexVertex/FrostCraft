package xelitez.frostcraft.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;

public class CreativeTabs extends net.minecraft.creativetab.CreativeTabs
{
	private ItemStack iconItem;
	
	public static final CreativeTabs FCMechanical = new CreativeTabs("FrostCraft Mechanical", new ItemStack(IdMap.IdThermalMachines, 1, 0));
	public static final CreativeTabs FCEquipment = new CreativeTabs("FrostCraft Equipment", new ItemStack(IdMap.IdFrostBow + 256, 1, 0));
	public static final CreativeTabs FCMiscItems = new CreativeTabs("FrostCraft Misc", new ItemStack(IdMap.IdCraftingItems + 256, 1, 0));
	
	public CreativeTabs(int par1, String par2Str, ItemStack iconItem) {
		super(par1, par2Str);
		this.iconItem = iconItem;
	}
	
	public CreativeTabs(String par2Str, ItemStack iconItem) {
		this(getNextID(), par2Str, iconItem);
	}
	
	public CreativeTabs(String par2Str)
	{
		this(par2Str, new ItemStack(1, 1, 0));
	}
    
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
    
    public ItemStack getIconItemStack()
    {
        return iconItem;
    }

}
