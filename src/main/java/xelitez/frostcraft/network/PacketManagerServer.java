package xelitez.frostcraft.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

import xelitez.frostcraft.FCLog;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;

import com.google.common.io.ByteArrayDataInput;

public class PacketManagerServer
{
	public static PacketManagerServer INSTANCE = new PacketManagerServer();
	
	public void onPacketData(ByteArrayDataInput dat, short ID, EntityPlayer player) 
	{
        if(ID != 0)
        {
            EntityPlayer thePlayer = player;
            World world = thePlayer.worldObj;
            switch(ID)
            {
            case 1:
                int coords[] = new int[3];

                for (int var1 = 0; var1 < 3; var1++)
                {
                    coords[var1] = dat.readInt();
                }
                int dimension = dat.readInt();
                if(world.provider.dimensionId == dimension)
                {
                	TileEntity tet = world.getTileEntity(coords[0], coords[1], coords[2]);
                	if(tet != null && tet instanceof TileEntityThermalMachines)
                	{
                		PacketSendManagerServer.sendBlockData((TileEntityThermalMachines)tet);
                	}
                }
                return;
            default:
            	return;
            }
            
        }
        else
        {
        	FCLog.log(Level.INFO, "Frostcraft packet recieved with invalid id");
        }
        
	}

}
