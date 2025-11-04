package com.pietroluongo.rmq_client.exceptions;

public class ShortStringIsTooBigException extends Exception {
    public ShortStringIsTooBigException(String msg) {
        super(msg);
    }
}
