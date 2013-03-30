package xelitez.frostcraft.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockBaseContainer extends BlockContainer
{

	protected BlockBaseContainer(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
    public String getTextureFile()
    {
        return "/mods/FrostCraft/textures/Blocks_0.png";
    }
}
