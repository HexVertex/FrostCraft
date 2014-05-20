package xelitez.frostcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import xelitez.frostcraft.registry.IdMap;

public class BlockIcicle extends Block
{

	public BlockIcicle(Material material) 
	{
		super(material);
        this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}
	
	@Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }
    
	@Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block top = par1World.getBlock(par2, par3 + 1, par4);
        return top != null && (top.isSideSolid(par1World, par2, par3, par4, ForgeDirection.DOWN) || top instanceof BlockLeaves);
    }
    
	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.checkIcicleChange(par1World, par2, par3, par4);
        if (par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 8)
        {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5Block);
        this.checkIcicleChange(par1World, par2, par3, par4);
    }
    
    protected final void checkIcicleChange(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
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
    public int getRenderType()
    {
        return 1;
    }
    
	@Override
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(2) + 1;
    }
    
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return IdMap.itemIcicle;
    }
    
}
