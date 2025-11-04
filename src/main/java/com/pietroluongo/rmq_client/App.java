package com.pietroluongo.rmq_client;

import java.net.Socket;
// import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.LogManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // final var LOGGER = LogManager.getLogger();
        // LOGGER.info("Server starting...");
        try {
            var s = new Socket("localhost", 5672);
        } catch (Exception e) {
            // LOGGER.error("Something went wrong!", e);
        }

    }
}
