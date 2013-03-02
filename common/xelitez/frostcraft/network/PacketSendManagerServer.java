package xelitez.frostcraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import xelitez.frostcraft.entity.EntityFrostArrow;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketSendManagerServer
{
	private static void sendPacket(Packet packet)
	{
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendPacketToAllPlayers(packet);
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
            data.writeInt(tilet.worldObj.provider.dimensionId);
            data.writeInt(tilet.front);
            data.writeBoolean(tilet.isActive);
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "XFCC";
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        sendPacket(packet);
    }
    
    public static void sendEntityData(Entity entity)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(2);
            data.writeInt(entity.entityId);
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

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "XFCC";
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        sendPacket(packet);
    }
    
    public static void sendArrowData(EntityFrostArrow arrow)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeShort(3);
            data.writeInt(arrow.entityId);
            data.writeBoolean(arrow.canFreeze);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "XFCC";
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        sendPacket(packet);
    }
}
