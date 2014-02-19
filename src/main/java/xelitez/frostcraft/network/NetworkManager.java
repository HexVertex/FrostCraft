package xelitez.frostcraft.network;

import net.minecraft.util.ChatComponentText;
import xelitez.frostcraft.Version;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class NetworkManager
{
	private boolean notify = false;

	@SubscribeEvent
	public void onConnect(PlayerEvent.PlayerLoggedInEvent evt) 
	{
    	if (Version.notify && !this.notify && !Version.registered)
    	{
    		evt.player.addChatMessage(new ChatComponentText("\u00a7eFrostcraft failed to register to the XEliteZ UpdateUtility. You should download it if you can. You can disable this message in the config file."));
    		this.notify = true;
    	}
        if (!Version.registered && Version.available)
        {
            evt.player.addChatMessage(new ChatComponentText("A new version of the \u00a7bFrostcraft mod\u00a7f is available (" + Version.color + Version.newVersion + "\u00a7f)."));
        }
	}

}
