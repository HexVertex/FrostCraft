package xelitez.frostcraft.tileentity;

import xelitez.frostcraft.block.BlockThermalMachines;
import xelitez.frostcraft.energy.IIsSource;
import xelitez.frostcraft.registry.BiomeRegistry;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.interfaces.IChargeable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityThermalPump extends TileEntityThermalMachines implements IIsSource, IInventory
{
	public ItemStack[] item = new ItemStack[1];
	
	public double value = 0;
	
	public void calculateValue()
	{
		double Bvalue = (double)BiomeRegistry.getBiomeValue(worldObj, xCoord, zCoord);
		double value = Bvalue * 1.50;
		value += Bvalue * ((double)yCoord * 1.0 / 256.0);
		long time = worldObj.getWorldTime();
		if((time >= 12000 && time <= 15000) || (time >= 21000 && time <= 24000))
		{
			value += Bvalue * 0.25;
		}
		else if(time > 15000 && time < 21000)
		{
			value += Bvalue * 0.50;
		}
		if(worldObj.isRaining())
		{
			value += Bvalue* 0.25;
		}
		if(value > this.getMaxValue())
		{
			value = this.getMaxValue();
		}
		this.value = value;
	}
	
	public TileEntityThermalPump()
	{
		this.capacity = 500;
	}

	@Override
	public boolean handleRequest(int id) 
	{
		if(!this.isActive)
		{
			return false;
		}
		int[] dat = EnergyRequestRegistry.getInstance().getRequestData(id);
		if(dat != null && dat[1] <= this.storage)
		{
			if(Block.blocksList[worldObj.getBlockId(dat[2], dat[3], dat[4])] instanceof BlockThermalMachines && worldObj.getBlockTileEntity(dat[2], dat[3], dat[4]) instanceof TileEntityThermalMachines)
			{
				TileEntityThermalMachines te = (TileEntityThermalMachines)worldObj.getBlockTileEntity(dat[2], dat[3], dat[4]);
				int value = te.recieveEnergy(dat[1]);
				this.storage -= value;
			}
			return true;
		}
		return false;
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.item = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.item.length)
            {
                this.item[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.value = par1NBTTagCompound.getShort("Value");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("Value", (short)this.value);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.item.length; ++var3)
        {
            if (this.item[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.item[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }
	
    public void updateEntity() 
    {
    	super.updateEntity();
    	if(this.worldObj != null && !this.worldObj.isRemote)
    	{
    		if(!(this.xCoord == 0 && this.yCoord == 0 && this.zCoord == 0))
    		{
    			this.calculateValue();
    			this.recieveEnergy((int)((double)value / 7.5D));
    		}
        	if(this.item[0] != null && this.item[0].getItem() instanceof IChargeable)
        	{
        		IChargeable item = (IChargeable)this.item[0].getItem();
        		if(this.storage >= item.getChargeRate())
        		{
        			this.storage -= item.charge(item.getChargeRate(), this.item[0]);
        		}
        		else
        		{
        			this.storage -= item.charge(this.storage, this.item[0]);
        		}
        	}
    	}	
    }
    
    public double getMaxValue()
    {
    	return 64.0;
    }
    
    public int getCurrentStorgaeScaled(int index)
    {
    	return this.storage * index / 500;
    }
    
    public int getCurrentValueScaled(double index)
    {
    	return (int)(this.value * index / this.getMaxValue());
    }

	@Override
	public int getSizeInventory() 
	{
		return this.item.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) 
	{
		return this.item[var1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if (this.item[par1] != null)
        {
            ItemStack var3;

            if (this.item[par1].stackSize <= par2)
            {
                var3 = this.item[par1];
                this.item[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.item[par1].splitStack(par2);

                if (this.item[par1].stackSize == 0)
                {
                    this.item[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) 
	{
        if (this.item[par1] != null)
        {
            ItemStack var2 = this.item[par1];
            this.item[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack par2ItemStack) 
	{
        this.item[var1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }		
	}

	@Override
	public String getInvName() 
	{
		return "Thermal Pump";
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) 
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}
}
