package xelitez.frostcraft;

import xelitez.frostcraft.client.particle.EntitySnowFX;
import xelitez.frostcraft.client.render.RenderEffects;
import xelitez.frostcraft.registry.IdMap;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class WorldAccess implements IWorldAccess
{
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
    
	@Override
	public void markBlockForUpdate(int var1, int var2, int var3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockForRenderUpdate(int var1, int var2, int var3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markBlockRangeForRenderUpdate(int var1, int var2, int var3,
			int var4, int var5, int var6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(String var1, double var2, double var4, double var6,
			float var8, float var9) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoundToNearExcept(EntityPlayer var1, String var2,
			double var3, double var5, double var7, float var9, float var10) {
		// TODO Auto-generated method stub
		
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
            		mc.effectRenderer.addEffect(new EntitySnowFX(this.theWorld, x, y, z, mx, my, mz, 1.25f), IdMap.itemParticleItem);
            	}
            	if(var1.matches("frostExplosion"))
            	{
                    RenderEffects.spawnFrostExplosion(this.theWorld, 4, x, y, z, mc.effectRenderer);
            	}
            }
		}
		
	}

	@Override
	public void obtainEntitySkin(Entity var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseEntitySkin(Entity var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRecord(String var1, int var2, int var3, int var4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcastSound(int var1, int var2, int var3, int var4, int var5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playAuxSFX(EntityPlayer var1, int var2, int var3, int var4,
			int var5, int var6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyBlockPartially(int var1, int var2, int var3, int var4,
			int var5) {
		// TODO Auto-generated method stub
		
	}

}
