package com.pietroluongo.rmq_client.client.frames.amqp_types;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.pietroluongo.rmq_client.utils.ByteUtils;

public class RLongString {
    private ByteBuffer data;

    public RLongString(String d) {
        this.data = ByteBuffer.allocate(d.length() + Long.BYTES);
        var bytes = d.getBytes();
        var szAsBytes = ByteUtils.longToBytes(d.length());
        for (int i = 0; i < Long.BYTES; i++) {
            this.data.put(i, szAsBytes[i]);
        }

        for (int i = Long.BYTES; i < data.capacity(); i++) {
            this.data.put(i, bytes[i - Long.BYTES]);
        }
    }

    public ByteBuffer getData() {
        return this.data.asReadOnlyBuffer();
    }

    public String toString() {
        return Arrays.toString(this.data.array());
    }
}
