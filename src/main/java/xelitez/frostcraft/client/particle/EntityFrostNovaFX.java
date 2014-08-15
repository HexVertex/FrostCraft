package xelitez.frostcraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class EntityFrostNovaFX extends EntityFX
{

	public EntityFrostNovaFX(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.noClip = true;
		this.particleMaxAge = 10;
	}
	
    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
    
    public int getFXLayer()
    {
        return 2;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	GL11.glPushMatrix();
    	GL11.glDisable(GL11.GL_TEXTURE_2D);
    	GL11.glDisable(GL11.GL_CULL_FACE);
    	GL11.glTranslated(posX - interpPosX, posY - interpPosY, posZ - interpPosZ);
    	GL11.glColor4f(0.0F, 200.0F / 255.0F, 1.0F, 0.25F - this.particleAge * 0.25f / this.particleMaxAge);
    	new Sphere().draw((float)this.particleAge * 1.0F, MathHelper.floor_float((float)this.particleAge * 0.3F) + 12, MathHelper.floor_float((float)this.particleAge * 0.3F) + 12);
    	GL11.glEnable(GL11.GL_CULL_FACE);
    	GL11.glEnable(GL11.GL_TEXTURE_2D);
    	GL11.glPopMatrix();
    }

}
