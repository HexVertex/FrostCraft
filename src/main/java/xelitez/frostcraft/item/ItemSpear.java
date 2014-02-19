package xelitez.frostcraft.item;

import net.minecraft.item.ItemSword;
import xelitez.frostcraft.enums.FrostToolMaterial;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

public class ItemSpear extends ItemSword
{

	public ItemSpear() 
	{
		super(FrostToolMaterial.GUARDIAN);
        this.setCreativeTab(FrostcraftCreativeTabs.FCEquipment);
	}

}
