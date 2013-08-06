package xelitez.frostcraft.plugins;

import codechicken.nei.api.API;
import cpw.mods.fml.common.FMLCommonHandler;

public class NEIFCLoader 
{
	public static void register()
	{
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			API.registerRecipeHandler(new FreezerRecipeHandler());
			API.registerUsageHandler(new FreezerRecipeHandler());
			API.registerRecipeHandler(new EnforcerRecipeHandler());
			API.registerUsageHandler(new EnforcerRecipeHandler());
		}
	}
}
