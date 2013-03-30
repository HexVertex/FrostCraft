package xelitez.frostcraft.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import xelitez.frostcraft.entity.EntityFrostShot;
import xelitez.frostcraft.registry.IdMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderFrostShot extends Render
{
    private float field_77002_a;

    public RenderFrostShot(float par1)
    {
        this.field_77002_a = par1;
    }

    public void doRenderFrostShot(EntityFrostShot par1EntityFrostShot, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4 - 0.15f, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float var10 = this.field_77002_a;
        GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
        Icon icon = IdMap.itemCraftingItems.getIconFromDamage(0);
        this.loadTexture("/gui/items.png");
        Tessellator var12 = Tessellator.instance;
        float f3 = icon.getMinU();
        float f4 = icon.getMaxU();
        float f5 = icon.getMinV();
        float f6 = icon.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        var12.startDrawingQuads();
        var12.setNormal(0.0F, 1.0F, 0.0F);
        var12.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, (double)f3, (double)f6);
        var12.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, (double)f4, (double)f6);
        var12.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, (double)f4, (double)f5);
        var12.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, (double)f3, (double)f5);
        var12.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderFrostShot((EntityFrostShot)par1Entity, par2, par4, par6, par8, par9);
    }
}
