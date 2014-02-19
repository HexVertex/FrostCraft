package xelitez.frostcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xelitez.frostcraft.entity.EntityFrostWing;
import xelitez.frostcraft.tileentity.TileEntityStatue;
import xelitez.frostcraft.world.WorldGenFrostWingTower;

public class BlockStatue extends BlockContainer{

	public BlockStatue(Material par1Material) 
	{
		super(par1Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) 
	{
		TileEntityStatue statue = new TileEntityStatue();
		return statue;
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
        return 22;
    }
    
    /**
     * Called when the block is placed in the world.
     */
	@Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    	if(par1World != null)
    	{
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
    	}
    }
    
	@Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par3 >= 254 ? false : super.canPlaceBlockAt(par1World, par2, par3, par4) && super.canPlaceBlockAt(par1World, par2, par3 + 1, par4) && super.canPlaceBlockAt(par1World, par2, par3 + 2, par4);
    }
    
	@Override
    public int getMobilityFlag()
    {
        return 1;
    }
    
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        if(i1 < 4)
        {
        	if(par1World.getBlock(par2, par3 + 1, par4) != this || par1World.getBlock(par2, par3 + 2, par4) != this )
        	{
                par1World.setBlockToAir(par2, par3, par4);
        	}
        }
        else if(i1 == 4)
        {
        	if(par1World.getBlock(par2, par3 - 1, par4) != this|| par1World.getBlock(par2, par3 + 1, par4) != this)
        	{
                par1World.setBlockToAir(par2, par3, par4);
        	}
        }
        else if(i1 > 4)
        {
        	if(par1World.getBlock(par2, par3 - 1, par4) != this || par1World.getBlock(par2, par3 - 2, par4) != this)
        	{
                par1World.setBlockToAir(par2, par3, par4);
        	}
        }
    }
    
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        if(par1World.getBlockMetadata(par2, par3, par4) < 4)
        {
	        par1World.setBlock(par2, par3 + 1, par4, this, 4, 2);
	        par1World.setBlock(par2, par3 + 2, par4, this, 5, 2);
        }
    }
    
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        if(par5EntityPlayer.isSneaking() || par1World.getBlockMetadata(par2, par3, par4) > 3)
        {
        	return false;
        }
        else
        {
        	if(par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.diamond)
        	{
        		boolean flag = false;
        		for(Object obj : par1World.loadedEntityList)
        		{
        			if(obj instanceof EntityFrostWing && ((Entity)obj).isEntityAlive())
        			{
        				flag = true;
        			}
        		}
        		if(flag)
        		{
        			par5EntityPlayer.addChatComponentMessage(new ChatComponentText("<Statue> There can only be one Frost Wing and it is already being fought somewhere."));
        		}
        		else
        		{
        			par5EntityPlayer.inventory.consumeInventoryItem(Items.diamond);
        			EntityFrostWing entity = new EntityFrostWing(par1World, par2, par3, par4);
        			int x = par2;
        			int z = par4;
        			int y = par1World.getActualHeight() - 24;
        			entity.setPosition(x, y, z);
        			WorldGenFrostWingTower.generateFrostWingCylinder(par1World, x, z);
        			par1World.spawnEntityInWorld(entity);
        			for(Object obj : par1World.playerEntities)
        			{
        				if(obj instanceof EntityPlayerMP)
        				{
        					EntityPlayerMP player = (EntityPlayerMP)obj;
        					int dx = par2 - (int)Math.floor(player.posX);
        					int dy = par3 - (int)Math.floor(player.posY);
        					int dz = par4 - (int)Math.floor(player.posZ);
        					if(Math.sqrt(dx * dx + dy * dy + dz * dz) < 10.0D)
        					{
        						player.mountEntity(null);
        						player.addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 4));
        						player.setPositionAndUpdate(x, y, z);
        					}
        				}
        			}
        		}
        	}
        	return true;
        }
    }
    
}
