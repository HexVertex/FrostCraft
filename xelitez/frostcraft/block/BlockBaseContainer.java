package xelitez.frostcraft.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockBaseContainer extends BlockContainer
{

	protected BlockBaseContainer(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
    public String getTextureFile()
    {
        return "/xelitez/frostcraft/textures/Blocks_0.png";
    }
}
