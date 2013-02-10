package xelitez.updateutility;

public interface IXEZUpdate 
{
	public String getCurrentVersion();
	
	public String getNewVersion();
	
	public void checkForUpdates();
	
	public boolean doesModCheckForUpdates();
}
