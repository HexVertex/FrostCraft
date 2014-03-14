package xelitez.frostcraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import xelitez.frostcraft.entity.EntityFrostWing;
import cpw.mods.fml.client.FMLClientHandler;

public class ModelFrostWingLow extends ModelBase
{
	//fields
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
    
    private float headTempX = 0.0F;
    private float headTempY = 0.0F;
    private float leftLegTempX = 0.0F;
    private float rightLegTempX = 0.0F;
    
    private Minecraft mc = FMLClientHandler.instance().getClient();
  
    public ModelFrostWingLow()
    {
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
      	RightLeg = new ModelRenderer(this, 32, 0);
      	RightLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      	RightLeg.setRotationPoint(-1.5F, 5F, 7F);
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

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, f2, f3, f4, f5);
    	boolean pause = mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
    	if(!pause)
    	{
    		setRotationAngles(f, f1, f2, f3, f4, f5, (EntityFrostWing)entity);
    	}
    	Body.render(f5);
    	Head.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityFrostWing entity)
    {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	this.syncList(entity);
    	Body.rotateAngleX = this.getFrostWingRotation(0, Body.rotateAngleX, entity);
    	Body.rotateAngleY = this.getFrostWingRotation(1, Body.rotateAngleY, entity);
    	Body.rotateAngleZ = this.getFrostWingRotation(2, Body.rotateAngleZ, entity);
    	this.headTempX = this.getFrostWingRotation(3, this.headTempX, entity);
    	this.headTempY = this.getFrostWingRotation(4, this.headTempY, entity);
    	Head.rotateAngleX = this.headTempX + (f4 / (180F / (float)Math.PI));
    	Head.rotateAngleY = this.headTempY + (f3 / (180F / (float)Math.PI));
    	Head.rotateAngleZ = this.getFrostWingRotation(5, Head.rotateAngleZ, entity);
    	WingLeft.rotateAngleX = this.getFrostWingRotation(6, WingLeft.rotateAngleX, entity);
    	WingLeft.rotateAngleY = this.getFrostWingRotation(7, WingLeft.rotateAngleY, entity);
    	WingLeft.rotateAngleZ = this.getFrostWingRotation(8, WingLeft.rotateAngleZ, entity);
    	WingRight.rotateAngleX = this.getFrostWingRotation(9, WingRight.rotateAngleX, entity);
    	WingRight.rotateAngleY = this.getFrostWingRotation(10, WingRight.rotateAngleY, entity);
    	WingRight.rotateAngleZ = this.getFrostWingRotation(11, WingRight.rotateAngleZ, entity);
    	WingLeftOuter.rotateAngleX = this.getFrostWingRotation(12, WingLeftOuter.rotateAngleX, entity);
    	WingLeftOuter.rotateAngleY = this.getFrostWingRotation(13, WingLeftOuter.rotateAngleY, entity);
    	WingLeftOuter.rotateAngleZ = this.getFrostWingRotation(14, WingLeftOuter.rotateAngleZ, entity);
    	WingRightOuter.rotateAngleX = this.getFrostWingRotation(15, WingRightOuter.rotateAngleX, entity);
    	WingRightOuter.rotateAngleY = this.getFrostWingRotation(16, WingRightOuter.rotateAngleY, entity);
    	WingRightOuter.rotateAngleZ = this.getFrostWingRotation(17, WingRightOuter.rotateAngleZ, entity);
    	TailPiece.rotateAngleX = this.getFrostWingRotation(18, TailPiece.rotateAngleX, entity);
    	TailPiece.rotateAngleY = this.getFrostWingRotation(19, TailPiece.rotateAngleY, entity);
    	TailPiece.rotateAngleZ = this.getFrostWingRotation(20, TailPiece.rotateAngleZ, entity);
    	this.leftLegTempX = this.getFrostWingRotation(21, this.leftLegTempX, entity);
    	LeftLeg.rotateAngleX = this.leftLegTempX + (entity.isFlying ? 0.0F : MathHelper.cos(f * 0.6662F) * 0.8F * f1);
    	LeftLeg.rotateAngleY = this.getFrostWingRotation(22, LeftLeg.rotateAngleY, entity);
    	LeftLeg.rotateAngleZ = this.getFrostWingRotation(23, LeftLeg.rotateAngleZ, entity);
    	this.rightLegTempX = this.getFrostWingRotation(24, this.rightLegTempX, entity);
    	RightLeg.rotateAngleX = this.rightLegTempX + (entity.isFlying ? 0.0F : MathHelper.cos(f * 0.6662F + (float)Math.PI) * 0.8F * f1);
    	RightLeg.rotateAngleY = this.getFrostWingRotation(25, RightLeg.rotateAngleY, entity);
    	RightLeg.rotateAngleZ = this.getFrostWingRotation(26, RightLeg.rotateAngleZ, entity);
    }

    private void syncList(EntityFrostWing entity)
    {
    	this.cleanupRotationList(entity);
    	if(entity.rotationListModel.size() < entity.rotationList.size())
    	{
    		for(Object[] list : entity.rotationList)
    		{
    			if(this.getList(entity, (Integer)list[0], (Integer)list[1]) == null)
    			{
    				float currentRotation = this.getRotationFromIdentifier((Integer)list[0]);
    				entity.rotationListModel.add(new Object[] {list[0], list[1], list[2], list[3], list[4], currentRotation / (float)Math.PI});
    			}
    		}
    	}
    }

    private Object[] getList(EntityFrostWing entity, int identifier, int move)
    {
    	for(Object[] list : entity.rotationListModel)
    	{
    		if((Integer)list[0] == identifier && (Integer)list[1] == move)
    		{
    			return list;
    		}
    	}
    	return null;
    }
    
    private Object[] getList(EntityFrostWing entity, int identifier)
    {
    	for(Object[] list : entity.rotationListModel)
    	{
    		if((Integer)list[0] == identifier)
    		{
    			return list;
    		}
    	}
    	return null;
    }

    private void cleanupRotationList(EntityFrostWing entity)
    {
    	for(int var1 = 0; var1 < entity.rotationListModel.size();var1++)
    	{
    		Object[] list = entity.rotationListModel.get(var1);
    		{
    			if(entity.getList((Integer)list[0], (Integer)list[1]) == null)
    			{
    				entity.rotationListModel.remove(var1);
    				this.cleanupRotationList(entity);
    				return;
    			}
    		}
    	}
    }

    private float getFrostWingRotation(int identifier, float currentRotation, EntityFrostWing entity)
    {
    	long time = System.currentTimeMillis();
    	float rotation = 0.0F;
    	Object[] list = this.getList(entity, identifier);
    	if(list != null)
    	{
    		long timePassed = (time - (Long)list[4]) > (Long)list[3] ? (Long)list[3] : time - (Long)list[4];
    		rotation = (((Float)list[2] - (Float)list[5]) / (Long)list[3] * timePassed + (Float)list[5]) * (float)Math.PI;
    		if(timePassed == (Long)list[3] && currentRotation != rotation)
    		{
    			entity.onRotationFinish(identifier, (Integer)list[1]);
    		}
    	}
    	else
    	{
    		rotation = currentRotation;
    	}
    	return rotation;
    }

    private float getRotationFromIdentifier(int identifier)
    {
    	switch(identifier)
    	{
    	case 0:
    		return Body.rotateAngleX;
    	case 1:
    		return Body.rotateAngleY;
    	case 2:
    		return Body.rotateAngleZ;
    	case 3:
    		return this.headTempX;
    	case 4:
    		return this.headTempY;
    	case 5:
    		return Head.rotateAngleZ;
    	case 6:
    		return WingLeft.rotateAngleX;
    	case 7:
    		return WingLeft.rotateAngleY;
    	case 8:
    		return WingLeft.rotateAngleZ;
    	case 9:
    		return WingRight.rotateAngleX;
    	case 10:
    		return WingRight.rotateAngleY;
    	case 11:
    		return WingRight.rotateAngleZ;
    	case 12:
    		return WingLeftOuter.rotateAngleX;
    	case 13:
    		return WingLeftOuter.rotateAngleY;
    	case 14:
    		return WingLeftOuter.rotateAngleZ;
    	case 15:
    		return WingRightOuter.rotateAngleX;
    	case 16:
    		return WingRightOuter.rotateAngleY;
    	case 17:
    		return WingRightOuter.rotateAngleZ;
    	case 18:
    		return TailPiece.rotateAngleX;
    	case 19:
    		return TailPiece.rotateAngleY;
    	case 20:
    		return TailPiece.rotateAngleZ;
    	case 21:
    		return this.leftLegTempX;
    	case 22:
    		return LeftLeg.rotateAngleY;
    	case 23:
    		return LeftLeg.rotateAngleZ;
    	case 24:
    		return this.rightLegTempX;
    	case 25:
    		return RightLeg.rotateAngleY;
    	case 26:
    		return RightLeg.rotateAngleZ;
    	default:
    		return 0.0F;
    	}
    }

}
