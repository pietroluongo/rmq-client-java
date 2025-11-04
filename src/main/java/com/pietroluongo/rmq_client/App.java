package com.pietroluongo.rmq_client;

import org.apache.logging.log4j.LogManager;
import com.pietroluongo.rmq_client.client.Client;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        final var LOGGER = LogManager.getLogger();
        LOGGER.info("Server starting...");
        final var HOST = "127.0.0.1";
        final var PORT = 5672;
        try (var client = new Client(HOST, PORT)) {
        } catch (Exception e) {
            LOGGER.error("Client throws error ", e);
        }
    }
}
