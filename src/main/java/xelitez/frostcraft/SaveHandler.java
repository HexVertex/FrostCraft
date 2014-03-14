package xelitez.frostcraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SaveHandler 
{
	private static NBTTagCompound tagCompound = null;
	
	public static NBTTagCompound getTagCompound(World world, boolean forceReload)
	{
		if(tagCompound == null || forceReload)
		{
			if(world.getSaveHandler() instanceof net.minecraft.world.storage.SaveHandler)
			{
				File file = new File(((net.minecraft.world.storage.SaveHandler) world.getSaveHandler()).getWorldDirectory(), "xelitez/data.dat");
				if(file.exists())
				{
					try
					{
						tagCompound = CompressedStreamTools.readCompressed(new FileInputStream(file));
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					tagCompound = new NBTTagCompound();
				}
			}
		}
		return tagCompound;
	}
	
	public static void saveTagCompound(World world)
	{
		File file1 = new File(((net.minecraft.world.storage.SaveHandler) world.getSaveHandler()).getWorldDirectory(), "xelitez");
		File file = new File(file1, "data.dat");
		file1.mkdirs();
		if(tagCompound != null)
		{
			if(file.exists())
			{
				file.delete();
			}
			try
			{
					CompressedStreamTools.writeCompressed(tagCompound, new FileOutputStream(file));
					tagCompound = null;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
