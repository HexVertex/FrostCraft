package xelitez.frostcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import xelitez.frostcraft.registry.FrostcraftCreativeTabs;

public class BlockBlackFrostStairs extends BlockStairs{

	public BlockBlackFrostStairs(Block par1Block, int par2) 
	{
		super(par1Block, par2);
		this.slipperiness = 0.7F;
        this.setCreativeTab(FrostcraftCreativeTabs.FCMiscItems);
	}

}
