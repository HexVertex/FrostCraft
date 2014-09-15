package xelitez.frostcraft.client.render;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderCrossbow implements IItemRenderer
{
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) return true;
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			NBTTagCompound tag = item.getTagCompound();
			if(tag == null)
			{
				tag = new NBTTagCompound();
			}
			boolean loaded = tag.hasKey("loaded") ? tag.getBoolean("loaded") : false;
			if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			{
				GL11.glTranslatef(0.90F, 0.2F, 0.50F);
				GL11.glRotatef(100.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
			}
			//positioning
			else
			{
				GL11.glRotatef(12.5F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(16.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(12.5F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.425F, 0.2F, 0.25F);
			}
			net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
			//color
			GL11.glColor3f(110.0F / 255.0F, 60.0F / 255.0F, 0.0F);
			//stick
			RenderHelper.renderCubic(0.2F, 0.15F, 1.5F, false);
			//bow
			float partRotation = loaded ? 22.5F : 17.5F;
			float stringRotation = loaded ? 46.0F : 110.0F;
			float stringLength = loaded ? 0.735F : 0.689F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.025F, 0.75F);
			RenderHelper.renderCubic(0.4F, 0.15F, 0.1F, false);
			//bowleft
			//test: System.currentTimeMillis() / 10.0F % 360.0F
			GL11.glPushMatrix();
			GL11.glTranslatef(0.1875F, -0.075F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
//			GL11.glRotatef(System.currentTimeMillis() / 10 % 360, 0.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.05F, -0.15F, -0.04F);
			GL11.glRotatef(stringRotation, 1.0F, 0.0F, 0.0F);
			GL11.glColor3f(200.0F / 255.0F, 200.0F / 255.0F, 200.0F / 255.0F);
			RenderHelper.renderCylinder(0.01F, stringLength, 16, false);
			GL11.glColor3f(110.0F / 255.0F, 60.0F / 255.0F, 0.0F);
			GL11.glPopMatrix();
			//bowright
			GL11.glPushMatrix();
			GL11.glTranslatef(-0.1875F, -0.075F, 0.0F);
			GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(0.0F, -0.1875F, 0.0F);
			GL11.glRotatef(partRotation, 1.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(0.145F, 0.2F, 0.08F, false);
			GL11.glTranslatef(-0.05F, -0.15F, -0.04F);
			GL11.glRotatef(stringRotation, 1.0F, 0.0F, 0.0F);
			GL11.glColor3f(200.0F / 255.0F, 200.0F / 255.0F, 200.0F / 255.0F);
			RenderHelper.renderCylinder(0.01F, stringLength, 16, false);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			//deco
			GL11.glPushMatrix();
			GL11.glColor3f(125.0F / 255.0F, 125.0F / 255.0F, 125.0F / 255.0F);
			GL11.glTranslatef(0.0F, -0.02F, 0.85F);
			RenderHelper.renderCubic(0.25F, 0.05F, 0.1F, false);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.025F, 0.25F, 0.015F);
			RenderHelper.renderCylinder(0.0075F, 1.0F, 12, false);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, -0.0075F);
			RenderHelper.renderCubic(0.0075F, 1.0F, 0.015F, false);
			GL11.glPopMatrix();
			GL11.glTranslatef(-0.05F, 0.0F, 0.0F);
			RenderHelper.renderCylinder(0.0075F, 1.0F, 12, false);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, -0.0075F);
			RenderHelper.renderCubic(0.0075F, 1.0F, 0.015F, false);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.0F, 0.025F, -0.25F);
			RenderHelper.renderTriangle(90.0F, 0.03F, 0.05F, 0.05F, true);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.075F, 0.10001F, -0.65F);
			GL11.glColor3f(0.0F, 1.0F, 1.0F);
			RenderHelper.renderCylinder(0.03F, 0.0F, 12, false);
			GL11.glTranslatef(0.0F, -0.20002F, 0.0F);
			RenderHelper.renderCylinder(0.03F, 0.0F, 12, false);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.15F, 0.1F, -0.1F);
			GL11.glColor3f(110.0F / 255.0F, 60.0F / 255.0F, 0.0F);
			RenderHelper.renderTriangle(90.0F, 0.4F, 0.2F, 0.4F, false);
			GL11.glPopMatrix();
			//ammo
			if(loaded)
			{
				GL11.glPushMatrix();
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.0F, 0.15F, -0.025F);
				GL11.glColor3f(150.0F / 255.0F, 150.0F / 255.0F, 150.0F / 255.0F);
				RenderHelper.renderCylinder(0.01F, 0.375F, 12, false);
				GL11.glTranslatef(0.0F, 0.125F, 0.0F);
				GL11.glColor3f(175.0F / 255.0F, 175.0F / 255.0F, 175.0F / 255.0F);
				RenderHelper.renderCone(0.020F, 0.125F, 12, false);
				GL11.glTranslatef(0.0025F, -0.4F, 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glColor3f(125.0F / 255.0F, 0.0F / 255.0F, 0.0F / 255.0F);
				RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, false);
				GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
				RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, false);
				GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
				RenderHelper.renderTriangle(-20.0F, 0.075F, 0.005F, 0.1F, false);
				GL11.glPopMatrix();
			}
		}
		
	}

}
