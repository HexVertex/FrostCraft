package xelitez.frostcraft.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.enums.ConnectionTypes;
import xelitez.frostcraft.interfaces.IConnect;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.tileentity.TileEntityThermalPipe;
import cpw.mods.fml.common.FMLCommonHandler;

public class BlockThermalPipe extends Block implements IConnect, ITileEntityProvider
{
	private IIcon outertexture;
	public IIcon innertexture;
	
	public BlockThermalPipe(Material material) 
	{
		super(material);
		this.setCreativeTab(FrostcraftCreativeTabs.FCMechanical);
	}
    
	@Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	outertexture = par1IconRegister.registerIcon("Frostcraft:pipe_outer_iron");
    	innertexture = par1IconRegister.registerIcon("Frostcraft:pipe_inner_iron");
    }
    
	@Override
    public IIcon getIcon(int par1, int par2)
    {
        return outertexture;
    }

	@Override
	public ConnectionTypes getConnectionType() 
	{
		return ConnectionTypes.THERMAL;
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block) 
	{
		((TileEntityThermalPipe)par1World.getTileEntity(par2, par3, par4)).updateConnections(par1World, par2, par3, par4);
	}
	
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	((TileEntityThermalPipe)par1World.getTileEntity(par2, par3, par4)).updateConnections(par1World, par2, par3, par4);
    }
	
	@SuppressWarnings("rawtypes")
	@Override
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
    	boolean canConnectTop = par1World.getBlock(par2, par3 + 1, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3 + 1, par4)).getConnectionType() == this.getConnectionType();
    	boolean canConnectBottom = par1World.getBlock(par2, par3 - 1, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3 - 1, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectLeft = par1World.getBlock(par2 + 1, par3, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2 + 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectRight = par1World.getBlock(par2 - 1, par3, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2 - 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectFront = par1World.getBlock(par2, par3, par4 + 1) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3, par4 + 1)).getConnectionType() == this.getConnectionType();
        boolean canConnectBack = par1World.getBlock(par2, par3, par4 - 1) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3, par4 - 1)).getConnectionType() == this.getConnectionType();
        float minX = 0.3125F;
        float minY = 0.3125F;
        float minZ = 0.3125F;
        float maxX = 0.6875F;
        float maxY = 0.6875F;
        float maxZ = 0.6875F;
        if (canConnectTop)
        {
            maxY = 1.0F;
        }

        if (canConnectBottom)
        {
            minY = 0.0F;
        }
        
        if (canConnectTop || canConnectBottom)
        {
            this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, p_149743_5_, p_149743_6_, p_149743_7_);
        }
        
        if (canConnectRight)
        {
            minX = 0.0F;
        }

        if (canConnectLeft)
        {
            maxX = 1.0F;
        }
        
        if (canConnectRight || canConnectLeft)
        {
            this.setBlockBounds(minX, 0.3125F, minZ, maxX, 0.6875F, maxZ);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, p_149743_5_, p_149743_6_, p_149743_7_);
        }
        
        if (canConnectBack)
        {
            minZ = 0.0F;
        }

        if (canConnectFront)
        {
            maxZ = 1.0F;
        }
        
        if (canConnectBack || canConnectFront || !canConnectTop && !canConnectBottom && !canConnectRight && !canConnectLeft)
        {
        	this.setBlockBounds(0.3125F, 0.3125F, minZ, 0.6875F, 0.6875F, maxZ);
        	super.addCollisionBoxesToList(par1World, par2, par3, par4, p_149743_5_, p_149743_6_, p_149743_7_);
        }
        
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) 
	{
    	boolean canConnectTop = par1IBlockAccess.getBlock(par2, par3 + 1, par4) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2, par3 + 1, par4)).getConnectionType() == this.getConnectionType();
    	boolean canConnectBottom = par1IBlockAccess.getBlock(par2, par3 - 1, par4) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2, par3 - 1, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectLeft = par1IBlockAccess.getBlock(par2 + 1, par3, par4) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2 + 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectRight = par1IBlockAccess.getBlock(par2 - 1, par3, par4) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2 - 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectFront = par1IBlockAccess.getBlock(par2, par3, par4 + 1) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2, par3, par4 + 1)).getConnectionType() == this.getConnectionType();
        boolean canConnectBack = par1IBlockAccess.getBlock(par2, par3, par4 - 1) instanceof IConnect && ((IConnect)par1IBlockAccess.getBlock(par2, par3, par4 - 1)).getConnectionType() == this.getConnectionType();
        float minX = 0.3125F;
        float minY = 0.3125F;
        float minZ = 0.3125F;
        float maxX = 0.6875F;
        float maxY = 0.6875F;
        float maxZ = 0.6875F;

        if (canConnectTop)
        {
            maxY = 1.0F;
        }

        if (canConnectBottom)
        {
            minY = 0.0F;
        }

        if (canConnectRight)
        {
            minX = 0.0F;
        }

        if (canConnectLeft)
        {
            maxX = 1.0F;
        }
        
        if (canConnectBack)
        {
            minZ = 0.0F;
        }

        if (canConnectFront)
        {
            maxZ = 1.0F;
        }

        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
    	boolean canConnectTop = par1World.getBlock(par2, par3 + 1, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3 + 1, par4)).getConnectionType() == this.getConnectionType();
    	boolean canConnectBottom = par1World.getBlock(par2, par3 - 1, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3 - 1, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectLeft = par1World.getBlock(par2 + 1, par3, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2 + 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectRight = par1World.getBlock(par2 - 1, par3, par4) instanceof IConnect && ((IConnect)par1World.getBlock(par2 - 1, par3, par4)).getConnectionType() == this.getConnectionType();
        boolean canConnectFront = par1World.getBlock(par2, par3, par4 + 1) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3, par4 + 1)).getConnectionType() == this.getConnectionType();
        boolean canConnectBack = par1World.getBlock(par2, par3, par4 - 1) instanceof IConnect && ((IConnect)par1World.getBlock(par2, par3, par4 - 1)).getConnectionType() == this.getConnectionType();
        float minX = 0.3125F;
        float minY = 0.3125F;
        float minZ = 0.3125F;
        float maxX = 0.6875F;
        float maxY = 0.6875F;
        float maxZ = 0.6875F;

        if (canConnectTop)
        {
            maxY = 1.0F;
        }

        if (canConnectBottom)
        {
            minY = 0.0F;
        }

        if (canConnectRight)
        {
            minX = 0.0F;
        }

        if (canConnectLeft)
        {
            maxX = 1.0F;
        }
        
        if (canConnectBack)
        {
            minZ = 0.0F;
        }

        if (canConnectFront)
        {
            maxZ = 1.0F;
        }

        return AxisAlignedBB.getBoundingBox((double)((float)par2 + minX), (double)((float)par3 + minY), (double)((float)par4 + minZ), (double)((float)par2 + maxX), (double)((float)par3 + maxY), (double)((float)par4 + maxZ));
    }
    
	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
    {
    	super.breakBlock(par1World, par2, par3, par4, par5Block, par6);
    	EnergyRequestRegistry.getInstance().removePipeFromQueue(par2, par3, par4, par1World.provider.dimensionId);
    }

	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
	@Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
    
	@Override
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
    
	@Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityThermalPipe();
    }  
    
	@Override
    public int getRenderType()
    {
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			return IdMap.thermalPipeRenderer.getRenderId();
		}
		else
		{
			return 0;
		}
    }

	@Override
	public TileEntity createNewTileEntity(World world, int var2) 
	{
		return new TileEntityThermalPipe();
	}
	

}
