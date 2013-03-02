package xelitez.frostcraft.plugins;

import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.Version;
import xelitez.frostcraft.registry.Settings;
import xelitez.updateutility.IXEZUpdate;

public class Update implements IXEZUpdate
{

	@Override
	public String getCurrentVersion() 
	{
		return Version.getVersion() + " for " + Version.MC;
	}

	@Override
	public String getNewVersion() 
	{
		return Version.newVersion;
	}

	@Override
	public void checkForUpdates() 
	{
		FrostCraft.instance.version.checkForUpdates();
	}

	@Override
	public boolean doesModCheckForUpdates() 
	{
		return Settings.checkForUpdates;
	}

	@Override
	public boolean isUpdateAvailable() 
	{
		return Version.available;
	}

	@Override
	public String getModIcon() 
	{
		return "/xelitez/frostcraft/textures/xezmods.png";
	}

	@Override
	public String getUpdateUrl() 
	{
		return null;
	}

	@Override
	public String getDownloadUrl() 
	{
		return null;
	}

}
