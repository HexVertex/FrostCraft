package xelitez.frostcraft.client.model.rotations;

import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRotationAssistant 
{
	private EntryList entries;
	
	public ModelRotationAssistant(Class<? extends ModelBase> model)
	{
		if(FMLCommonHandler.instance().getSide().isClient())
		{
			entries = new EntryList();
			for(Field field : model.getFields())
			{
				if(field.getType() == ModelRenderer.class || field.getType().isAssignableFrom(ModelRenderer.class))
				{
					entries.addEntry(field.getName());
				}
			}
			for(Field field : model.getDeclaredFields())
			{
				if(field.getType() == ModelRenderer.class || field.getType().isAssignableFrom(ModelRenderer.class))
				{
					entries.addEntry(field.getName());
				}
			}
		}
	}
	
	public EntryList getEntryList()
	{
		return entries;
	}
}
