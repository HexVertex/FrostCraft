package xelitez.frostcraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import xelitez.frostcraft.registry.IdMap;

public class EntityAIWanderInCastle extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;

    public EntityAIWanderInCastle(EntityCreature par1EntityCreature, double par2)
    {
        this.entity = par1EntityCreature;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.entity.getAge() >= 100)
        {
            return false;
        }
        else if (this.entity.getRNG().nextInt(60) != 0)
        {
            return false;
        }
        else
        {
        	Vec3 vec3 = null;
			for(int i = 0;i < 50;i++)
			{
				vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
				if(!this.isValidBlock(entity.worldObj.getBlock((int)vec3.xCoord, (int)vec3.yCoord, (int)vec3.zCoord)))
				{
					vec3 = null;
					continue;
				}
				break;
			}

            if (vec3 == null)
            {
                return false;
            }
            else if(!this.isValidBlock(entity.worldObj.getBlock((int)vec3.xCoord, (int)vec3.yCoord, (int)vec3.zCoord)))
            {
            	return false;
            }
            else
            {
                this.xPosition = vec3.xCoord;
                this.yPosition = vec3.yCoord;
                this.zPosition = vec3.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
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
