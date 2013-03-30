package xelitez.frostcraft.world;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import xelitez.frostcraft.registry.IdMap;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class WorldTicker implements ITickHandler
{	
    protected int updateLCG = (new Random()).nextInt();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		World world = (World)tickData[0];
        Iterator<?> var3 = world.activeChunkSet.iterator();
        while (var3.hasNext())
        {
            ChunkCoordIntPair var4 = (ChunkCoordIntPair)var3.next();
            int var5 = var4.chunkXPos * 16;
            int var6 = var4.chunkZPos * 16;
            Chunk var7 = world.getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
            if (world.provider.canDoRainSnowIce(var7) && world.rand.nextInt(48) == 0)
            {
                this.updateLCG = this.updateLCG * 3 + 1013904223;
                int var8 = this.updateLCG >> 2;
                int var9 = var8 & 15;
                int var10 = var8 >> 8 & 15;
                List<Integer> yPosses = WorldGenIcicles.getPossibleYPos(world, var9 + var5, var10 + var6);
				if(yPosses.size() > 0)
				{
					int index = world.rand.nextInt(yPosses.size());
					if(index >= yPosses.size())
					{
						index = yPosses.size() - 1;
					}
					int var11 = yPosses.get(index);
					if (world.isRaining() && this.canSpawnIcicles(world, var9 + var5, var11, var10 + var6) && world.getSavedLightValue(EnumSkyBlock.Block, var9 + var5, var11, var10 + var6) < 11)
					{
						world.setBlock(var9 + var5, var11, var10 + var6, IdMap.blockIcicle.blockID);
					}
				}
            }
        }
	}

	private boolean canSpawnIcicles(World world, int i, int j, int k) 
	{
		return IdMap.blockIcicle.canBlockStay(world, i, j, k) && WorldGenIcicles.getInstance().biomeList.contains(world.getBiomeGenForCoords(i, k));
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() 
	{
		return "frostcraft.worldticker";
	}

}
