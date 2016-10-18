package org.telegram.tl.L57.messages;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;
import org.telegram.tl.APIContext;
import org.telegram.tl.L57.*;

public class SaveDraft extends TLObject {

    public static final int ID = 0xbc39e14b;

    public int flags;
    public int reply_to_msg_id;
    public TLInputPeer peer;
    public String message;
    public TLVector<TLMessageEntity> entities;

    public SaveDraft() {
        this.entities = new TLVector<>();
    }

    public SaveDraft(int flags, int reply_to_msg_id, TLInputPeer peer, String message, TLVector<TLMessageEntity> entities) {
        this.flags = flags;
        this.reply_to_msg_id = reply_to_msg_id;
        this.peer = peer;
        this.message = message;
        this.entities = entities;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        flags = buffer.readInt();
        if ((flags & (1 << 0)) != 0) {
            reply_to_msg_id = buffer.readInt();
        }
        peer = (TLInputPeer) buffer.readTLObject(APIContext.getInstance());
        message = buffer.readString();
        if ((flags & (1 << 3)) != 0) {
            entities = (TLVector<TLMessageEntity>) buffer.readTLVector(TLMessageEntity.class);
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
        if (reply_to_msg_id != 0) {
            flags |= (1 << 0);
        }
        if (entities != null) {
            flags |= (1 << 3);
        }
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(flags);
        if ((flags & (1 << 0)) != 0) {
            buff.writeInt(reply_to_msg_id);
        }
        buff.writeTLObject(peer);
        buff.writeString(message);
        if ((flags & (1 << 3)) != 0) {
            buff.writeTLObject(entities);
        }
    }

    public boolean is_no_webpage() {
        return (flags & (1 << 1)) != 0;
    }

    public void set_no_webpage(boolean v) {
        if (v) {
            flags |= (1 << 1);
        } else {
            flags &= ~(1 << 1);
        }
    }

    public int getConstructor() {
        return ID;
    }
}