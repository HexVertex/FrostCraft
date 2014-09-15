package xelitez.frostcraft.netty;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class Packet implements IMessage
{
    /**
     * Handle a packet on the client side. Note this occurs after decoding has completed.
     *
     * @param player the player reference
     */
    public abstract void handleClientSide(EntityPlayer player);

    /**
     * Handle a packet on the server side. Note this occurs after decoding has completed.
     *
     * @param player the player reference
     */
    public abstract void handleServerSide(EntityPlayer player);
}
