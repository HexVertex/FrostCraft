package xelitez.frostcraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityStatue extends TileEntity
{
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
    }
    
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
    }
    
    public AxisAlignedBB getRenderBoundingBox()
    {
    	AxisAlignedBB cbb = INFINITE_EXTENT_AABB;
    	if(this.getBlockType() != null)
    	{
	    	if(this.getBlockMetadata() < 4)
	    	{
	    		cbb = getBlockType().getCollisionBoundingBoxFromPool(worldObj, xCoord, yCoord, zCoord).expand(0.0D, 2.0D, 0.0D);
	    	}
	    	else
	    	{
	    		cbb = getBlockType().getCollisionBoundingBoxFromPool(worldObj, xCoord, yCoord, zCoord);
	    	}
    	}
        return cbb;
    }
}
