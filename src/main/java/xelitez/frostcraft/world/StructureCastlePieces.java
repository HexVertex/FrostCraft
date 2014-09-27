package xelitez.frostcraft.world;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import xelitez.frostcraft.registry.IdMap;

public class StructureCastlePieces 
{
	private HashMap<Integer[], StructureComponent> grid;
	private HashMap<String, Class<? extends StructureComponent>> componentRegister = new HashMap<String, Class<? extends StructureComponent>>();
	
	private int offsetX;
	private int offsetY;
	private int offsetZ;
	
	private byte rotationMod = 0;
	
	public StructureCastlePieces()
	{
		componentRegister.put("Default", Castle.class);
		componentRegister.put("Gate", Gate.class);
	}
	
	public void createGrid()
	{
		grid = new HashMap<Integer[], StructureComponent>();
		this.addComponentToGrid(0, 0, "Gate");
	}
	
	private void addComponentToGrid(int x, int z, String tag)
	{
		for(Integer[] ints : grid.keySet())
		{
			if(ints[0] == x && ints[1] == z)
			{
				grid.remove(ints);
			}
		}
		grid.put(new Integer[] {x, z}, this.getComponentFromString(tag));
	}
	
	public StructureComponent getComponentFromString(String tag)
	{
		StructureComponent comp = null;
		try
		{
			// I jave no idea why this requires that value twice honestly
			comp = (StructureComponent) this.getClassFromString(tag).getConstructor(StructureCastlePieces.class, StructureCastlePieces.class).newInstance(this, this);
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			comp = getComponentFromString("Default");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return comp;
	}
	
	private Class<? extends StructureComponent> getClassFromString(String tag) throws InstantiationException, IllegalAccessException
	{
		for(Entry<String, Class<? extends StructureComponent>> entry : componentRegister.entrySet())
		{
			String string = entry.getKey();
			if(string.matches(tag))
			{
				return entry.getValue();
			}
		}
		throw new IllegalArgumentException("No such compnent!");
	}
	
	public HashMap<String, Class<? extends StructureComponent>> getComponents()
	{
		return this.componentRegister;
	}
	
	public void rotateGrid()
	{
		HashMap<Integer[], StructureComponent> tempGrid = new HashMap<Integer[], StructureComponent>();
		for(Entry<Integer[], StructureComponent> entry : grid.entrySet())
		{
			Integer[] ints = entry.getKey();
			Integer[] newInts = new Integer[] {ints[1], -ints[0]};
			tempGrid.put(newInts, entry.getValue());
		}
		grid = tempGrid;
	}
	
	public void addOffset(int x, int y, int z)
	{
		for(StructureComponent comp : grid.values())
		{
			comp.getBoundingBox().offset(x, y, z);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList gridToList(LinkedList list)
	{
		for(Entry<Integer[], StructureComponent> entry : grid.entrySet())
		{
			entry.getValue().getBoundingBox().offset(entry.getKey()[0] * 9, 0, entry.getKey()[1] * 9);
			list.add(entry.getValue());
		}
		return list;
	}
	
	public HashMap<Integer[], StructureComponent> getGrid()
	{
		return grid;
	}
	
	public void setOffset(int x, int y, int z)
	{
		offsetX = x;
		offsetY = y;
		offsetZ = z;
	}
	
	public void setRotateOffset(byte b)
	{
		rotationMod = b;
	}
	
	public boolean placeBlock(World world, int x, int y, int z, Block block)
	{
		return this.placeBlock(world, x, y, z, block, 0);
	}
	
	public boolean placeBlock(World world, int x, int y, int z, Block block, int meta)
	{
		for(int i = 0;i < rotationMod;i++)
		{
			int[] ints = new int[] {z, y, -x};
			x = ints[0];
			y = ints[1];
			z = ints[2];
			if(block instanceof BlockStairs)
			{
				switch(meta)
				{
				case 0:
					meta = 3;
					break;
				case 1:
					meta = 2;
					break;
				case 2:
					meta = 0;
					break;
				case 3:
					meta = 1;
					break;
				case 4:
					meta = 7;
					break;
				case 5:
					meta = 6;
					break;
				case 6:
					meta = 4;
					break;
				case 7:
					meta = 5;
					break;
				}
				
			}
		}
		return world.setBlock(x + offsetX, y + offsetY, z + offsetZ, block, meta, 2);
	}
	
	public void fillAreaWithBlock(World world, int x, int y, int z, int x2, int y2, int z2, Block block, int meta)
	{
		for(int i = x;i <= x2;i++)
		{
			for(int j = y;j <= y2;j++)
			{
				for(int k = z;k <= z2;k++)
				{
					this.placeBlock(world, i, j, k, block, meta);
				}
			}
		}
	}
	
	public class Castle extends StructureComponent
	{		
		protected StructureCastlePieces pieces;
		
		public Castle(StructureCastlePieces pieces)
		{
			this.pieces = pieces;
			this.boundingBox = new StructureBoundingBox(-4, -1, -4, 4, 13, 4);
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox box)
		{
			if(box != null)
			{
				pieces.setOffset(box.getCenterX(), box.minY, box.getCenterZ());
			}
			return true;
		}

		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class Gate extends Castle
	{
		public Gate(StructureCastlePieces pieces) {
			super(pieces);
		}

		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox p_74875_3_) 
		{
			super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_);
			
			//Floor Section
			pieces.fillAreaWithBlock(p_74875_1_, -5, -1, 4, 5, -1, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, -1, -2, -4, -1, 2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, -1, 2, 3, -1, 2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 2, -2, -1, 2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 0, -1, 1, 0, -1, 2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 1, -1, -1, 1, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 1, -1, 1, 3, -1, 1, IdMap.blockBlackFrost, 0);
			pieces.placeBlock(p_74875_1_, -1, -1, 2, IdMap.blockBlackFrost, 0);
			pieces.placeBlock(p_74875_1_, 1, -1, 2, IdMap.blockBlackFrost, 0);
			pieces.placeBlock(p_74875_1_, 0, -1, 0, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 0, -1, -1, 0, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 1, -1, 0, 3, -1, 0, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -1, -1, -2, IdMap.blockBlackFrost, 0);
			pieces.placeBlock(p_74875_1_, 1, -1, -2, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -1, -1, -1, -1, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 1, -1, -1, 3, -1, -1, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -2, -2, -1, -2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, -1, -2, 3, -1, -2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 0, -1, -2, 0, -1, -1, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, -1, -2, 4, -1, 2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, -1, 3, 4, -1, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, -1, -4, 4, -1, -3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, -1, -8, 5, -1, -5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, -1, -9, 4, -1, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -4, -1, -10, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 4, -1, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -10, 3, -1, -10, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -11, 3, -1, -11, IdMap.blockBlackFrost, 2);
			//Wall Section
			pieces.fillAreaWithBlock(p_74875_1_, -4, 2, 6, 4, 13, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 0, 6, -2, 1, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 0, 0, 6, 0, 1, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 0, 6, 4, 1, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 0, 5, 5, 13, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 0, 5, -5, 13, 6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 0, 2, 4, 13, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 0, 2, -4, 13, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 11, -1, 4, 13, 1, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 11, -1, -4, 13, 1, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 0, -4, 4, 13, -2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 0, -4, -4, 13, -2, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 0, -8, 5, 13, -5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 0, -8, -5, 13, -5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 0, -10, 4, 13, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 0, -10, -4, 13, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 9, -10, 3, 13, -10, IdMap.blockBlackFrost, 2);
			//Roof Section
			pieces.fillAreaWithBlock(p_74875_1_, -6, 14, 4, 6, 14, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 14, -3, 5, 14, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 14, -9, 6, 14, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 14, -11, 5, 14, -10, IdMap.blockBlackFrost, 2);
			//Gate Section
			pieces.fillAreaWithBlock(p_74875_1_, -2, 5, -11, -2, 6, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 5, -11, 2, 6, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 0, -11, -3, 4, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 0, -11, 3, 4, -11, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -1, 7, -11, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 1, 7, -11, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 0, 8, -11, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 0, 7, -11, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, 0, 9, -11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -1, 8, -11, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, -2, 7, -11, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, -3, 5, -11, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, 1, 8, -11, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 2, 7, -11, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 3, 5, -11, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 1, 6, -11, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, 2, 4, -11, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -1, 6, -11, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, -2, 4, -11, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 5, -9, -2, 6, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 5, -9, 2, 6, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 0, -9, -3, 4, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 0, -9, 3, 4, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -1, 7, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 1, 7, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 0, 8, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 0, 7, -9, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, 0, 9, -9, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -1, 8, -9, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, -2, 7, -9, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, -3, 5, -9, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, 1, 8, -9, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 2, 7, -9, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 3, 5, -9, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, 1, 6, -9, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, 2, 4, -9, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -1, 6, -9, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, -2, 4, -9, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 8, -10, -1, 8, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 1, 8, -10, 3, 8, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 7, -10, -2, 7, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 7, -10, 3, 7, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 5, -10, -3, 6, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 5, -10, 3, 6, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 4, -10, 3, 4, -10, IdMap.blockBlackFrostFenceSet, 1);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 5, -10, 2, 6, -10, IdMap.blockBlackFrostFenceSet, 1);
			pieces.fillAreaWithBlock(p_74875_1_, -1, 7, -10, 1, 7, -10, IdMap.blockBlackFrostFenceSet, 1);
			pieces.placeBlock(p_74875_1_, 0, 8, -10, IdMap.blockBlackFrostFenceSet, 1);
			//second lvl gates
			pieces.placeBlock(p_74875_1_, -4, 2, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, -4, 2, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 3, -1, -4, 4, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -4, 8, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, -4, 8, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 9, -1, -4, 10, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 4, 2, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, 4, 2, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 3, -1, 4, 4, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 4, 8, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, 4, 8, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 9, -1, 4, 10, 1, IdMap.blockBlackFrost, 2);
			//Bridge Section
			pieces.fillAreaWithBlock(p_74875_1_, -4, 5, -1, 4, 5, 1, IdMap.blockBlackFrost, 2);

			return true;
		}
		
	}
	
	public class Corridor extends Castle
	{

		public Corridor(StructureCastlePieces pieces) {
			super(pieces);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox p_74875_3_) 
		{
			super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 3, -3, 10, 3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, 3, 3, 10, 3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, -3, 3, 10, -3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -3, -3, 10, -3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 11, -3, 3, 11, 3, IdMap.blockBlackFrost, 0);
			return true;
		}
		
	}
}
