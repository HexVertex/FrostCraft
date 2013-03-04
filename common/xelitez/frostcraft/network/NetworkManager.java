package xelitez.frostcraft.network;

import xelitez.frostcraft.Version;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class NetworkManager implements IConnectionHandler
{
	private boolean notify = false;
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) 
	{
    	if (Version.notify && !this.notify && !Version.registered)
    	{
    		clientHandler.getPlayer().addChatMessage("\u00a7eFrostcraft failed to register to the XEliteZ UpdateUtility. You should download it if you can. You can disable this message in the config file.");
    		this.notify = true;
    	}
        if (!Version.registered && Version.available)
        {
            clientHandler.getPlayer().addChatMessage("A new version of the \u00a7bFrostCraft mod\u00a7f is available (" + Version.color + Version.newVersion + "\u00a7f).");
        }
	}

}
