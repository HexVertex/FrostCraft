package xelitez.frostcraft.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderFrostBow implements IItemRenderer
{

    private static final ResourceLocation effectTexture = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		if(type == ItemRenderType.EQUIPPED) return true;
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if(type == ItemRenderType.EQUIPPED)
		{
	        TextureManager texturemanager = FMLClientHandler.instance().getClient().getTextureManager();
			if(FMLClientHandler.instance().getClient().gameSettings.thirdPersonView != 0 || !data[1].equals(FMLClientHandler.instance().getClient().thePlayer))
			{
	            GL11.glScalef(1.0f / 0.375f, 1.0f / 0.375f, 1.0f / 0.375f);
	            float f4 = 0.625F;
	            GL11.glTranslatef(0.2f, 0.0f, -0.55f);
	            GL11.glScalef(f4, -f4, f4);
	            GL11.glRotatef(-85f, 0.0f, 1.0f, 0.0f);
	            GL11.glRotatef(-0f, 1.0f, 0.0f, 0.0f);
	            GL11.glRotatef(-45f, 0.0f, 0.0f, 1.0f);
	            Tessellator var5 = Tessellator.instance;
	            EntityLivingBase entity = (EntityLivingBase)data[1];
	            int texture = 0;
	            if(entity instanceof EntityPlayer)
	            {
	            	texture = ((EntityPlayer) entity).getItemInUseDuration();
	            }
	            IIcon icon = entity.getItemIcon(item, texture);
	            float f = icon.getMinU();
	            float f1 = icon.getMaxU();
	            float f2 = icon.getMinV();
	            float f3 = icon.getMaxV();
	            ItemRenderer.renderItemIn2D(var5, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
	            if (item != null && item.hasEffect(texture))
	            {
	                GL11.glDepthFunc(GL11.GL_EQUAL);
	                GL11.glDisable(GL11.GL_LIGHTING);
	                texturemanager.bindTexture(effectTexture);
	                GL11.glEnable(GL11.GL_BLEND);
	                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
	                float var14 = 0.76F;
	                GL11.glColor4f(0.5F * var14, 0.25F * var14, 0.8F * var14, 1.0F);
	                GL11.glMatrixMode(GL11.GL_TEXTURE);
	                GL11.glPushMatrix();
	                float var15 = 0.125F;
	                GL11.glScalef(var15, var15, var15);
	                float var16 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
	                GL11.glTranslatef(var16, 0.0F, 0.0F);
	                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	                GL11.glPopMatrix();
	                GL11.glPushMatrix();
	                GL11.glScalef(var15, var15, var15);
	                var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	                GL11.glTranslatef(-var16, 0.0F, 0.0F);
	                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	                GL11.glPopMatrix();
	                GL11.glMatrixMode(GL11.GL_MODELVIEW);
	                GL11.glDisable(GL11.GL_BLEND);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glDepthFunc(GL11.GL_LEQUAL);
	            }
			}
			else
			{
	            Tessellator tessellator = Tessellator.instance;
	            EntityLivingBase entity = (EntityLivingBase)data[1];
	            int texture = 0;
	            if(entity instanceof EntityPlayer)
	            {
	            	texture = ((EntityPlayer) entity).getItemInUseDuration();
	            }
	            IIcon icon = entity.getItemIcon(item, texture);
	            float f = icon.getMinU();
	            float f1 = icon.getMaxU();
	            float f2 = icon.getMinV();
	            float f3 = icon.getMaxV();
	            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	            ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);

	            if (item != null && item.hasEffect(texture))
	            {
	                GL11.glDepthFunc(GL11.GL_EQUAL);
	                GL11.glDisable(GL11.GL_LIGHTING);
	                texturemanager.bindTexture(effectTexture);
	                GL11.glEnable(GL11.GL_BLEND);
	                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
	                float f7 = 0.76F;
	                GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
	                GL11.glMatrixMode(GL11.GL_TEXTURE);
	                GL11.glPushMatrix();
	                float f8 = 0.125F;
	                GL11.glScalef(f8, f8, f8);
	                float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
	                GL11.glTranslatef(f9, 0.0F, 0.0F);
	                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	                GL11.glPopMatrix();
	                GL11.glPushMatrix();
	                GL11.glScalef(f8, f8, f8);
	                f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	                GL11.glTranslatef(-f9, 0.0F, 0.0F);
	                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
	                GL11.glPopMatrix();
	                GL11.glMatrixMode(GL11.GL_MODELVIEW);
	                GL11.glDisable(GL11.GL_BLEND);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glDepthFunc(GL11.GL_LEQUAL);
	            }

	            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			}
		}
	}

}
