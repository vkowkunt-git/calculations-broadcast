package com.calculations.streamer;

/*
   setting all the json values to variables received from the client
 */
public class Information {
    private String user;
    private String a;
    private String b;
    private String operation;

    public Information(String user, String a, String b, String operation) {
        this.user = user;
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    //getters and setters to access the values
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
