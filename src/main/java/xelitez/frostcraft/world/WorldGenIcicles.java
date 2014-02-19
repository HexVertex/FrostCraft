package xelitez.frostcraft.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;
import xelitez.frostcraft.registry.IdMap;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenIcicles implements IWorldGenerator
{
	public List<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
	
	private static WorldGenIcicles instance = new WorldGenIcicles();
	
	public static WorldGenIcicles getInstance()
	{
		return instance;
	}
	
	public WorldGenIcicles()
	{
		biomeList.add(BiomeGenBase.frozenOcean);
		biomeList.add(BiomeGenBase.frozenRiver);
		biomeList.add(BiomeGenBase.iceMountains);
		biomeList.add(BiomeGenBase.icePlains);
		biomeList.add(BiomeGenBase.coldTaiga);
		biomeList.add(BiomeGenBase.coldTaigaHills);
		biomeList.add(BiomeGenBase.coldBeach);
		
	}
	
	public static List<Integer> getPossibleYPos(World world, int x, int z)
	{
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 30;i < world.getActualHeight() - 60;i++)
		{
			if(world.getBlock(x, i, z) != null && (world.getBlock(x, i, z).isSideSolid(world, x, i, z, ForgeDirection.DOWN) || world.getBlock(x, i, z) instanceof BlockLeaves) && world.getBlock(x, i - 1, z).getMaterial() == Material.air)
			{
				list.add(i - 1);
			}
		}
		return list;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		for(int i = 0; i < 12;i++)
		{
			int posX = chunkX * 16 + random.nextInt(16);
			int posZ = chunkZ * 16 + random.nextInt(16);
			if(biomeList.contains(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16)))
			{
				List<Integer> list = WorldGenIcicles.getPossibleYPos(world, posX, posZ);
				int posY;
				if(list.size() > 0)
				{
					int index = random.nextInt(list.size());
					if(index >= list.size())
					{
						index = list.size() - 1;
					}
					posY = list.get(index);
					world.setBlock(posX, posY, posZ, IdMap.blockIcicle);
				}
			}
		}
		
	}

}
