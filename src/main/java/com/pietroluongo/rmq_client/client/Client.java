package com.pietroluongo.rmq_client.client;

import java.io.InputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pietroluongo.rmq_client.client.frames.FrameWriter;
import com.pietroluongo.rmq_client.client.frames.MethodFrame;
import com.pietroluongo.rmq_client.client.frames.FrameScanner;
import com.pietroluongo.rmq_client.client.frames.FrameType;
import com.pietroluongo.rmq_client.client.frames.ProtocolFrame;
import com.pietroluongo.rmq_client.client.frames.StartConnectionFrame;
import com.pietroluongo.rmq_client.exceptions.FailedToOpenSocketException;

public class Client implements AutoCloseable {
    private Socket socket;
    private InputStream in;
    private FrameWriter out;
    private FrameScanner frames;
    private final static Logger logger = LogManager.getLogger();

    public Client(String host, int port) throws FailedToOpenSocketException {
        try {
            logger.info("Connecting to {}:{}", host, port);
            this.socket = new Socket(host, port);
            this.out = new FrameWriter(this.socket.getOutputStream(), true);
            this.in = this.socket.getInputStream();
            this.frames = new FrameScanner(this.socket.getInputStream());
            this.doHandshake();
        } catch (Exception e) {
            logger.error("Failed to open socket", e);
            throw new FailedToOpenSocketException("Failed to open socket");
        }
    }

    public void doHandshake() {
        try {
            logger.info("Attempting to do handshake");
            var handshakeFrame = new ProtocolFrame();
            this.out.write(handshakeFrame);
            var openConnectionFrame = new StartConnectionFrame();

            this.out.write(openConnectionFrame);

            var response = this.frames.next();

            logger.info("Got response {}", response);

            // logger.info("Sent header {} ({}), waiting for response...",
            // handshakeFrame.toString(),
            // handshakeFrame.toBytes());
            // var response = this.frames.next();
            // if (!response.isOfType(FrameType.METHOD)) {
            // logger.error("Response is of bad type\n", response);
            // }
            // logger.info("Response ok! Frame: ", response);
        } catch (Exception e) {
            dealWithResponseWhenHandshakeFails();
            logger.error("Handshake failed ", e);
        }
    }

    // private Frame readFrame() {
    // try {
    // var response = this.in.readAllBytes();
    // logger.info("read {} bytes", response.length);
    // Frame f = Frame.fromBytes(response);
    // return f;
    // } catch (Exception e) {
    // logger.error("Failed to parse response from amqp", e);
    // }
    // return null;
    // }

    private void dealWithResponseWhenHandshakeFails() {
        try {
            var response = new byte[8];
            var bytes = new byte[8];
            while (in.read(bytes) != -1) {
                for (int i = 0; i < 8; i++) {
                    response[i] = bytes[i];
                }
            }
            var framedResponse = new ProtocolFrame(response);
            logger.info("Got following response from rabbit: {} ({})", framedResponse.toString(),
                    framedResponse.toBytes());

        } catch (Exception e) {
            logger.error("Failed to get response from handshake");
        }

    }

    @Override
    public void close() throws Exception {
        try {
            this.socket.close();
            this.out.close();
            this.in.close();

        } catch (Exception e) {
            logger.error("Failed to destroy client properly", e);
        }
    }
}
