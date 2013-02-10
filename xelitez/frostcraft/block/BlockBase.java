package xelitez.frostcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBase extends Block
{

	public BlockBase(int id, int texture, Material material) {
		super(id, texture, material);	
	}
	
	public BlockBase(int id, Material material) {
		super(id, material);	
	}
	
    public String getTextureFile()
    {
        return "/xelitez/frostcraft/textures/Blocks_0.png";
    }

}
