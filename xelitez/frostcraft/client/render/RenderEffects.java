package xelitez.frostcraft.client.render;

import java.util.Random;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xelitez.frostcraft.client.particle.EntitySnowFX;

public class RenderEffects 
{
	private static Random rand = new Random();
	
	public static void spawnFrostExplosion(World world, int size, double x, double y, double z, EffectRenderer er)
	{
		double var8 = x;
        double var10 = y;
        double var12 = z;

        for (int var14 = -size; var14 <= size; ++var14)
        {
            for (int var15 = -size; var15 <= size; ++var15)
            {
                for (int var16 = -size; var16 <= size; ++var16)
                {
                    double var17 = (double)var15 + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double var19 = (double)var14 + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double var21 = (double)var16 + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double var23 = (double)MathHelper.sqrt_double(var17 * var17 + var19 * var19 + var21 * var21) / 0.25D + rand.nextGaussian() * 0.05D;
                    EntitySnowFX fx = new EntitySnowFX(world, var8, var10, var12, var17 / var23, var19 / var23, var21 / var23, 1.75f);
                    er.addEffect(fx);

                    if (var14 != -size && var14 != size && var15 != -size && var15 != size)
                    {
                        var16 += size * 2 - 1;
                    }
                }
            }
        }
	}
}
