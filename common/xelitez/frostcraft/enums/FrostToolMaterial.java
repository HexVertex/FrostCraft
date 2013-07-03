package xelitez.frostcraft.enums;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public class FrostToolMaterial
{
	public static EnumToolMaterial FROST = EnumHelper.addToolMaterial("Frost", 2, 400, 7.0F, 2, 17);
	public static EnumToolMaterial FROST_COMPILED = EnumHelper.addToolMaterial("Compiled Frost", 1, 1, 7.0F, 2, 17);
	public static EnumToolMaterial FROST_FROZEN = EnumHelper.addToolMaterial("Frozen Frost", 1, 100, 5.5F, 1, 17);
	public static EnumToolMaterial GUARDIAN = EnumHelper.addToolMaterial("Guardian", 2, 91, 6.0F, 2, 22);
	
	public FrostToolMaterial()
	{
		FROST.customCraftingMaterial = FROST_COMPILED.customCraftingMaterial = FROST_FROZEN.customCraftingMaterial = Item.itemsList[Block.ice.blockID];
		GUARDIAN.customCraftingMaterial = Item.ingotGold;
	}
}
