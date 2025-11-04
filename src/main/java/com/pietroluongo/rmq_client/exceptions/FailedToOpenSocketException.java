package com.pietroluongo.rmq_client.exceptions;

public class FailedToOpenSocketException extends Exception {
    public FailedToOpenSocketException(String msg) {
        super(msg);
    }
}
