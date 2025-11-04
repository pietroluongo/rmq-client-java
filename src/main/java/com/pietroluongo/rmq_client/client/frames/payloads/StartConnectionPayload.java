package com.pietroluongo.rmq_client.client.frames.payloads;

import com.pietroluongo.rmq_client.client.frames.amqp_types.MethodId;

public class StartConnectionPayload extends MethodPayload {
    private static final short METHOD_ID = 10;

    public StartConnectionPayload() {
        super(MethodId.CONNECTION, METHOD_ID);
    }
}
