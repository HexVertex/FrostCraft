package xelitez.frostcraft.tileentity;

import xelitez.frostcraft.block.BlockThermalMachines;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.energy.IIsSource;
import xelitez.frostcraft.interfaces.IChargeable;
import xelitez.frostcraft.network.PacketSendManagerServer;
import xelitez.frostcraft.registry.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityFrostGenerator extends TileEntityThermalMachines implements IIsSource, ISidedInventory
{
	private ItemStack[] items = new ItemStack[2];
	
    public int generatorBurnTime = 0;
    public int currentItemBurnTime = 0;
    
    public TileEntityFrostGenerator()
    {
    	this.capacity = 2500;
    }


	@Override
	public boolean handleRequest(int id) 
	{
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
        this.items = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.items.length)
            {
                this.items[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.generatorBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.currentItemBurnTime = RecipeRegistry.registry().getFrostTime(this.items[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.generatorBurnTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.items.length; ++var3)
        {
            if (this.items[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.items[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }
    
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.generatorBurnTime * par1 / this.currentItemBurnTime;
    }
    
    public boolean isBurning()
    {
        return this.generatorBurnTime > 0;
    }
    
    public void updateEntity()
    {
    	super.updateEntity();
        int var1 = this.generatorBurnTime;
        boolean var2 = false;

        if (!this.worldObj.isRemote)
        {
            if (this.generatorBurnTime == 0)
            {
                this.currentItemBurnTime = this.generatorBurnTime = RecipeRegistry.registry().getFrostTime(this.items[1]);

                if (this.generatorBurnTime > 0)
                {
                    var2 = true;

                    if (this.items[1] != null)
                    {
                        --this.items[1].stackSize;

                        if (this.items[1].stackSize == 0)
                        {
                            this.items[1] = this.items[1].getItem().getContainerItemStack(items[1]);
                        }
                    }
                }
            }
            
            if(this.isBurning() && this.storage < this.capacity - 3)
            {
                if (this.generatorBurnTime > 0)
                {
                    --this.generatorBurnTime;
                }
            	this.storage += 4;
            	if(this.storage >= this.capacity)
            	{
            		this.storage = this.capacity;
            	}
            }

            if (var1 != this.generatorBurnTime)
            {
            	if(!this.isActive)
            	{
            		var2 = true;
            		this.setActive(true);
            	}
            }
            else if (this.isActive)
            {
            	this.setActive(false);
            }
            
        	if(this.items[0] != null && this.items[0].getItem() instanceof IChargeable)
        	{
        		IChargeable item = (IChargeable)this.items[0].getItem();
        		if(this.storage >= item.getChargeRate())
        		{
        			this.storage -= item.charge(item.getChargeRate(), this.items[0]);
        		}
        		else
        		{
        			this.storage -= item.charge(this.storage, this.items[0]);
        		}
        	}
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }
    
    public int getCurrentStorgaeScaled(int index)
    {
    	return this.storage * index / this.capacity;
    }

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) 
	{
		return items[var1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
        if (this.items[par1] != null)
        {
            ItemStack var3;

            if (this.items[par1].stackSize <= par2)
            {
                var3 = this.items[par1];
                this.items[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.items[par1].splitStack(par2);

                if (this.items[par1].stackSize == 0)
                {
                    this.items[par1] = null;
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
        if (this.items[par1] != null)
        {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) 
	{
        this.items[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() 
	{
		return "frostcraft.frostgenerator";
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) 
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() 
	{		
	}

	@Override
	public void closeChest() 
	{
	}

	@Override
	public int getStartInventorySide(ForgeDirection side) 
	{
		if(side == ForgeDirection.DOWN) return 0;
		return 1;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) 
	{
		return 1;
	}

}
