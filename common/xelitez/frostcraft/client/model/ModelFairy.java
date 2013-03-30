package xelitez.frostcraft.client.model;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;

public class ModelFairy extends ModelBase
{
    public ModelRenderer fairyHead;
    public ModelRenderer fairyHeadwear;
    public ModelRenderer fairyBody;
    public ModelRenderer fairyRightArm;
    public ModelRenderer fairyLeftArm;
    public ModelRenderer fairyRightLeg;
    public ModelRenderer fairyLeftLeg;
    public ModelRenderer fairyLeftWingTop;
    public ModelRenderer fairyLeftWingBottom;
    public ModelRenderer fairyRightWingTop;
    public ModelRenderer fairyRightWingBottom;
    
    public int heldItemLeft;
    public int heldItemRight;
    
    private String wingTexture;
    
    public ModelFairy(String wingTexture)
    {
        this(1.0F, wingTexture);
    }

    public ModelFairy(float par1, String wingTexture)
    {
        this(par1, 0.0F, 64, 32, wingTexture);
    }
    
    public ModelFairy(float par1, float par2, int par3, int par4, String wingTexture)
    {
    	this.wingTexture = wingTexture;
        this.textureWidth = par3;
        this.textureHeight = par4;
        this.fairyHead = new ModelRenderer(this, 0, 0);
        this.fairyHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.fairyHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.fairyHeadwear = new ModelRenderer(this, 32, 0);
        this.fairyHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.fairyHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.fairyBody = new ModelRenderer(this, 16, 16);
        this.fairyBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.fairyBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.fairyRightArm = new ModelRenderer(this, 40, 16);
        this.fairyRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.fairyRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.fairyLeftArm = new ModelRenderer(this, 40, 16);
        this.fairyLeftArm.mirror = true;
        this.fairyLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.fairyLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.fairyRightLeg = new ModelRenderer(this, 0, 16);
        this.fairyRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.fairyRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.fairyLeftLeg = new ModelRenderer(this, 0, 16);
        this.fairyLeftLeg.mirror = true;
        this.fairyLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.fairyLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        this.fairyRightWingTop = new ModelRenderer(this, 0, 0);
        this.fairyRightWingTop.addBox(-12.0F, -12.0F, 2.0F, 12, 12, 2, par1);
        this.fairyRightWingTop.setRotationPoint(-1.0F, -11.0F + par2, 0.0F);
        this.fairyRightWingBottom = new ModelRenderer(this, 24, 0);
        this.fairyRightWingBottom.addBox(-8.0F, 0.0F, 1.0F, 8, 8, 1, par1);
        this.fairyRightWingBottom.setRotationPoint(-1.0F, -11.0F + par2, 0.0F);
        this.fairyLeftWingTop = new ModelRenderer(this, 0, 0);
        this.fairyLeftWingTop.mirror = true;
        this.fairyLeftWingTop.addBox(0.0F, -12.0F, 2.0F, 12, 12, 2, par1);
        this.fairyLeftWingTop.setRotationPoint(1.0F, -11.0F + par2, 0.0F);
        this.fairyLeftWingBottom = new ModelRenderer(this, 24, 0);
        this.fairyLeftWingBottom.mirror = true;
        this.fairyLeftWingBottom.addBox(0.0F, 0.0F, 1.0F, 8, 8, 1, par1);
        this.fairyLeftWingBottom.setRotationPoint(1.0F, -11.0F + par2, 0.0F);
    }
    
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.fairyHead.render(par7);
        this.fairyBody.render(par7);
        this.fairyRightArm.render(par7);
        this.fairyLeftArm.render(par7);
        this.fairyRightLeg.render(par7);
        this.fairyLeftLeg.render(par7);
        this.fairyHeadwear.render(par7);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(wingTexture);
        this.fairyRightWingTop.render(par7);
        this.fairyRightWingBottom.render(par7);
        this.fairyLeftWingTop.render(par7);
        this.fairyLeftWingBottom.render(par7);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.fairyHead.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.fairyHead.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.fairyHeadwear.rotateAngleY = this.fairyHead.rotateAngleY;
        this.fairyHeadwear.rotateAngleX = this.fairyHead.rotateAngleX;
        this.fairyRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.fairyLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.fairyRightArm.rotateAngleZ = 0.0F;
        this.fairyLeftArm.rotateAngleZ = 0.0F;
        this.fairyRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.fairyLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.fairyRightLeg.rotateAngleY = 0.0F;
        this.fairyLeftLeg.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.fairyRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.fairyLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.fairyRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.fairyLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.fairyRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.fairyLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.fairyLeftArm.rotateAngleX = this.fairyLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.fairyRightArm.rotateAngleX = this.fairyRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.fairyRightArm.rotateAngleY = 0.0F;
        this.fairyLeftArm.rotateAngleY = 0.0F;
        float var8;
        float var9;

        if (this.onGround > -9990.0F)
        {
            var8 = this.onGround;
            this.fairyBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var8) * (float)Math.PI * 2.0F) * 0.2F;
            this.fairyRightArm.rotationPointZ = MathHelper.sin(this.fairyBody.rotateAngleY) * 5.0F;
            this.fairyRightArm.rotationPointX = -MathHelper.cos(this.fairyBody.rotateAngleY) * 5.0F;
            this.fairyLeftArm.rotationPointZ = -MathHelper.sin(this.fairyBody.rotateAngleY) * 5.0F;
            this.fairyLeftArm.rotationPointX = MathHelper.cos(this.fairyBody.rotateAngleY) * 5.0F;
            this.fairyRightArm.rotateAngleY += this.fairyBody.rotateAngleY;
            this.fairyLeftArm.rotateAngleY += this.fairyBody.rotateAngleY;
            this.fairyLeftArm.rotateAngleX += this.fairyBody.rotateAngleY;
            var8 = 1.0F - this.onGround;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0F - var8;
            var9 = MathHelper.sin(var8 * (float)Math.PI);
            float var10 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.fairyHead.rotateAngleX - 0.7F) * 0.75F;
            this.fairyRightArm.rotateAngleX = (float)((double)this.fairyRightArm.rotateAngleX - ((double)var9 * 1.2D + (double)var10));
            this.fairyRightArm.rotateAngleY += this.fairyBody.rotateAngleY * 2.0F;
            this.fairyRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        this.fairyBody.rotateAngleX = 0.0F;
        this.fairyRightLeg.rotationPointZ = 0.1F;
        this.fairyLeftLeg.rotationPointZ = 0.1F;
        this.fairyRightLeg.rotationPointY = 12.0F;
        this.fairyLeftLeg.rotationPointY = 12.0F;
        this.fairyHead.rotationPointY = 0.0F;
        this.fairyHeadwear.rotationPointY = 0.0F;

        this.fairyRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.fairyLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.fairyRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.fairyLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        
    	int ticks = par7Entity.ticksExisted;
    	int positions = 5;
    	int rotation = (90 / positions) * (ticks % positions);
    }
}
