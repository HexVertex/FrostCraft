package xelitez.frostcraft.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.entity.EntityFrostWing;

public class RenderFrostWing extends RenderLiving
{
    private float scale;
	
    private static final ResourceLocation texture = new ResourceLocation("frostcraft:textures/entities/frostwing.png");

	public RenderFrostWing(ModelBase par1ModelBase, float par2, float par3) 
	{
        super(par1ModelBase, (par2 * par3) / 2);
        this.scale = par3;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return texture;
	}
	
    protected void preRenderScale(EntityFrostWing par1EntityFrostWing, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((EntityFrostWing)par1EntityLivingBase, par2);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.setBossStatus((EntityFrostWing)par1Entity, true);
        super.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }

}
