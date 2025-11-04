package com.pietroluongo.rmq_client.utils;

import java.nio.ByteBuffer;

public class ByteUtils {
    private static ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES);
    private static ByteBuffer shortBuffer = ByteBuffer.allocate(Short.BYTES);

    static public byte[] longToBytes(long x) {
        longBuffer.putLong(x);
        return longBuffer.array();
    }

    static public long bytesToLong(byte[] bytes) {
        longBuffer.put(bytes);
        longBuffer.flip();// need flip
        return longBuffer.getLong();
    }

    static public byte[] shortToBytes(short x) {
        shortBuffer.putShort(x);
        return shortBuffer.array();
    }
}
