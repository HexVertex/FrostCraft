package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.entity.EntityFairy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityGiantZombie;

public class RenderFairy extends RenderLiving
{
    private float scale;

    public RenderFairy(ModelBase par1ModelBase, float par2, float par3)
    {
        super(par1ModelBase, par2 * par3);
        this.scale = par3;
    }
    
    protected void preRenderScale(EntityFairy par1EntityFairy, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderScale((EntityFairy)par1EntityLiving, par2);
    }
}
