package xelitez.frostcraft.world;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import xelitez.frostcraft.registry.IdMap;

public class WorldGenFrostWingTower
{
	
	private static void sendChatMsg(String Msg)
	{
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText("<Frostcraft Generator> " + Msg));
	}
	
//	private void generateSimple(Random random, int xCoord, int yCoord, int zCoord, World world)
//	{
//		for(int x = -7;x < 8;x++)
//		{
//			for(int z = -7;z < 8;z++)
//			{
//				for(int y = -1;y <= 20;y++)
//				{
//					if(x * x + z * z <= 7 * 7)
//					{
//						world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//						if(y == -1)
//						{
//							this.generateFloor(world, xCoord + x, yCoord + y - 1, zCoord + z);
//						}
//					}
//					if(x * x + z * z == 7 * 7)
//					{
//						if(!(Math.abs(x) < 2 && z < 0 && y < 3 && y >= 0) && !(x == 0 && y == 3 && z < 0) && y <= 18)
//						{					
//							world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//						}
//						if(y == 18)
//						{
//							if(Math.abs(x) == 1 || Math.abs(z) == 1 || Math.abs(x) == 3 || Math.abs(z) == 3)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostSingleSlabSet, 2, 2);
//							}
//							if(Math.abs(x) == 5 && Math.abs(z) == 5)
//							{
//								world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//							}
//						}
//					}
//					if(y == -1)
//					{
//						if(x * x + z * z <= 6 * 6)
//						{
//							world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//							if(x * x + z * z == 6 * 6)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//							}
//							if(x * x + z * z == 2 * 2)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//							}
//							if(Math.abs(x) > 1 && z == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//							}
//							if(Math.abs(z) > 1 && x == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//							}
//						}
//					}
//					if(y == 17)
//					{
//						if(x * x + z * z > 2 * 2)
//						{
//							if(x * x + z * z <= 6 * 6)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//								if(x * x + z * z == 6 * 6)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//								}
//								if(Math.abs(x) > 1 && z == 0)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//								}
//								if(Math.abs(z) > 1 && x == 0)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//								}
//							}
//						}
//					}
//					if(x == 0 && z == 0 && y <= 17)
//					{
//						world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//					}
//					if(x * x + z * z <= 2 * 2 && y >= 0 && y <= 17)
//					{
//						if(y % 4 == 0)
//						{
//							if(x < 0 && z > 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//							}
//							if(z == 0 && x < 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 2, 2);
//							}
//							if(z > 0 && x == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 5, 2);
//							}
//						}
//						if(y % 4 == 1)
//						{
//							if(x > 0 && z > 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//							}
//							if(z > 0 && x == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 0, 2);
//							}
//							if(z == 0 && x > 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 6, 2);
//							}
//							if(y == 17 && x * x + z * z == 2 * 2)
//							{
//								if(z > 0 && x > 0)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//								}
//								if(z == 0 && x > 0)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 6, 2);
//								}
//							}
//						}
//						if(y % 4 == 2)
//						{
//							if(x > 0 && z < 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//							}
//							if(z == 0 && x > 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 3, 2);
//							}
//							if(z < 0 && x == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 4, 2);
//							}
//						}
//						if(y % 4 == 3)
//						{
//							if(x < 0 && z < 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//							}
//							if(z < 0 && x == 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 1, 2);
//							}
//							if(z == 0 && x < 0)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 7, 2);
//							}
//						}
//					}
//				}
//			}
//		}
//		this.generateCandle(world, xCoord + 6, yCoord, zCoord, 2);
//		this.generateCandle(world, xCoord, yCoord, zCoord + 6, 1);
//		this.generateCandle(world, xCoord - 6, yCoord, zCoord, 2);
//		this.generateCandle(world, xCoord - 2, yCoord, zCoord - 6, 0);
//		this.generateCandle(world, xCoord + 2, yCoord, zCoord - 6, 0);
//		this.generateCandle(world, xCoord, yCoord + 18, zCoord, 0);
//		this.generateCandle(world, xCoord + 6, yCoord + 18, zCoord, 0);
//		this.generateCandle(world, xCoord, yCoord + 18, zCoord + 6, 0);
//		this.generateCandle(world, xCoord - 6, yCoord + 18, zCoord, 0);
//		world.setBlock(xCoord, yCoord + 18, zCoord - 6, IdMap.blockStatue, 2, 2);
//		world.setBlock(xCoord, yCoord + 19, zCoord - 6, IdMap.blockStatue, 4, 2);
//		world.setBlock(xCoord, yCoord + 20, zCoord - 6, IdMap.blockStatue, 5, 2);
//		world.setBlock(xCoord, yCoord - 1, zCoord, Blocks.mob_spawner, 0, 2);
//		((TileEntityMobSpawner)world.getTileEntity(xCoord, yCoord - 1, zCoord)).func_145881_a().setEntityName("FrostGuard");
//	}
//	
//	private void generateFloor(World world, int x, int yStart, int z)
//	{
//		for(int y = yStart; y > 0; y--)
//		{
//			if(world.getBlock(x, y, z).getMaterial() == Material.air || world.getBlock(x, y, z).isReplaceable(world, x, y, z))
//			{
//				world.setBlock(x, y, z, Blocks.dirt, 0, 2);
//			}
//			else
//			{
//				break;
//			}
//		}
//	}
//	
//	private void generateCandle(World world, int x, int y, int z, int type)
//	{
//		switch(type)
//		{
//		case 0:
//			world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 1, z, Blocks.torch, 5, 2);
//			break;
//		case 1:
//			world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x + 1, y + 1, z , IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x - 1, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x + 1, y + 2, z, Blocks.torch, 5, 2);
//			world.setBlock(x - 1, y + 2, z, Blocks.torch, 5, 2);
//			break;
//		case 2:
//			world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 1, z + 1, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 1, z - 1, IdMap.blockBlackFrostFenceSet, 1, 2);
//			world.setBlock(x, y + 2, z + 1, Blocks.torch, 5, 2);
//			world.setBlock(x, y + 2, z - 1, Blocks.torch, 5, 2);
//			break;
//		}
//	}
//	
//	public static void generateAt(Random random, int i, int j, int k, World world1) 
//	{
//		final World world  = world1;
//		final int xCoord = i;
//		final int yCoord = j;
//		final int zCoord = k;
//		new Thread()
//		{
//			public void run()
//			{
//				for(int x = -7;x < 8;x++)
//				{
//					for(int z = -7;z < 8;z++)
//					{
//						for(int y = -1;y <= 20;y++)
//						{
//							if(x * x + z * z <= 7 * 7)
//							{
//								world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//								if(y == -1)
//								{
//									this.generateFloor(world, xCoord + x, yCoord + y - 1, zCoord + z);
//								}
//							}
//							if(x * x + z * z == 7 * 7)
//							{
//								if(!(Math.abs(x) < 2 && z < 0 && y < 3 && y >= 0) && !(x == 0 && y == 3 && z < 0) && y <= 18)
//								{					
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//								}
//								if(y == 18)
//								{
//									if(Math.abs(x) == 1 || Math.abs(z) == 1 || Math.abs(x) == 3 || Math.abs(z) == 3)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostSingleSlabSet, 2, 2);
//									}
//									if(Math.abs(x) == 5 && Math.abs(z) == 5)
//									{
//										world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//									}
//								}
//							}
//							if(y == -1)
//							{
//								if(x * x + z * z <= 6 * 6)
//								{
//									world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//									if(x * x + z * z == 6 * 6)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//									}
//									if(x * x + z * z == 2 * 2)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//									}
//									if(Math.abs(x) > 1 && z == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//									}
//									if(Math.abs(z) > 1 && x == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//									}
//								}
//							}
//							if(y == 17)
//							{
//								if(x * x + z * z > 2 * 2)
//								{
//									if(x * x + z * z <= 6 * 6)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//										if(x * x + z * z == 6 * 6)
//										{
//											world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//										}
//										if(Math.abs(x) > 1 && z == 0)
//										{
//											world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//										}
//										if(Math.abs(z) > 1 && x == 0)
//										{
//											world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//										}
//									}
//								}
//							}
//							if(x == 0 && z == 0 && y <= 17)
//							{
//								world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//							}
//							if(x * x + z * z <= 2 * 2 && y >= 0 && y <= 17)
//							{
//								if(y % 4 == 0)
//								{
//									if(x < 0 && z > 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//									}
//									if(z == 0 && x < 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 2, 2);
//									}
//									if(z > 0 && x == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 5, 2);
//									}
//								}
//								if(y % 4 == 1)
//								{
//									if(x > 0 && z > 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//									}
//									if(z > 0 && x == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 0, 2);
//									}
//									if(z == 0 && x > 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 6, 2);
//									}
//									if(y == 17 && x * x + z * z == 2 * 2)
//									{
//										if(z > 0 && x > 0)
//										{
//											world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//										}
//										if(z == 0 && x > 0)
//										{
//											world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 6, 2);
//										}
//									}
//								}
//								if(y % 4 == 2)
//								{
//									if(x > 0 && z < 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//									}
//									if(z == 0 && x > 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 3, 2);
//									}
//									if(z < 0 && x == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 4, 2);
//									}
//								}
//								if(y % 4 == 3)
//								{
//									if(x < 0 && z < 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 2, 2);
//									}
//									if(z < 0 && x == 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 1, 2);
//									}
//									if(z == 0 && x < 0)
//									{
//										world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrostStairBrick, 7, 2);
//									}
//								}
//							}
//						}
//					}
//				}
//				this.generateCandle(world, xCoord + 6, yCoord, zCoord, 2);
//				this.generateCandle(world, xCoord, yCoord, zCoord + 6, 1);
//				this.generateCandle(world, xCoord - 6, yCoord, zCoord, 2);
//				this.generateCandle(world, xCoord - 2, yCoord, zCoord - 6, 0);
//				this.generateCandle(world, xCoord + 2, yCoord, zCoord - 6, 0);
//				this.generateCandle(world, xCoord, yCoord + 18, zCoord, 0);
//				this.generateCandle(world, xCoord + 6, yCoord + 18, zCoord, 0);
//				this.generateCandle(world, xCoord, yCoord + 18, zCoord + 6, 0);
//				this.generateCandle(world, xCoord - 6, yCoord + 18, zCoord, 0);
//				world.setBlock(xCoord, yCoord + 18, zCoord - 6, IdMap.blockStatue, 2, 2);
//				world.setBlock(xCoord, yCoord + 19, zCoord - 6, IdMap.blockStatue, 4, 2);
//				world.setBlock(xCoord, yCoord + 20, zCoord - 6, IdMap.blockStatue, 5, 2);
//				world.setBlock(xCoord, yCoord - 1, zCoord, Blocks.mob_spawner, 0, 2);
//				((TileEntityMobSpawner)world.getTileEntity(xCoord, yCoord - 1, zCoord)).func_145881_a().setEntityName("FrostGuard");
//			}
//			
//			private void generateFloor(World world, int x, int yStart, int z)
//			{
//				for(int y = yStart; y > 0; y--)
//				{
//					if(world.getBlock(x, y, z).getMaterial() == Material.air || world.getBlock(x, y, z).isReplaceable(world, x, y, z))
//					{
//						world.setBlock(x, y, z, Blocks.dirt, 0, 2);
//					}
//					else
//					{
//						break;
//					}
//				}
//			}
//			
//			private void generateCandle(World world, int x, int y, int z, int type)
//			{
//				switch(type)
//				{
//				case 0:
//					world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 1, z, Blocks.torch, 5, 2);
//					break;
//				case 1:
//					world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x + 1, y + 1, z , IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x - 1, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x + 1, y + 2, z, Blocks.torch, 5, 2);
//					world.setBlock(x - 1, y + 2, z, Blocks.torch, 5, 2);
//					break;
//				case 2:
//					world.setBlock(x, y, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 1, z + 1, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 1, z - 1, IdMap.blockBlackFrostFenceSet, 1, 2);
//					world.setBlock(x, y + 2, z + 1, Blocks.torch, 5, 2);
//					world.setBlock(x, y + 2, z - 1, Blocks.torch, 5, 2);
//					break;
//				}
//			}
//		}.start();
//	}
	public static void generateFrostWingCylinder(World worldObj, int xCoord, int zCoord)
	{
		int yCoord = worldObj.getActualHeight() - 27;

		sendChatMsg("Generating Arena...");
		sendChatMsg("PREPARE YOURSELFS FOR A TON OF LAGG...");
		sendChatMsg("For your own safety try not to move while the arena is generating!");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		for(int y = 0;y <= 25;y++)
		{
			int x = 25;
			int z = 0;
			int radiusError = 1-x;
			while(x >= z)
			{
				worldObj.setBlock(x + xCoord, y + yCoord, z + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(z + xCoord, y + yCoord, x + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(-x + xCoord, y + yCoord, z + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(-z + xCoord, y + yCoord, x + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(-x + xCoord, y + yCoord, -z + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(-z + xCoord, y + yCoord, -x + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(x + xCoord, y + yCoord, -z + zCoord, IdMap.blockBlackFrost, 0, 2);
				worldObj.setBlock(z + xCoord, y + yCoord, -x + zCoord, IdMap.blockBlackFrost, 0, 2);
				if(y == 0)
				{
					int i = x - 1;
					while(i >= z)
					{
						worldObj.setBlock(xCoord + i, y + yCoord, z + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord - i, y + yCoord, z + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord + i, y + yCoord, -z + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord - i, y + yCoord, -z + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord + z, y + yCoord, i + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord - z, y + yCoord, i + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord + z, y + yCoord, -i + zCoord, Blocks.snow);
						worldObj.setBlock(xCoord - z, y + yCoord, -i + zCoord, Blocks.snow);
						i--;
					}
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
		sendChatMsg("Generation complete");
		sendChatMsg("Good luck");
		sendChatMsg("If you are defeated (or if you fell down) use the \"/joinfight FrostWing\" command to re-join the fight!");

//		sendChatMsg("Generating Arena... 0%");
//		int yCoord = world.getActualHeight() - 27;
//		for(int x = -25;x <= 25;x++)
//		{
//			for(int z = -25;z <= 25;z++)
//			{
//				for(int y = 0;y <= 25;y++)
//				{
//					if(x == -15 && y == 5 && z == -15)
//					{
//						sendChatMsg("Generating Arena... 20%");
//					}
//					if(x == -5 && y == 10 && z == -5)
//					{
//						sendChatMsg("Generating Arena... 40%");
//					}
//					if(x == 5 && y == 15 && z == 5)
//					{
//						sendChatMsg("Generating Arena... 60%");
//					}
//					if(x == 15 && y == 20 && z == 15)
//					{
//						sendChatMsg("Generating Arena... 80%");
//					}
//					if(x == 25 && y == 25 && z == 25)
//					{
//						sendChatMsg("Generating Arena... 100%");
//					}
//					if(y == 0 && x * x + z * z < 25 * 25)
//					{
//						world.setBlock(xCoord + x, yCoord + y, zCoord + z, Blocks.snow, 0, 2);
//					}
//					if(x * x + z * z <= 25 * 25 && x * x + z * z > 24 * 24)
//					{
//						world.setBlock(xCoord + x, yCoord + y, zCoord + z, IdMap.blockBlackFrost, 0, 2);
//					}
//				}
//			}
//		}
//		sendChatMsg("Generation complete!");
	}
	
	public static void removeCylinder(World world, int xCoord, int zCoord)
	{
		int yCoord = world.getActualHeight() - 27;
		for(int y = 0;y <= 25;y++)
		{
			int x = 25;
			int z = 0;
			int radiusError = 1-x;
			while(x >= z)
			{
				world.setBlockToAir(x + xCoord, y + yCoord, z + zCoord);
				world.setBlockToAir(z + xCoord, y + yCoord, x + zCoord);
				world.setBlockToAir(-x + xCoord, y + yCoord, z + zCoord);
				world.setBlockToAir(-z + xCoord, y + yCoord, x + zCoord);
				world.setBlockToAir(-x + xCoord, y + yCoord, -z + zCoord);
				world.setBlockToAir(-z + xCoord, y + yCoord, -x + zCoord);
				world.setBlockToAir(x + xCoord, y + yCoord, -z + zCoord);
				world.setBlockToAir(z + xCoord, y + yCoord, -x + zCoord);
				if(y == 0)
				{
					int i = x - 1;
					while(i >= z)
					{
						world.setBlockToAir(xCoord + i, y + yCoord, z + zCoord);
						world.setBlockToAir(xCoord - i, y + yCoord, z + zCoord);
						world.setBlockToAir(xCoord + i, y + yCoord, -z + zCoord);
						world.setBlockToAir(xCoord - i, y + yCoord, -z + zCoord);
						world.setBlockToAir(xCoord + z, y + yCoord, i + zCoord);
						world.setBlockToAir(xCoord - z, y + yCoord, i + zCoord);
						world.setBlockToAir(xCoord + z, y + yCoord, -i + zCoord);
						world.setBlockToAir(xCoord - z, y + yCoord, -i + zCoord);
						i--;
					}
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
//		if(!world.isRemote)
//		{
//			sendChatMsg("Removing Arena... 0%");
//			int yCoord = world.getActualHeight() - 27;
//			for(int x = -25;x <= 25;x++)
//			{
//				for(int z = -25;z <= 25;z++)
//				{
//					for(int y = 0;y <= 25;y++)
//					{
//						if(x == -15 && y == 5 && z == -15)
//						{
//							sendChatMsg("Removing Arena... 20%");
//						}
//						if(x == -5 && y == 10 && z == -5)
//						{
//							sendChatMsg("Removing Arena... 40%");
//						}
//						if(x == 5 && y == 15 && z == 5)
//						{
//							sendChatMsg("Removing Arena... 60%");
//						}
//						if(x == 15 && y == 20 && z == 15)
//						{
//							sendChatMsg("Removing Arena... 80%");
//						}
//						if(x == 25 && y == 25 && z == 25)
//						{
//							sendChatMsg("Removing Arena... 100%");
//						}
//						if(y == 0 && x * x + z * z < 25 * 25)
//						{
//							world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//						}
//						if(x * x + z * z <= 25 * 25  && x * x + z * z > 24 * 24)
//						{
//							world.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
//						}
//					}
//				}
//			}
//			sendChatMsg("Removal complete!");
//		}
	}
	
}
