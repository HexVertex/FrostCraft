package xelitez.frostcraft.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import xelitez.frostcraft.entity.EntityCrossbowBolt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrossbowBolt extends Render
{
    public void renderBolt(EntityCrossbowBolt par1EntityCrossBowBolt, double par2, double par4, double par6, float par8, float par9)
    {
		GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(par1EntityCrossBowBolt.prevRotationYaw + (par1EntityCrossBowBolt.rotationYaw - par1EntityCrossBowBolt.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityCrossBowBolt.prevRotationPitch + (par1EntityCrossBowBolt.rotationPitch - par1EntityCrossBowBolt.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float var21 = (float)par1EntityCrossBowBolt.crossbowBoltShake - par9;

        if (var21 > 0.0F)
        {
            float var22 = -MathHelper.sin(var21 * 3.0F) * var21;
            GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
        }

		GL11.glColor3f(150.0F / 255.0F, 150.0F / 255.0F, 150.0F / 255.0F);
		RenderHelper.renderCylinder(0.01F, 0.375F, 12, false);
		GL11.glTranslatef(0.0F, 0.125F, 0.0F);
		GL11.glColor3f(175.0F / 255.0F, 175.0F / 255.0F, 175.0F / 255.0F);
		RenderHelper.renderCone(0.020F, 0.125F, 12, false);
		GL11.glTranslatef(0.0025F, -0.4F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glColor3f(125.0F / 255.0F, 0.0F / 255.0F, 0.0F / 255.0F);
		RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, true);
		GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, true);
		GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, true);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
    }
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) 
	{
		this.renderBolt((EntityCrossbowBolt)var1, var2, var4, var6, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) 
	{
		return null;
	}

}
