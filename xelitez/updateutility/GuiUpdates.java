package xelitez.updateutility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.UpdateSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.UpdateInfo;

public class GuiUpdates extends GuiScreen
{
    /** simple date formater */
    private final DateFormat dateFormatter = new SimpleDateFormat();

    /**
     * A reference to the screen object that created this. Used for navigating between screens.
     */
    protected GuiScreen parentScreen;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Select world";

    /** True if a world has been selected. */
    private boolean selected = false;

    /** the currently selected world */
    private int selectedUpdate;

    /** The save list for the world selection screen */
    private List saveList;
    private GuiUpdatesSlot updateSlotContainer;

    /** E.g. Update, Welt, Monde, Mundo */
    private String localizedUpdateText;
    private String localizedMustConvertText;

    /**
     * The game mode text that is displayed with each world on the world selection list.
     */
    private String[] localizedGameModeText = new String[3];

    /** set to true if you arein the process of deleteing a world/save */
    private boolean deleting;

    /** the rename button in the world selection gui */
    private GuiButton buttonRename;

    /** the select button in the world selection gui */
    private GuiButton buttonSelect;

    /** the delete button in the world selection gui */
    private GuiButton buttonDelete;
    private GuiButton field_82316_w;

    public GuiUpdates(GuiScreen par1GuiScreen)
    {
        this.parentScreen = par1GuiScreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.screenTitle = var1.translateKey("selectUpdate.title");
        this.localizedUpdateText = var1.translateKey("selectUpdate.world");
        this.localizedMustConvertText = var1.translateKey("selectUpdate.conversion");
        this.localizedGameModeText[EnumGameType.SURVIVAL.getID()] = var1.translateKey("gameMode.survival");
        this.localizedGameModeText[EnumGameType.CREATIVE.getID()] = var1.translateKey("gameMode.creative");
        this.localizedGameModeText[EnumGameType.ADVENTURE.getID()] = var1.translateKey("gameMode.adventure");
        this.loadSaves();
        this.updateSlotContainer = new GuiUpdatesSlot(this);
        this.updateSlotContainer.registerScrollButtons(this.controlList, 4, 5);
        this.initButtons();
    }

    /**
     * loads the saves
     */
    private void loadSaves()
    {
        ISaveFormat var1 = this.mc.getSaveLoader();
        this.saveList = var1.getSaveList();
        Collections.sort(this.saveList);
        this.selectedUpdate = -1;
    }

    /**
     * returns the file name of the specified save number
     */
    protected String getSaveFileName(int par1)
    {
        return ((SaveFormatComparator)this.saveList.get(par1)).getFileName();
    }

    /**
     * returns the name of the saved game
     */
    protected String getSaveName(int par1)
    {
        String var2 = ((SaveFormatComparator)this.saveList.get(par1)).getDisplayName();

        if (var2 == null || MathHelper.stringNullOrLengthZero(var2))
        {
            StringTranslate var3 = StringTranslate.getInstance();
            var2 = var3.translateKey("selectUpdate.world") + " " + (par1 + 1);
        }

        return var2;
    }

    /**
     * intilize the buttons for this GUI
     */
    public void initButtons()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.controlList.add(this.buttonSelect = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, var1.translateKey("selectUpdate.select")));
        this.controlList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, var1.translateKey("selectUpdate.create")));
        this.controlList.add(this.buttonDelete = new GuiButton(6, this.width / 2 - 154, this.height - 28, 72, 20, var1.translateKey("selectUpdate.rename")));
        this.controlList.add(this.buttonRename = new GuiButton(2, this.width / 2 - 76, this.height - 28, 72, 20, var1.translateKey("selectUpdate.delete")));
        this.controlList.add(this.field_82316_w = new GuiButton(7, this.width / 2 + 4, this.height - 28, 72, 20, var1.translateKey("selectUpdate.recreate")));
        this.controlList.add(new GuiButton(0, this.width / 2 + 82, this.height - 28, 72, 20, var1.translateKey("gui.cancel")));
        this.buttonSelect.enabled = false;
        this.buttonRename.enabled = false;
        this.buttonDelete.enabled = false;
        this.field_82316_w.enabled = false;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 2)
            {
                String var2 = this.getSaveName(this.selectedUpdate);

                if (var2 != null)
                {
                    this.deleting = true;
                    GuiYesNo var3 = getDeleteUpdateScreen(this, var2, this.selectedUpdate);
                    this.mc.displayGuiScreen(var3);
                }
            }
            else if (par1GuiButton.id == 1)
            {
                this.selectUpdate(this.selectedUpdate);
            }
            else if (par1GuiButton.id == 0)
            {
                this.mc.displayGuiScreen(this.parentScreen);
            }
            else
            {
                this.updateSlotContainer.actionPerformed(par1GuiButton);
            }
        }
    }

    /**
     * Gets the selected world.
     */
    public void selectUpdate(int par1)
    {
        this.mc.displayGuiScreen((GuiScreen)null);

        if (!this.selected)
        {
            this.selected = true;
            String var2 = this.getSaveFileName(par1);

            if (var2 == null)
            {
                var2 = "Update" + par1;
            }

            String var3 = this.getSaveName(par1);

            if (var3 == null)
            {
                var3 = "Update" + par1;
            }

            if (this.mc.getSaveLoader().canLoadUpdate(var2))
            {
                this.mc.launchIntegratedServer(var2, var3, (UpdateSettings)null);
            }
        }
    }

    public void confirmClicked(boolean par1, int par2)
    {
        if (this.deleting)
        {
            this.deleting = false;

            if (par1)
            {
                ISaveFormat var3 = this.mc.getSaveLoader();
                var3.flushCache();
                var3.deleteUpdateDirectory(this.getSaveFileName(par2));
                this.loadSaves();
            }

            this.mc.displayGuiScreen(this);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.updateSlotContainer.drawScreen(par1, par2, par3);
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Gets a GuiYesNo screen with the warning, buttons, etc.
     */
    public static GuiYesNo getDeleteUpdateScreen(GuiScreen par0GuiScreen, String par1Str, int par2)
    {
        StringTranslate var3 = StringTranslate.getInstance();
        String var4 = var3.translateKey("selectUpdate.deleteQuestion");
        String var5 = "\'" + par1Str + "\' " + var3.translateKey("selectUpdate.deleteWarning");
        String var6 = var3.translateKey("selectUpdate.deleteButton");
        String var7 = var3.translateKey("gui.cancel");
        GuiYesNo var8 = new GuiYesNo(par0GuiScreen, var4, var5, var6, var7, par2);
        return var8;
    }

    static List getSize(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.saveList;
    }

    /**
     * called whenever an element in this gui is selected
     */
    static int onElementSelected(GuiUpdates par0GuiUpdates, int par1)
    {
        return par0GuiUpdates.selectedUpdate = par1;
    }

    /**
     * returns the world currently selected
     */
    static int getSelectedUpdate(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.selectedUpdate;
    }

    /**
     * returns the select button
     */
    static GuiButton getSelectButton(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.buttonSelect;
    }

    /**
     * returns the rename button
     */
    static GuiButton getRenameButton(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.buttonRename;
    }

    /**
     * returns the delete button
     */
    static GuiButton getDeleteButton(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.buttonDelete;
    }

    static GuiButton func_82312_f(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.field_82316_w;
    }

    static String func_82313_g(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.localizedUpdateText;
    }

    static DateFormat func_82315_h(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.dateFormatter;
    }

    static String func_82311_i(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.localizedMustConvertText;
    }

    static String[] func_82314_j(GuiUpdates par0GuiUpdates)
    {
        return par0GuiUpdates.localizedGameModeText;
    }
}
