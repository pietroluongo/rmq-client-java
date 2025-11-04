package com.pietroluongo.rmq_client.client.frames;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrameScanner implements Iterator<Frame> {
    private Scanner scanner;
    private Logger logger = LogManager.getLogger();

    public FrameScanner(InputStream r) {
        this.scanner = new Scanner(r);
    }

    @Override
    public Frame next() {
        logger.info("Getting next frame");
        var bytes = new ByteArrayOutputStream();
        do {
            bytes.write(this.scanner.nextByte());
        } while (this.scanner.hasNextByte());
        logger.info("Read {} bytes", bytes.size());
        try {
            return Frame.fromBytes(bytes.toByteArray());
        } catch (Exception e) {
            throw new Error("Failed to match frame");
        }
    }

    @Override
    public boolean hasNext() {
        return this.scanner.hasNext();
    }

}
