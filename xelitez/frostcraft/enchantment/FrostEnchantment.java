package xelitez.frostcraft.enchantment;

import cpw.mods.fml.common.registry.LanguageRegistry;
import xelitez.frostcraft.item.ItemFrostBlade;
import xelitez.frostcraft.item.ItemFrostEnforced;
import xelitez.frostcraft.item.ItemFrostHoe;
import xelitez.frostcraft.item.ItemFrostTool;
import xelitez.frostcraft.registry.Settings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.EnumHelper;

public class FrostEnchantment extends Enchantment
{
	public static Enchantment freeze;
	public static Enchantment frostburn;
	
	public static final EnumEnchantmentType frost = EnumHelper.addEnchantmentType("frost");

	protected FrostEnchantment(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) 
	{
		super(par1, par2, par3EnumEnchantmentType);
	}
	
	private static int getFreeID()
	{
		for(int i = 0;i < Enchantment.enchantmentsList.length;i++)
		{
			if(Enchantment.enchantmentsList[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static void registerFrostEnchantments()
	{
		freeze = new EnchantmentFreeze(Settings.enchantmentFreezeId != -1 ? Settings.enchantmentFreezeId : getFreeID(), 5, frost).setName("Freeze");
		frostburn = new EnchantmentFrostburn(Settings.enchantmentFrostburnId != -1 ? Settings.enchantmentFrostburnId : getFreeID(), 5, frost).setName("Frostburn");
		LanguageRegistry.instance().addStringLocalization(freeze.getName(), "Freeze");
		LanguageRegistry.instance().addStringLocalization(frostburn.getName(), "Frostburn");
	}
	
    public boolean func_92037_a(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemFrostBlade || stack.getItem() instanceof ItemFrostTool || stack.getItem() instanceof ItemFrostHoe || stack.getItem() instanceof ItemFrostEnforced) return true;
        return false;
    }
	
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemFrostBlade || stack.getItem() instanceof ItemFrostTool || stack.getItem() instanceof ItemFrostHoe || stack.getItem() instanceof ItemFrostEnforced) return true;
        return false;
    }

}
