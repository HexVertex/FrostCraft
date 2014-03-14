package xelitez.frostcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.inventory.ContainerThermalPump;
import xelitez.frostcraft.tileentity.TileEntityThermalPump;

public class GuiThermalPump extends GuiContainer
{
	private TileEntityThermalPump inventory;
	
    private static final ResourceLocation texture = new ResourceLocation("frostcraft:textures/thermalpump.png");

    public GuiThermalPump(InventoryPlayer par1InventoryPlayer, TileEntityThermalPump par2TileEntityThermalPump)
    {
        super(new ContainerThermalPump(par1InventoryPlayer, par2TileEntityThermalPump));
        this.inventory = par2TileEntityThermalPump;
    }
    
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("Thermal Pump"), 55, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.fontRendererObj.drawString(new StringBuilder().append(inventory.storage).toString(), 110, this.ySize - 135, 4210752);
        this.fontRendererObj.drawString(new StringBuilder().append("/").append(inventory.capacity).append(" CFU").toString(), 100, this.ySize - 125, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        var7 = this.inventory.getCurrentValueScaled(14);
        this.drawTexturedModalRect(var5 + 81, var6 + 51 - var7, 176, 14 - var7, 14, var7);

        var7 = this.inventory.getCurrentStorgaeScaled(54);
        this.drawTexturedModalRect(var5 + 152, var6 + 68 - var7, 176, 68 - var7, 8, var7);
    }
}
