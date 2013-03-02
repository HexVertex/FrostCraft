package xelitez.frostcraft.world;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.world.WorldType;

public class WorldTypeBase extends WorldType
{
	public WorldTypeBase(int par1, String par2Str) 
	{
		super(par1, par2Str);
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			LanguageRegistry.instance().addStringLocalization(this.getTranslateName(), par2Str);
		}
	}

	public WorldTypeBase(int par1, String par2Str, int par3) 
	{
		super(par1, par2Str, par3);
	}
	
	public static int getFreeId()
	{
		for(int i = 0;i < WorldType.worldTypes.length;i++)
		{
			if(WorldType.worldTypes[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
}
