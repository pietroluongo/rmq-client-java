package com.pietroluongo.rmq_client.frames.amqp_types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import com.pietroluongo.rmq_client.client.frames.amqp_types.RLongString;

public class RLongStringTest {
    @Test
    public void CreatesBufferAsExpected() {
        var unit = new RLongString("abcd");
        var expected = ByteBuffer.wrap(new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4, 'a', 'b', 'c', 'd' });
        int mismatch = expected.mismatch(unit.getData());

        assertEquals(-1, mismatch);
    }
}
