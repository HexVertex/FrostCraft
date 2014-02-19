package xelitez.frostcraft.world;

import net.minecraft.world.WorldType;
import cpw.mods.fml.common.FMLCommonHandler;

public class WorldTypeBase extends WorldType
{
	public WorldTypeBase(String par2Str) 
	{
		super(par2Str);
		if(FMLCommonHandler.instance().getSide().isClient())
		{
//			LanguageRegistry.instance().addStringLocalization(this.getTranslateName(), par2Str);
		}
	}
}
