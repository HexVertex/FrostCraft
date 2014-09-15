package xelitez.frostcraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import xelitez.frostcraft.Frostcraft;
import xelitez.frostcraft.entity.EntityFrostArrow;
import xelitez.frostcraft.netty.Packet;
import xelitez.frostcraft.netty.PacketCustomData;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;

public class PacketSendManagerServer
{
	private static void sendPacket(Packet packet)
	{
		Frostcraft.network.sendToAll(packet);
	}
	
    public static void sendBlockData(TileEntityThermalMachines tilet)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(1);
            int[] coords = {tilet.xCoord, tilet.yCoord, tilet.zCoord};

            for (int var6 = 0; var6 < 3; var6++)
            {
                data.writeInt(coords[var6]);
            }   
            data.writeInt(tilet.getWorldObj().provider.dimensionId);
            data.writeInt(tilet.front);
            data.writeBoolean(tilet.isActive);
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PacketCustomData packet = new PacketCustomData(bytes.toByteArray());
        sendPacket(packet);
    }
    
    public static void sendEntityData(Entity entity)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(2);
            data.writeInt(entity.getEntityId());
            data.writeDouble(entity.posX);
            data.writeDouble(entity.posY);
            data.writeDouble(entity.posZ);
            data.writeDouble(entity.prevPosX);
            data.writeDouble(entity.prevPosY);
            data.writeDouble(entity.prevPosZ);
            data.writeDouble(entity.motionX);
            data.writeDouble(entity.motionY);
            data.writeDouble(entity.motionZ);
            data.writeFloat(entity.rotationYaw);
            data.writeFloat(entity.rotationPitch);
            data.writeFloat(entity.prevRotationYaw);
            data.writeFloat(entity.prevRotationPitch);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PacketCustomData packet = new PacketCustomData(bytes.toByteArray());
        sendPacket(packet);
    }
    
    public static void sendArrowData(EntityFrostArrow arrow)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(3);
            data.writeInt(arrow.getEntityId());
            data.writeBoolean(arrow.canFreeze);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PacketCustomData packet = new PacketCustomData(bytes.toByteArray());
        sendPacket(packet);
    }
}
