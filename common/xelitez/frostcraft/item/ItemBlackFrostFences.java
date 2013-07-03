package xelitez.frostcraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import xelitez.frostcraft.registry.IdMap;

public class ItemBlackFrostFences extends ItemBlock
{

	public ItemBlackFrostFences(int par1) 
	{
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
        return "XFC." + ItemBlackFrost.name[par1ItemStack.getItemDamage()] + "Stairs";
    }

}
