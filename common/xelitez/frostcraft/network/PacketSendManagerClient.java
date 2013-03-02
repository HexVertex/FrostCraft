package xelitez.frostcraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import xelitez.frostcraft.tileentity.TileEntityThermalMachines;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketSendManagerClient 
{
	private static void sendPacket(Packet250CustomPayload packet)
	{
		FMLClientHandler.instance().sendPacket(packet);
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
            data.writeInt(tet.worldObj.provider.dimensionId);         
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "XFCS";
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        sendPacket(packet);
	}
}
