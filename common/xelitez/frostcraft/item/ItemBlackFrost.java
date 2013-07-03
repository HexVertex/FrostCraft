package xelitez.frostcraft.item;

import xelitez.frostcraft.registry.IdMap;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlackFrost extends ItemBlock
{

	public static String[] name = new String[] {"BlackFrost", "CrackedBlackFrost", "BlackFrostBrick"};
	
	public ItemBlackFrost(int par1) {
		super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@Override
    public Icon getIconFromDamage(int par1)
    {
        return IdMap.blockBlackFrost.getIcon(0, par1);
    }
    
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "XFC." + name[par1ItemStack.getItemDamage()];
    }
}
