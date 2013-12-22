package xelitez.frostcraft.block;

import java.util.Random;

import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockIcicle extends Block
{

	public BlockIcicle(int id, Material material) 
	{
		super(id, material);
        this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setCreativeTab(CreativeTabs.FCMiscItems);
	}
	
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }
    
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block top = blocksList[par1World.getBlockId(par2, par3 + 1, par4)];
        return top != null && (top.isBlockSolidOnSide(par1World, par2, par3, par4, ForgeDirection.DOWN) || top instanceof BlockLeaves);
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.checkIcicleChange(par1World, par2, par3, par4);
        if (par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 11)
        {
            par1World.setBlock(par2, par3, par4, 0);
        }
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        this.checkIcicleChange(par1World, par2, par3, par4);
    }
    
    protected final void checkIcicleChange(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, 0);
        }
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return 1;
    }
    
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(2) + 1;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return IdMap.itemIcicle.itemID;
    }
    
}
