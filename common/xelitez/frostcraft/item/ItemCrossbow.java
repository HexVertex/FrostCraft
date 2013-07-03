package xelitez.frostcraft.item;

import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.item.ItemBow;

public class ItemCrossbow extends ItemBow
{

	public ItemCrossbow(int par1) {
		super(par1);
		this.setMaxDamage(284);
		this.setCreativeTab(CreativeTabs.FCEquipment);
	}

}
