package xelitez.frostcraft.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import xelitez.frostcraft.entity.EntityFrostGuard;

public class RenderFrostGuard extends RenderBiped
{
	public ResourceLocation texture = new ResourceLocation("/texture.png");
	
	
	public RenderFrostGuard(ModelBase par1ModelBase, float par2) 
	{
		super((ModelBiped) par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return texture;
	}

    protected void renderEquippedItems(EntityFrostGuard par1EntityFrostGuard, float par2)
    {
        super.renderEquippedItems((EntityLiving)par1EntityFrostGuard, par2);
    }
    
    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.renderEquippedItems((EntityFrostGuard)par1EntityLivingBase, par2);
    }
    
    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderEquippedItems((EntityFrostGuard)par1EntityLiving, par2);
    }
}
