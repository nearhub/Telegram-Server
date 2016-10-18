package org.telegram.tl.L57;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;
import org.telegram.tl.APIContext;
import org.telegram.tl.L57.*;

public class InputBotInlineMessageMediaContact extends TLInputBotInlineMessage {

    public static final int ID = 0x2daf01a7;

    public int flags;
    public String phone_number;
    public String first_name;
    public String last_name;
    public TLReplyMarkup reply_markup;

    public InputBotInlineMessageMediaContact() {
    }

    public InputBotInlineMessageMediaContact(int flags, String phone_number, String first_name, String last_name, TLReplyMarkup reply_markup) {
        this.flags = flags;
        this.phone_number = phone_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.reply_markup = reply_markup;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        flags = buffer.readInt();
        phone_number = buffer.readString();
        first_name = buffer.readString();
        last_name = buffer.readString();
        if ((flags & (1 << 2)) != 0) {
            reply_markup = (TLReplyMarkup) buffer.readTLObject(APIContext.getInstance());
        }
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        setFlags();
        serializeTo(buffer);
        return buffer;
    }

    public void setFlags() {
        if (reply_markup != null) {
            flags |= (1 << 2);
        }
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(flags);
        buff.writeString(phone_number);
        buff.writeString(first_name);
        buff.writeString(last_name);
        if ((flags & (1 << 2)) != 0) {
            buff.writeTLObject(reply_markup);
        }
    }


    public int getConstructor() {
        return ID;
    }
}