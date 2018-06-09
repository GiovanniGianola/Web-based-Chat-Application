/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.singleton;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.chat.model.database.Message;
import org.chat.model.database.User;

/**
 *
 * @author giano
 */
public class MessageDatabase {
    
    private static List<Message> messagesList;

    private MessageDatabase() {

    }

    public static List<Message> getInstance() {
        if (messagesList == null) {
            messagesList = new LinkedList<>();
        }
        return messagesList;
    }

    public static void printSingleton() {
        if (MessageDatabase.getInstance() != null) {
            for (int i = 0; i < MessageDatabase.getInstance().size(); i++) {

                System.out.println("Massage #" + (i) + ": " + MessageDatabase.getInstance().get(i));
            }
        }
    }
    
}
