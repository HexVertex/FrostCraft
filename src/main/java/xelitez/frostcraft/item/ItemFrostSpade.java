package xelitez.frostcraft.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.google.common.collect.Sets;

public class ItemFrostSpade extends ItemFrostTool
{
    public static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});

    public ItemFrostSpade(ToolMaterial par1ToolMaterial)
    {
        super(1, par1ToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Blocks.snow_layer ? true : par1Block == Blocks.snow;
    }
}
