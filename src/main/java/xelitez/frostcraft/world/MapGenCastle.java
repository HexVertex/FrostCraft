package xelitez.frostcraft.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.util.ForgeDirection;
import xelitez.frostcraft.SaveHandler;

public class MapGenCastle extends MapGenStructure
{
	private static List<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
	
	private int[] spawnCoords = new int[3];
	@Override
	public String func_143025_a() {
		// TODO Auto-generated method stub
		return null;
	}
	
	static
	{
		biomeList.add(BiomeGenBase.iceMountains);
		biomeList.add(BiomeGenBase.icePlains);
		biomeList.add(BiomeGenBase.coldTaiga);
		biomeList.add(BiomeGenBase.coldTaigaHills);
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) 
	{
		Random random = new Random();
		int centerX = chunkX * 16 + random.nextInt(16);
		int centerZ = chunkZ * 16 + random.nextInt(16);
		if(!biomeList.contains(this.worldObj.getBiomeGenForCoords(centerX, centerZ)))
		{
			return false;
		}
		NBTTagCompound nbt = SaveHandler.getTagCompound(this.worldObj, false);
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
		for(Integer int1 : getPossibleYPos(this.worldObj, centerX, centerZ))
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
				return true;
			}
		}
		return false;
	}
	
	private static List<Integer> getPossibleYPos(World world, int x, int z)
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

	@Override
	protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) 
	{
		NBTTagCompound nbt = SaveHandler.getTagCompound(this.worldObj, false);
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
		public Start(int x, int y, int z)
		{
			StructureCastlePieces pieces = new StructureCastlePieces();
			pieces.createGrid();
			pieces.addOffset(x, y, z);
			pieces.gridToList(components);
		}
	}
}
