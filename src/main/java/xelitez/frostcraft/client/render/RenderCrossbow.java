package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

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
		Tessellator tes = Tessellator.instance;
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			//positioning
			GL11.glRotatef(12.5F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(16.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(12.5F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.425F, 0.2F, 0.25F);
			//color
			GL11.glColor3f(110.0F / 255.0F, 60.0F / 255.0F, 0.0F);
			//stick
			RenderHelper.renderCubic(tes, 0.2F, 0.15F, 1.5F, false);
			//bow
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 0.0F, 0.75F);
			RenderHelper.renderCubic(tes, 0.4F, 0.15F, 0.1F, false);
			//bowleft
			//test: System.currentTimeMillis() / 10.0F % 360.0F
			GL11.glPushMatrix();
			GL11.glTranslatef(0.2F, -0.075F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
			GL11.glColor3f(110.0F / 255.0F, 80.0F / 255.0F, 0.0F);
			GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//			GL11.glRotatef(System.currentTimeMillis() / 10 % 360, 0.0F, 0.0F, 0.0F);
			RenderHelper.renderCubic(tes, 0.145F, 0.2F, 0.08F, false);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			//deco
			GL11.glPushMatrix();
			GL11.glColor3f(150.0F / 255.0F, 150.0F / 255.0F, 150.0F / 255.0F);
			GL11.glTranslatef(0.0F, -0.02F, 0.85F);
			RenderHelper.renderCubic(tes, 0.25F, 0.05F, 0.1F, false);
			GL11.glPopMatrix();
		}
		
	}

}
