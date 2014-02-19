package xelitez.frostcraft.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FrostcraftCreativeTabs
{
	public static final CreativeTabs FCMechanical = new CreativeTabs(CreativeTabs.getNextID(), "Frostcraft Mechanical")
	{
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(IdMap.blockThermalMachines);
        }
	};
	public static final CreativeTabs FCEquipment = new CreativeTabs(CreativeTabs.getNextID(), "Frostcraft Equipment")
	{
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return IdMap.itemFrostBow;
        }
	};
	public static final CreativeTabs FCMiscItems = new CreativeTabs(CreativeTabs.getNextID(), "Frostcraft Misc")
	{
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return IdMap.itemCraftingItems;
        }
	};
}
