package xelitez.frostcraft.network;

import java.util.logging.Level;

import xelitez.frostcraft.FCLog;
import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketManagerServer implements IPacketHandler{

	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) 
	{
		ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
        short ID = 0;
        ID = dat.readShort();
        if(ID != 0)
        {
            EntityPlayer thePlayer = (EntityPlayer)player;
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
                	TileEntityThermalMachines tet = (TileEntityThermalMachines)world.getBlockTileEntity(coords[0], coords[1], coords[2]);
                	PacketSendManagerServer.sendBlockData(tet);
                }
                return;
            default:
            	return;
            }
            
        }
        else
        {
        	FCLog.log(Level.INFO, "FrostCraft packet recieved with invalid id");
        }
        
	}

}
