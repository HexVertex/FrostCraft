package xelitez.frostcraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import xelitez.frostcraft.registry.IdMap;

public class EntityAIWalkBackToLastBlackfrost extends EntityAIBase
{
	private EntityCreature entity;
	
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
	
	private Vec3 vec3;
	
	public EntityAIWalkBackToLastBlackfrost(EntityCreature entity, double speed)
	{
		this.entity = entity;
		this.speed = speed;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		if(this.entity.getAttackTarget() != null)
		{
			return false;
		}
		if(!this.entity.getNavigator().noPath())
		{
			return false;
		}
		if(this.isValidBlock(entity.worldObj.getBlock(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY) - 1, MathHelper.floor_double(entity.posZ))))
		{
			vec3 = Vec3.createVectorHelper(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
			return false;
		}
		if(this.vec3 == null)
		{
			for(int i = 0;i < 20;i++)
			{
				vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
				if(!this.isValidBlock(entity.worldObj.getBlock(MathHelper.floor_double(vec3.xCoord), MathHelper.floor_double(vec3.yCoord) - 1, MathHelper.floor_double(vec3.zCoord))))
				{
					vec3 = null;
					continue;
				}
				break;
			}
		}
		if(vec3 != null)
		{
			this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            return true;
		}
		return false;
	}
	
    public boolean continueExecuting()
    {
        return !this.entity.getNavigator().noPath() && !this.isValidBlock(entity.worldObj.getBlock((int)Math.floor(vec3.xCoord), (int)Math.floor(vec3.yCoord) - 1, (int)Math.floor(vec3.zCoord)));
    }
	
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
	
    private boolean isValidBlock(Block block)
    {
    	if(block == IdMap.blockBlackFrost) return true;
    	if(block == IdMap.blockBlackFrostStair) return true;
    	if(block == IdMap.blockBlackFrostStairBrick) return true;
    	if(block == IdMap.blockBlackFrostStairCobble) return true;
    	if(block == IdMap.blockBlackFrostSingleSlabSet) return true;
    	if(block == IdMap.blockBlackFrostDoubleSlabSet) return true;
    	return false;
    }
	
}
