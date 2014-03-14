package xelitez.frostcraft.registry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xelitez.frostcraft.inventory.ContainerFreezer;
import xelitez.frostcraft.inventory.ContainerFrostEnforcer;
import xelitez.frostcraft.inventory.ContainerFrostFurnace;
import xelitez.frostcraft.inventory.ContainerFrostGenerator;
import xelitez.frostcraft.inventory.ContainerThermalPump;
import xelitez.frostcraft.tileentity.TileEntityFreezer;
import xelitez.frostcraft.tileentity.TileEntityFrostEnforcer;
import xelitez.frostcraft.tileentity.TileEntityFrostFurnace;
import xelitez.frostcraft.tileentity.TileEntityFrostGenerator;
import xelitez.frostcraft.tileentity.TileEntityThermalPump;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null)
		{
			switch(ID)
			{
			case 0:
				return new ContainerThermalPump(player.inventory, (TileEntityThermalPump)te);
			case 1:
				return new ContainerFrostFurnace(player.inventory, (TileEntityFrostFurnace)te);
			case 2:
				return new ContainerFrostGenerator(player.inventory, (TileEntityFrostGenerator)te);
			case 3:
				return new ContainerFreezer(player.inventory, (TileEntityFreezer)te);
			case 4:
				return new ContainerFrostEnforcer(player.inventory, (TileEntityFrostEnforcer)te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		return null;
	}
	
	public void registerSidedElements()
	{
		
	}

}
