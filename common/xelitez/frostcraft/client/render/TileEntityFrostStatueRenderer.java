package xelitez.frostcraft.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import xelitez.frostcraft.client.model.ModelFrostWingStatue;

public class TileEntityFrostStatueRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation frostWingTexture = new ResourceLocation("frostcraft:textures/entities/frostwing.png");
	private static final ResourceLocation statueBaseTexture = new ResourceLocation("frostcraft:textures/blocks/statue.png");
	
	private ModelFrostWingStatue model = new ModelFrostWingStatue();
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) 
	{
		int i = 0;
		if(tileentity.hasWorldObj())
		{
			i = tileentity.getBlockMetadata();
		}
		if(i < 4)
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef((float)d0, (float)d1, (float)d2);
			short short1 = 0;
			if (i == 0)
			{
				short1 = 180;
				GL11.glTranslatef(0.0F, 0.0F, 1.0F);
			}

			if (i == 2)
			{
				short1 = 0;
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if (i == 1)
			{
				short1 = 90;
			}

			if (i == 3)
			{
				short1 = -90;
				GL11.glTranslatef(1.0F, 0.0F, 1.0F);
			}

			GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
			this.bindTexture(statueBaseTexture);
			model.renderStatueBase(0.0625F);
			GL11.glTranslatef(0.0F, -1.383F, 0.0F);
			GL11.glScalef(1.25F, 1.25F, 1.25F);
			this.bindTexture(frostWingTexture);
			model.renderFrostWing(0.0625F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
		}
	}

}
