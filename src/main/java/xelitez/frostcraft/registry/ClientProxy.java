package xelitez.frostcraft.registry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xelitez.frostcraft.client.gui.GuiFreezer;
import xelitez.frostcraft.client.gui.GuiFrostEnforcer;
import xelitez.frostcraft.client.gui.GuiFrostFurnace;
import xelitez.frostcraft.client.gui.GuiFrostGenerator;
import xelitez.frostcraft.client.gui.GuiThermalPump;
import xelitez.frostcraft.tileentity.TileEntityFreezer;
import xelitez.frostcraft.tileentity.TileEntityFrostEnforcer;
import xelitez.frostcraft.tileentity.TileEntityFrostFurnace;
import xelitez.frostcraft.tileentity.TileEntityFrostGenerator;
import xelitez.frostcraft.tileentity.TileEntityThermalPump;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null)
		{
			switch(ID)
			{
			case 0:
				return new GuiThermalPump(player.inventory, (TileEntityThermalPump)te);
			case 1:
				return new GuiFrostFurnace(player.inventory, (TileEntityFrostFurnace)te);
			case 2:
				return new GuiFrostGenerator(player.inventory, (TileEntityFrostGenerator)te);
			case 3:
				return new GuiFreezer(player.inventory, (TileEntityFreezer)te);
			case 4:
				return new GuiFrostEnforcer(player.inventory, (TileEntityFrostEnforcer)te);
			}
		}
		return null;
	}
	
}
