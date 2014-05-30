package xelitez.frostcraft.client.model.rotations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class EntryList 
{
	private HashMap<String, Entry> entries = new HashMap<String, Entry>();
	
	public void addEntry(String name)
	{
		entries.put(name, new Entry());
	}
	
	public Entry getEntry(String name)
	{
		for(String string : entries.keySet())
		{
			if(string.matches(name))
			{
				return entries.get(string);
			}
		}
		System.err.println("No entry found with name: " + name);
		return null;
	}
	
	public Collection<Entry> getAllEntries()
	{
		return entries.values();
	}
	
	public Set<String> getAllKeys()
	{
		return entries.keySet();
	}
	
	public class Entry
	{
		private float rotationX = 0.0F;
		private float rotationY = 0.0F;
		private float rotationZ = 0.0F;
		
		private float targetRotationX = 0.0F;
		private float targetRotationY = 0.0F;
		private float targetRotationZ = 0.0F;
		
		private float startRotationX = 0.0F;
		private float startRotationY = 0.0F;
		private float startRotationZ = 0.0F;
		
		private long targetTimeX = 0L;
		private long targetTimeY = 0L;
		private long targetTimeZ = 0L;
		
		private long startTimeX = 0L;
		private long startTimeY = 0L;
		private long startTimeZ = 0L;
		
		
		public float getRotation(EnumAxis ax)
		{
			if(ax == EnumAxis.X)
			{
				float rotationChange = targetRotationX - startRotationX;
				float progress = targetTimeX < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeX) / (float)(targetTimeX - startTimeX);
				rotationX = targetTimeX < System.currentTimeMillis() ? targetRotationX : startRotationX + rotationChange * progress;
				return rotationX * (float)Math.PI;
			}
			if(ax == EnumAxis.Y)
			{
				float rotationChange = targetRotationY - startRotationY;
				float progress = targetTimeY < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeY) / (float)(targetTimeY - startTimeY);
				rotationY = targetTimeY < System.currentTimeMillis() ? targetRotationY : startRotationY + rotationChange * progress;
				return rotationY * (float)Math.PI;
			}
			if(ax == EnumAxis.Z)
			{
				float rotationChange = targetRotationZ - startRotationZ;
				float progress = targetTimeZ < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeZ) / (float)(targetTimeZ - startTimeZ);
				rotationZ = targetTimeZ < System.currentTimeMillis() ? targetRotationZ : startRotationZ + rotationChange * progress;
				return rotationZ * (float)Math.PI;
			}
			return 0.0F;
		}
		
		public float getRawRotation(EnumAxis ax)
		{
			if(ax == EnumAxis.X)
			{
				float rotationChange = targetRotationX - startRotationX;
				float progress = targetTimeX < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeX) / (float)(targetTimeX - startTimeX);
				rotationX = targetTimeX < System.currentTimeMillis() ? targetRotationX : startRotationX + rotationChange * progress;
				return rotationX;
			}
			if(ax == EnumAxis.Y)
			{
				float rotationChange = targetRotationY - startRotationY;
				float progress = targetTimeY < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeY) / (float)(targetTimeY - startTimeY);
				rotationY = targetTimeY < System.currentTimeMillis() ? targetRotationY : startRotationY + rotationChange * progress;
				return rotationY;
			}
			if(ax == EnumAxis.Z)
			{
				float rotationChange = targetRotationZ - startRotationZ;
				float progress = targetTimeZ < System.currentTimeMillis() ? 1.0F : (float)(System.currentTimeMillis() - startTimeZ) / (float)(targetTimeZ - startTimeZ);
				rotationZ = targetTimeZ < System.currentTimeMillis() ? targetRotationZ : startRotationZ + rotationChange * progress;
				return rotationZ;
			}
			return 0.0F;
		}
		
		public void setRotation(EnumAxis ax, float rotation)
		{
			if(ax == EnumAxis.X)
			{
				startRotationX = getRawRotation(ax);
				targetRotationX = rotation;
				targetTimeX = 0L;
			}
			if(ax == EnumAxis.Y)
			{
				startRotationY = getRawRotation(ax);
				targetRotationY = rotation;
				targetTimeY = 0L;
			}
			if(ax == EnumAxis.Z)
			{
				startRotationZ = getRawRotation(ax);
				targetRotationZ = rotation;
				targetTimeZ = 0L;
			}
		}
		
		public void addRotation(EnumAxis ax, float rotation, int duration)
		{
			if(ax == EnumAxis.X)
			{
				startRotationX = getRawRotation(ax);
				targetRotationX = rotation;
				startTimeX = System.currentTimeMillis();
				targetTimeX = System.currentTimeMillis() + duration;
			}
			if(ax == EnumAxis.Y)
			{
				startRotationY = getRawRotation(ax);
				targetRotationY = rotation;
				startTimeY = System.currentTimeMillis();
				targetTimeY = System.currentTimeMillis() + duration;
			}
			if(ax == EnumAxis.Z)
			{
				startRotationZ = getRawRotation(ax);
				targetRotationZ = rotation;
				startTimeZ = System.currentTimeMillis();
				targetTimeZ = System.currentTimeMillis() + duration;
			}
		}
		
		public boolean isDoneRotating(EnumAxis ax)
		{
			if(ax == EnumAxis.X)
			{
				return targetTimeX < System.currentTimeMillis();
			}
			if(ax == EnumAxis.Y)
			{
				return targetTimeY < System.currentTimeMillis();
			}
			if(ax == EnumAxis.Z)
			{
				return targetTimeZ < System.currentTimeMillis();
			}
			return false;
		}
		
	}
	
}
