package xelitez.frostcraft.client.render;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class RenderHelper 
{
	public static void renderCubic(Tessellator tes, float length, float height, float depth, boolean flag)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		if(!flag)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		float[][] verteces = new float[][] {
				{-length / 2.0F, 0.0F, depth / 2.0F}, //top top left (0)
				{length / 2.0F, 0.0F, depth / 2.0F}, //top top right(1)
				{length / 2.0F, 0.0F, -depth / 2.0F}, //top bottom right(2)
				{-length / 2.0F, 0.0F, -depth / 2.0F}, //top bottom left (3)
				{-length / 2.0F, -height, depth / 2.0F}, //bottom top left (4)
				{length / 2.0F, -height, depth / 2.0F}, //bottom top right(5)
				{length / 2.0F, -height, -depth / 2.0F}, //bottom bottom right(6)
				{-length / 2.0F, -height, -depth / 2.0F}}; //bottom bottom left (7)
		//top
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[0][0], verteces[0][1], verteces[0][2]);
		GL11.glVertex3f(verteces[1][0], verteces[1][1], verteces[1][2]);
		GL11.glVertex3f(verteces[2][0], verteces[2][1], verteces[2][2]);
		GL11.glVertex3f(verteces[3][0], verteces[3][1], verteces[3][2]);
		GL11.glEnd();
		//front
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[3][0], verteces[3][1], verteces[3][2]);
		GL11.glVertex3f(verteces[2][0], verteces[2][1], verteces[2][2]);
		GL11.glVertex3f(verteces[6][0], verteces[6][1], verteces[6][2]);
		GL11.glVertex3f(verteces[7][0], verteces[7][1], verteces[7][2]);
		GL11.glEnd();
		//left
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[0][0], verteces[0][1], verteces[0][2]);
		GL11.glVertex3f(verteces[3][0], verteces[3][1], verteces[3][2]);
		GL11.glVertex3f(verteces[7][0], verteces[7][1], verteces[7][2]);
		GL11.glVertex3f(verteces[4][0], verteces[4][1], verteces[4][2]);
		GL11.glEnd();
		//right
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[2][0], verteces[2][1], verteces[2][2]);
		GL11.glVertex3f(verteces[1][0], verteces[1][1], verteces[1][2]);
		GL11.glVertex3f(verteces[5][0], verteces[5][1], verteces[5][2]);
		GL11.glVertex3f(verteces[6][0], verteces[6][1], verteces[6][2]);
		GL11.glEnd();
		//back
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[1][0], verteces[1][1], verteces[1][2]);
		GL11.glVertex3f(verteces[0][0], verteces[0][1], verteces[0][2]);
		GL11.glVertex3f(verteces[4][0], verteces[4][1], verteces[4][2]);
		GL11.glVertex3f(verteces[5][0], verteces[5][1], verteces[5][2]);
		GL11.glEnd();
		//bottom
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(verteces[5][0], verteces[5][1], verteces[5][2]);
		GL11.glVertex3f(verteces[4][0], verteces[4][1], verteces[4][2]);
		GL11.glVertex3f(verteces[7][0], verteces[7][1], verteces[7][2]);
		GL11.glVertex3f(verteces[6][0], verteces[6][1], verteces[6][2]);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
