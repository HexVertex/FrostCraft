package xelitez.frostcraft.world;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class GenUtils 
{
	private static int minX = Integer.MIN_VALUE;
	private static int maxX = Integer.MAX_VALUE;
	private static int minY = Integer.MIN_VALUE;
	private static int maxY = Integer.MAX_VALUE;
	private static int minZ = Integer.MIN_VALUE;
	private static int maxZ = Integer.MAX_VALUE;
	
	private static int offsetX = 0;
	private static int offsetY = 0;
	private static int offsetZ = 0;
	
	private static int rotationMod = 0;
	
	private static boolean groundGen = false;
	private static Block groundBlock = null;
	private static int groundMeta = 0;
	
	public static void resetMinMax()
	{
		minX = Integer.MIN_VALUE;
		maxX = Integer.MAX_VALUE;
		minY = Integer.MIN_VALUE;
		maxY = Integer.MAX_VALUE;
		minZ = Integer.MIN_VALUE;
		maxZ = Integer.MAX_VALUE;
	}
	
	public static void resetOffset()
	{
		offsetX = 0;
		offsetY = 0;
		offsetZ = 0;
	}
	
	public static void resetRotation()
	{
		rotationMod = 0;
	}
	
	public static void resetGround()
	{
		groundGen = false;
		groundBlock = null;
		groundMeta = 0;
	}
	
	public static void resetAll()
	{
		resetMinMax();
		resetOffset();
		resetRotation();
		resetGround();
	}
	
	public static void setOffset(int X, int Y, int Z)
	{
		offsetX = X;
		offsetY = Y;
		offsetZ = Z;
	}
	
	public static void setGenLimit(int minimumX, int maximumX, int minimumY, int maximumY, int minimumZ, int maximumZ)
	{
		minX = minimumX;
		maxX = maximumX;
		minY = minimumY;
		maxY = maximumY;
		minZ = minimumZ;
		maxZ = maximumZ;	
	}
	
	public static void setRotation(int rotation)
	{
		rotationMod = rotation % 4;
	}
	
	public static boolean placeBlock(World world, int x, int y, int z, Block block, int meta)
	{
		if(x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ)
		{
			for(int i = 0;i < rotationMod;i++)
			{
				int[] ints = new int[] {z, y, -x};
				x = ints[0];
				y = ints[1];
				z = ints[2];
			}
			if(groundGen && groundBlock != null && y == 0)
			{
				generateGround(world, x + offsetX, y + offsetY - 1, z + offsetZ);
			}
			return world.setBlock(offsetX + x, offsetY + y, offsetZ + z, block, meta, 2);
		}
		return false;
	}
	
	private static void generateGround(World world, int x, int y, int z)
	{
		while(!world.getBlock(x, y, z).isBlockSolid(world, x, y, z, 0))
		{
			world.setBlock(x, y, z, groundBlock, groundMeta, 2);
			y--;
		}
	}
	
	public static void generateHCylinder(World world, int height, int radius, Block block, int meta)
	{
		for(int y = 0;y < height;y++)
		{
			int x = radius;
			int z = 0;
			int radiusError = 1-x;
			while(x >= z)
			{
				placeBlock(world, x, y, z, block, meta);
				placeBlock(world, z, y, x, block, meta);
				placeBlock(world, -x, y, z, block, meta);
				placeBlock(world, -z, y, x, block, meta);
				placeBlock(world, -x, y, -z, block, meta);
				placeBlock(world, -z, y, -x, block, meta);
				placeBlock(world, x, y, -z, block, meta);
				placeBlock(world, z, y, -x, block, meta);
				z++;
				if (radiusError<0)
				{
					radiusError += 2 * z + 1;
				}
				else
				{
					x--;
					radiusError += 2 * (z - x + 1);
				}
			}
		}
	}
	
	public static void generateCylinder(World world, int height, int radius, Block block, int meta)
	{
		for(int y = 0;y < height;y++)
		{
			int x = radius;
			int z = 0;
			int radiusError = 1-x;
			while(x >= z)
			{
				placeBlock(world, x, y, z, block, meta);
				placeBlock(world, z, y, x, block, meta);
				placeBlock(world, -x, y, z, block, meta);
				placeBlock(world, -z, y, x, block, meta);
				placeBlock(world, -x, y, -z, block, meta);
				placeBlock(world, -z, y, -x, block, meta);
				placeBlock(world, x, y, -z, block, meta);
				placeBlock(world, z, y, -x, block, meta);
				int i = x - 1;
				while(i >= z)
				{
					placeBlock(world, i, y, z, block, meta);
					placeBlock(world, -i, y, z, block, meta);
					placeBlock(world, i, y, -z, block, meta);
					placeBlock(world, -i, y, -z, block, meta);
					placeBlock(world, z, y, i, block, meta);
					placeBlock(world, -z, y, i, block, meta);
					placeBlock(world, z, y, -i, block, meta);
					placeBlock(world, -z, y, -i, block, meta);
					i--;
				}
				z++;
				if (radiusError<0)
				{
					radiusError += 2 * z + 1;
				}
				else
				{
					x--;
					radiusError += 2 * (z - x + 1);
				}
			}
		}
	}

	public static void setGroundGeneration(boolean b) 
	{
		groundGen = b;
	}

	public static void setGroundMaterial(Block block, int meta) 
	{
		groundBlock = block;
		groundMeta = meta;
	}
}
