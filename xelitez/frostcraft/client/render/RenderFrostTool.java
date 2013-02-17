package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.item.ItemFrostEnforced;
import xelitez.frostcraft.enums.EnumRenderType;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class RenderFrostTool implements IItemRenderer{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if(type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY) return true;
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) 
	{
		if(type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION)) return true;
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		RenderItem renderer= new RenderItem();
		Tessellator tes = Tessellator.instance;
		if(type == ItemRenderType.INVENTORY)
		{
			ForgeHooksClient.bindTexture(item.getItem().getTextureFile(), 0);
			int index = item.getIconIndex();
			renderer.renderTexturedQuad(0, 0, index % 16 * 16, index / 16 * 16, 16, 16);
			if(item.getItem() instanceof ItemFrostEnforced)
			{
				ForgeHooksClient.bindTexture("/xelitez/frostcraft/textures/Items_0.png", 0);
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					index = 6;
					break;
				case SHOVEL:
					index = 22;
					break;
				case PICKAXE:
					index = 38;
					break;
				case AXE:
					index = 54;
					break;
				case HOE:
					index = 70;
					break;
				default:
					index = 6;
					break;			
				}
				renderer.renderTexturedQuad(0, 0, index % 16 * 16, index / 16 * 16, 16, 16);
			}
		}
		if(type == ItemRenderType.EQUIPPED)
		{
			ForgeHooksClient.bindTexture(item.getItem().getTextureFile(), 0);
			int index = item.getIconIndex();
            float var7 = ((float)(index % 16 * 16) + 0.0F) / 256.0F;
            float var8 = ((float)(index % 16 * 16) + 15.99F) / 256.0F;
            float var9 = ((float)(index / 16 * 16) + 0.0F) / 256.0F;
            float var10 = ((float)(index / 16 * 16) + 15.99F) / 256.0F;
			ItemRenderer.renderItemIn2D(tes, var8, var9, var7, var10, 0.0625F);
            if (item != null && item.hasEffect())
            {
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDisable(GL11.GL_LIGHTING);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(FMLClientHandler.instance().getClient().renderEngine.getTexture("%blur%/misc/glint.png"));
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
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(var15, var15, var15);
                var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-var16, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }
			if(item.getItem() instanceof ItemFrostEnforced)
			{
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				ForgeHooksClient.bindTexture("/xelitez/frostcraft/textures/Items_0.png", 0);
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					index = 6;
					break;
				case SHOVEL:
					index = 22;
					break;
				case PICKAXE:
					index = 38;
					break;
				case AXE:
					index = 54;
					break;
				case HOE:
					index = 70;
					break;
				default:
					index = 6;
					break;			
				}
	            var7 = ((float)(index % 16 * 16) + 0.0F) / 256.0F;
	            var8 = ((float)(index % 16 * 16) + 15.99F) / 256.0F;
	            var9 = ((float)(index / 16 * 16) + 0.0F) / 256.0F;
	            var10 = ((float)(index / 16 * 16) + 15.99F) / 256.0F;
	            GL11.glTranslatef(0.0F, 0.0F, 0.015625F);
				ItemRenderer.renderItemIn2D(tes, var8, var9, var7, var10, 0.09375F);
	            if (item != null && item.hasEffect())
	            {
	                GL11.glDepthFunc(GL11.GL_EQUAL);
	                GL11.glDisable(GL11.GL_LIGHTING);
	                FMLClientHandler.instance().getClient().renderEngine.bindTexture(FMLClientHandler.instance().getClient().renderEngine.getTexture("%blur%/misc/glint.png"));
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
	                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.09375F);
	                GL11.glPopMatrix();
	                GL11.glPushMatrix();
	                GL11.glScalef(var15, var15, var15);
	                var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	                GL11.glTranslatef(-var16, 0.0F, 0.0F);
	                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.09375F);
	                GL11.glPopMatrix();
	                GL11.glMatrixMode(GL11.GL_MODELVIEW);
	                GL11.glDisable(GL11.GL_BLEND);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glDepthFunc(GL11.GL_LEQUAL);
	            }
			}
		}
		if(type == ItemRenderType.ENTITY)
		{
			GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
			ForgeHooksClient.bindTexture(item.getItem().getTextureFile(), 0);
			int index = item.getIconIndex();
	        float var7 = (float)(index % 16 * 16 + 0) / 256.0F;
	        float var8 = (float)(index % 16 * 16 + 16) / 256.0F;
	        float var9 = (float)(index / 16 * 16 + 0) / 256.0F;
	        float var10 = (float)(index / 16 * 16 + 16) / 256.0F;
			ItemRenderer.renderItemIn2D(tes, var8, var9, var7, var10, 0.0625F);
            if (item != null && item.hasEffect())
            {
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDisable(GL11.GL_LIGHTING);
                RenderManager.instance.renderEngine.bindTexture(RenderManager.instance.renderEngine.getTexture("%blur%/misc/glint.png"));
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                float var21 = 0.76F;
                GL11.glColor4f(0.5F * var21, 0.25F * var21, 0.8F * var21, 1.0F);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glPushMatrix();
                float var22 = 0.125F;
                GL11.glScalef(var22, var22, var22);
                float var23 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(var23, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(var22, var22, var22);
                var23 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-var23, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }
			if(item.getItem() instanceof ItemFrostEnforced)
			{
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				ForgeHooksClient.bindTexture("/xelitez/frostcraft/textures/Items_0.png", 0);
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					index = 6;
					break;
				case SHOVEL:
					index = 22;
					break;
				case PICKAXE:
					index = 38;
					break;
				case AXE:
					index = 54;
					break;
				case HOE:
					index = 70;
					break;
				default:
					index = 6;
					break;			
				}
	            var7 = ((float)(index % 16 * 16) + 0.0F) / 256.0F;
	            var8 = ((float)(index % 16 * 16) + 15.99F) / 256.0F;
	            var9 = ((float)(index / 16 * 16) + 0.0F) / 256.0F;
	            var10 = ((float)(index / 16 * 16) + 15.99F) / 256.0F;
	            GL11.glTranslatef(0.0F, 0.0F, 0.015625F);
				ItemRenderer.renderItemIn2D(tes, var8, var9, var7, var10, 0.09375F);
	            if (item != null && item.hasEffect())
	            {
	                GL11.glDepthFunc(GL11.GL_EQUAL);
	                GL11.glDisable(GL11.GL_LIGHTING);
	                RenderManager.instance.renderEngine.bindTexture(RenderManager.instance.renderEngine.getTexture("%blur%/misc/glint.png"));
	                GL11.glEnable(GL11.GL_BLEND);
	                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
	                float var21 = 0.76F;
	                GL11.glColor4f(0.5F * var21, 0.25F * var21, 0.8F * var21, 1.0F);
	                GL11.glMatrixMode(GL11.GL_TEXTURE);
	                GL11.glPushMatrix();
	                float var22 = 0.125F;
	                GL11.glScalef(var22, var22, var22);
	                float var23 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
	                GL11.glTranslatef(var23, 0.0F, 0.0F);
	                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.09375F);
	                GL11.glPopMatrix();
	                GL11.glPushMatrix();
	                GL11.glScalef(var22, var22, var22);
	                var23 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
	                GL11.glTranslatef(-var23, 0.0F, 0.0F);
	                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
	                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 0.09375F);
	                GL11.glPopMatrix();
	                GL11.glMatrixMode(GL11.GL_MODELVIEW);
	                GL11.glDisable(GL11.GL_BLEND);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glDepthFunc(GL11.GL_LEQUAL);
	            }
			}
		}
	}

}
