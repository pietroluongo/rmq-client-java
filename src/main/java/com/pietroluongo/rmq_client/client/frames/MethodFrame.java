package com.pietroluongo.rmq_client.client.frames;

import com.pietroluongo.rmq_client.utils.ByteUtils;

public class MethodFrame extends Frame {
    public MethodFrame() {
        super();
        this.type = FrameType.METHOD;
        this.channel = 0;
        this.payload = null;
        this.size = 0;

        this.append(
                new byte[] { (byte) 0x1, (byte) 0x0, (byte) 0x10, (byte) 0x10, (byte) 0x9, (byte) 0x1, (byte) 0x0 }); // connection,
                                                                                                                      // start,
        // maj, min, props,
        // mechs, locales
    }

    @Override
    public String toString() {
        String original = super.toString();
        return String.format("Method Frame\nRaw content: %s\nFrameType: %d", original, this.type);
    }

    @Override
    protected void serialize() {
        super.serialize();
        // missing serialized payload
        this.append(Constants.FRAME_END);
    }
}
