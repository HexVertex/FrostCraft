package xelitez.frostcraft.client.render;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.interfaces.IConnect;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RendererThermalPipe implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) 
	{
        Tessellator var4 = Tessellator.instance;
		renderer.setRenderBounds(0.0625D, 0.3125D, 0.3125D, 0.9375D, 0.6875D, 0.6875D);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var4.startDrawingQuads();
        var4.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, metadata));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, metadata));
        var4.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) 
	{
    	boolean canConnectTop = Block.blocksList[world.getBlockId(x, y + 1, z)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x, y + 1, z)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
    	boolean canConnectBottom = Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x, y - 1, z)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
        boolean canConnectLeft = Block.blocksList[world.getBlockId(x + 1, y, z)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x + 1, y, z)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
        boolean canConnectRight = Block.blocksList[world.getBlockId(x - 1, y, z)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x - 1, y, z)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
        boolean canConnectFront = Block.blocksList[world.getBlockId(x, y, z + 1)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x, y, z + 1)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
        boolean canConnectBack = Block.blocksList[world.getBlockId(x, y, z - 1)] instanceof IConnect && ((IConnect)Block.blocksList[world.getBlockId(x, y, z - 1)]).getConnectionType() == ((IConnect)Block.blocksList[world.getBlockId(x, y, z)]).getConnectionType();
        int connections = 0;
        
        if (canConnectTop)
        {
            renderer.setRenderBounds(0.3125D, 0.6875D, 0.3125D, 0.6875D, 0.9375D, 0.6875D);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            renderer.setRenderBounds(0.3125D, 0.9375D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.clearOverrideBlockTexture();
            connections += 1;
        }

        if (canConnectBottom)
        {
            renderer.setRenderBounds(0.3125D, 0.0625D, 0.3125D, 0.6875D, 0.3125D, 0.6875D);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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
            renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
        	}
        	if(canConnectBottom)
        	{
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.625D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            	renderer.setRenderBounds(0.3125D, 0.625D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
        	}
        	if(canConnectRight)
        	{
            	renderer.uvRotateEast = 1;
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.625D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
            	renderer.uvRotateEast = 0;
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            	renderer.setRenderBounds(0.625D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
        	}
        	if(canConnectLeft)
        	{
            	renderer.uvRotateEast = 1;
            	renderer.setRenderBounds(0.375D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
            	renderer.uvRotateEast = 0;
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.375D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
        	}
        	if(canConnectBack)
        	{
            	renderer.uvRotateSouth = 1;
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.625D);
            	renderer.renderStandardBlock(block, x, y, z);
            	renderer.uvRotateSouth = 0;
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.625D, 0.6875D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
        	}
        	if(canConnectFront)
        	{
            	renderer.uvRotateSouth = 1;
            	renderer.setRenderBounds(0.3125D, 0.3125D, 0.375D, 0.6875D, 0.6875D, 0.6875D);
            	renderer.renderStandardBlock(block, x, y, z);
            	renderer.uvRotateSouth = 0;
            	renderer.setOverrideBlockTexture(block.blockIndexInTexture + 1);
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

	@Override
	public boolean shouldRender3DInInventory() 
	{
		return true;
	}

	@Override
	public int getRenderId() 
	{
		return 2200;
	}

}
