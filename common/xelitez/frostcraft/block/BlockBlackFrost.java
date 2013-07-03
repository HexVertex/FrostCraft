package xelitez.frostcraft.block;

import java.util.List;

import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockBlackFrost extends BlockBase
{
	public Icon[] icons;
	public String[] iconStrings = new String[] {"blackfrost", "blackfrost_cracked", "blackfrost_brick"};

	public BlockBlackFrost(int id) 
	{
		super(id, Material.ice);
        this.slipperiness = 0.7F;
        this.setCreativeTab(CreativeTabs.FCMiscItems);
	}
	
    public Icon getIcon(int par1, int par2)
    {
        return this.icons[par2 % this.icons.length];
    }

    public int getRenderBlockPass()
    {
        return 1;
    }
    
    public int getMobilityFlag()
    {
        return 0;
    }
    
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 3; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[3];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("FrostCraft:" + iconStrings[i]);
        }
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
    	return true;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return i1 == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
