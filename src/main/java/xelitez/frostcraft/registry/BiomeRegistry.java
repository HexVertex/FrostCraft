package xelitez.frostcraft.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class BiomeRegistry 
{
	private static List<Object[]> biomeValues = new ArrayList<Object[]>();
	
	private static final BiomeRegistry reg = new BiomeRegistry();
	
	public static final BiomeRegistry getInstance() 
	{
		return reg;
	}
	
	static
	{
		addBiomeValue(BiomeGenBase.beach, 7);
		addBiomeValue(BiomeGenBase.desert, 0);
		addBiomeValue(BiomeGenBase.desertHills, 1);
		addBiomeValue(BiomeGenBase.extremeHills, 22);
		addBiomeValue(BiomeGenBase.extremeHillsEdge, 20);
		addBiomeValue(BiomeGenBase.forest, 14);
		addBiomeValue(BiomeGenBase.forestHills, 16);
		addBiomeValue(BiomeGenBase.frozenOcean, 29);
		addBiomeValue(BiomeGenBase.frozenRiver, 27);
		addBiomeValue(BiomeGenBase.hell, 0);
		addBiomeValue(BiomeGenBase.iceMountains, 28);
		addBiomeValue(BiomeGenBase.icePlains, 25);
		addBiomeValue(BiomeGenBase.jungle, 8);
		addBiomeValue(BiomeGenBase.jungleHills, 9);
		addBiomeValue(BiomeGenBase.mushroomIsland, 10);
		addBiomeValue(BiomeGenBase.mushroomIslandShore, 11);
		addBiomeValue(BiomeGenBase.ocean, 16);
		addBiomeValue(BiomeGenBase.plains, 10);
		addBiomeValue(BiomeGenBase.river, 11);
		addBiomeValue(BiomeGenBase.sky, 24);
		addBiomeValue(BiomeGenBase.taiga, 28);
		addBiomeValue(BiomeGenBase.taigaHills, 29);
	}
	
	public static boolean addBiomeValue(BiomeGenBase biome, int value)
	{
		for(int i = 0;i < biomeValues.size();i++)
		{
			Object[] obj = (Object[])biomeValues.get(i);
			if(obj[0] == biome)
			{
				FMLLog.log(Level.INFO, new StringBuilder().append("Value for biome \"").append(biome.biomeName).append("\" was already added... won't override").toString());
				return false;
			}
		}
		biomeValues.add(new Object[] {biome , value});
		return true;
	}
	
	public static int getBiomeValue(BiomeGenBase biome)
	{
		for(int i = 0;i < biomeValues.size();i++)
		{
			Object[] obj = (Object[])biomeValues.get(i);
			BiomeGenBase storedBiome = (BiomeGenBase)obj[0];
			if(storedBiome.biomeID == biome.biomeID)
			{
				return (Integer)obj[1];
			}
		}
		return 10;
	}
	
	public static int getBiomeValue(World world, int x, int z)
	{
		return getBiomeValue(world.getBiomeGenForCoords(x, z));
	}
}
