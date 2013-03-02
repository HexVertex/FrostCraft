package xelitez.frostcraft.plugins;

import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.registry.Settings;
import xelitez.updateutility.IXEZUpdate;

public class Update implements IXEZUpdate
{

	@Override
	public String getCurrentVersion() 
	{
		return FrostCraft.instance.version.getVersion() + " for " + FrostCraft.instance.version.MC;
	}

	@Override
	public String getNewVersion() 
	{
		return FrostCraft.instance.version.newVersion;
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
		return FrostCraft.instance.version.available;
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
