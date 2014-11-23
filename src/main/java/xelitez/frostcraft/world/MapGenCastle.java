package xelitez.frostcraft.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.util.ForgeDirection;
import xelitez.frostcraft.SaveHandler;
import cpw.mods.fml.common.IWorldGenerator;

public class MapGenCastle implements IWorldGenerator
{
	private List<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
	
	private int[] spawnCoords = new int[3];
	
	public MapGenCastle()
	{
		biomeList.add(BiomeGenBase.iceMountains);
		biomeList.add(BiomeGenBase.icePlains);
		biomeList.add(BiomeGenBase.coldTaiga);
		biomeList.add(BiomeGenBase.coldTaigaHills);
	}
	
	private List<Integer> getPossibleYPos(World world, int x, int z)
	{
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 50;i < world.getActualHeight() - 50;i++)
		{
			if(world.getBlock(x, i - 1, z) != null && (world.getBlock(x, i - 1, z).isSideSolid(world, x, i - 1, z, ForgeDirection.UP)) && world.getBlock(x, i, z).getMaterial() == Material.air)
			{
				list.add(i);
			}
		}
		return list;
	}

	private Start getStructureStart(World world) 
	{
		NBTTagCompound nbt = SaveHandler.getTagCompound(world, false);
		NBTTagList list = null;
		if(nbt.hasKey("Castles"))
		{
			list = nbt.getTagList("Castles", 10);
		}
		if(list == null)
		{
			list = new NBTTagList();
		}
		NBTTagCompound nbt2 = new NBTTagCompound();
		nbt2.setInteger("xCoord", spawnCoords[0]);
		nbt2.setInteger("yCoord", spawnCoords[1]);
		nbt2.setInteger("zCoord", spawnCoords[2]);
		list.appendTag(nbt2);
		nbt.setTag("Castles", list);
		return new Start(spawnCoords[0], spawnCoords[1], spawnCoords[2]);
	}
	
	public static class Start extends StructureStart
	{
		StructureCastlePieces pieces;
		
		public Start(int x, int y, int z)
		{
			pieces = new StructureCastlePieces();
			pieces.createGrid();
			pieces.addOffset(x, y, z);
			components = pieces.gridToList(components);
		}
		
		@SuppressWarnings("rawtypes")
		@Override
	    public void generateStructure(World p_75068_1_, Random p_75068_2_, StructureBoundingBox p_75068_3_)
	    {
	        Iterator iterator = this.components.iterator();

	        while (iterator.hasNext())
	        {
	        	pieces.setRotateOffset((byte)0);
	        	StructureComponent structurecomponent = (StructureComponent)iterator.next();
	        	if (!structurecomponent.addComponentParts(p_75068_1_, p_75068_2_, structurecomponent.getBoundingBox()))
	        	{
	        		iterator.remove();
	        	}
	        }
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		int centerX = chunkX * 16 + random.nextInt(16);
		int centerZ = chunkZ * 16 + random.nextInt(16);
		if(!biomeList.contains(world.getBiomeGenForCoords(centerX, centerZ)))
		{
			return;
		}
		NBTTagCompound nbt = SaveHandler.getTagCompound(world, false);
		NBTTagList list = null;
		if(nbt.hasKey("Castles"))
		{
			list = nbt.getTagList("Castles", 10);
		}
		if(list == null)
		{
			list = new NBTTagList();
		}
		List<Integer[]> intlist = new ArrayList<Integer[]>();
		if(list != null)
		{
			for(int var0 = 0;var0 < list.tagCount();var0++)
			{
				NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(var0);
				Integer[] coords = new Integer[] {nbt1.getInteger("xCoord"), nbt1.getInteger("yCoord"), nbt1.getInteger("zCoord")};
				intlist.add(coords);
			}
		}

		int centerY = 0;
		for(Integer int1 : getPossibleYPos(world, centerX, centerZ))
		{
			if(int1 > 50 && random.nextInt(6) == 2)
			{
				centerY = int1;
			}
		}

		if(centerY != 0)
		{
			boolean canGenerate = true;
			for(Integer[] ints : intlist)
			{
				int dx = ints[0] - centerX;
				int dy = ints[1] - centerY;
				int dz = ints[2] - centerZ;
				if(Math.sqrt(dx * dx + dy * dy + dz * dz) < 750)
				{
					canGenerate = false;
				}
			}

			if(canGenerate)
			{
				this.spawnCoords = new int[] {centerX, centerY, centerZ};
				Start start = this.getStructureStart(world);
				start.generateStructure(world, random, null);
			}
		}
	}
	
}
