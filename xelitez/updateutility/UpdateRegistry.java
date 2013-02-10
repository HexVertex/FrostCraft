package xelitez.updateutility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;

public class UpdateRegistry 
{
	private static List mods = new ArrayList();
	
	private static UpdateRegistry instance = new UpdateRegistry();
	
	public static UpdateRegistry instance()
	{
		return instance;
	}
	
	public static void addMod(Object mod, IXEZUpdate update)
	{
		ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);
		if(mc == null)
		{
			XEZUpdate.instance.log.log(Level.WARNING, new StringBuilder().append("Invalid mod trying to add(").append(mod).append(")").toString());
		}
		else
		{
			instance.mods.add(new ModInstance(mc, update));
		}
	}
	
	public int getModAmount()
	{
		return mods.size();
	}
	
	public void updateModVersionData()
	{
		for(int i = 0;i < mods.size();i++)
		{
			this.getModCurrentVersion(i);
			this.getModNewVersion(i);
		}
	}
	
	public void checkForUpdates()
	{
		new Thread()
		{
			public void run()
			{
				for(int i = 0;i < mods.size();i++)
				{
					try
					{
					XEZUpdate.instance.log.info("Checking for updates of " + ((ModInstance)mods.get(i)).mod.getName() + "...");
					((ModInstance)mods.get(i)).update.checkForUpdates();
					} catch(Exception e)
					{
						XEZUpdate.instance.log.warning("Unable to check for updates of " + ((ModInstance)mods.get(i)).mod.getName());
					}
				}
			}
		}.start();
	}
	
	public String getModCurrentVersion(int i)
	{
		return ((ModInstance)mods.get(i)).update.getCurrentVersion();
	}
	
	public String getModNewVersion(int i)
	{
		return ((ModInstance)mods.get(i)).update.getNewVersion();
	}
	
	public String getModName(int i)
	{
		return ((ModInstance)mods.get(i)).mod.getName();
	}
	
	private static class ModInstance
	{
		ModContainer mod;
		IXEZUpdate update;
		
		public ModInstance(ModContainer mod, IXEZUpdate update)
		{
			this.mod = mod;
			this.update = update;
		}
	}
}
