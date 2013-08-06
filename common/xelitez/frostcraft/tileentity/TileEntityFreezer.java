package xelitez.frostcraft.tileentity;

import xelitez.frostcraft.registry.RecipeRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityFreezer extends TileEntityThermalMachines implements ISidedInventory
{
	public ItemStack[] items = new ItemStack[3];
	
	public int freezeTime = 0;
	
	public TileEntityFreezer()
	{
		this.capacity = 1200;
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

        this.freezeTime = par1NBTTagCompound.getShort("FreezeTime");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("FreezeTime", (short)this.freezeTime);
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
    
    public int getFreezeProgressScaled(int par1)
    {
        return this.freezeTime * par1 / 300;
    }
    
    public int getStorageRemainingScaled(int par1)
    {
        return this.storage * par1 / this.capacity;
    }
    
    public boolean isFreezing()
    {
        return this.storage > 0;
    }
    
    public void updateEntity()
    {
    	super.updateEntity();
    	int var1 = this.freezeTime;
    	this.requestEnergy(5);
        boolean var2 = false;

        if (!this.worldObj.isRemote)
        {
            if (this.canFreeze() && this.isFreezing())
            {
                ++this.freezeTime;
                --this.storage;

                if (this.freezeTime == 300)
                {
                    this.freezeTime = 0;
                    this.freezeItem();
                    var2 = true;
                }
            }
            else if(!this.canFreeze())
            {
                this.freezeTime = 0;
            }

            if (this.freezeTime != var1 && this.isFreezing())
            {
            	if(!this.isActive)
            	{
            		var2 = true;
            		this.setActive(true);
            	}
            }
            else if(this.isActive)
            {
        		var2 = true;
        		this.setActive(false);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }
    
    public void freezeItem()
    {
        if (this.canFreeze())
        {
            ItemStack var1 = RecipeRegistry.registry().getFreezingResult(this.items[0]);

            if (this.items[1] == null)
            {
                this.items[1] = var1.copy();
            }
            else if (this.items[1].isItemEqual(var1))
            {
            	items[1].stackSize += var1.stackSize;
            }
            if(this.items[0].getItem().getContainerItemStack(items[0]) != null)
            {
            	if(this.items[2] == null)
            	{
            		this.items[2] = this.items[0].getItem().getContainerItemStack(items[0]).copy();
            	}
            	else if(this.items[0].getItem().getContainerItemStack(items[0]).isItemEqual(this.items[2]) && this.items[2].stackSize + 1 < this.items[2].getMaxStackSize())
            	{
            		this.items[2].stackSize += this.items[0].getItem().getContainerItemStack(this.items[0]).stackSize;
            	}
            }

            --this.items[0].stackSize;

            if (this.items[0].stackSize <= 0)
            {
                this.items[0] = null;
            }
        }
    }
    
    private boolean canFreeze()
    {
        if (this.items[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = RecipeRegistry.registry().getFreezingResult(this.items[0]);
            if (var1 == null) return false;
            if (this.items[1] == null && (this.items[0].getItem().getContainerItemStack(items[0]) == null || (this.items[2] == null || (this.items[2].isItemEqual(var1) && this.items[2].stackSize + 1 < this.items[2].getMaxStackSize())))) return true;
            if (this.items[1] != null && !this.items[1].isItemEqual(var1)) return false;
            int result = items[1] != null ? (items[1].stackSize + var1.stackSize) : 64;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
    }

	@Override
	public int getSizeInventory() 
	{
		return this.items.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) 
	{
		return this.items[var1];
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
	public ItemStack getStackInSlotOnClosing(int var1) 
	{
        if (this.items[var1] != null)
        {
            ItemStack var2 = this.items[var1];
            this.items[var1] = null;
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
		return "frostcraft.freezer";
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
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInvNameLocalized() 
	{
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) 
	{
		return i == 0 ? new int[] {1, 2} : new int[] {0};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) 
	{
		return i == 2 ? false : (i == 1 ? false : true);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) 
	{
		return i == 0 ? RecipeRegistry.registry().getFreezingResult(itemstack) == null : true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return i == 2 ? false : (i == 1 ? false : true);
	}
}
