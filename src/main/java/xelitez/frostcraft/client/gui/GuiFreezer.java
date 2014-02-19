package xelitez.frostcraft.client.gui;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.inventory.ContainerFreezer;
import xelitez.frostcraft.tileentity.TileEntityFreezer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiFreezer extends GuiContainer
{
	private TileEntityFreezer tile;
	
    private static final ResourceLocation texture = new ResourceLocation("frostcraft:textures/freezer.png");

    public GuiFreezer(InventoryPlayer par1InventoryPlayer, TileEntityFreezer par2TileEntityFreezer)
    {
        super(new ContainerFreezer(par1InventoryPlayer, par2TileEntityFreezer));
        this.tile = par2TileEntityFreezer;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Freezer", 68, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        if (this.tile.isFreezing())
        {
            var7 = this.tile.getStorageRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 54, var6 + 48 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.tile.getFreezeProgressScaled(24);
        this.drawTexturedModalRect(var5 + 75, var6 + 34, 176, 14, var7 + 1, 16);
    }
}
