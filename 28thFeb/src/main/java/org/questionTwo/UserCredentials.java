package org.questionTwo;

import java.util.HashMap;

public class UserCredentials extends HashMap<String, String> {

    @Override
    public String put(String username, String password) {

        if (this.containsKey(username)) {
            throw new UsernameExistsException(
                    "Username '" + username + "' already exists!"
            );
        }

        System.out.println("User added Successfully"); // -> Just for confirmation
        return super.put(username, password);
    }
}


