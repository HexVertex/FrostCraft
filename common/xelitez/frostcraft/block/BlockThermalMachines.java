package xelitez.frostcraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.enums.ConnectionTypes;
import xelitez.frostcraft.interfaces.IConnect;
import xelitez.frostcraft.network.PacketSendManagerClient;
import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.tileentity.*;

public class BlockThermalMachines extends BlockContainer implements IConnect
{
	Random rand = new Random();
	Icon[] icons;
	String[] iconNames = new String[] {"FrostCraft:thermal_pump_active", "FrostCraft:thermal_pump_inactive", "FrostCraft:frost_furnace_active", 
			"FrostCraft:frost_furnace_inactive", "FrostCraft:frost_generator_active", "FrostCraft:frost_generator_inactive", "FrostCraft:freezer_active",
			"FrostCraft:freezer_inactive", "FrostCraft:frost_enforcer_active", "FrostCraft:frost_enforcer_inactive"};
	
	public BlockThermalMachines(int id, Material material) 
	{
		super(id, material);
	}

	@Override
	public ConnectionTypes getConnectionType() {
		return ConnectionTypes.THERMAL;
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	return IdMap.blockThermalMachines.blockID;
    }
    
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }
	
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
        	TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
        	TileEntityThermalMachines tet = null;
        	if(te instanceof TileEntityThermalMachines)
        	{
        		tet = (TileEntityThermalMachines)te;
        	}
        	if(tet != null)
        	{
	            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
	            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
	            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
	            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
	            byte var9 = 3;
	
	            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
	            {
	                var9 = 3;
	            }
	
	            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
	            {
	                var9 = 2;
	            }
	
	            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
	            {
	                var9 = 5;
	            }
	
	            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
	            {
	                var9 = 4;
	            }
	
	            tet.front = var9;
        	}
        }
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
        if(!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
        	TileEntityThermalPump te = (TileEntityThermalPump)par1World.getBlockTileEntity(par2, par3, par4);
        	if (te.isActive && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        	{
        		te.setActive(false);
        	}
        	else if (!te.isActive && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        	{
        		te.setActive(true);
        	}
        }
    }
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	TileEntityThermalMachines var7 = (TileEntityThermalMachines)par1World.getBlockTileEntity(par2, par3, par4);

        if (var7 != null && var7 instanceof IInventory)
        {
            for (int var8 = 0; var8 < ((IInventory) var7).getSizeInventory(); ++var8)
            {
                ItemStack var9 = ((IInventory) var7).getStackInSlot(var8);

                if (var9 != null)
                {
                    float var10 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float var12 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (var9.stackSize > 0)
                    {
                        int var13 = this.rand.nextInt(21) + 10;

                        if (var13 > var9.stackSize)
                        {
                            var13 = var9.stackSize;
                        }

                        var9.stackSize -= var13;
                        EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (double)((float)this.rand.nextGaussian() * var15);
                        var14.motionY = (double)((float)this.rand.nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)this.rand.nextGaussian() * var15);
                        par1World.spawnEntityInWorld(var14);
                    }
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) == 0 && ((TileEntityThermalPump)par1World.getBlockTileEntity(par2, par3, par4)).isActive && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            ((TileEntityThermalPump)par1World.getBlockTileEntity(par2, par3, par4)).setActive(false);
        }
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	super.registerIcons(par1IconRegister);
    	icons = new Icon[iconNames.length];
    	for(int i = 0; i < iconNames.length;i++)
    	{
    		icons[i] = par1IconRegister.registerIcon(iconNames[i]);
    	}
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	TileEntity te = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
    	TileEntityThermalMachines tet = null;
    	if(te instanceof TileEntityThermalMachines)
    	{
    		tet = (TileEntityThermalMachines)te;
    	}
    	if(tet != null && (!tet.getHasData() || tet.front == 0))
    	{
    		PacketSendManagerClient.requestBlockDataFromServer(tet);
    	}
    	if(tet != null)
    	{
    		int var6 = tet.front;
    		int var8 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    		Icon frontTexture = blockIcon;
    		switch(var8)
    		{
    		case 0:
    			if(tet.isActive)
    			{
    				frontTexture = icons[0];
    			}
    			else
    			{
    				frontTexture = icons[1];
    			}
    			break;
    		case 1:
    			if(tet.isActive)
    			{
    				frontTexture = icons[2];
    			}
    			else
    			{
    				frontTexture = icons[3];
    			}
    			break;
    		case 2:
    			if(tet.isActive)
    			{
    				frontTexture = icons[4];
    			}
    			else
    			{
    				frontTexture = icons[5];
    			}
    			break;
    		case 3:
    			if(tet.isActive)
    			{
    				frontTexture = icons[6];
    			}
    			else
    			{
    				frontTexture = icons[7];
    			}
    			break;
    		case 4:
    			if(tet.isActive)
    			{
    				frontTexture = icons[8];
    			}
    			else
    			{
    				frontTexture = icons[9];
    			}
    			break;
    		}
    		return par5 != var6 ? this.blockIcon : frontTexture;
    	}
    	return this.blockIcon;
    }
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
    	Icon frontTexture;
        switch(par2)
        {
        case 0:
        	frontTexture = icons[1];
        	break;
        case 1:
        	frontTexture = icons[3];
        	break;
        case 2:
        	frontTexture = icons[5];
        	break;
        case 3:
        	frontTexture = icons[7];
        	break;
        case 4:
        	frontTexture = icons[9];
        	break;
        default:
        	frontTexture = this.blockIcon;
        	break;
        }

        return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par1 == 3 ? frontTexture : this.blockIcon));
    }
    
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
    	TileEntityThermalMachines tet = null;
    	if(te instanceof TileEntityThermalMachines)
    	{
    		tet = (TileEntityThermalMachines)te;
    	}
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	if(tet != null)
    	{
	        if (tet.isActive && (meta == 0))
	        {
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.3F + par5Random.nextFloat() * 12.0F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.55F;
	            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
	
	            par1World.spawnParticle("snow", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("snow", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
	        }
	        if (tet.isActive && (meta == 1))
	        {
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.65F + par5Random.nextFloat() * 3.5F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.55F;
	            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
	
	            if (tet.front == 4)
	            {
	                par1World.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("snow", (double)(var7 - var10), (double)var8 - 0.5D, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 5)
	            {
	                par1World.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("snow", (double)(var7 + var10), (double)var8 - 0.5D, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 2)
	            {
	                par1World.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8 - 0.5D, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 3)
	            {
	                par1World.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8 - 0.5D, (double)(var9 + var10), 0.0D, 0.0D, 0.0D); 
	            }
	        }
	        if(tet.isActive && meta == 2)
	        {
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.65F + par5Random.nextFloat() * 3.5F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.55F;
	            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
	
	            if (tet.front == 4)
	            {
	                par1World.spawnParticle("snow", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 5)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 2)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 3)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D); 
	            }
	        }
	        if(tet.isActive && meta == 3)
	        {
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.3F + par5Random.nextFloat() * 12.0F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.55F;
	            float var11 = par5Random.nextFloat() * 0.8F - 0.4F;
	
	            if (tet.front == 4)
	            {
	                par1World.spawnParticle("snow", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 5)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 2)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 3)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D); 
	            }
	        }
	        if(tet.isActive && meta == 4)
	        {
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.3F + par5Random.nextFloat() * 12.0F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.55F;
	            float var11 = par5Random.nextFloat() * 0.8F - 0.4F;
	
	            if (tet.front == 4)
	            {
	                par1World.spawnParticle("snow", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 5)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 2)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
	            }
	            else if (tet.front == 3)
	            {
	                par1World.spawnParticle("snow", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D); 
	            }
	        }
    	}
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    	TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
    	TileEntityThermalMachines tet = null;
    	if(te instanceof TileEntityThermalMachines)
    	{
    		tet = (TileEntityThermalMachines)te;
    	}
    	if(tet != null)
    	{
            if (var6 == 0)
            {
                tet.front = 2;
            }

            if (var6 == 1)
            {
                tet.front = 5;
            }

            if (var6 == 2)
            {
                tet.front = 3;
            }

            if (var6 == 3)
            {
                tet.front = 4;
            }
    	}
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 5; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        if(par5EntityPlayer.isSneaking())
        {
        	return false;
        }
        else
        {
        	switch(par1World.getBlockMetadata(par2, par3, par4))
        	{
        	case 0:
        		par5EntityPlayer.openGui(FrostCraft.instance, 0, par1World, par2, par3, par4);
        		break;
        	case 1:
        		par5EntityPlayer.openGui(FrostCraft.instance, 1, par1World, par2, par3, par4);
        		break;
        	case 2:
        		par5EntityPlayer.openGui(FrostCraft.instance, 2, par1World, par2, par3, par4);
        		break;
        	case 3:
        		par5EntityPlayer.openGui(FrostCraft.instance, 3, par1World, par2, par3, par4);
        		break;
        	case 4:
        		par5EntityPlayer.openGui(FrostCraft.instance, 4, par1World, par2, par3, par4);
        		break;
        	}

            return true;
        }
    }
    
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
        	TileEntityThermalPump te = (TileEntityThermalPump)par1World.getBlockTileEntity(par2, par3, par4);
            if (te.isActive && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                te.setActive(false);
            }
            else if (!te.isActive && !par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
            {
                te.setActive(true);
            }
        }
    }
    
    

	@Override
	public TileEntity createTileEntity(World var1, int meta) 
	{
		switch(meta)
		{
		case 0:
			return new TileEntityThermalPump();
		case 1:
			return new TileEntityFrostFurnace();
		case 2:
			return new TileEntityFrostGenerator();
		case 3:
			return new TileEntityFreezer();
		case 4:
			return new TileEntityFrostEnforcer();
		default:
			return new TileEntityThermalMachines();
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) 
	{
		return new TileEntityThermalMachines();
	}

}
