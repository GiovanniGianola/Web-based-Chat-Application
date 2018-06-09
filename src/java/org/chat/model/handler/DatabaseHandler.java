/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.handler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.chat.model.database.Message;
import org.chat.model.database.MessageFacade;
import org.chat.model.database.User;
import org.chat.model.database.UserFacade;
import org.chat.model.singleton.MessageDatabase;
import org.chat.model.singleton.UserDatabase;

/**
 *
 * @author giano
 */
public class DatabaseHandler {

    private List<User> userDB = null;
    private List<Message> messageDB = null;

    public DatabaseHandler() {
    }

    public void restoreLocalDatabase() {
        try {
            messageDB = MessageFacade.getAllMessages();
            userDB = UserFacade.getAllUsers();

            restoreUsers(userDB);
            restoreMessages(messageDB);
            System.out.println("Restored All");

        } catch (Exception e) {
            System.err.println("ERROR: " + Arrays.toString(e.getStackTrace()));
            System.err.println("ERROR: " + e);
        }
    }

    private void restoreUsers(List<User> users) {
        if (users != null && !users.isEmpty()) {
            for (User u : users) {
                for (User u1 : users) {
                    if (!u.getUsername().equals(u1.getUsername())) {
                        u.getUserMessages().put(u1, new LinkedList<>());
                        u.getBufferUserMessages().put(u1, new LinkedList<>());
                    }
                }
                UserDatabase.getInstance().put(u.getUsername().toUpperCase(), u);
            }
        } else {
            System.out.println("User Table Empty.");
        }
    }

    private void restoreMessages(List<Message> messages) {
        
        

        if (messages != null && !messages.isEmpty()) {
            for (Message m : messages) {
                
                User userByIdFromInstance = UserDatabase.getInstance().get(m.getUserByIdFrom().getUsername().toUpperCase());
                User userByIdToInstance = UserDatabase.getInstance().get(m.getUserByIdTo().getUsername().toUpperCase());
                
                if (userByIdFromInstance.getUserMessages() == null) {
                    userByIdFromInstance.getUserMessages().put(userByIdToInstance, new LinkedList<>());
                }

                if (userByIdFromInstance.getBufferUserMessages() == null) {
                    userByIdFromInstance.getBufferUserMessages().put(userByIdToInstance, new LinkedList<>());
                }

                if (userByIdToInstance.getUserMessages() == null) {
                    userByIdToInstance.getUserMessages().put(userByIdFromInstance, new LinkedList<>());
                }

                if (userByIdToInstance.getBufferUserMessages() == null) {
                    userByIdToInstance.getBufferUserMessages().put(userByIdFromInstance, new LinkedList<>());
                }

                userByIdFromInstance.getUserMessages().get(userByIdToInstance).add(m);
                userByIdToInstance.getUserMessages().get(userByIdFromInstance).add(m);

                if (m.getUserByIdTo().getIsGroup()) {
                    for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
                        UserDatabase.getInstance().get(entry.getKey().toUpperCase()).getChatRoomMessages().add(m);
                    }
                }
                
                MessageDatabase.getInstance().add(m);
            }
        } else {
            System.out.println("Message Table Empty.");
        }
    }
    
    public void backupDatabase() {

    }

    @Override
    public String toString() {

        StringBuilder strDB = new StringBuilder("\n**** DATABASE ****\n");

        if (userDB != null && !userDB.isEmpty()) {
            strDB.append("\tUSER table: \n");
            userDB.stream().forEach((userDB1) -> {
                strDB.append("\t").append(userDB1.toString()).append("\n");
            });
        } else {
            strDB.append("\tUSER table: empty\n");
        }

        if (messageDB != null && !messageDB.isEmpty()) {
            strDB.append("\n\tMESSAGE table: \n");
            messageDB.stream().forEach((messageDB1) -> {
                strDB.append("\t").append(messageDB1.toString()).append("\n");
            });
        } else {
            strDB.append("\tMESSAGE table: empty\n");
        }

        return strDB.toString();
    }
}
