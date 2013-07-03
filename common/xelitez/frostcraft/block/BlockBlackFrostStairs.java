package xelitez.frostcraft.block;

import xelitez.frostcraft.registry.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockBlackFrostStairs extends BlockStairs{

	public BlockBlackFrostStairs(int par1, Block par2Block, int par3) 
	{
		super(par1, par2Block, par3);
		this.slipperiness = 0.7F;
        this.setCreativeTab(CreativeTabs.FCMiscItems);
	}

}
