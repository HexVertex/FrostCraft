package xelitez.updateutility;

import java.util.Date;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.SaveFormatComparator;

public class GuiUpdatesSlot extends GuiSlot
{
    final GuiUpdates parentUpdateGui;

    public GuiUpdatesSlot(GuiUpdates par1GuiUpdates)
    {
        super(par1GuiUpdates.mc, par1GuiUpdates.width, par1GuiUpdates.height, 32, par1GuiUpdates.height - 64, 36);
        this.parentUpdateGui = par1GuiUpdates;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiUpdates.getSize(this.parentUpdateGui).size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        GuiUpdates.onElementSelected(this.parentUpdateGui, par1);
        boolean var3 = GuiUpdates.getSelectedUpdate(this.parentUpdateGui) >= 0 && GuiUpdates.getSelectedUpdate(this.parentUpdateGui) < this.getSize();
        GuiUpdates.getSelectButton(this.parentUpdateGui).enabled = var3;
        GuiUpdates.getRenameButton(this.parentUpdateGui).enabled = var3;
        GuiUpdates.getDeleteButton(this.parentUpdateGui).enabled = var3;
        GuiUpdates.func_82312_f(this.parentUpdateGui).enabled = var3;

        if (par2 && var3)
        {
            this.parentUpdateGui.selectUpdate(par1);
        }
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == GuiUpdates.getSelectedUpdate(this.parentUpdateGui);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return GuiUpdates.getSize(this.parentUpdateGui).size() * 36;
    }

    protected void drawBackground()
    {
        this.parentUpdateGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        SaveFormatComparator var6 = (SaveFormatComparator)GuiUpdates.getSize(this.parentUpdateGui).get(par1);
        String var7 = var6.getDisplayName();

        if (var7 == null || MathHelper.stringNullOrLengthZero(var7))
        {
            var7 = GuiUpdates.func_82313_g(this.parentUpdateGui) + " " + (par1 + 1);
        }

        String var8 = var6.getFileName();
        var8 = var8 + " (" + GuiUpdates.func_82315_h(this.parentUpdateGui).format(new Date(var6.getLastTimePlayed()));
        var8 = var8 + ")";
        String var9 = "";

        if (var6.requiresConversion())
        {
            var9 = GuiUpdates.func_82311_i(this.parentUpdateGui) + " " + var9;
        }
        else
        {
            var9 = GuiUpdates.func_82314_j(this.parentUpdateGui)[var6.getEnumGameType().getID()];

            if (var6.isHardcoreModeEnabled())
            {
                var9 = "\u00a74" + StatCollector.translateToLocal("gameMode.hardcore") + "\u00a7r";
            }

            if (var6.getCheatsEnabled())
            {
                var9 = var9 + ", " + StatCollector.translateToLocal("selectUpdate.cheats");
            }
        }

        this.parentUpdateGui.drawString(this.parentUpdateGui.fontRenderer, var7, par2 + 2, par3 + 1, 16777215);
        this.parentUpdateGui.drawString(this.parentUpdateGui.fontRenderer, var8, par2 + 2, par3 + 12, 8421504);
        this.parentUpdateGui.drawString(this.parentUpdateGui.fontRenderer, var9, par2 + 2, par3 + 12 + 10, 8421504);
    }
}
