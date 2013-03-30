package xelitez.frostcraft.world;

import xelitez.frostcraft.client.particle.EntitySnowFX;
import xelitez.frostcraft.client.render.RenderEffects;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorldAccess;

@SideOnly(Side.CLIENT)
public class WorldAccess implements IWorldAccess
{
	private static WorldAccess instance = new WorldAccess();
	
	public static WorldAccess instance()
	{
		return instance;
	}
	
	private Minecraft mc = FMLClientHandler.instance().getClient();
	private WorldClient theWorld;
	
    public void setWorldAndLoadRenderers()
    {
        if (this.theWorld != null)
        {
            this.theWorld.removeWorldAccess(this);
        }

        this.theWorld = this.mc.theWorld;

        if (this.mc.theWorld != null)
        {
        	this.mc.theWorld.addWorldAccess(this);
        }
    }
    
    public void setWorldAndLoadRenderers(WorldClient world)
    {
        if (this.theWorld != null)
        {
            this.theWorld.removeWorldAccess(this);
        }

        this.theWorld = world;

        if (world != null)
        {
        	world.addWorldAccess(this);
        }
    }
    

	@Override
	public void spawnParticle(String var1, double x, double y,
			double z, double mx, double my, double mz) 
	{
		if(mc != null && mc.renderViewEntity != null && mc.effectRenderer != null && mc.gameSettings.particleSetting <= 1)
		{
            double var15 = mc.renderViewEntity.posX - x;
            double var17 = mc.renderViewEntity.posY - y;
            double var19 = mc.renderViewEntity.posZ - z;
            double var22 = 128.0D;

            if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
            {
                return;
            }
            else
            {
            	if(var1.matches("snow"))
            	{
            		mc.effectRenderer.addEffect(new EntitySnowFX(this.theWorld, x, y, z, mx, my, mz, 1.25f));
            	}
            	if(var1.matches("frostExplosion"))
            	{
                    RenderEffects.spawnFrostExplosion(this.theWorld, 4, x, y, z, mc.effectRenderer);
            	}
            }
		}
		
	}

	@Override
	public void markBlockForUpdate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockForRenderUpdate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockRangeForRenderUpdate(int i, int j, int k, int l,
			int i1, int j1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(String s, double d0, double d1, double d2, float f,
			float f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoundToNearExcept(EntityPlayer entityplayer, String s,
			double d0, double d1, double d2, float f, float f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityCreate(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityDestroy(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRecord(String s, int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastSound(int i, int j, int k, int l, int i1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playAuxSFX(EntityPlayer entityplayer, int i, int j, int k,
			int l, int i1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyBlockPartially(int i, int j, int k, int l, int i1) {
		// TODO Auto-generated method stub
		
	}

}
