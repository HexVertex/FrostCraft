package xelitez.frostcraft.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlackFrost extends Block
{
	public IIcon[] icons;
	public String[] iconStrings = new String[] {"blackfrost", "blackfrost_cracked", "blackfrost_brick"};

	public BlockBlackFrost() 
	{
		super(Material.ice);
        this.slipperiness = 0.7F;
        this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}
	
	@Override
    public IIcon getIcon(int par1, int par2)
    {
        return this.icons[par2 % this.icons.length];
    }

	@Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
	@Override
    public int getMobilityFlag()
    {
        return 0;
    }
    
	@Override
    public int damageDropped(int par1)
    {
        return par1;
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
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.icons = new IIcon[3];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IIconRegister.registerIcon("Frostcraft:" + iconStrings[i]);
        }
    }
    
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
    	return true;
    }
    
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
	@Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
        return i1 == this ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
