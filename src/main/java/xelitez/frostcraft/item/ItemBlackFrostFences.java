package xelitez.frostcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import xelitez.frostcraft.registry.IdMap;

public class ItemBlackFrostFences extends ItemBlock
{

	public ItemBlackFrostFences(Block block) 
	{
		super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@Override
    public IIcon getIconFromDamage(int par1)
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
        return "XFC." + ItemBlackFrost.name[par1ItemStack.getItemDamage()] + "Fence";
    }

}
