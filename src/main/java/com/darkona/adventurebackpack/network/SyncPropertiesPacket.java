package com.darkona.adventurebackpack.network;

import com.darkona.adventurebackpack.common.BackpackProperty;
import com.darkona.adventurebackpack.common.ClientActions;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;

/**
 * Created on 08/01/2015
 *
 * @author Darkona
 */
public class SyncPropertiesPacket implements IMessageHandler<SyncPropertiesPacket.Message,SyncPropertiesPacket.Message>
{


    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public Message onMessage(Message message, MessageContext ctx)
    {

        if(ctx.side.isClient())
        {
            ClientActions.synchronizePlayer(message.properties);
        }
        return null;
    }

    public static class Message implements IMessage
    {

        NBTTagCompound properties;


        public Message(){}

        public Message(NBTTagCompound props)
        {
            properties = props;
        }
        @Override
        public void fromBytes(ByteBuf buf)
        {
            properties = ByteBufUtils.readTag(buf);
        }

        /**
         * Deconstruct your message into the supplied byte buffer
         *
         * @param buf
         */
        @Override
        public void toBytes(ByteBuf buf)
        {
            ByteBufUtils.writeTag(buf,properties);
        }
    }
}
