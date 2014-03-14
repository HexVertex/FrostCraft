package xelitez.frostcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.inventory.ContainerFrostFurnace;
import xelitez.frostcraft.tileentity.TileEntityFrostFurnace;

public class GuiFrostFurnace extends GuiContainer
{
	private TileEntityFrostFurnace furnaceInventory;
	
    private static final ResourceLocation texture = new ResourceLocation("frostcraft:textures/frostfurnace.png");

    public GuiFrostFurnace(InventoryPlayer par1InventoryPlayer, TileEntityFrostFurnace par2TileEntityFrostFurnace)
    {
        super(new ContainerFrostFurnace(par1InventoryPlayer, par2TileEntityFrostFurnace));
        this.furnaceInventory = par2TileEntityFrostFurnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(new StringBuilder().append("Frost ").append(StatCollector.translateToLocal("container.furnace")).toString(), 55, 6, 4210752);
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

        if (this.furnaceInventory.isBurning())
        {
            var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 57, var6 + 36 + 13 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }
}
