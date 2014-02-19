package xelitez.util;

import net.minecraft.util.MathHelper;

public class DegMath 
{
	public static float sin(float par1)
	{
		return MathHelper.sin(par1 / 180 * (float)Math.PI);
	}
	
	public static float cos(float par1)
	{
		return MathHelper.cos(par1 / 180 * (float)Math.PI);
	}
}
