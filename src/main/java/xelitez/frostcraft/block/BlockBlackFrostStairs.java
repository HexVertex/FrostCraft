package xelitez.frostcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

public class BlockBlackFrostStairs extends BlockStairs implements IBlackFrost
{

	public BlockBlackFrostStairs(Block par1Block, int par2) 
	{
		super(par1Block, par2);
		this.slipperiness = 0.7F;
        this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}

	@Override
	public boolean getsIgnoredByRenderEffects(IBlockAccess access, int par1, int par2, int par3, int side) 
	{
		int meta = access.getBlockMetadata(par1, par2, par3);
		if(meta <= 3 && side == 1) return false;
		if(meta > 3 && side == 0) return false;
		if(meta % 4 == 0 && side == 4) return false;
		if(meta % 4 == 1 && side == 5) return false;
		if(meta % 4 == 2 && side == 2) return false;
		if(meta % 4 == 3 && side == 3) return false;
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
        Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
		if(meta <= 3 && par5 == 0) ignoredSide = true;
		if(meta > 3 && par5 == 1) ignoredSide = true;
		if(meta % 4 == 0 && par5 == 5) ignoredSide = true;
		if(meta % 4 == 1 && par5 == 4) ignoredSide = true;
		if(meta % 4 == 2 && par5 == 3) ignoredSide = true;
		if(meta % 4 == 3 && par5 == 2) ignoredSide = true;
        return i1 instanceof IBlackFrost && !((IBlackFrost)i1).getsIgnoredByRenderEffects(par1IBlockAccess, par2, par3, par4, par5) && ignoredSide ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

}
