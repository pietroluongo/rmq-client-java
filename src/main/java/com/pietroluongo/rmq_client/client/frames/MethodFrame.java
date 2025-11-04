package com.pietroluongo.rmq_client.client.frames;

public class MethodFrame extends Frame {
    public MethodFrame() {
        super();
        this.type = FrameType.METHOD;
        this.channel = 0;
        this.payload = null;
    }

    @Override
    public String toString() {
        String original = super.toString();
        return String.format("Method Frame\nRaw content: %s\nFrameType: %d", original, this.type);
    }
}
