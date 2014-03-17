package xelitez.frostcraft;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import xelitez.frostcraft.registry.IdMap;
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
		if(event instanceof RenderLivingEvent.Pre)
		{
			try
			{
				RenderLivingEvent.Pre evt = (RenderLivingEvent.Pre) event;
				if(evt.entity instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) evt.entity;
					ItemStack item = player.inventory.getCurrentItem();
					if(item != null && IdMap.itemCrossbow == item.getItem())
					{
						NBTTagCompound tag = item.getTagCompound();
						if(tag == null)
						{
							return;
						}
						if(tag.hasKey("loaded") && tag.getBoolean("loaded"))
						{
							Class<?> clazz = evt.renderer.getClass();
							Field field = clazz.getDeclaredFields()[1];
							field.setAccessible(true);
							ModelBiped model = (ModelBiped) field.get(evt.renderer);
							model.heldItemRight = 2;
							field = clazz.getDeclaredFields()[2];
							field.setAccessible(true);
							model = (ModelBiped) field.get(evt.renderer);
							model.heldItemRight = 2;
							field = clazz.getDeclaredFields()[3];
							field.setAccessible(true);
							model = (ModelBiped) field.get(evt.renderer);
							model.heldItemRight = 2;
						}
					}
					if(item != null && IdMap.itemSpear == item.getItem())
					{
						Class<?> clazz = evt.renderer.getClass();
						Field field = clazz.getDeclaredFields()[1];
						field.setAccessible(true);
						ModelBiped model = (ModelBiped) field.get(evt.renderer);
						model.heldItemRight = 4;
						field = clazz.getDeclaredFields()[2];
						field.setAccessible(true);
						model = (ModelBiped) field.get(evt.renderer);
						model.heldItemRight = 4;
						field = clazz.getDeclaredFields()[3];
						field.setAccessible(true);
						model = (ModelBiped) field.get(evt.renderer);
						model.heldItemRight = 4;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
