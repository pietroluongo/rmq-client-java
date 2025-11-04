package com.pietroluongo.rmq_client.client.frames;

public enum FrameType {
    METHOD((byte) 1),
    HEADER((byte) 2);

    private final byte index;

    FrameType(Byte index) {
        this.index = index;
    }

    public Byte getValue() {
        return index;
    }

    public static FrameType fromByteArray(byte[] bytes) {
        return FrameType.fromByte(bytes[0]);
    }

    public static FrameType fromByte(byte b) {
        return FrameType.values()[b];
    }
}
