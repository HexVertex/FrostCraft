package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.item.ItemFrostEnforced;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderFrostTool implements IItemRenderer
{
	
    private static final ResourceLocation effectTexture = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if(type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.ENTITY) return true;
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
		RenderItem renderer = new RenderItem();
		Tessellator tes = Tessellator.instance;
        TextureManager texturemanager = FMLClientHandler.instance().getClient().getTextureManager();
		if(type == ItemRenderType.INVENTORY)
		{
			Icon icon = item.getIconIndex();
			GL11.glTranslatef(0.0F, 0.0F, -renderer.zLevel + renderer.zLevel / 3);
	        renderer.renderIcon(0, 0, icon, 16, 16);
			if(item.getItem() instanceof ItemFrostEnforced)
			{
				Icon overlayicon;
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;
				case SHOVEL:
					overlayicon = ItemFrostEnforced.overlays[1];
					break;
				case PICKAXE:
					overlayicon = ItemFrostEnforced.overlays[2];
					break;
				case AXE:
					overlayicon = ItemFrostEnforced.overlays[3];
					break;
				case HOE:
					overlayicon = ItemFrostEnforced.overlays[4];
					break;
				default:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;			
				}
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
    	        renderer.renderIcon(0, 0, overlayicon, 16, 16);
                GL11.glDisable(GL11.GL_BLEND);
                if (item.hasEffect(0))
                {
                    GL11.glDepthFunc(GL11.GL_GREATER);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glDepthMask(false);
                    texturemanager.bindTexture(effectTexture);
                    renderer.zLevel -= 50.0F;
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_DST_COLOR);
                    GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
                    this.renderGlint(renderer, 431278612 + 32178161, -2, -2, 20, 20);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glDepthMask(true);
                    renderer.zLevel += 50.0F;
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDepthFunc(GL11.GL_LEQUAL);
                }
			}
		}
		if(type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			Icon icon = item.getIconIndex();
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            ItemRenderer.renderItemIn2D(tes, f1, f2, f, f3, icon.getIconWidth(), icon.getIconWidth(), 0.0625F);
            if (item != null && item.hasEffect(0))
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
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }
			if(item.getItem() instanceof ItemFrostEnforced)
			{
	            FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TextureMap.locationItemsTexture);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				Icon overlayicon;
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;
				case SHOVEL:
					overlayicon = ItemFrostEnforced.overlays[1];
					break;
				case PICKAXE:
					overlayicon = ItemFrostEnforced.overlays[2];
					break;
				case AXE:
					overlayicon = ItemFrostEnforced.overlays[3];
					break;
				case HOE:
					overlayicon = ItemFrostEnforced.overlays[4];
					break;
				default:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;			
				}
	            f = overlayicon.getMinU();
	            f1 = overlayicon.getMaxU();
	            f2 = overlayicon.getMinV();
	            f3 = overlayicon.getMaxV();
	            GL11.glTranslatef(0.0F, 0.0F, 0.015625F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
                ItemRenderer.renderItemIn2D(tes, f1, f2, f, f3, overlayicon.getIconWidth(), overlayicon.getIconHeight(), 0.09375F);
                GL11.glDisable(GL11.GL_BLEND);
			}
		}
		if(type == ItemRenderType.ENTITY)
		{
			GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
			Icon icon = item.getIconIndex();
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            ItemRenderer.renderItemIn2D(tes, f1, f2, f, f3, icon.getIconWidth(), icon.getIconWidth(), 0.0625F);
            if (item != null && item.hasEffect(0))
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
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tes, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }
			if(item.getItem() instanceof ItemFrostEnforced)
			{
	            FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TextureMap.locationItemsTexture);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				Icon overlayicon;
				switch(((ItemFrostEnforced)item.getItem()).getRenderType())
				{
				case SWORD:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;
				case SHOVEL:
					overlayicon = ItemFrostEnforced.overlays[1];
					break;
				case PICKAXE:
					overlayicon = ItemFrostEnforced.overlays[2];
					break;
				case AXE:
					overlayicon = ItemFrostEnforced.overlays[3];
					break;
				case HOE:
					overlayicon = ItemFrostEnforced.overlays[4];
					break;
				default:
					overlayicon = ItemFrostEnforced.overlays[0];
					break;			
				}
	            f = overlayicon.getMinU();
	            f1 = overlayicon.getMaxU();
	            f2 = overlayicon.getMinV();
	            f3 = overlayicon.getMaxV();
	            GL11.glTranslatef(0.0F, 0.0F, 0.015625F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
                ItemRenderer.renderItemIn2D(tes, f1, f2, f, f3, overlayicon.getIconWidth(), overlayicon.getIconHeight(), 0.09375F);
                GL11.glDisable(GL11.GL_BLEND);
			}
		}
	}
	
    private void renderGlint(RenderItem renderer, int par1, int par2, int par3, int par4, int par5)
    {
        for (int j1 = 0; j1 < 2; ++j1)
        {
            if (j1 == 0)
            {
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            }

            if (j1 == 1)
            {
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            }

            float f = 0.00390625F;
            float f1 = 0.00390625F;
            float f2 = (float)(Minecraft.getSystemTime() % (long)(3000 + j1 * 1873)) / (3000.0F + (float)(j1 * 1873)) * 256.0F;
            float f3 = 0.0F;
            Tessellator tessellator = Tessellator.instance;
            float f4 = 4.0F;

            if (j1 == 1)
            {
                f4 = -1.0F;
            }

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(par2 + 0), (double)(par3 + par5), (double)renderer.zLevel, (double)((f2 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
            tessellator.addVertexWithUV((double)(par2 + par4), (double)(par3 + par5), (double)renderer.zLevel, (double)((f2 + (float)par4 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
            tessellator.addVertexWithUV((double)(par2 + par4), (double)(par3 + 0), (double)renderer.zLevel, (double)((f2 + (float)par4) * f), (double)((f3 + 0.0F) * f1));
            tessellator.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), (double)renderer.zLevel, (double)((f2 + 0.0F) * f), (double)((f3 + 0.0F) * f1));
            tessellator.draw();
        }
    }

}
