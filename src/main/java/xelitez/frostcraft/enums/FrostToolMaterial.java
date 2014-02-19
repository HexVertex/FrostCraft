package xelitez.frostcraft.enums;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class FrostToolMaterial
{
	public static ToolMaterial FROST = EnumHelper.addToolMaterial("Frost", 2, 400, 7.0F, 2, 17);
	public static ToolMaterial FROST_COMPILED = EnumHelper.addToolMaterial("Compiled Frost", 1, 1, 7.0F, 2, 17);
	public static ToolMaterial FROST_FROZEN = EnumHelper.addToolMaterial("Frozen Frost", 1, 100, 5.5F, 1, 17);
	public static ToolMaterial GUARDIAN = EnumHelper.addToolMaterial("Guardian", 2, 91, 6.0F, 2, 22);
	
	public FrostToolMaterial()
	{
		FROST.customCraftingMaterial = FROST_COMPILED.customCraftingMaterial = FROST_FROZEN.customCraftingMaterial = Item.getItemFromBlock(Blocks.ice);
		GUARDIAN.customCraftingMaterial = Items.gold_ingot;
	}
}
