package xelitez.frostcraft.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import xelitez.frostcraft.item.ItemFrostBow;

public class EnchantmentFrostburn extends FrostEnchantment{

	protected EnchantmentFrostburn(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) 
	{
		super(par1, par2, par3EnumEnchantmentType);
	}
	
    public int getMinEnchantability(int par1)
    {
        return 5 + 5 * (par1 - 1);
    }
    
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
    
    public boolean func_92037_a(ItemStack stack)
    {
    	return stack.getItem() instanceof ItemFrostBow ? true : super.canApplyAtEnchantingTable(stack);
    }
    
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	return stack.getItem() instanceof ItemFrostBow ? true : super.canApplyAtEnchantingTable(stack);
    }
	
    public int getMaxLevel()
    {
        return 3;
    }

}
