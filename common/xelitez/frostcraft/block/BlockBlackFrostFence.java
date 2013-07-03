package xelitez.frostcraft.block;

import java.util.List;

import xelitez.frostcraft.registry.CreativeTabs;
import xelitez.frostcraft.registry.IdMap;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBlackFrostFence extends BlockFence{

	public BlockBlackFrostFence(int par1, Material par3Material) 
	{
		super(par1, "", par3Material);
        this.slipperiness = 0.7F;
        this.setCreativeTab(CreativeTabs.FCMiscItems);
	}
	
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = IdMap.blockBlackFrost.getIcon(0, 0);
    }
    
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    public Icon getIcon(int par1, int par2)
    {
        return ((BlockBlackFrost)IdMap.blockBlackFrost).icons[par2 % ((BlockBlackFrost)IdMap.blockBlackFrost).icons.length];
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int par1, net.minecraft.creativetab.CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 3; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
    
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
    	return true;
    }
}
