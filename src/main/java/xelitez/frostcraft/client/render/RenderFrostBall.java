package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import xelitez.frostcraft.entity.EntityFrostBall;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFrostBall extends Render
{
	private ResourceLocation mainTexture = new ResourceLocation("frostcraft:textures/entities/frostball.png");
	private ResourceLocation back1 = new ResourceLocation("frostcraft:textures/entities/frostball_back1.png");
	private ResourceLocation back2 = new ResourceLocation("frostcraft:textures/entities/frostball_back2.png");
	
    public void doRenderFrostBall(EntityFrostBall par1EntityFrostBall, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
		RenderHelper.disableStandardItemLighting();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glColor3f(64.0F / 255.0F, 179.0F / 255.0F, 255.0F / 255.0F);
        Tessellator tes = Tessellator.instance;
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.back2);
        GL11.glPushMatrix();
        GL11.glRotatef(((float)(System.currentTimeMillis() % 1000) / 1000.0F) * -360.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(1.3F, 1.3F, 1.3F);
        tes.startDrawingQuads();
        tes.addVertexWithUV(-0.5D, -0.5D, 0.0D, 0.0D, 0.0D);
        tes.addVertexWithUV(0.5D, -0.5D, 0.0D, 1.0D, 0.0D);
        tes.addVertexWithUV(0.5D, 0.5D, 0.0D, 1.0D, 1.0D);
        tes.addVertexWithUV(-0.5D, 0.5D, 0.0D, 0.0D, 1.0D);
        tes.draw();
        GL11.glPopMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.back1);
        GL11.glPushMatrix();
        GL11.glRotatef(((float)(System.currentTimeMillis() % 1000) / 1000.0F) * 360.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(1.3F, 1.3F, 1.3F);
        tes.startDrawingQuads();
        tes.addVertexWithUV(-0.5D, -0.5D, 0.0D, 0.0D, 0.0D);
        tes.addVertexWithUV(0.5D, -0.5D, 0.0D, 1.0D, 0.0D);
        tes.addVertexWithUV(0.5D, 0.5D, 0.0D, 1.0D, 1.0D);
        tes.addVertexWithUV(-0.5D, 0.5D, 0.0D, 0.0D, 1.0D);
        tes.draw();
        GL11.glPopMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.mainTexture);
        tes.startDrawingQuads();
        tes.addVertexWithUV(-0.5D, -0.5D, 0.0D, 0.0D, 0.0D);
        tes.addVertexWithUV(0.5D, -0.5D, 0.0D, 1.0D, 0.0D);
        tes.addVertexWithUV(0.5D, 0.5D, 0.0D, 1.0D, 1.0D);
        tes.addVertexWithUV(-0.5D, 0.5D, 0.0D, 0.0D, 1.0D);
        tes.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glShadeModel(GL11.GL_FLAT);
		RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
    }
	
	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) 
	{
		this.doRenderFrostBall((EntityFrostBall)entity, d0, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return mainTexture;
	}

}
