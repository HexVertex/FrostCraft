package xelitez.frostcraft.client.gui;

import org.lwjgl.opengl.GL11;

import xelitez.frostcraft.inventory.ContainerFrostGenerator;
import xelitez.frostcraft.tileentity.TileEntityFrostGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiFrostGenerator extends GuiContainer
{
	private TileEntityFrostGenerator generator;
	
    private static final ResourceLocation texture = new ResourceLocation("frostcraft:textures/frostgenerator.png");
	
	public GuiFrostGenerator(InventoryPlayer par1InventoryPlayer, TileEntityFrostGenerator par2TileEntityFrostGenerator) 
	{
		super(new ContainerFrostGenerator(par1InventoryPlayer, par2TileEntityFrostGenerator));
		this.generator = par2TileEntityFrostGenerator;
	}
	
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("Thermal Pump"), 55, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(new StringBuilder().append(generator.storage).toString(), 95, this.ySize - 135, 4210752);
        this.fontRenderer.drawString(new StringBuilder().append("/").append(generator.capacity).append(" CFU").toString(), 80, this.ySize - 125, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) 
	{
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(texture);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;

        if (this.generator.isBurning())
        {
            var7 = this.generator.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 57, var6 + 36 + 13 - var7, 176, 12 - var7, 14, var7 + 2);
        }
        
        var7 = this.generator.getCurrentStorgaeScaled(54);
        this.drawTexturedModalRect(var5 + 146, var6 + 70 - var7, 176, 68 - var7, 8, var7);
	}

}
