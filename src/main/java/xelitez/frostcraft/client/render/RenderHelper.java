package xelitez.frostcraft.client.render;

import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderHelper 
{
	public static void renderCubic(float length, float height, float depth, boolean flag)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
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
		GL11.glPopMatrix();
	}
	
	public static void renderCylinder(float radius, float height, int segments, boolean flag)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		if(!flag)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		float[][] vertecesTop = new float[segments][3];
		float[][] vertecesBottom = new float[segments][3];
		for(int i = 0;i < segments;i++)
		{
			vertecesTop[i][0] = (float)Math.cos(2.0F * Math.PI / (float)segments * (float)i) * radius;
			vertecesTop[i][1] = 0.0F;
			vertecesTop[i][2] = (float)Math.sin(2.0F * Math.PI / (float)segments * (float)i) * radius;
			vertecesBottom[i][0] = (float)Math.cos(2.0F * Math.PI / (float)segments * (float)i) * radius;
			vertecesBottom[i][1] = -height;
			vertecesBottom[i][2] = (float)Math.sin(2.0F * Math.PI / (float)segments * (float)i) * radius;
		}
		GL11.glBegin(GL11.GL_POLYGON);
		for(int i = 0;i < segments;i++)
		{
			GL11.glVertex3f(vertecesTop[segments - 1 - i][0], vertecesTop[segments - 1 - i][1], vertecesTop[segments - 1 - i][2]);
		}
		GL11.glEnd();
		GL11.glBegin(GL11.GL_POLYGON);
		for(int i = 0;i < segments;i++)
		{
			GL11.glVertex3f(vertecesBottom[i][0], vertecesBottom[i][1], vertecesBottom[i][2]);
		}
		GL11.glEnd();
		for(int i = 0;i < segments;i++)
		{
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(vertecesTop[i][0], vertecesTop[i][1], vertecesTop[i][2]);
			GL11.glVertex3f(vertecesTop[(i + 1) % segments][0], vertecesTop[(i + 1) % segments][1], vertecesTop[(i + 1) % segments][2]);
			GL11.glVertex3f(vertecesBottom[(i + 1) % segments][0], vertecesBottom[(i + 1) % segments][1], vertecesBottom[(i + 1) % segments][2]);
			GL11.glVertex3f(vertecesBottom[i][0], vertecesBottom[i][1], vertecesBottom[i][2]);
			GL11.glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	public static void renderCone(float radius, float height, int segments, boolean flag)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		if(!flag)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		float[][] verteces = new float[segments][3];
		float[] topVertex = new float[] {0.0F, 0.0F, 0.0F};
		for(int i = 0;i < segments;i++)
		{
			verteces[i][0] = (float)Math.cos(2.0F * Math.PI / (float)segments * (float)i) * radius;
			verteces[i][1] = -height;
			verteces[i][2] = (float)Math.sin(2.0F * Math.PI / (float)segments * (float)i) * radius;
		}
		GL11.glBegin(GL11.GL_POLYGON);
		for(int i = 0;i < segments;i++)
		{
			GL11.glVertex3f(verteces[i][0], verteces[i][1], verteces[i][2]);
		}
		GL11.glEnd();
		for(int i = 0;i < segments;i++)
		{
			GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glVertex3f(verteces[i][0], verteces[i][1], verteces[i][2]);
			GL11.glVertex3f(topVertex[0], topVertex[1], topVertex[2]);
			GL11.glVertex3f(verteces[(i + 1) % segments][0], verteces[(i + 1) % segments][1], verteces[(i + 1) % segments][2]);
			GL11.glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	public static void renderTriangle(float angle, float length, float height, float depth, boolean flag)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		if(!flag)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		float[][] verteces = new float[6][3];
		verteces[0][0] = 0.0F;
		verteces[0][1] = 0.0F;
		verteces[0][2] = 0.0F;
		verteces[1][0] = (float)MathHelper.cos(angle / 180.0F * (float)Math.PI) * depth;
		verteces[1][1] = 0.0F;
		verteces[1][2] = (float)MathHelper.sin(angle / 180.0F * (float)Math.PI) * depth;
		verteces[2][0] = length;
		verteces[2][1] = 0.0F;
		verteces[2][2] = 0.0F;
		verteces[3][0] = 0.0F;
		verteces[3][1] = -height;
		verteces[3][2] = 0.0F;
		verteces[4][0] = (float)MathHelper.cos(angle / 180.0F * (float)Math.PI) * depth;
		verteces[4][1] = -height;
		verteces[4][2] = (float)MathHelper.sin(angle / 180.0F * (float)Math.PI) * depth;
		verteces[5][0] = length;
		verteces[5][1] = -height;
		verteces[5][2] = 0.0F;
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glVertex3f(verteces[0][0], verteces[0][1], verteces[0][2]);
		GL11.glVertex3f(verteces[1][0], verteces[1][1], verteces[1][2]);
		GL11.glVertex3f(verteces[2][0], verteces[2][1], verteces[2][2]);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glVertex3f(verteces[5][0], verteces[5][1], verteces[5][2]);
		GL11.glVertex3f(verteces[4][0], verteces[4][1], verteces[4][2]);
		GL11.glVertex3f(verteces[3][0], verteces[3][1], verteces[3][2]);
		GL11.glEnd();
		for(int i = 0;i < 3;i++)
		{
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(verteces[i][0], verteces[i][1], verteces[i][2]);
			GL11.glVertex3f(verteces[(i + 1) % 3][0], verteces[(i + 1) % 3][1], verteces[(i + 1) % 3][2]);
			GL11.glVertex3f(verteces[(i + 1) % 3 + 3][0], verteces[(i + 1) % 3 + 3][1], verteces[(i + 1) % 3 + 3][2]);
			GL11.glVertex3f(verteces[i + 3][0], verteces[i + 3][1], verteces[i + 3][2]);
			GL11.glEnd();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
}
