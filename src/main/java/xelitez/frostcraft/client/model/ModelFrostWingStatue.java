package xelitez.frostcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFrostWingStatue extends ModelBase
{
    ModelRenderer UnderBox;
    ModelRenderer MainBox;
    ModelRenderer UpperBox;
    
	ModelRenderer Head;
	ModelRenderer Body;
    ModelRenderer WingLeft;
    ModelRenderer WingLeftOuter;
    ModelRenderer WingRight;
    ModelRenderer WingRightOuter;
    ModelRenderer TailPiece;
    ModelRenderer HeadExtra;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer LeftFoot;
    ModelRenderer RightFoot;
    ModelRenderer Beak;
    
    
  
    public ModelFrostWingStatue()
    {
    	textureWidth = 64;
    	textureHeight = 64;
    		
    	UnderBox = new ModelRenderer(this, 0, 0);
    	UnderBox.addBox(-8F, 0.0F, -8F, 16, 1, 16);
    	UnderBox.setRotationPoint(0F, 23.0F, 0F);	
    	MainBox = new ModelRenderer(this, 0, 17);
    	MainBox.addBox(-7F, 0.0F, -7F, 14, 14, 14);
    	MainBox.setRotationPoint(0F, 9.0F, 0F);
    	UpperBox = new ModelRenderer(this, 0, 0);
    	UpperBox.addBox(-8F, 0.0F, -8F, 16, 1, 16);
    	UpperBox.setRotationPoint(0F, 8.0F, 0F);
    	
    	textureWidth = 128;
    	textureHeight = 128;
    	
    	Head = new ModelRenderer(this, 0, 0);
    	Head.addBox(-4F, -8F, -6F, 8, 8, 8);
    	Head.setRotationPoint(0F, 9.5F, -1F);
      	HeadExtra = new ModelRenderer(this, 0, 16);
      	HeadExtra.addBox(-4F, -8F, -6F, 8, 8, 11, 0.5F);
      	HeadExtra.setRotationPoint(0F, 0F, 0F);
    	Body = new ModelRenderer(this, 32, 0);
    	Body.addBox(-3.5F, -3F, -3F, 7, 8, 14);
    	Body.setRotationPoint(0F, 11.5F, 0F);
    	Body.rotateAngleX = -0.3F * (float)Math.PI;
 	   	WingLeft = new ModelRenderer(this, 0, 45);
 	   	WingLeft.addBox(-1F, 0F, 0F, 2, 7, 7);
 	   	WingLeft.setRotationPoint(3.5F, -2F, -1F);
 	   	WingLeft.mirror = true;
 	   	WingLeftOuter = new ModelRenderer(this, 30, 22);
 	   	WingLeftOuter.addBox(-0.5F, -5F, 0F, 1, 5, 13);
 	   	WingLeftOuter.setRotationPoint(0F, 6.5F, 0.5F);
 	   	WingLeftOuter.mirror = true;
 	   	WingRight = new ModelRenderer(this, 0, 45);
 	   	WingRight.addBox(-1F, 0F, 0F, 2, 7, 7);
      	WingRight.setRotationPoint(-3.5F, -2F, -1F);
      	WingRightOuter = new ModelRenderer(this, 30, 22);
      	WingRightOuter.addBox(-0.5F, -5F, 0F, 1, 5, 13);
      	WingRightOuter.setRotationPoint(0F, 6.5F, 0.5F);
      	TailPiece = new ModelRenderer(this, 0, 35);
      	TailPiece.addBox(-5F, 0F, 0F, 10, 0, 10);
      	TailPiece.setRotationPoint(0F, -2F, 11F);
      	LeftLeg = new ModelRenderer(this, 32, 0);
      	LeftLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      	LeftLeg.setRotationPoint(1.5F, 5F, 7F);
      	LeftLeg.mirror = true;
      	LeftLeg.rotateAngleX = 0.3F * (float)Math.PI;
      	RightLeg = new ModelRenderer(this, 32, 0);
      	RightLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      	RightLeg.setRotationPoint(-1.5F, 5F, 7F);
      	RightLeg.rotateAngleX = 0.3F * (float)Math.PI;
      	LeftFoot = new ModelRenderer(this, 28, 5);
      	LeftFoot.addBox(-1.5F, 0F, -2.5F, 3, 0, 4);
      	LeftFoot.setRotationPoint(0.5F, 4F, 0.5F);
      	LeftFoot.mirror = true;
      	RightFoot = new ModelRenderer(this, 28, 5);
      	RightFoot.addBox(-1.5F, 0F, -2.5F, 3, 0, 4);
      	RightFoot.setRotationPoint(-0.5F, 4F, 0.5F);
      	Beak = new ModelRenderer(this, 11, 45);
      	Beak.addBox(-1F, -1F, 0F, 2, 2, 4);
      	Beak.setRotationPoint(0F, -2F, -8F);
      	Head.addChild(this.Beak);
      	Head.addChild(this.HeadExtra);
      	Body.addChild(this.WingLeft);
      	Body.addChild(this.WingRight);
      	Body.addChild(this.LeftLeg);
      	Body.addChild(this.RightLeg);
      	Body.addChild(this.TailPiece);
      	WingLeft.addChild(this.WingLeftOuter);
      	WingRight.addChild(this.WingRightOuter);
      	LeftLeg.addChild(this.LeftFoot);
      	RightLeg.addChild(this.RightFoot);
    }
    
    public void renderFrostWing(float f)
    {
    	this.Body.render(f);
    	this.Head.render(f);
    }
    
    public void renderStatueBase(float f)
    {
    	this.UnderBox.render(f);
    	this.MainBox.render(f);
    	this.UpperBox.render(f);
    }
    
//    public void RenderAll()
//    {
//    	GL11.glColor3f(84.0F / 255.0F, 84.0F / 255.0F, 84.0F / 255.0F);
//    	this.Body.render(0.0625F);
//    	this.Head.render(0.0625F);
//    	GL11.glColor3f(1.0F, 1.0F, 1.0F);
//    	this.UnderBox.render(0.0625F);
//    	this.MainBox.render(0.0625F);
//    	this.UpperBox.render(0.0625F);
//    }
}
