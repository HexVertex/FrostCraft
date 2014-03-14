package xelitez.frostcraft.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
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
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		if(data != null)
		{
			buffer.writeBytes(data);
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		data = buffer.copy();
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
}
