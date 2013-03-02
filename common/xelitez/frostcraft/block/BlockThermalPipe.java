package xelitez.frostcraft.block;

import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.enums.ConnectionTypes;
import xelitez.frostcraft.interfaces.IConnect;
import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.tileentity.TileEntityThermalPipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockThermalPipe extends BlockBase implements IConnect{

	public BlockThermalPipe(int id, Material material) 
	{
		super(id, material);
		this.setCreativeTab(CreativeTabs.FCMechanical);
	}
    
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return 0;
    }

	@Override
	public ConnectionTypes getConnectionType() 
	{
		return ConnectionTypes.THERMAL;
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) 
	{
		((TileEntityThermalPipe)par1World.getBlockTileEntity(par2, par3, par4)).updateConnections(par1World, par2, par3, par4);
	}
	
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	((TileEntityThermalPipe)par1World.getBlockTileEntity(par2, par3, par4)).updateConnections(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) 
	{
    	boolean canConnectTop = Block.blocksList[par1IBlockAccess.getBlockId(par2, par3 + 1, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2, par3 + 1, par4)]).getConnectionType() == this.getConnectionType();
    	boolean canConnectBottom = Block.blocksList[par1IBlockAccess.getBlockId(par2, par3 - 1, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2, par3 - 1, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectLeft = Block.blocksList[par1IBlockAccess.getBlockId(par2 + 1, par3, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2 + 1, par3, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectRight = Block.blocksList[par1IBlockAccess.getBlockId(par2 - 1, par3, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2 - 1, par3, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectFront = Block.blocksList[par1IBlockAccess.getBlockId(par2, par3, par4 + 1)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2, par3, par4 + 1)]).getConnectionType() == this.getConnectionType();
        boolean canConnectBack = Block.blocksList[par1IBlockAccess.getBlockId(par2, par3, par4 - 1)] instanceof IConnect && ((IConnect)Block.blocksList[par1IBlockAccess.getBlockId(par2, par3, par4 - 1)]).getConnectionType() == this.getConnectionType();
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
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
    	boolean canConnectTop = Block.blocksList[par1World.getBlockId(par2, par3 + 1, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2, par3 + 1, par4)]).getConnectionType() == this.getConnectionType();
    	boolean canConnectBottom = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectLeft = Block.blocksList[par1World.getBlockId(par2 + 1, par3, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2 + 1, par3, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectRight = Block.blocksList[par1World.getBlockId(par2 - 1, par3, par4)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2 - 1, par3, par4)]).getConnectionType() == this.getConnectionType();
        boolean canConnectFront = Block.blocksList[par1World.getBlockId(par2, par3, par4 + 1)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2, par3, par4 + 1)]).getConnectionType() == this.getConnectionType();
        boolean canConnectBack = Block.blocksList[par1World.getBlockId(par2, par3, par4 - 1)] instanceof IConnect && ((IConnect)Block.blocksList[par1World.getBlockId(par2, par3, par4 - 1)]).getConnectionType() == this.getConnectionType();
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

        return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)par2 + minX), (double)((float)par3 + minY), (double)((float)par4 + minZ), (double)((float)par2 + maxX), (double)((float)par3 + maxY), (double)((float)par4 + maxZ));
    }
    
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    	EnergyRequestRegistry.getInstance().removePipeFromQueue(par2, par3, par4, par1World.provider.dimensionId);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
    
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
    
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityThermalPipe();
    }  
    
    public int getRenderType()
    {
        return 2200;
    }
	

}
