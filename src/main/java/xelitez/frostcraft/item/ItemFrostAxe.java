package xelitez.frostcraft.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemFrostAxe extends ItemFrostTool 
{
    public static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

    public ItemFrostAxe(ToolMaterial par1EnumToolMaterial)
    {
        super(3, par1EnumToolMaterial, blocksEffectiveAgainst);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.getMaterial() == Material.wood || par2Block.getMaterial() == Material.plants || par2Block.getMaterial() == Material.vine) ? this.efficiencyOnProperMaterial : super.func_150893_a(par1ItemStack, par2Block);
    }
}
