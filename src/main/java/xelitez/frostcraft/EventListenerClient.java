package xelitez.frostcraft;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderLivingEvent;
import xelitez.frostcraft.registry.IdMap;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventListenerClient  implements IEventListener
{
	private HashMap<EntityPlayer, ModelBiped[]> modelCache = new HashMap<EntityPlayer, ModelBiped[]>();
	
	@SubscribeEvent
	public void invoke(Event event) 
	{
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
							ModelBiped[] models = this.getModelCache((EntityPlayer)evt.entity, (RenderPlayer)evt.renderer);
							if(models != null)
							{
								models[0].heldItemRight = 2;
								models[1].heldItemRight = 2;
								models[2].heldItemRight = 2;
							}
						}
					}
					if(item != null && IdMap.itemSpear == item.getItem())
					{
						ModelBiped[] models = this.getModelCache((EntityPlayer)evt.entity, (RenderPlayer)evt.renderer);
						if(models != null)
						{
							models[0].heldItemRight = 4;
							models[1].heldItemRight = 4;
							models[2].heldItemRight = 4;
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private ModelBiped[] getModelCache(EntityPlayer entity, RenderPlayer renderer)
	{
		if(modelCache.containsKey(entity))
		{
			return modelCache.get(entity);
		}
		else
		{
			ModelBiped[] model = null;
			try {
				Class<?> clazz = renderer.getClass();
				while(!clazz.getName().matches(RenderPlayer.class.getName()) && clazz.getSuperclass() != null)
				{
					clazz = clazz.getSuperclass();
				}
				if(!clazz.getName().matches(RenderPlayer.class.getName()))
				{
					return null;
				}
				model = new ModelBiped[3];
				Field field = clazz.getDeclaredFields()[1];
				field.setAccessible(true);
				model[0] = (ModelBiped) field.get(renderer);
				field = clazz.getDeclaredFields()[2];
				field.setAccessible(true);
				model[1] = (ModelBiped) field.get(renderer);
				field = clazz.getDeclaredFields()[3];
				field.setAccessible(true);
				model[2] = (ModelBiped) field.get(renderer);
				modelCache.put(entity, model);
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			return model;
		}
	}
	
}