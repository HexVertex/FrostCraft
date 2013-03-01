package xelitez.frostcraft.world;

import net.minecraft.world.biome.BiomeGenBase;

public class WorldTypeWinterLand extends WorldTypeBase 
{
	private BiomeGenBase[] biomes = new BiomeGenBase[] {BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.taiga, BiomeGenBase.taigaHills};
	
	public WorldTypeWinterLand(int par1, String par2Str) 
	{
		super(par1, par2Str);
	}
	
	@Override
    public BiomeGenBase[] getBiomesForWorldType() 
    {
        return biomes;
    }
}
