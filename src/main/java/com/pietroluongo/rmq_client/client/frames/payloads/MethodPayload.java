package com.pietroluongo.rmq_client.client.frames.payloads;

import java.util.ArrayList;

import com.pietroluongo.rmq_client.client.frames.amqp_types.MethodId;

public class MethodPayload extends Payload {
    private MethodId classId;
    private short methodId;

    public MethodPayload(MethodId classId, short methodId) {
        super();
        this.classId = classId;
        this.methodId = methodId;
    }

    @Override
    public byte[] serialize() {
        return new byte[] {};
    }
}
