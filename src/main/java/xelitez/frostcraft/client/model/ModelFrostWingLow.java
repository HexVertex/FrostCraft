package xelitez.frostcraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import xelitez.frostcraft.client.model.rotations.EnumAxis;
import xelitez.frostcraft.entity.EntityFrostWing;
import cpw.mods.fml.client.FMLClientHandler;

public class ModelFrostWingLow extends ModelBase
{
	//fields
	ModelRenderer head;
	ModelRenderer body;
    ModelRenderer wingLeft;
    ModelRenderer wingLeftOuter;
    ModelRenderer wingRight;
    ModelRenderer wingRightOuter;
    ModelRenderer tailPiece;
    ModelRenderer headExtra;
    ModelRenderer leftLeg;
    ModelRenderer rightLeg;
    ModelRenderer leftFoot;
    ModelRenderer rightFoot;
    ModelRenderer beak;
    
    private Minecraft mc = FMLClientHandler.instance().getClient();
  
    public ModelFrostWingLow()
    {
    	textureWidth = 128;
    	textureHeight = 128;
    		
    	head = new ModelRenderer(this, 0, 0);
    	head.addBox(-4F, -8F, -6F, 8, 8, 8);
    	head.setRotationPoint(0F, 9.5F, -1F);
      	headExtra = new ModelRenderer(this, 0, 16);
      	headExtra.addBox(-4F, -8F, -6F, 8, 8, 11, 0.5F);
      	headExtra.setRotationPoint(0F, 0F, 0F);
    	body = new ModelRenderer(this, 32, 0);
    	body.addBox(-3.5F, -3F, -3F, 7, 8, 14);
    	body.setRotationPoint(0F, 11.5F, 0F);
 	   	wingLeft = new ModelRenderer(this, 0, 45);
 	   	wingLeft.addBox(-1F, 0F, 0F, 2, 7, 7);
 	   	wingLeft.setRotationPoint(3.5F, -2F, -1F);
 	   	wingLeft.mirror = true;
 	   	wingLeftOuter = new ModelRenderer(this, 30, 22);
 	   	wingLeftOuter.addBox(-0.5F, -5F, 0F, 1, 5, 13);
 	   	wingLeftOuter.setRotationPoint(0F, 6.5F, 0.5F);
 	   	wingLeftOuter.mirror = true;
 	   	wingRight = new ModelRenderer(this, 0, 45);
 	   	wingRight.addBox(-1F, 0F, 0F, 2, 7, 7);
      	wingRight.setRotationPoint(-3.5F, -2F, -1F);
      	wingRightOuter = new ModelRenderer(this, 30, 22);
      	wingRightOuter.addBox(-0.5F, -5F, 0F, 1, 5, 13);
      	wingRightOuter.setRotationPoint(0F, 6.5F, 0.5F);
      	tailPiece = new ModelRenderer(this, 0, 35);
      	tailPiece.addBox(-5F, 0F, 0F, 10, 0, 10);
      	tailPiece.setRotationPoint(0F, -2F, 11F);
      	leftLeg = new ModelRenderer(this, 32, 0);
      	leftLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      	leftLeg.setRotationPoint(1.5F, 5F, 7F);
      	leftLeg.mirror = true;
      	rightLeg = new ModelRenderer(this, 32, 0);
      	rightLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      	rightLeg.setRotationPoint(-1.5F, 5F, 7F);
      	leftFoot = new ModelRenderer(this, 28, 5);
      	leftFoot.addBox(-1.5F, 0F, -2.5F, 3, 0, 4);
      	leftFoot.setRotationPoint(0.5F, 4F, 0.5F);
      	leftFoot.mirror = true;
      	rightFoot = new ModelRenderer(this, 28, 5);
      	rightFoot.addBox(-1.5F, 0F, -2.5F, 3, 0, 4);
      	rightFoot.setRotationPoint(-0.5F, 4F, 0.5F);
      	beak = new ModelRenderer(this, 11, 45);
      	beak.addBox(-1F, -1F, 0F, 2, 2, 4);
      	beak.setRotationPoint(0F, -2F, -8F);
      	head.addChild(this.beak);
      	head.addChild(this.headExtra);
      	body.addChild(this.wingLeft);
      	body.addChild(this.wingRight);
      	body.addChild(this.leftLeg);
      	body.addChild(this.rightLeg);
      	body.addChild(this.tailPiece);
      	wingLeft.addChild(this.wingLeftOuter);
      	wingRight.addChild(this.wingRightOuter);
      	leftLeg.addChild(this.leftFoot);
      	rightLeg.addChild(this.rightFoot);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, f2, f3, f4, f5);
    	boolean pause = mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
    	if(!pause)
    	{
    		setRotationAngles(f, f1, f2, f3, f4, f5, (EntityFrostWing)entity);
    	}
    	body.render(f5);
    	head.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityFrostWing entity)
    {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	body.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("body").getRotation(EnumAxis.X);
    	body.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("body").getRotation(EnumAxis.Y);
    	body.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("body").getRotation(EnumAxis.Z);
    	head.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("head").getRotation(EnumAxis.X) + (f4 / (180F / (float)Math.PI));
    	head.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("head").getRotation(EnumAxis.Y) + (f3 / (180F / (float)Math.PI));
    	head.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("head").getRotation(EnumAxis.Z);
    	wingLeft.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("wingLeft").getRotation(EnumAxis.X);
    	wingLeft.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("wingLeft").getRotation(EnumAxis.Y);
    	wingLeft.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("wingLeft").getRotation(EnumAxis.Z);
    	wingRight.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("wingRight").getRotation(EnumAxis.X);
    	wingRight.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("wingRight").getRotation(EnumAxis.Y);
    	wingRight.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("wingRight").getRotation(EnumAxis.Z);
    	wingLeftOuter.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("wingLeftOuter").getRotation(EnumAxis.X);
    	wingLeftOuter.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("wingLeftOuter").getRotation(EnumAxis.Y);
    	wingLeftOuter.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("wingLeftOuter").getRotation(EnumAxis.Z);
    	wingRightOuter.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("wingRightOuter").getRotation(EnumAxis.X);
    	wingRightOuter.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("wingRightOuter").getRotation(EnumAxis.Y);
    	wingRightOuter.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("wingRightOuter").getRotation(EnumAxis.Z);
    	tailPiece.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("tailPiece").getRotation(EnumAxis.X);
    	tailPiece.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("tailPiece").getRotation(EnumAxis.Y);
    	tailPiece.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("tailPiece").getRotation(EnumAxis.Z);
    	leftLeg.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("leftLeg").getRotation(EnumAxis.X) + (entity.isFlying ? 0.0F : MathHelper.cos(f * 0.6662F) * 0.8F * f1);
    	leftLeg.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("leftLeg").getRotation(EnumAxis.Y);
    	leftLeg.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("leftLeg").getRotation(EnumAxis.Z);
    	rightLeg.rotateAngleX = entity.rotationHelper.getEntryList().getEntry("rightLeg").getRotation(EnumAxis.X) + (entity.isFlying ? 0.0F : MathHelper.cos(f * 0.6662F + (float)Math.PI) * 0.8F * f1);
    	rightLeg.rotateAngleY = entity.rotationHelper.getEntryList().getEntry("rightLeg").getRotation(EnumAxis.Y);
    	rightLeg.rotateAngleZ = entity.rotationHelper.getEntryList().getEntry("rightLeg").getRotation(EnumAxis.Z);
    	checkedFinishedRotations(entity);  	
    }
    
    private void checkedFinishedRotations(EntityFrostWing entity)
    {
    	for(String string: entity.rotationHelper.getEntryList().getAllKeys())
    	{
    		for(EnumAxis axis : EnumAxis.values())
    		{
    			if(entity.rotationHelper.getEntryList().getEntry(string).isDoneRotating(axis))
    			{
    				entity.onRotationFinish(string, axis);
    			}
    		}
    	}
    }

}
