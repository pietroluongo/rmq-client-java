package com.pietroluongo.rmq_client.client.frames;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrameWriter extends BufferedOutputStream {
    private Logger logger = LogManager.getLogger();
    private boolean autoFlush;

    public FrameWriter(OutputStream s, boolean autoFlush) {
        super(s);
        this.autoFlush = autoFlush;
    }

    public void write(Frame f) throws IOException {
        logger.info("Writing {} to output", f.toBytes());
        this.write(f.toBytes());
        if (this.autoFlush) {
            this.flush();
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        logger.info("CLOSING STREAM");
    }

}
