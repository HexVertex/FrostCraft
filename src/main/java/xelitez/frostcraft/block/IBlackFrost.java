package xelitez.frostcraft.block;

import net.minecraft.world.IBlockAccess;

public interface IBlackFrost 
{
	public boolean getsIgnoredByRenderEffects(IBlockAccess access, int par1, int par2, int par3, int side);
}
