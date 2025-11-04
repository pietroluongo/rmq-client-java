package com.pietroluongo.rmq_client.client.frames;

public class HeaderFrame extends Frame {
    @Override
    public String toString() {
        String original = super.toString();
        return String.format("Header Frame\nRaw content: %s", original);
    }
}
