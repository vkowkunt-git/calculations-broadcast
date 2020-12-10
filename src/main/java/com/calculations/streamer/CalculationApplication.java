package com.calculations.streamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 This application utilizes websocket and interacts with the client via Stomp and sockJs client
 the messages are being broadcast with the help of a simple message broker and are received by the
 sockJs and Stomp clients that are subscribed to the topic being broadcast through the message broker
 */
@SpringBootApplication
public class CalculationApplication {

	public static void main(String[] args) {

		SpringApplication.run(CalculationApplication.class, args);
	}

}
