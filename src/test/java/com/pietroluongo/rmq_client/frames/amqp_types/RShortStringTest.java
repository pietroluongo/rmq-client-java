package com.pietroluongo.rmq_client.frames.amqp_types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.pietroluongo.rmq_client.client.frames.amqp_types.RShortString;
import com.pietroluongo.rmq_client.exceptions.ShortStringIsTooBigException;

public class RShortStringTest {
    @Test
    public void CreatesBufferAsExpected() throws ShortStringIsTooBigException {
        var unit = new RShortString("abcdef");
        var expected = ByteBuffer.wrap(new byte[] { 0x0, 0x6, 'a', 'b', 'c', 'd', 'e', 'f' });
        int mismatch = expected.mismatch(unit.getData());

        System.out.printf("Generated is %s, expected is %s", unit, Arrays.toString(expected.array()));

        assertEquals(-1, mismatch);
    }

    @Test
    public void RejectsBigStrings() {
        var s = new String(new char[999]).replace("/0", "x");
        assertThrows(ShortStringIsTooBigException.class, () -> {
            new RShortString(s);
        });
    }
}
