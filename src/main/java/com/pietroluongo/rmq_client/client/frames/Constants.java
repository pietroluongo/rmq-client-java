package com.pietroluongo.rmq_client.client.frames;

public class Constants {
    public static final byte FRAME_END = (byte) 0xCE;
    public static final long INITIAL_FRAME_SIZE = 7;
    public static final int MAX_FRAME_SIZE = 131072;
    public static final int BASE_FRAME_SIZE = Byte.BYTES + Short.BYTES + Long.BYTES + Byte.BYTES;
}
