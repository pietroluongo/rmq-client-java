package com.pietroluongo.rmq_client.client.frames.payloads;

public class MethodPayload extends Payload {
    private short classId;
    private short methodId;

    public MethodPayload() {
        super();
    }
}
