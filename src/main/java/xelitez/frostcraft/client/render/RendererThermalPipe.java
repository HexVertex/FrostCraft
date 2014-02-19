package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.interfaces.IConnect;
import xelitez.frostcraft.block.BlockThermalPipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererThermalPipe implements ISimpleBlockRenderingHandler
{
	int id;
	
	public RendererThermalPipe(int id)
	{
		this.id = id;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) 
	{
        Tessellator var4 = Tessellator.instance;
		renderer.setRenderBounds(0.0625D, 0.3125D, 0.3125D, 0.9375D, 0.6875D, 0.6875D);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var4.startDrawingQuads();
        var4.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, metadata));
        var4.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) 
	{
		if(block instanceof BlockThermalPipe)
		{
			BlockThermalPipe pipe = (BlockThermalPipe)block;
	    	boolean canConnectTop = world.getBlock(x, y + 1, z) instanceof IConnect && ((IConnect)world.getBlock(x, y + 1, z)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	    	boolean canConnectBottom = world.getBlock(x, y - 1, z) instanceof IConnect && ((IConnect)world.getBlock(x, y - 1, z)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	        boolean canConnectLeft = world.getBlock(x + 1, y, z) instanceof IConnect && ((IConnect)world.getBlock(x + 1, y, z)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	        boolean canConnectRight = world.getBlock(x - 1, y, z) instanceof IConnect && ((IConnect)world.getBlock(x - 1, y, z)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	        boolean canConnectFront = world.getBlock(x, y, z + 1) instanceof IConnect && ((IConnect)world.getBlock(x, y, z + 1)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	        boolean canConnectBack = world.getBlock(x, y, z - 1) instanceof IConnect && ((IConnect)world.getBlock(x, y, z - 1)).getConnectionType() == ((IConnect)world.getBlock(x, y, z)).getConnectionType();
	        int connections = 0;
	        
	        if (canConnectTop)
	        {
	            renderer.setRenderBounds(0.3125D, 0.6875D, 0.3125D, 0.6875D, 0.9375D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.setRenderBounds(0.3125D, 0.9375D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            connections += 1;
	        }
	
	        if (canConnectBottom)
	        {
	            renderer.setRenderBounds(0.3125D, 0.0625D, 0.3125D, 0.6875D, 0.3125D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.uvRotateEast = 3;
	            renderer.uvRotateSouth = 3;
	            renderer.uvRotateWest = 3;
	            renderer.uvRotateNorth = 3;
	            renderer.setRenderBounds(0.3125D, 0.0, 0.3125D, 0.6875D, 0.0625D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            renderer.uvRotateEast = 0;
	            renderer.uvRotateSouth = 0;
	            renderer.uvRotateWest = 0;
	            renderer.uvRotateNorth = 0;
	            connections += 1;
	        }
	
	        if (canConnectRight)
	        {
	            renderer.setRenderBounds(0.0625D, 0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.uvRotateTop = 2;
	            renderer.uvRotateBottom = 2;
	            renderer.setRenderBounds(0.0D, 0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            renderer.uvRotateTop = 0;
	            renderer.uvRotateBottom = 0;
	            connections += 1;
	        }
	
	        if (canConnectLeft)
	        {
	            renderer.setRenderBounds(0.6875D, 0.3125D, 0.3125D, 0.9375D, 0.6875D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.uvRotateTop = 1;
	            renderer.uvRotateBottom = 1;
	            renderer.setRenderBounds(0.9375D, 0.3125D, 0.3125D, 1.0D, 0.6875D, 0.6875D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            renderer.uvRotateTop = 0;
	            renderer.uvRotateBottom = 0;
	            connections += 1;
	        }
	        
	        if (canConnectBack)
	        {
	            renderer.setRenderBounds(0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.6875D, 0.3125D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.uvRotateBottom = 3;
	            renderer.setRenderBounds(0.3125D, 0.3125D, 0.0D, 0.6875D, 0.6875D, 0.0625D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            renderer.uvRotateBottom = 0;
	            connections += 1;
	        }
	
	        if (canConnectFront)
	        {
	            renderer.setRenderBounds(0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D, 0.9375D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.setOverrideBlockTexture(pipe.innertexture);
	            renderer.uvRotateTop = 3;
	            renderer.setRenderBounds(0.3125D, 0.3125D, 0.9375D, 0.6875D, 0.6875D, 1.0D);
	            renderer.renderStandardBlock(block, x, y, z);
	            renderer.clearOverrideBlockTexture();
	            renderer.uvRotateTop = 0;
	            connections += 1;
	        }
	        if(connections == 1)
	        {
	        	if(canConnectTop)
	        	{
	            	renderer.setRenderBounds(0.3125D, 0.375D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	if(canConnectBottom)
	        	{
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.625D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.3125D, 0.625D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	if(canConnectRight)
	        	{
	            	renderer.uvRotateEast = 1;
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.625D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.uvRotateEast = 0;
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.625D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	if(canConnectLeft)
	        	{
	            	renderer.uvRotateEast = 1;
	            	renderer.setRenderBounds(0.375D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.uvRotateEast = 0;
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.375D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	if(canConnectBack)
	        	{
	            	renderer.uvRotateSouth = 1;
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.625D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.uvRotateSouth = 0;
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.625D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	if(canConnectFront)
	        	{
	            	renderer.uvRotateSouth = 1;
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.375D, 0.6875D, 0.6875D, 0.6875D);
	            	renderer.renderStandardBlock(block, x, y, z);
	            	renderer.uvRotateSouth = 0;
	            	renderer.setOverrideBlockTexture(pipe.innertexture);
	            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.375D);
	            	renderer.renderStandardBlock(block, x, y, z);
	        	}
	        	renderer.clearOverrideBlockTexture();
	        }
	        else
	        {
	        	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
	        	renderer.renderStandardBlock(block, x, y, z);
	        }
	        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelID) 
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return id;
	}

}
