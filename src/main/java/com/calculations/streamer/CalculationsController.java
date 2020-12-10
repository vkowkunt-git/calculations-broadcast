package com.calculations.streamer;

import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculationsController {
    static volatile List<String> results = new ArrayList<>(); //a list to store our results for the session

    /**
     *
     * @param information is mapped to the json message received from the client
     * the necessary calculations are performed by invoking the calculation method of the Calculator class
     * @return the top ten results starting from the latest to the oldest
     * @throws Exception
     */
    @MessageExceptionHandler(MessageConversionException.class)
    @MessageMapping("/information")
    @SendTo("/topic/calculations_subscriber")
    public String calculate(Information information) throws Exception {
        Thread.sleep(500); // simulated delay
        Calculator calculator = new Calculator();
        return calculator.calculation(results, information.getUser(), information.getA(), information.getB(), information.getOperation());
    }

}