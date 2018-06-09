package org.chat.model.singleton;

import java.util.HashMap;
import java.util.Map;
import org.chat.model.database.User;

public class UserDatabase {

    private static Map<String, User> usersMap = null;

    private UserDatabase() {

    }

    public static Map<String, User> getInstance() {
        if (usersMap == null) {
            usersMap = new HashMap<>();
        }
        return usersMap;
    }

    public static void printSingleton() {
        if (UserDatabase.getInstance() != null) {
            for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();

                System.out.println("KEY: " + key);
                System.out.println("\tVALUE: " + value);
            }
        }
    }
}
