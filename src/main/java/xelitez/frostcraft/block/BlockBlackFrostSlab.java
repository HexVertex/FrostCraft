package xelitez.frostcraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xelitez.frostcraft.item.ItemBlackFrost;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlackFrostSlab extends BlockSlab implements IBlackFrost
{

	public BlockBlackFrostSlab(boolean par2, Material par3Material) 
	{
		super(par2, par3Material);
        this.slipperiness = 0.7F;
        if (!par2) this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}
	
	@Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
	@Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(IdMap.blockBlackFrostSingleSlabSet, 2, par1 & 3);
    }
    
	@Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(IdMap.blockBlackFrostSingleSlabSet);
    }
	
	@Override
    public IIcon getIcon(int par1, int par2)
    {
        return ((BlockBlackFrost)IdMap.blockBlackFrost).icons[par2 > 2 ? (par2 % 4 > 2 ? 0 : par2 % 4): par2];
    }
    
	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 3; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
    
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
	public String func_150002_b(int i) 
	{
        if (i < 0 || i >= ItemBlackFrost.name.length)
        {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + ItemBlackFrost.name[i];
	}
	
	@Override
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return super.getDamageValue(par1World, par2, par3, par4) & 3;
    }
    
	@Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return this.field_150004_a ? par9 : (par5 != 0 && (par5 == 1 || (double)par7 <= 0.5D) ? par9 : par9 | 4);
    }
    
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (this.field_150004_a)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
            boolean flag = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 4) != 0;

            if (flag)
            {
                this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

	@Override
	public boolean getsIgnoredByRenderEffects(IBlockAccess access, int par1, int par2, int par3, int side) 
	{
		int meta = access.getBlockMetadata(par1, par2, par3);
		if(this.field_150004_a) return false;
		if(meta < 3 && side == 1) return false;
		if(meta >= 3 && side == 0) return false;
		return true;
	}
	
	@Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		int x = par2;
		int y = par3;
		int z = par4;
		switch(par5)
		{
		case 0:
			y++;
			break;
		case 1:
			y--;
			break;
		case 2:
			z++;
			break;
		case 3:
			z--;
			break;
		case 4:
			x++;
			break;
		case 5:
			x--;
			break;
		}
		boolean ignoredSide = false;
		int meta = par1IBlockAccess.getBlockMetadata(x, y, z);
		if(meta < 3 && par5 != 1) ignoredSide = true;
		if(meta >= 3 && par5 != 0) ignoredSide = true;
        Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
        return i1 instanceof IBlackFrost && !((IBlackFrost)i1).getsIgnoredByRenderEffects(par1IBlockAccess, par2, par3, par4, par5) && ignoredSide ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
    
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return this == IdMap.blockBlackFrostSingleSlabSet ? Item.getItemFromBlock(this) : Item.getItemFromBlock(IdMap.blockBlackFrostSingleSlabSet);
    }

}
