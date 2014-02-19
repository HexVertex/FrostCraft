package xelitez.frostcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import xelitez.frostcraft.registry.IdMap;

public class ItemBlackFrostSlabDouble extends ItemSlab
{

	public ItemBlackFrostSlabDouble(Block block) 
	{
		super(block, IdMap.blockBlackFrostSingleSlabSet, IdMap.blockBlackFrostDoubleSlabSet, false);
	}

}
