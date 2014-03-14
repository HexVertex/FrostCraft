package xelitez.frostcraft.client.render;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.entity.EntityFrostWingIcicle;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderIcicle extends Render
{
	private ResourceLocation targetTexture = new ResourceLocation("frostcraft:textures/entities/icicletarget.png");
	
	private void renderIcicle(EntityFrostWingIcicle entity, double d0, double d1, double d2,
			float f, float f1)
	{
		Tessellator tes = Tessellator.instance;
		
		RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glColor4f(64.0F / 255.0F, 179.0F / 255.0F, 255.0F / 255.0F, 0.7F);
        
		GL11.glPushMatrix();
        GL11.glTranslatef((float)d0, (float)d1, (float)d2);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f1 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
        tes.startDrawing(GL11.GL_TRIANGLES);
        tes.addVertex(-0.5D, 1.0D, -0.5D);
        tes.addVertex(0.5D, 1.0D, -0.5D);
        tes.addVertex(0.0D, -1.0D, 0.0D);
        tes.draw();
        tes.startDrawing(GL11.GL_TRIANGLES);
        tes.addVertex(0.5D, 1.0D, -0.5D);
        tes.addVertex(0.5D, 1.0D, 0.5D);
        tes.addVertex(0.0D, -1.0D, 0.0D);
        tes.draw();
        tes.startDrawing(GL11.GL_TRIANGLES);
        tes.addVertex(-0.5D, 1.0D, -0.5D);
        tes.addVertex(0.0D, -1.0D, 0.0D);
        tes.addVertex(-0.5D, 1.0D, 0.5D);
        tes.draw();
        tes.startDrawing(GL11.GL_TRIANGLES);
        tes.addVertex(-0.5D, 1.0D, 0.5D);
        tes.addVertex(0.0D, -1.0D, 0.0D);
        tes.addVertex(0.5D, 1.0D, 0.5D);
        tes.draw();
        tes.startDrawingQuads();
        tes.addVertex(0.5D, 1.0D, 0.5D);
        tes.addVertex(0.5D, 1.0D, -0.5D);
        tes.addVertex(-0.5D, 1.0D, -0.5D);
        tes.addVertex(-0.5D, 1.0D, 0.5D);
        tes.draw();
        
		GL11.glPopMatrix();
		
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glPushMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.targetTexture);
        double dy = -RenderManager.renderPosY + this.getLandingHeight(entity);
        
        GL11.glTranslatef((float)d0, (float)dy, (float)d2);
        GL11.glRotatef((float)(System.currentTimeMillis() / 10 % 360), 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F - (float)(System.currentTimeMillis() / 10 % 45) / 45.0F * 0.5F, 1.0F, 1.0F - (float)(System.currentTimeMillis() / 10 % 45) / 45.0F * 0.5F);
        tes.startDrawingQuads();
        tes.addVertexWithUV(1.0F, 0.0F, 1.0F, 1, 1);
        tes.addVertexWithUV(1.0F, 0.0F, -1.0F, 1, 0);
        tes.addVertexWithUV(-1.0F, 0.0F, -1.0F, 0, 0);
        tes.addVertexWithUV(-1.0F, 0.0F, 1.0F, 0, 1);
        tes.draw();
        
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_CULL_FACE);
        RenderHelper.enableStandardItemLighting();
	}
	
	private double getLandingHeight(Entity entity)
	{
		for(int var1 = (int)entity.posY; var1 > 0;var1--)
		{
			if(entity.worldObj.getBlock((int)entity.posX, var1, (int)entity.posZ) != Blocks.air)
			{
				return var1 + 1.0001F;
			}
		}
		return 0;
	}
	
	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) 
	{
		this.renderIcicle((EntityFrostWingIcicle)entity, d0, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return null;
	}

}
