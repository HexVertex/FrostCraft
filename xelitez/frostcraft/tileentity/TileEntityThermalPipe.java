package xelitez.frostcraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

import xelitez.frostcraft.energy.IIsSource;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.interfaces.IConnect;
import xelitez.frostcraft.enums.ConnectionTypes;

public class TileEntityThermalPipe extends TileEntity
{	
	private int[] connected = new int[6];
	private int connections;
	
	public void updateConnections(World world, int x, int y, int z)
	{
		connected = new int[6];
		connections = 0;
		for(int i = 0;i < 6;i++)
		{
			int x1 = x;
			int y1 = y;
			int z1 = z;
			switch(i)
			{
			case 0:
				y1 += 1;
				break;
			case 1:
				y1 -= 1;
				break;
			case 2:
				x1 -= 1;
				break;
			case 3:
				x1 += 1;
				break;
			case 4:
				z1 -= 1;
				break;
			case 5:
				z1 += 1;
				break;
			}
			if(Block.blocksList[world.getBlockId(x1, y1, z1)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x1, y1, z1)]).getConnectionType() == ConnectionTypes.THERMAL && world.getBlockTileEntity(x1, y1, z1) instanceof IIsSource)
			{
				connected[i] = 2;
				connections += 1;
			}
			else if(Block.blocksList[world.getBlockId(x1, y1, z1)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x1, y1, z1)]).getConnectionType() == ConnectionTypes.THERMAL && world.getBlockTileEntity(x1, y1, z1) instanceof TileEntityThermalPipe)
			{
				connected[i] = 1;
				connections += 1;
			}
			else if(Block.blocksList[world.getBlockId(x1, y1, z1)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x1, y1, z1)]).getConnectionType() == ConnectionTypes.THERMAL)
			{
				connected[i] = 3;
				connections += 1;
			}
			else
			{
				connected[i] = 0;
			}
		}
	}
	
	public void check(int id)
	{
		EnergyRequestRegistry reg = EnergyRequestRegistry.getInstance();
		this.updateConnections(worldObj, xCoord, yCoord, zCoord);
		boolean checked = false;
		for(int i = 0;i < 6;i++)
		{
			int x1 = xCoord;
			int y1 = yCoord;
			int z1 = zCoord;
			switch(i)
			{
			case 0:
				y1 += 1;
				break;
			case 1:
				y1 -= 1;
				break;
			case 2:
				x1 -= 1;
				break;
			case 3:
				x1 += 1;
				break;
			case 4:
				z1 -= 1;
				break;
			case 5:
				z1 += 1;
				break;
			}
			if(connected[i] == 2)
			{
				if(((IIsSource)worldObj.getBlockTileEntity(x1, y1, z1)).handleRequest(id))
				{
					reg.removeAll(id);
					return;
				}	
			}
			else if(connected[i] == 1)
			{
				if(!reg.isPipeChecked((TileEntityThermalPipe)worldObj.getBlockTileEntity(x1, y1, z1), id))
				{
					reg.addPipeToCheckQueue(id, (TileEntityThermalPipe)worldObj.getBlockTileEntity(x1, y1, z1));
					checked = true;
				}
			}
		}
		if(!checked && EnergyRequestRegistry.getInstance().getNumberOfPipesInQueue(id) == 0)
		{
			reg.removeAll(id);
		}
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readFromNBT(par1NBTTagCompound);
    	for(int var0 = 0;var0 < this.connected.length;var0++)
    	{
    		this.connected[var0] = par1NBTTagCompound.getInteger(new StringBuilder().append("connected").append(var0).toString());
    	}
    	this.connections = par1NBTTagCompound.getInteger("connections");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeToNBT(par1NBTTagCompound);
    	for(int var0 = 0;var0 < this.connected.length;var0++)
    	{
    		par1NBTTagCompound.setInteger(new StringBuilder().append("connected").append(var0).toString(), connected[var0]);
    	}
    	par1NBTTagCompound.setInteger("connections", this.connections);
    }
	
}