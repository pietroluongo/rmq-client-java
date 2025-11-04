package com.pietroluongo.rmq_client.client.frames;

import com.pietroluongo.rmq_client.client.frames.payloads.StartConnectionPayload;

public class StartConnectionFrame extends MethodFrame {
    public StartConnectionFrame() {
        super();
        this.payload = new StartConnectionPayload();
    }
}
