package xelitez.frostcraft.block;

import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;
import xelitez.frostcraft.registry.IdMap;

public class BlockBlackFrostFence extends BlockFence implements IBlackFrost{

	public BlockBlackFrostFence(Material par3Material) 
	{
		super("", par3Material);
        this.slipperiness = 0.7F;
        this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}
	
	@Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        this.blockIcon = IdMap.blockBlackFrost.getIcon(0, 0);
    }
    
	@Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
	@Override
    public IIcon getIcon(int par1, int par2)
    {
        return ((BlockBlackFrost)IdMap.blockBlackFrost).icons[par2 % ((BlockBlackFrost)IdMap.blockBlackFrost).icons.length];
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
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
    	return true;
    }

	@Override
	public boolean getsIgnoredByRenderEffects(IBlockAccess access, int par1, int par2, int par3, int side) 
	{
		return true;
	}
	
}
