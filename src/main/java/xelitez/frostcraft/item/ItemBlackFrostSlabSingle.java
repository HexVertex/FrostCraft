package xelitez.frostcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import xelitez.frostcraft.registry.IdMap;

public class ItemBlackFrostSlabSingle extends ItemSlab{

	public ItemBlackFrostSlabSingle(Block block) 
	{
		super(block, IdMap.blockBlackFrostSingleSlabSet, IdMap.blockBlackFrostDoubleSlabSet, false);
	}

}
