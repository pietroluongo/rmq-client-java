package com.pietroluongo.rmq_client.client.frames.amqp_types;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.pietroluongo.rmq_client.exceptions.ShortStringIsTooBigException;
import com.pietroluongo.rmq_client.utils.ByteUtils;

public class RShortString {
    private ByteBuffer data;

    public RShortString(String d) throws ShortStringIsTooBigException {
        if (d.length() > 255) {
            throw new ShortStringIsTooBigException("String is too big for short string");
        }

        var bytes = d.getBytes();
        this.data = ByteBuffer.allocate(d.length() + Short.BYTES);
        var szAsShort = ByteUtils.shortToBytes((short) d.length());

        for (int i = 0; i < szAsShort.length; i++) {
            this.data.put(i, szAsShort[i]);
        }

        for (int i = Short.BYTES; i < this.data.capacity(); i++) {
            this.data.put(i, bytes[i - Short.BYTES]);
        }
    }

    public ByteBuffer getData() {
        return this.data.asReadOnlyBuffer();
    }

    public String toString() {
        return Arrays.toString(this.data.array());
    }
}
