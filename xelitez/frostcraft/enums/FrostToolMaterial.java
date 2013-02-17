package xelitez.frostcraft.enums;

import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class FrostToolMaterial
{
	public static EnumToolMaterial FROST = EnumHelper.addToolMaterial("Frost", 2, 400, 7.0F, 2, 17);
	public static EnumToolMaterial FROST_COMPILED = EnumHelper.addToolMaterial("Compiled Frost", 1, 1, 7.0F, 2, 17);
	public static EnumToolMaterial FROST_FROZEN = EnumHelper.addToolMaterial("Frozen Frost", 1, 100, 5.5F, 1, 17);
}
