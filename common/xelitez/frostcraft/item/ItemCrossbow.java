package xelitez.frostcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBow;

public class ItemCrossbow extends ItemBow
{

	public ItemCrossbow(int par1) {
		super(par1);
		this.setMaxDamage(284);
		this.setCreativeTab(CreativeTabs.FCEquipment);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

}
