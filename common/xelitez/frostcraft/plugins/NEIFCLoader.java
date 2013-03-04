package xelitez.frostcraft.plugins;

import xelitez.frostcraft.registry.IdMap;
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
		API.hideItem(IdMap.itemParticleItem.itemID);
	}
}
