package com.calculations.streamer;

import java.util.List;

public class Calculator {
    /**
     *
     * @param results the list to store the results
     * @param user name of the user received
     * @param a first value in the calculation
     * @param b second value in the calculation
     * @param operation type of calculating operation that needs to be performed
     *                  This method calculates a and b with the operation received adds to the
     *                  results list and returns the top ten values ranging from the latest to oldest
     * @return the results string
     */
    public synchronized String calculation(List<String> results, String user, String a, String b, String operation) {
        StringBuilder buildMessage= new StringBuilder();
        try {
            double result = 0;
            double aValue = Double.parseDouble(a); //changing the string values to double to perform the calculation
            double bValue = Double.parseDouble(b);

            //performing the calculation based on the type of operation
            if (operation.equals("+")) {
                result = aValue + bValue;
            }

            if (operation.equals("-")) {
                result = aValue - bValue;
            }

            if (operation.equals("*")) {
                result = aValue * bValue;
            }

            if (operation.equals("/")) {
                result = aValue / bValue;
            }
            //adding the result to the list
            results.add(user + " performed the calculation " + a + " " + operation + " " + b + " " + "=" + " " + result);

            //iterating through the list from the latest element
            for (int i = results.size() - 1; i >= results.size() - 10; i--) {
                buildMessage.append("<b>").append(results.get(i)).append("<b>").append("<br>");
                if (i == 0) { //ensuring to not to go over 0 during the iteration
                    break;
                }
            }
        }catch(Exception e){
            buildMessage.setLength(0); //returns a simple invalid values message when an exception is occured
            results.add(user+" entered an invalid calculation, please check the values entered "+user);
            for (int i = results.size() - 1; i >= results.size() - 10; i--) {
                buildMessage.append("<b>").append(results.get(i)).append("<b>").append("<br>");
                if (i == 0) { //ensuring to not to go over 0 during the iteration
                    break;
                }
            }
        }finally{
            return buildMessage.toString();
        }

    }
}



