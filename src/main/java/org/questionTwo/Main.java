package org.questionTwo;

public class Main {
    public static void main(String[] args) {

        UserCredentials users = new UserCredentials();

        users.put("sushant", "1234");
        users.put("sachin", "4321");

        // This should throw exception
        users.put("sushant", "3442");
    }
}