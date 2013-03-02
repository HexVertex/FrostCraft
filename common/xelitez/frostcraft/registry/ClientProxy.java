package xelitez.frostcraft.registry;

import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import xelitez.frostcraft.client.particle.EntitySnowFX;
import xelitez.frostcraft.client.gui.*;
import xelitez.frostcraft.tileentity.*;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
	Random rand = new Random();
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
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
	
	@Override
	public void registerSidedElements()
	{
		
	}
	
}
