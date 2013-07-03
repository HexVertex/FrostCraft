package xelitez.frostcraft.item;

import xelitez.frostcraft.enums.FrostToolMaterial;
import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemSpear extends ItemSword
{

	public ItemSpear(int par1) 
	{
		super(par1, FrostToolMaterial.GUARDIAN);
        this.setCreativeTab(CreativeTabs.FCEquipment);
	}

}
