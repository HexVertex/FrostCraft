package xelitez.updateutility;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(	
		modid = "XEZUpdate",
		name = "XEliteZ Update Utility",
		acceptedMinecraftVersions = "[1.4.7]",
		version = "1.0.0")
public class XEZUpdate 
{
	@Instance(value = "XEZUpdate")
	public static XEZUpdate instance;
	
	public Logger log;
	
	@PreInit
    public void preload(FMLPreInitializationEvent evt)
    {
		log = Logger.getLogger("XEZUpdateUtility");
		log.setParent(FMLLog.getLogger());
    }
	
	@PostInit
    public void postload(FMLPostInitializationEvent evt)
    {
		UpdateRegistry.instance().checkForUpdates();
    }
}
