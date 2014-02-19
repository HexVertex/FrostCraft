package xelitez.frostcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBow;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrossbow extends ItemBow
{

	public ItemCrossbow() {
		super();
		this.setMaxDamage(284);
		this.setCreativeTab(FrostcraftCreativeTabs.FCEquipment);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

}
