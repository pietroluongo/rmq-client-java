package com.pietroluongo.rmq_client.client.frames.amqp_types;

public enum MethodId {
    CONNECTION((short) 10),
    CHANNEL((short) 20),
    EXCHANGE((short) 30),
    QUEUE((short) 40),
    BASIC((short) 50),
    TX((short) 90);

    private final short index;

    MethodId(short index) {
        this.index = index;
    }

    public short getValue() {
        return index;
    }

    public static MethodId fromShortArray(short[] bytes) {
        return MethodId.fromShort(bytes[0]);
    }

    public static MethodId fromShort(short b) {
        return MethodId.values()[b];
    }

}
