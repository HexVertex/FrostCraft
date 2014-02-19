package xelitez.frostcraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import xelitez.frostcraft.Frostcraft;
import xelitez.frostcraft.netty.Packet;
import xelitez.frostcraft.netty.PacketCustomData;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;

public class PacketSendManagerClient 
{
	private static void sendPacket(Packet packet)
	{
		Frostcraft.PIPELINE.sendToServer(packet);;
	}
	
	public static void requestBlockDataFromServer(TileEntityThermalMachines tet)
	{
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(1);
            int[] coords = {tet.xCoord, tet.yCoord, tet.zCoord};

            for (int var6 = 0; var6 < 3; var6++)
            {
                data.writeInt(coords[var6]);
            }   
            data.writeInt(tet.getWorldObj().provider.dimensionId);         
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PacketCustomData packet = new PacketCustomData(bytes.toByteArray());
        sendPacket(packet);
	}
}
