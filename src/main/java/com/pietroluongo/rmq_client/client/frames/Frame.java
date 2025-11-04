package com.pietroluongo.rmq_client.client.frames;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import com.pietroluongo.rmq_client.client.frames.payloads.Payload;
import com.pietroluongo.rmq_client.exceptions.FrameTypeNotFoundException;
import com.pietroluongo.rmq_client.utils.ByteUtils;

public abstract class Frame {
    protected ByteArrayOutputStream data = new ByteArrayOutputStream(4096);
    protected FrameType type;
    protected short channel;
    protected long size = Constants.INITIAL_FRAME_SIZE;
    protected Payload payload;

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
        return data.toByteArray();
    };

    public String toString() {
        return new String(data.toByteArray(), StandardCharsets.US_ASCII);
    }

    protected void append(byte[] bytes) {
        data.write(bytes, ((int) size), bytes.length);
        size += bytes.length;
    }

    protected void append(byte b) {
        data.write(new byte[] { b }, (int) size, Byte.BYTES);
        size += 1;
    }

    protected void append(short s) {
        var channelBytes = ByteUtils.shortToBytes(channel);
        data.write(channelBytes, (int) size, Short.BYTES);
        size += 2;
    }

    protected void append(long s) {
        var channelBytes = ByteUtils.longToBytes(channel);
        data.write(channelBytes, (int) size, Long.BYTES);
        size += Long.BYTES;
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
