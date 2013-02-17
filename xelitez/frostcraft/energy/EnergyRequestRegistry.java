package xelitez.frostcraft.energy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;

import xelitez.frostcraft.tileentity.TileEntityThermalMachines;
import xelitez.frostcraft.tileentity.TileEntityThermalPipe;
import xelitez.frostcraft.registry.IdMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class EnergyRequestRegistry implements ITickHandler
{
	private List<int[]> ids = new ArrayList<int[]>();
	private List<int[]> queue = new ArrayList<int[]>();
	private List<int[]> checked = new ArrayList<int[]>();
	
	public int checkSpeed = 5;
	
	private static EnergyRequestRegistry INSTANCE = new EnergyRequestRegistry();
	
	public static EnergyRequestRegistry getInstance()
	{
		return INSTANCE;
	}

	public int getAvailableRequestId()
	{
		return getAvailableId(0);
	}
	
	private int getAvailableId(int par1)
	{
		if(par1 >= Integer.MAX_VALUE)
		{
			return -1;
		}
		for(int i = 0;i < ids.size();i++)
		{
			if(i < ids.size())
			{
				int[] id = ids.get(i);
				if(id != null && id[0] == par1)
				{
					par1++;
					return getAvailableId(par1);
				}
			}
		}	
		return par1;
	}
	
	public int addRequest(TileEntityThermalMachines te, int request)
	{
		int id = this.getAvailableRequestId();
		if(id != -1)
		{
			int[] set = new int[] {id, request, te.xCoord, te.yCoord, te.zCoord, te.worldObj.provider.dimensionId};
			for(int i = 0;i < this.ids.size();i++)
			{
				int[] dat = ids.get(i);
				if(dat == null)
				{
					ids.set(i, set);
					return id;
				}
			}
			ids.add(set);
		}
		return id;
	}
	
	public int[] getRequestData(int id)
	{
		for(int i = 0;i < ids.size();i++)
		{
			int[] dat = ids.get(i);
			if(dat != null && dat[0] == id)
			{
				return dat;
			}
		}
		return null;
	}
	
	public boolean hasTileEntityRequests(TileEntityThermalMachines te)
	{
		for(int i = 0;i < ids.size();i++)
		{
			if(i < ids.size())
			{
				int[] id = ids.get(i);
				if(id != null && id[2] == te.xCoord && id[3] == te.yCoord && id[4] == te.zCoord && id[5] == te.worldObj.provider.dimensionId)
				{
					if(this.getNumberOfPipesInQueue(id[0]) <= 0)
					{
						this.removeAll(id[0]);
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public void setPipeChecked(TileEntityThermalPipe te, int id)
	{
		int[] set = new int[] {id, te.xCoord, te.yCoord, te.zCoord, te.worldObj.provider.dimensionId };
		for(int i = 0;i < checked.size();i++)
		{
			if(i <= this.checked.size())
			{
				int[] dat = checked.get(i);
				if(checked == null)
				{
					checked.set(i, set);
					return;
				}
			}
		}	
		checked.add(set);
	}
	
	public boolean isPipeChecked(TileEntityThermalPipe te, int id)
	{
		for(int i = 0;i < checked.size();i++)
		{
			int[] check = checked.get(i);
			if(check != null && check[0] == id)
			{
				if(te.xCoord == check[1] && te.yCoord == check[2] && te.zCoord == check[3] && te.worldObj.provider.dimensionId == check[4])
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private int getDimensionQueue(int dim)
	{
		int count = 0;
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[4] == dim)
			{
				count++;
			}
		}
		return count;
	}
	
	public boolean addPipeToCheckQueue(int id, TileEntityThermalPipe te)
	{
		if(te != null && te instanceof TileEntityThermalPipe && !this.getIsPipeInQueue(id, te))
		{
			int[] set = new int[] {id, te.xCoord, te.yCoord, te.zCoord, te.worldObj.provider.dimensionId};
			for(int i = 0;i < queue.size();i++)
			{
				int[] dat = queue.get(i);
				if(dat == null)
				{
					queue.set(i, set);
					return true;
				}
			}
			queue.add(set);
			return true;
		}
		return false;
	}
	
	public void removePipeFromQueue(int x, int y, int z, int dim, int id)
	{
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[0] == id && dat[1] == x && dat[2] == y && dat[3] == z && dat[4] == dim)
			{
				queue.remove(i);
				return;
			}
		}
	}
	
	public void removePipeFromQueue(int x, int y, int z, int dim)
	{
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[1] == x && dat[2] == y && dat[3] == z && dat[4] == dim)
			{
				this.removePipeFromQueue(x, y, z, dim, dat[0]);
			}
		}
	}
	
	public boolean getIsPipeInQueue(int id, TileEntityThermalPipe te)
	{
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[0] == id && dat[1] == te.xCoord && dat[2] == te.yCoord && dat[3] == te.zCoord && dat[4] == te.worldObj.provider.dimensionId)
			{
				return true;
			}
		}
		return false;
	}
	
	public int getNumberOfPipesInQueue(int id)
	{
		int count = 0;
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[0] == id)
			{
				count++;
			}
		}
		return count;
	}
	
	public void removeAll(int id)
	{
		this.removeQueue(id);
		this.removeChecked(id);
		this.removeRequest(id);
	}
	
	public void removeQueue(int id)
	{
		for(int i = 0;i < queue.size();i++)
		{
			int[] dat = queue.get(i);
			if(dat != null && dat[0] == id)
			{
				queue.remove(i);
				this.removeQueue(id);
				return;
			}
		}
	}
	
	public void removeRequest(int id)
	{
		for(int i = 0;i < ids.size();i++)
		{
			int[] dat = ids.get(i);
			if(dat != null && dat[0] == id)
			{
				ids.remove(i);
				return;
			}
		}
	}
	
	public void removeChecked(int id)
	{
		for(int i = 0;i < checked.size();i++)
		{
			int[] dat = checked.get(i);
			if(dat != null && dat[0] == id)
			{
				checked.remove(i);
				this.removeChecked(id);
				return;
			}
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{		
		World world = (World)tickData[0];
		while(INSTANCE.getDimensionQueue(world.provider.dimensionId) > 0)
		{
			for(int i = 0;i < INSTANCE.queue.size();i++)
			{
				int[] dat = (int[])INSTANCE.queue.get(i);
				if(dat != null && dat[4] == world.provider.dimensionId)
				{
					TileEntityThermalPipe te = (TileEntityThermalPipe)world.getBlockTileEntity(dat[1], dat[2], dat[3]);
					if(te != null)
					{
						INSTANCE.setPipeChecked(te, dat[0]);
						INSTANCE.queue.remove(i);
						te.check(dat[0]);
					}
					else
					{
						INSTANCE.queue.remove(i);
					}
					break;
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() 
	{
		return "FrostCraftEnergyTicker";
	}
}
