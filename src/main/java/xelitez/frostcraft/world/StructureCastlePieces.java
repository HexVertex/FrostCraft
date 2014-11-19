package xelitez.frostcraft.world;

import static net.minecraftforge.common.ChestGenHooks.STRONGHOLD_CORRIDOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import xelitez.frostcraft.registry.IdMap;

public class StructureCastlePieces 
{
	private HashMap<Integer[], StructureComponent> grid;
	private HashMap<String, Class<? extends StructureComponent>> componentRegister = new HashMap<String, Class<? extends StructureComponent>>();
	
	protected int offsetX;
	protected int offsetY;
	protected int offsetZ;
	
	private byte rotationMod = 0;
	
	private Random RNG = new Random();
	
	public StructureCastlePieces()
	{
		componentRegister.put("Default", Castle.class);
		componentRegister.put("Gate", Gate.class);
		componentRegister.put("Corridor", Corridor.class);
		componentRegister.put("Tower", Tower.class);
		componentRegister.put("Main", Main.class);
		grid = new HashMap<Integer[], StructureComponent>();
	}
	
	public void createGrid()
	{
		grid = new HashMap<Integer[], StructureComponent>();
		this.addComponentToGrid(0, 0, "Gate");
		this.addComponentToGrid(1, 0, "Corridor");
		this.addComponentToGrid(2, 1, "Corridor");
		this.addComponentToGrid(2, 2, "Corridor");
		this.addComponentToGrid(2, 3, "Corridor");
		this.addComponentToGrid(2, 4, "Corridor");
		this.addComponentToGrid(1, 4, "Corridor");
		this.addComponentToGrid(-1, 0, "Corridor");
		this.addComponentToGrid(-2, 1, "Corridor");
		this.addComponentToGrid(-2, 2, "Corridor");
		this.addComponentToGrid(-2, 3, "Corridor");
		this.addComponentToGrid(-2, 4, "Corridor");
		this.addComponentToGrid(-1, 4, "Corridor");
		this.addComponentToGrid(-2, 0, "Tower");
		this.addComponentToGrid(2, 0, "Tower");
		this.addComponentToGrid(0, 4, "Main");
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
		Castle comp = (Castle)getComponentFromString(tag);
		comp.setType(RNG.nextInt(comp.getTypeCount()));
		comp.setGridPos(x, z);
		grid.put(new Integer[] {x, z}, comp);
	}
	
	public StructureComponent getComponentFromStringWithType(String tag, int i)
	{
		Castle comp = (Castle)getComponentFromString(tag);
		comp.setType(i);
		return comp;
	}
	
	public StructureComponent getComponentFromString(String tag)
	{
		StructureComponent comp = null;
		try
		{
			// I have no idea why this requires that value twice honestly
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
			if(string.toLowerCase().matches(tag.toLowerCase()))
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
			System.out.println(entry);
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
		System.out.println("placing block");
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
			if(block instanceof BlockDoor)
			{
				if(meta < 4)
				{
					meta = meta - 1 % 4;
				}
				if(meta < 8 && meta >= 4)
				{
					meta = 4 + (meta - 4 - 1) % 4;
				}
			}
		}
		System.out.println(offsetX);
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
	
	public void placeBlocksSwapping(World world, int x, int y, int z, int x2, int y2, int z2, Object... object)
	{
		List<Block> blocks = new ArrayList<Block>();
		List<Integer> metas = new ArrayList<Integer>();
		
		for(int d = 0; d < object.length;d += 2)
		{
			if(object[d] instanceof Block && d + 1 < object.length && object[d + 1] instanceof Integer)
			{
				blocks.add((Block)object[d]);
				metas.add((Integer)object[d + 1]);
			}
		}
		
		int tracker = 0;
		Block block;
		int meta;
		for(int i = x;i <= x2;i++)
		{
			for(int j = y;j <= y2;j++)
			{
				for(int k = z;k <= z2;k++)
				{
					block = blocks.get(tracker);
					meta = metas.get(tracker);
					this.placeBlock(world, i, j, k, block, meta);
					tracker = (tracker + 1) % blocks.size();
				}
			}
		}
	}
	
	public void placeCandle(World world, int x, int y, int z, boolean rotate)
	{
		if(rotate)
		{
			this.placeBlock(world, x, y, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x - 1, y + 1, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x + 1, y + 1, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x + 1, y + 2, z, Blocks.torch, 5);
			this.placeBlock(world, x - 1, y + 2, z, Blocks.torch, 5);
		}
		else
		{
			this.placeBlock(world, x, y, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x, y + 1, z - 1, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x, y + 1, z, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x, y + 1, z + 1, IdMap.blockBlackFrostFenceSet, 1);
			this.placeBlock(world, x, y + 2, z + 1, Blocks.torch, 5);
			this.placeBlock(world, x, y + 2, z - 1, Blocks.torch, 5);
		}
	}
	
	public Castle isComponentOnGrid(int x, int z)
	{
		for(Entry<Integer[], StructureComponent> entry : grid.entrySet())
		{
			if(entry.getKey()[0] == x && entry.getKey()[1] == z)
			{
				return (Castle)entry.getValue();
			}
		}
		return null;
	}
	
	public class Castle extends StructureComponent
	{		
		protected StructureCastlePieces pieces;
		private int type = 0;
		protected int[] gridPos = new int[] {0,0};
		
		public Castle(StructureCastlePieces pieces)
		{
			this.pieces = pieces;
			this.boundingBox = new StructureBoundingBox(-4, -1, -4, 4, 13, 4);
		}
		
		public Castle(StructureCastlePieces pieces, int[] gridPos)
		{
			this(pieces);
			this.gridPos = gridPos;
		}
		
		public void setGridPos(int x, int y)
		{
			gridPos = new int[] {x, y};
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
		
		public int getTypeCount()
		{
			return 1;
		}
		
		public int getType()
		{
			return type % this.getTypeCount();
		}
		
		public void setType(int i)
		{
			type = i;
		}
	}
	
	public class Gate extends Castle
	{
		public Gate(StructureCastlePieces pieces) {
			super(pieces);
		}
		
		public Gate(StructureCastlePieces pieces, int[] gridPos)
		{
			super(pieces, gridPos);
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
			pieces.fillAreaWithBlock(p_74875_1_, -4, 3, -1, -4, 5, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -4, 8, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, -4, 8, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 9, -1, -4, 10, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 4, 2, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, 4, 2, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 3, -1, 4, 5, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 4, 8, -1, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, 4, 8, 1, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 9, -1, 4, 10, 1, IdMap.blockBlackFrost, 2);
			//Bridge Section
			pieces.fillAreaWithBlock(p_74875_1_, -3, 5, -1, 3, 5, 1, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 4, -1, -2, 4, 1, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 4, 0, 3, 4, 0, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -1, 4, 0, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 1, 4, 0, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, 0, 4, 0, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, -3, 4, -1, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, -3, 4, 1, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 3, 4, -1, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, 3, 4, 1, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -3, 3, 0, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, -2, 4, -1, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, -2, 4, 1, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, 3, 3, 0, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, 2, 4, -1, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.placeBlock(p_74875_1_, 2, 4, 1, IdMap.blockBlackFrostSingleSlabSet, 6);
			//Bridge deco
			pieces.placeBlock(p_74875_1_, -3, 6, -1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -3, 6, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 3, 6, -1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 3, 6, 1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -2, 6, -1, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -2, 6, 1, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, 2, 6, -1, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, 2, 6, 1, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -1, 6, -1, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 1, 6, -1, IdMap.blockBlackFrost, 2);
			//roof deco
			pieces.fillAreaWithBlock(p_74875_1_, -5, 13, -11, 5, 13, -11, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, -9, -5, 13, -9, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 13, -9, 6, 13, -9, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, 4, -5, 13, 4, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 13, 4, 6, 13, 4, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, 7, 6, 13, 7, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, -4, -5, 13, -4, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 13, -4, 6, 13, -4, IdMap.blockBlackFrostStairBrick, 7);
			pieces.placeBlock(p_74875_1_, -5, 13, -10, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, -8, -6, 13, -5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 13, -3, -5, 13, 3, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 13, 5, -6, 13, 6, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, 5, 13, -10, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, 6, 13, -8, 6, 13, -5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 13, -3, 5, 13, 3, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, 6, 13, 5, 6, 13, 6, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlocksSwapping(p_74875_1_, -5, 15, -11, 5, 15, -11, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -5, 15, -10, -5, 15, -9, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 5, 15, -10, 5, 15, -9, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -6, 15, -9, -6, 15, -4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 6, 15, -9, 6, 15, -4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -5, 15, -4, -5, 15, 4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 5, 15, -4, 5, 15, 4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -6, 15, 4, -6, 15, 6, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 6, 15, 4, 6, 15, 6, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -6, 15, 7, 6, 15, 7, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			//inside deco
			pieces.placeCandle(p_74875_1_, -3, 0, -3, false);
			pieces.placeCandle(p_74875_1_, -3, 0, 3, false);
			pieces.placeCandle(p_74875_1_, 3, 0, -3, false);
			pieces.placeCandle(p_74875_1_, 3, 0, 3, false);
			pieces.placeBlocksSwapping(p_74875_1_, -1, 0, 6, -1, 1, 6, Blocks.iron_door, 3, Blocks.iron_door, 8);
			pieces.placeBlocksSwapping(p_74875_1_, 1, 0, 6, 1, 1, 6, Blocks.iron_door, 3, Blocks.iron_door, 9);
			//type-specific deco
			if(this.getType() == 0)
			{
				
			}
			else
			{
				pieces.placeBlocksSwapping(p_74875_1_, -1, 6, 1, 1, 6, 1, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
				pieces.placeBlock(p_74875_1_, 0, 6, -1, IdMap.blockBlackFrostSingleSlabSet, 2);
			}
			return true;
		}

		@Override
		public int getTypeCount() 
		{
			return 4;
		}
		
	}
	
	public class Corridor extends Castle
	{

		public Corridor(StructureCastlePieces pieces) {
			super(pieces);
			// TODO Auto-generated constructor stub
		}
		
		public Corridor(StructureCastlePieces pieces, int[] gridPos)
		{
			super(pieces, gridPos);
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox p_74875_3_) 
		{
			super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_);
			//main structure
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 3, -3, 12, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, 3, 3, 12, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, -3, 3, 12, -3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -3, -3, 12, -3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 11, -3, 3, 11, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 5, -3, 3, 5, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -3, 3, -1, 3, IdMap.blockBlackFrost, 2);
			//floor deco
			pieces.placeBlock(p_74875_1_, 0, -1, 0, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, -1, -1, -1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, 1, -1, -1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, 1, -1, 1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, -1, -1, 1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, 0, 5, 0, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, -1, 5, -1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, 1, 5, -1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, 1, 5, 1, IdMap.blockBlackFrost);
			pieces.placeBlock(p_74875_1_, -1, 5, 1, IdMap.blockBlackFrost);
			//close off unconnected sides
			pieces.setRotateOffset((byte)0);
			if(pieces.isComponentOnGrid(gridPos[0], gridPos[1] + 1) == null)
			{
				pieces.fillAreaWithBlock(p_74875_1_, -2, 0, 3, 2, 11, 3, IdMap.blockBlackFrost, 2);
				pieces.placeBlocksSwapping(p_74875_1_, -2, 12, 3, 2, 12, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
				this.generateTypeSpecificElements(p_74875_1_, 0, 0);
				this.generateTypeSpecificElements(p_74875_1_, 1, 6);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)1);
			if(pieces.isComponentOnGrid(gridPos[0] + 1, gridPos[1]) == null)
			{	
				pieces.fillAreaWithBlock(p_74875_1_, -2, 0, 3, 2, 11, 3, IdMap.blockBlackFrost, 2);
				pieces.placeBlocksSwapping(p_74875_1_, -2, 12, 3, 2, 12, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
				this.generateTypeSpecificElements(p_74875_1_, 2, 0);
				this.generateTypeSpecificElements(p_74875_1_, 3, 6);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)2);
			if(pieces.isComponentOnGrid(gridPos[0], gridPos[1] - 1) == null)
			{
				pieces.fillAreaWithBlock(p_74875_1_, -2, 0, 3, 2, 11, 3, IdMap.blockBlackFrost, 2);
				pieces.placeBlocksSwapping(p_74875_1_, -2, 12, 3, 2, 12, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
				this.generateTypeSpecificElements(p_74875_1_, 4, 0);
				this.generateTypeSpecificElements(p_74875_1_, 5, 6);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)3);
			if(pieces.isComponentOnGrid(gridPos[0] - 1, gridPos[1]) == null)
			{
				pieces.fillAreaWithBlock(p_74875_1_, -2, 0, 3, 2, 11, 3, IdMap.blockBlackFrost, 2);
				pieces.placeBlocksSwapping(p_74875_1_, -2, 12, 3, 2, 12, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
				this.generateTypeSpecificElements(p_74875_1_, 6, 0);
				this.generateTypeSpecificElements(p_74875_1_, 7, 6);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			//side 0
			
			
			return true;
		}

		@Override
		public int getTypeCount() 
		{
			return (int)Math.pow(6.0D, 8.0D);
		}
		
		private void generateLoot(World world, int yOffset)
		{
			pieces.placeBlock(world, -1, yOffset, 2, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(world, 1, yOffset, 2, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(world, 0, yOffset, 2, Blocks.chest);
//			int x = 0;
//			int y = yOffset;
//			int z = 2;
//			for(int i = 0;i < rotationMod;i++)
//			{
//				int[] ints = new int[] {z, y, -x};
//				x = ints[0];
//				y = ints[1];
//				z = ints[2];
//			}
//			TileEntityChest te = (TileEntityChest)world.getTileEntity(pieces.offsetX + x, pieces.offsetY + y, pieces.offsetZ + z);
//			if(te != null)
//			{
//				WeightedRandomChestContent.generateChestContents(RNG, ChestGenHooks.getItems(STRONGHOLD_CORRIDOR, RNG), te, ChestGenHooks.getCount(STRONGHOLD_CORRIDOR, RNG));
//			}
		}
		
		private void generateSpawner(World world, int yOffset)
		{
			pieces.placeBlock(world, 0, yOffset, 2, Blocks.chest);
		}
		
		private void generateTypeSpecificElements(World world, int side, int yOffset)
		{
			float toGen = MathHelper.floor_float(((float)this.getType() % (float)(Math.pow(6.0D, (double)side + 1.0D))) / (float)(Math.pow(6.0D, (double)side)));
			System.out.println(new Integer(side).toString() + " " + new Float(toGen).toString());
			if(toGen == 0)
			{
				this.generateLoot(world, yOffset);
			}
			else if(toGen == 1)
			{
				this.generateSpawner(world, yOffset);
			}
			else if(toGen == 2 || toGen == 3)
			{
				pieces.placeCandle(world, 0, yOffset, 2, true);
			}
		}
		
		private void generateConnection(World world)
		{
			pieces.fillAreaWithBlock(world, -3, 0, 4, -3, 11, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(world, 3, 0, 4, 3, 11, 4, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(world, -3, 12, 4, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(world, 3, 12, 4, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(world, -3, -1, 4, 3, -1, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(world, -2, 5, 4, 2, 5, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(world, -2, 11, 4, 2, 11, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(world, -1, -1, 2, -1, -1, 3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(world, 1, -1, 2, 1, -1, 3, IdMap.blockBlackFrost, 01);
			pieces.fillAreaWithBlock(world, -1, 5, 2, -1, 5, 3, IdMap.blockBlackFrost, 0);
			pieces.fillAreaWithBlock(world, 1, 5, 2, 1, 5, 3, IdMap.blockBlackFrost, 0);
		}
	}
	
	public class Tower extends Castle
	{

		public Tower(StructureCastlePieces pieces) {
			super(pieces);
			// TODO Auto-generated constructor stub
		}
		
		public Tower(StructureCastlePieces pieces, int[] gridPos)
		{
			super(pieces, gridPos);
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox p_74875_3_) 
		{
			super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_);
			GenUtils.setOffset(pieces.offsetX, pieces.offsetY - 1, pieces.offsetZ);
			GenUtils.setRotation(pieces.rotationMod);
//			GenUtils.setGenLimit(-4, 4, 0, 256, -4, 4);
			GenUtils.generateHCylinder(p_74875_1_, 24, 4, IdMap.blockBlackFrost, 2);
			GenUtils.generateCylinder(p_74875_1_, 1, 4, IdMap.blockBlackFrost, 2);
			GenUtils.setOffset(pieces.offsetX, pieces.offsetY + 5, pieces.offsetZ);
			GenUtils.generateCylinder(p_74875_1_, 1, 4, IdMap.blockBlackFrost, 2);
			GenUtils.setOffset(pieces.offsetX, pieces.offsetY + 23, pieces.offsetZ);
			GenUtils.generateCylinder(p_74875_1_, 1, 5, IdMap.blockBlackFrost, 2);	
			pieces.fillAreaWithBlock(p_74875_1_, -4, 23, -4, -4, 24, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 23, -4, 4, 24, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 23, 4, -4, 24, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 23, 4, 4, 24, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -3, -3, 23, -3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, 3, 3, 23, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, -1, -3, 3, 23, -3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, 3, -3, 23, 3, IdMap.blockBlackFrost, 2);
			
			pieces.placeBlocksSwapping(p_74875_1_, -2, 24, -5, 2, 24, -5, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -2, 24, 5, 2, 24, 5, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -5, 24, -2, -5, 24, 2, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 5, 24, -2, 5, 24, 2, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			
			pieces.placeBlocksSwapping(p_74875_1_, 2, 24, -4, 3, 24, -4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -3, 24, -4, -2, 24, -4, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 2, 24, 4, 3, 24, 4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -3, 24, 4, -2, 24, 4, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			
			pieces.placeBlocksSwapping(p_74875_1_, -4, 24, 2, -4, 24, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -4, 24, -3, -4, 24, -2, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 4, 24, 2, 4, 24, 3, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 4, 24, -3, 4, 24, -2, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			
			pieces.fillAreaWithBlock(p_74875_1_, -2, 22, -5, 2, 22, -5, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 22, -4, -3, 22, -4, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 22, -4, 4, 22, -4, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 22, -2, -4, 22, -2, IdMap.blockBlackFrostStairBrick, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 22, -2, 5, 22, -2, IdMap.blockBlackFrostStairBrick, 6);
			
			pieces.fillAreaWithBlock(p_74875_1_, -2, 22, 5, 2, 22, 5, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 22, 4, -3, 22, 4, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 22, 4, 4, 22, 4, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 22, 2, -4, 22, 2, IdMap.blockBlackFrostStairBrick, 7);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 22, 2, 5, 22, 2, IdMap.blockBlackFrostStairBrick, 7);
			
			pieces.fillAreaWithBlock(p_74875_1_, -5, 22, -1, -5, 22, 1, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -4, 22, -3, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -4, 22, 3, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -2, 22, -4, IdMap.blockBlackFrostStairBrick, 4);
			pieces.placeBlock(p_74875_1_, -2, 22, 4, IdMap.blockBlackFrostStairBrick, 4);
			
			pieces.fillAreaWithBlock(p_74875_1_, 5, 22, -1, 5, 22, 1, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 4, 22, -3, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 4, 22, 3, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 2, 22, -4, IdMap.blockBlackFrostStairBrick, 5);
			pieces.placeBlock(p_74875_1_, 2, 22, 4, IdMap.blockBlackFrostStairBrick, 5);
			
			pieces.fillAreaWithBlock(p_74875_1_, -1, 0, -1, 1, 23, 1, Blocks.air, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 0, -1, 0, 0, 23, 0, IdMap.blockBlackFrost, 0);
			
			pieces.placeBlock(p_74875_1_, 0, 24, 0, IdMap.blockBlackFrostFenceSet, 1);
			pieces.placeBlock(p_74875_1_, 0, 25, 0, Blocks.torch);
			
			GenUtils.setOffset(pieces.offsetX, pieces.offsetY - 1, pieces.offsetZ);
			GenUtils.setGenLimit(-1, 1, 0, 24, -1, 1);
			for(int i = 1;i < 24;i += 4)
			{

				GenUtils.placeBlock(p_74875_1_, -1, i, 0, IdMap.blockBlackFrostSingleSlabSet, 2);
				GenUtils.placeBlock(p_74875_1_, -1, i, -1, IdMap.blockBlackFrost, 2);
				GenUtils.placeBlock(p_74875_1_, 0, i, -1, IdMap.blockBlackFrostSingleSlabSet, 6);
				GenUtils.placeBlock(p_74875_1_, 0, i + 1, -1, IdMap.blockBlackFrostSingleSlabSet, 2);
				GenUtils.placeBlock(p_74875_1_, 1, i + 1, -1, IdMap.blockBlackFrost, 2);
				GenUtils.placeBlock(p_74875_1_, 1, i + 1, 0, IdMap.blockBlackFrostSingleSlabSet, 6);
				GenUtils.placeBlock(p_74875_1_, 1, i + 2, 0, IdMap.blockBlackFrostSingleSlabSet, 2);
				GenUtils.placeBlock(p_74875_1_, 1, i + 2, 1, IdMap.blockBlackFrost, 2);
				GenUtils.placeBlock(p_74875_1_, 0, i + 2, 1, IdMap.blockBlackFrostSingleSlabSet, 6);
				GenUtils.placeBlock(p_74875_1_, 0, i + 3, 1, IdMap.blockBlackFrostSingleSlabSet, 2);
				GenUtils.placeBlock(p_74875_1_, -1, i + 3, 1, IdMap.blockBlackFrost, 2);
				GenUtils.placeBlock(p_74875_1_, -1, i + 3, 0, IdMap.blockBlackFrostSingleSlabSet, 6);
			}
			pieces.setRotateOffset((byte)0);
			if(pieces.isComponentOnGrid(gridPos[0], gridPos[1] + 1) == null)
			{
				pieces.placeCandle(p_74875_1_, 0, 0, 3, true);
				pieces.placeCandle(p_74875_1_, 0, 6, 3, true);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)1);
			if(pieces.isComponentOnGrid(gridPos[0] + 1, gridPos[1]) == null)
			{	
				pieces.placeCandle(p_74875_1_, 0, 0, 3, true);
				pieces.placeCandle(p_74875_1_, 0, 6, 3, true);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)2);
			if(pieces.isComponentOnGrid(gridPos[0], gridPos[1] - 1) == null)
			{
				pieces.placeCandle(p_74875_1_, 0, 0, 3, true);
				pieces.placeCandle(p_74875_1_, 0, 6, 3, true);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			pieces.setRotateOffset((byte)3);
			if(pieces.isComponentOnGrid(gridPos[0] - 1, gridPos[1]) == null)
			{
				pieces.placeCandle(p_74875_1_, 0, 0, 3, true);
				pieces.placeCandle(p_74875_1_, 0, 6, 3, true);
			}
			else
			{
				this.generateConnection(p_74875_1_);
			}
			GenUtils.resetAll();
			return true;
		}
		
		private void generateConnection(World world)
		{
			pieces.fillAreaWithBlock(world, -1, 0, 4, 1, 4, 4, Blocks.air, 0);
			pieces.fillAreaWithBlock(world, -1, 6, 4, 1, 10, 4, Blocks.air, 0);
			pieces.fillAreaWithBlock(world, -2, -1, 4, -2, 11, 4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(world, 2, -1, 4, 2, 11, 4, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(world, -2, 12, 4, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(world, 2, 12, 4, IdMap.blockBlackFrostStairBrick, 1);
		}
	}
	
	public class Main extends Castle
	{

		public Main(StructureCastlePieces pieces) {
			super(pieces);
			// TODO Auto-generated constructor stub
		}
		
		public Main(StructureCastlePieces pieces, int[] gridPos)
		{
			super(pieces, gridPos);
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_,
				StructureBoundingBox p_74875_3_) 
		{
			super.addComponentParts(p_74875_1_, p_74875_2_, p_74875_3_);
			//floors
			pieces.placeBlocksSwapping(p_74875_1_, -3, -1, -3, 3, -1, 3, IdMap.blockBlackFrost, 0, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -8, -1, -6, 8, -1, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -7, -1, -8, 7, -1, -7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -6, -1, -9, 6, -1, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, -1, -10, 5, -1, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, -1, -11, 3, -1, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, -1, -3, -4, -1, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, -1, -3, 4, -1, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -14, -1, 4, 14, -1, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, -1, 8, 13, -1, 9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -12, -1, 10, 12, -1, 10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -11, -1, 11, 11, -1, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, -1, 12, 9, -1, 12, IdMap.blockBlackFrost, 2);
			//2nd
			pieces.fillAreaWithBlock(p_74875_1_, -2, 5, -6, 2, 5, -6, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 5, -5, 4, 5, -5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 5, -4, 5, 5, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 5, -3, 4, 5, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 5, 4, -7, 5, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 5, 4, 2, 5, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 7, 5, 4, 9, 5, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -10, 5, 6, 10, 5, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 5, 8, 9, 5, 8, IdMap.blockBlackFrost, 2);
			//roof
			pieces.fillAreaWithBlock(p_74875_1_, -8, 11, -6, 8, 11, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -7, 11, -8, 7, 11, -7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 11, -9, 6, 11, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 11, -10, 5, 11, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 11, -11, 3, 11, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 11, -3, 4, 11, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -14, 11, 4, -5, 11, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 0, 11, 4, 0, 11, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 11, 4, 14, 11, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -14, 11, 6, 14, 11, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, 11, 8, 13, 11, 9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -12, 11, 10, 12, 11, 10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -11, 11, 11, 11, 11, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 11, 12, 9, 11, 12, IdMap.blockBlackFrost, 2);
			//walls
			pieces.fillAreaWithBlock(p_74875_1_, -3, 0, -11, 3, 10, -11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 0, -10, -4, 10, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 0, -10, 5, 10, -10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 0, -9, -6, 10, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 6, 0, -9, 6, 10, -9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -7, 0, -8, -7, 10, -7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 7, 0, -8, 7, 10, -7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -8, 0, -6, -8, 10, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 8, 0, -6, 8, 10, -4, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -14, 0, 4, -14, 10, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 14, 0, 4, 14, 10, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, 0, 8, -13, 10, 9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 13, 0, 8, 13, 10, 9, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -12, 0, 10, -12, 10, 10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 12, 0, 10, 12, 10, 10, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -11, 0, 11, -10, 10, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 0, 11, 11, 10, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 0, 12, 9, 10, 12, IdMap.blockBlackFrost, 2);
			//inner walls
			pieces.fillAreaWithBlock(p_74875_1_, -4, 6, 3, -3, 10, 3, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 6, 3, 4, 10, 3, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -2, 10, 3, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 2, 10, 3, IdMap.blockBlackFrost, 2);
			//stairs
			pieces.fillAreaWithBlock(p_74875_1_, 5, 0, 9, 5, 0, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 6, 0, 9, 11, 0, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 7, 1, 9, 7, 1, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 8, 1, 9, 11, 1, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 9, 2, 9, 9, 2, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 0, 8, 12, 2, 10, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 10, 2, 10, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 3, 8, 12, 3, 8, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, 12, 3, 9, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 11, 0, 4, 13, 3, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 11, 4, 6, 13, 4, 6, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 11, 4, 4, 13, 4, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 4, 4, 10, 4, 5, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 5, 4, 10, 5, 5, IdMap.blockBlackFrostSingleSlabSet, 2);
			
			pieces.fillAreaWithBlock(p_74875_1_, -5, 0, 9, -5, 0, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -11, 0, 9, -6, 0, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -7, 1, 9, -7, 1, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -11, 1, 9, -8, 1, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 2, 9, -9, 2, 11, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -12, 0, 8, -10, 2, 10, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -10, 2, 10, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -12, 3, 8, -10, 3, 8, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -12, 3, 9, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, 0, 4, -11, 3, 7, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, 4, 6, -11, 4, 6, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -13, 4, 4, -11, 4, 5, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -10, 4, 4, -10, 4, 5, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -10, 5, 4, -10, 5, 5, IdMap.blockBlackFrostSingleSlabSet, 2);
			
			pieces.fillAreaWithBlock(p_74875_1_, 6, 6, 4, 6, 6, 5, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, 6, 5, 4, 6, 5, 5, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 6, 4, 5, 6, 5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, 5, 7, 4, 5, 7, 5, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 7, 4, 4, 7, 5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 8, 4, 4, 8, 5, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 8, 4, 3, 8, 5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 9, 4, 3, 9, 5, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 9, 3, 2, 9, 5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 10, 4, 2, 10, 5, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, 1, 10, 3, 1, 10, 5, IdMap.blockBlackFrostStairBrick, 4);
			pieces.fillAreaWithBlock(p_74875_1_, 1, 11, 4, 1, 11, 5, IdMap.blockBlackFrostStairBrick, 1);
			
			pieces.fillAreaWithBlock(p_74875_1_, -6, 6, 4, -6, 6, 5, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -6, 5, 4, -6, 5, 5, IdMap.blockBlackFrostSingleSlabSet, 6);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 6, 4, -5, 6, 5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 7, 4, -5, 7, 5, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 7, 4, -4, 7, 5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 8, 4, -4, 8, 5, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 8, 4, -3, 8, 5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -3, 9, 4, -3, 9, 5, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 9, 3, -2, 9, 5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 10, 4, -2, 10, 5, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -1, 10, 3, -1, 10, 5, IdMap.blockBlackFrostStairBrick, 5);
			pieces.fillAreaWithBlock(p_74875_1_, -1, 11, 4, -1, 11, 5, IdMap.blockBlackFrostStairBrick, 0);
			//walldeco
			pieces.placeBlocksSwapping(p_74875_1_, -3, 12, -11, 3, 12, -11, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -5, 12, -10, -4, 12, -10, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 4, 12, -10, 5, 12, -10, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, -6, 12, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, 6, 12, -9, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -7, 12, -8, -7, 12, -7, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 7, 12, -8, 7, 12, -7, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -8, 12, -6, -8, 12, -4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 8, 12, -6, 8, 12, -4, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -14, 12, 4, -14, 12, 7, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 14, 12, 4, 14, 12, 7, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -13, 12, 8, -13, 12, 9, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 13, 12, 8, 13, 12, 9, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -12, 12, 10, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlock(p_74875_1_, 12, 12, 10, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -11, 12, 11, -10, 12, 11, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 10, 12, 11, 11, 12, 11, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -9, 12, 12, -4, 12, 12, IdMap.blockBlackFrost, 2, IdMap.blockBlackFrostSingleSlabSet, 2);
			pieces.placeBlocksSwapping(p_74875_1_, 4, 12, 12, 9, 12, 12, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			pieces.placeBlock(p_74875_1_, -3, 12, 12, IdMap.blockBlackFrostStairBrick, 0);
			pieces.placeBlock(p_74875_1_, 3, 12, 12, IdMap.blockBlackFrostStairBrick, 1);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 12, 12, 2, 12, 12, IdMap.blockBlackFrost, 2);
			pieces.placeBlocksSwapping(p_74875_1_, -1, 13, 12, 1, 13, 12, IdMap.blockBlackFrostSingleSlabSet, 2, IdMap.blockBlackFrost, 2);
			//deco
			pieces.placeCandle(p_74875_1_, 7, 0, -5, false);
			pieces.placeCandle(p_74875_1_, -7, 0, -5, false);
			pieces.placeCandle(p_74875_1_, 7, 0, 4, true);
			pieces.placeCandle(p_74875_1_, 7, 0, 4, true);
			pieces.placeCandle(p_74875_1_, 0, 0, 11, true);
			
			pieces.fillAreaWithBlock(p_74875_1_, -10, 6, 6, -10, 6, 7, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 10, 6, 6, 10, 6, 7, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -9, 6, 7, -9, 6, 8, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 9, 6, 7, 9, 6, 8, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -8, 6, 8, 8, 6, 8, IdMap.blockBlackFrostFenceSet, 0);
			
			pieces.fillAreaWithBlock(p_74875_1_, -2, 6, 3, -2, 6, 6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 6, 3, 2, 6, 6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 6, 6, -3, 6, 6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 3, 6, 6, 5, 6, 6, IdMap.blockBlackFrostFenceSet, 0);
			
			pieces.fillAreaWithBlock(p_74875_1_, -2, 6, -6, 2, 6, -6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -4, 6, -5, -2, 6, -5, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 6, -5, 4, 6, -5, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, -5, 6, -4, -4, 6, -4, IdMap.blockBlackFrostFenceSet, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 4, 6, -4, 4, 6, -4, IdMap.blockBlackFrostFenceSet, 0);
			
			pieces.placeBlock(p_74875_1_, -4, 7, -5, Blocks.torch);
			pieces.placeBlock(p_74875_1_, 4, 7, -5, Blocks.torch);
			pieces.placeBlock(p_74875_1_, -2, 7, 3, Blocks.torch, 5);
			pieces.placeBlock(p_74875_1_, 2, 7, 3, Blocks.torch, 5);
			pieces.placeBlock(p_74875_1_, -5, 7, 6, Blocks.torch);
			pieces.placeBlock(p_74875_1_, 5, 7, 6, Blocks.torch);
			
			pieces.placeBlock(p_74875_1_, -4, 12, -6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.placeBlock(p_74875_1_, -4, 13, -6, Blocks.torch);
			pieces.placeBlock(p_74875_1_, 4, 12, -6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.placeBlock(p_74875_1_, 4, 13, -6, Blocks.torch);			
			pieces.placeBlock(p_74875_1_, -4, 12, -6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.placeBlock(p_74875_1_, -4, 13, -6, Blocks.torch);
			pieces.placeBlock(p_74875_1_, 5, 12, 6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.placeBlock(p_74875_1_, 5, 13, 6, Blocks.torch);
			pieces.placeBlock(p_74875_1_, -5, 12, 6, IdMap.blockBlackFrostFenceSet, 0);
			pieces.placeBlock(p_74875_1_, -5, 13, 6, Blocks.torch);
			//platform
			pieces.fillAreaWithBlock(p_74875_1_, -1, 12, 9, 1, 12, 11, IdMap.blockBlackFrost, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -1, 12, 8, 1, 12, 8, IdMap.blockBlackFrostStairBrick, 2);
			pieces.fillAreaWithBlock(p_74875_1_, -2, 12, 9, -2, 12, 11, IdMap.blockBlackFrostStairBrick, 0);
			pieces.fillAreaWithBlock(p_74875_1_, 2, 12, 9, 2, 12, 11, IdMap.blockBlackFrostStairBrick, 1);
			pieces.placeBlock(p_74875_1_, -1, 13, 11, IdMap.blockBlackFrostFenceSet);
			pieces.placeBlock(p_74875_1_, 1, 13, 11, IdMap.blockBlackFrostFenceSet);
			pieces.placeBlock(p_74875_1_, -1, 14, 11, Blocks.torch);
			pieces.placeBlock(p_74875_1_, 1, 14, 11, Blocks.torch);
			return true;
		}
	}
	
}
