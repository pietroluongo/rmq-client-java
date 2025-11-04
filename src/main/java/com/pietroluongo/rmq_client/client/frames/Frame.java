package com.pietroluongo.rmq_client.client.frames;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import com.pietroluongo.rmq_client.client.frames.payloads.Payload;
import com.pietroluongo.rmq_client.exceptions.FrameTypeNotFoundException;
import com.pietroluongo.rmq_client.utils.ByteUtils;

public abstract class Frame {
    private LinkedList<Byte> data = new LinkedList<Byte>();
    protected FrameType type;
    protected short channel;
    protected long size = Constants.INITIAL_FRAME_SIZE;
    protected Payload payload;
    private final int TYPE_OFFSET = 0;
    private final int CHANNEL_OFFSET = 1;
    private final int SIZE_OFFSET = 3;
    private final int PAYLOAD_OFFSET = 7;

    public static Frame fromBytes(byte[] bytes) throws FrameTypeNotFoundException {
        var type = FrameType.fromByteArray(bytes);
        switch (type) {
            case METHOD:
                return new MethodFrame();
            case HEADER:
                return new HeaderFrame();
            default:
                throw new FrameTypeNotFoundException(String.format("Failed to match frame type %c", bytes[0]));
        }
    }

    public byte[] toBytes() {
        var data = new byte[this.data.size()];
        var index = new AtomicInteger(0);
        this.data.forEach(i -> data[index.getAndIncrement()] = i);
        return data;
    };

    public String toString() {
        var bytes = this.toBytes();
        return new String(bytes, StandardCharsets.US_ASCII);
    }

    protected void append(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            data.push(bytes[i]);
        }
        size += bytes.length;
    }

    protected void append(byte b) {
        data.push(b);
        size += 1;
    }

    protected void append(short s) {
        var channelBytes = ByteUtils.shortToBytes(channel);
        this.append(channelBytes);
    }

    protected void append(long s) {
        var channelBytes = ByteUtils.longToBytes(channel);
        this.append(channelBytes);
    }

    public boolean isOfType(FrameType t) {
        return this.type == t;
    }

    protected void serialize() {
        this.append(this.type.getValue());
        this.append(this.channel);
        this.append(this.payload.getSize() + Constants.BASE_FRAME_SIZE);
        this.append(this.payload.serialize());
        this.append(Constants.FRAME_END);
    }
}
