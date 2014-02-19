package xelitez.frostcraft.tileentity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import xelitez.frostcraft.registry.RecipeRegistry;

public class TileEntityFrostEnforcer extends TileEntityThermalMachines implements ISidedInventory
{
	public ItemStack[] items = new ItemStack[2];
	
	public int enforceTime = 0;
	
	public TileEntityFrostEnforcer()
	{
		this.capacity = 1200;
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
        this.items = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.items.length)
            {
                this.items[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.enforceTime = par1NBTTagCompound.getShort("EnforceTime");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("FreezeTime", (short)this.enforceTime);
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
    
    public int getEnforcingProgressScaled(int par1)
    {
        return this.enforceTime * par1 / 1200;
    }
    
    public int getStorageRemainingScaled(int par1)
    {
        return this.storage * par1 / this.capacity;
    }
    
    public boolean isEnforcing()
    {
        return this.storage > 0;
    }
    
    public void updateEntity()
    {
    	super.updateEntity();
    	int var1 = this.enforceTime;
    	this.requestEnergy(5);
        boolean var2 = false;

        if (!this.worldObj.isRemote)
        {
            if (this.canEnforce() && this.isEnforcing())
            {
                ++this.enforceTime;
                --this.storage;

                if (this.enforceTime == 1200)
                {
                    this.enforceTime = 0;
                    this.enforceItem();
                    var2 = true;
                }
            }
            else if(!this.canEnforce())
            {
                this.enforceTime = 0;
            }

            if (this.enforceTime != var1 && this.isEnforcing())
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
            this.markDirty();
        }
    }
    
    public void enforceItem()
    {
        if (this.canEnforce())
        {
            ItemStack var1 = RecipeRegistry.registry().getEnforcingResult(this.items[0]);
            NBTTagList enchantments = this.items[0].getEnchantmentTagList();

            if (this.items[1] == null)
            {
                this.items[1] = var1.copy();
                if(enchantments != null)
                {
	                for(int i = 0;i < enchantments.tagCount();i++)
	                {
	                	NBTTagCompound tag = (NBTTagCompound) enchantments.getCompoundTagAt(i);
	                	items[1].addEnchantment(Enchantment.enchantmentsList[tag.getShort("id")], tag.getShort("lvl"));
	                }
                }
            }
            else if (this.items[1].isItemEqual(var1))
            {
            	items[1].stackSize += var1.stackSize;
            }

            --this.items[0].stackSize;

            if (this.items[0].stackSize <= 0)
            {
                this.items[0] = null;
            }
        }
    }
    
    private boolean canEnforce()
    {
        if (this.items[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = RecipeRegistry.registry().getEnforcingResult(this.items[0]);
            if (var1 == null) return false;
            if (this.items[1] == null) return true;
            if (!this.items[1].isItemEqual(var1)) return false;
            int result = items[1].stackSize + var1.stackSize;
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
	public String getInventoryName() 
	{
		return "frostcraft.enforcer";
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) 
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) 
	{
		return i != 0 ? new int[] {0} : new int[] {0, 1};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) 
	{
		return i == 0 ? true : false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) 
	{
		return i == 1 || (i == 0 && RecipeRegistry.registry().getEnforcingResult(itemstack) == null);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return i == 1 ? false : true;
	}
}

