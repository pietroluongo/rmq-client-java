package com.pietroluongo.rmq_client.client.frames;

import java.io.Serializable;

public class ProtocolFrame extends Frame implements Serializable {
    public ProtocolFrame() {
        this.append(new byte[] { 'A', 'M', 'Q', 'P', 0, 0, 9, 1 });
    }

    public ProtocolFrame(byte[] data) {
        super();
        this.append(data);
    }
}
