package xelitez.frostcraft.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import xelitez.frostcraft.network.PacketManagerClient;
import xelitez.frostcraft.network.PacketManagerServer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class PacketCustomData extends Packet
{
	private ByteBuf data;
	
	public PacketCustomData() {}
	
	public PacketCustomData(byte[] data)
	{
		this.data = Unpooled.wrappedBuffer(data);
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
        ByteArrayDataInput dat = ByteStreams.newDataInput(data.array());
        short ID = dat.readShort();
        PacketManagerClient.INSTANCE.onPacketData(dat, ID, player);
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
        ByteArrayDataInput dat = ByteStreams.newDataInput(data.array());
        short ID = dat.readShort();
        PacketManagerServer.INSTANCE.onPacketData(dat, ID, player);
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		data = buf.copy();	
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		if(data != null)
		{
			buf.writeBytes(data);
		}	
	}
}
