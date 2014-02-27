package xelitez.frostcraft;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.event.world.WorldEvent;
import xelitez.frostcraft.world.WorldAccess;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventListener implements IEventListener
{
	@SubscribeEvent
	public void invoke(Event event) 
	{
		if(event instanceof WorldEvent.Load)
		{
			World world = ((WorldEvent.Load)event).world;
			if(world.isRemote)
			{
				WorldAccess.instance().setWorldAndLoadRenderers((WorldClient)world);
			}
			if(world.provider instanceof WorldProviderSurface)
			{
				SaveHandler.getTagCompound(world, true);
			}
		}
		if(event instanceof WorldEvent.Save)
		{
			SaveHandler.saveTagCompound(((WorldEvent.Save)event).world);
		}	
	}

}
