package xelitez.frostcraft.client.particle;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.entity.EntityFrostWing;

public class EntityTrailFX extends EntityFX
{
	private List<double[]> verteces = new ArrayList<double[]>();
	EntityFrostWing entity;
	private boolean done = false;
	
	public EntityTrailFX(World par1World, double par2, double par4,
			double par6) 
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.noClip = true;
		this.particleMaxAge = 4000;
		this.entity = (EntityFrostWing)par1World.findNearestEntityWithinAABB(EntityFrostWing.class, this.boundingBox.expand(5.0D, 5.0D, 5.0D), this);
	}
	
    public void onUpdate()
    {
    	if(entity == null)
    	{
    		this.setDead();
    	}
    	for(int i = 0;i < verteces.size();i += 2)
    	{
    		verteces.set(i, new double[] {verteces.get(i)[0] < 0.0D ? verteces.get(i)[0] + 0.075D : 0.0D, verteces.get(i)[1], verteces.get(i)[2]});
    		verteces.set(i + 1, new double[] {verteces.get(i + 1)[0] > 0.0D ? verteces.get(i + 1)[0] - 0.075D : 0.0D, verteces.get(i + 1)[1], verteces.get(i + 1)[2]});
    	}
    	if(entity.isFlying && !done)
    	{	
    		verteces.add(new double[] {-0.9D, entity.posY + 2.5D, 0.0D});
    		verteces.add(new double[] {0.9D, entity.posY + 2.5D, 0.0D});
    		this.particleAge = 0;
    	}
    	if(!entity.isFlying)
    	{
    		done = true;
    	}
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
    	par1Tessellator.setBrightness(1);
    	GL11.glTranslated(posX - interpPosX, verteces.get(0)[1] - interpPosY, posZ - interpPosZ);
    	GL11.glColor4f(0.0F, 200.0F / 255.0F, 1.0F, 0.75F);
        GL11.glRotatef(180.0F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
        if(verteces.size() >= 4)
        {
        	for(int i = 0;i < verteces.size() - 2;i += 2)
        	{
        		GL11.glBegin(GL11.GL_QUADS);
        		GL11.glVertex3d(this.verteces.get(i + 1)[0], this.verteces.get(i + 1)[1] - this.verteces.get(0)[1], this.verteces.get(i + 1)[2]);
        		GL11.glVertex3d(this.verteces.get(i)[0], this.verteces.get(i)[1] - this.verteces.get(0)[1], this.verteces.get(i)[2]);
        		GL11.glVertex3d(this.verteces.get(i + 2)[0], this.verteces.get(i + 2)[1] - this.verteces.get(0)[1], this.verteces.get(i + 3)[2]);
        		GL11.glVertex3d(this.verteces.get(i + 3)[0], this.verteces.get(i + 3)[1] - this.verteces.get(0)[1], this.verteces.get(i + 2)[2]);
        		GL11.glEnd();
        	}
        }
    	GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
	
}
