/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.handler;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.chat.model.database.Message;
import org.chat.model.database.User;
import org.chat.model.singleton.MessageDatabase;
import org.chat.model.singleton.UserDatabase;

/**
 *
 * @author giano
 */
public class MessageHandler {

    public MessageHandler() {
    }

    public void sendMessage(HttpServletRequest request) {
        String userSender = request.getParameter("userSender");
        String userReceiver = request.getParameter("userReceiver");
        String text = request.getParameter("text");
        
        User senderInstance = UserDatabase.getInstance().get(userSender.toUpperCase());
        User receiverInstance = UserDatabase.getInstance().get(userReceiver.toUpperCase());

        Message m = new Message(receiverInstance, senderInstance, text);

        if (!receiverInstance.getIsGroup()) {
            senderInstance.getUserMessages().get(receiverInstance).add(m);
            senderInstance.getBufferUserMessages().get(receiverInstance).add(m);

            receiverInstance.getUserMessages().get(senderInstance).add(m);
            receiverInstance.getBufferUserMessages().get(senderInstance).add(m);
        } else if (receiverInstance.getIsGroup()) {
            if (UserDatabase.getInstance().containsKey(userSender.toUpperCase())) {
                for (User p : UserDatabase.getInstance().values()) {
                    p.getChatRoombufferMessages().add(m);
                    p.getChatRoomMessages().add(m);
                }
            }
        }

        MessageDatabase.getInstance().add(m);

        (new Thread(new ThreadHandler(m, 2))).start();
        //MessageFacade.addMessage(m);
    }

    public String getAllMessages(HttpServletRequest request) {
        String currentUser = request.getParameter("currentUser");
        String otherUser = request.getParameter("otherUser");
        
        User currentInstance = UserDatabase.getInstance().get(currentUser.toUpperCase());
        User otherInstance = UserDatabase.getInstance().get(otherUser.toUpperCase());

        List<Object> key = new ArrayList<>();
        List<Object> value = new ArrayList<>();
        List<Object> array = new ArrayList<>();

        if (!otherInstance.getIsGroup()) {
            for (int i = 0; i < MessageDatabase.getInstance().size(); i++) {
                
                Message messageIstance = MessageDatabase.getInstance().get(i);
                
                if (messageIstance.getUserByIdFrom().getUsername().toUpperCase().equalsIgnoreCase(currentUser.toUpperCase())
                        && messageIstance.getUserByIdTo().getUsername().toUpperCase().equalsIgnoreCase(otherUser.toUpperCase())) {

                    if (messageIstance.getFromVisibility()) {
                        array.add("MessagesList");
                        key.add("ContactName");
                        value.add(messageIstance.getUserByIdFrom().getUsername());
                        key.add("MessageText");
                        value.add(messageIstance.getTextMessage());
                        key.add("MessageDate");
                        value.add(messageIstance.getTime());
                    }
                }
                if (messageIstance.getUserByIdTo().getUsername().toUpperCase().equalsIgnoreCase(currentUser.toUpperCase())
                        && messageIstance.getUserByIdFrom().getUsername().toUpperCase().equalsIgnoreCase(otherUser.toUpperCase())) {

                    if (messageIstance.getToVisibility()) {
                        array.add("MessagesList");
                        key.add("ContactName");
                        value.add(messageIstance.getUserByIdFrom().getUsername());
                        key.add("MessageText");
                        value.add(messageIstance.getTextMessage());
                        key.add("MessageDate");
                        value.add(messageIstance.getTime());
                    }
                }
            }

            currentInstance.getBufferUserMessages().get(otherInstance).clear();

        } else {
            for (int i = 0; i < currentInstance.getChatRoomMessages().size(); i++) {
                if (currentInstance.getChatRoomMessages().get(i).getFromVisibility()) {
                    array.add("MessagesList");
                    key.add("ContactName");
                    value.add(currentInstance.getChatRoomMessages().get(i).getUserByIdFrom().getUsername());
                    key.add("MessageText");
                    value.add(currentInstance.getChatRoomMessages().get(i).getTextMessage());
                    key.add("MessageDate");
                    value.add(currentInstance.getChatRoomMessages().get(i).getTime());
                }
            }
            currentInstance.getChatRoombufferMessages().clear();
        }
        if (JSONHandler.createJSON(key, value, array)
                .equals("{}")) {
            return JSONHandler.createJSON("MessagesList", "EmptyDatabase");
        }

        return JSONHandler.createJSON(key, value, array);
    }

    public String getAllNewMessages(HttpServletRequest request) {
        String currentUser = request.getParameter("currentUser");
        String otherUser = request.getParameter("otherUser");
        
        User currentInstance = UserDatabase.getInstance().get(currentUser.toUpperCase());
        User otherInstance = UserDatabase.getInstance().get(otherUser.toUpperCase());

        List<Object> key = new ArrayList<>();
        List<Object> value = new ArrayList<>();
        List<Object> array = new ArrayList<>();

        if (!otherInstance.getIsGroup()) {
            if (currentInstance.getBufferUserMessages().get(otherInstance) != null) {
                if (currentInstance.getBufferUserMessages().get(otherInstance).size() > 0) {
                    for (int i = 0; i < currentInstance.getBufferUserMessages().get(otherInstance).size(); i++) {
                        array.add("MessagesList");
                        key.add("ContactName");
                        value.add(currentInstance.getBufferUserMessages().get(otherInstance).get(i).getUserByIdFrom().getUsername());
                        key.add("MessageText");
                        value.add(currentInstance.getBufferUserMessages().get(otherInstance).get(i).getTextMessage());
                        key.add("MessageDate");
                        value.add(currentInstance.getBufferUserMessages().get(otherInstance).get(i).getTime());
                    }
                    currentInstance.getBufferUserMessages().get(otherInstance).clear();
                }
            }
        } else if (otherInstance.getIsGroup()) {
            if (currentInstance.getChatRoombufferMessages() != null) {
                for (int i = 0; i < currentInstance.getChatRoombufferMessages().size(); i++) {
                    array.add("MessagesList");
                    key.add("ContactName");
                    value.add(currentInstance.getChatRoombufferMessages().get(i).getUserByIdFrom().getUsername());
                    key.add("MessageText");
                    value.add(currentInstance.getChatRoombufferMessages().get(i).getTextMessage());
                    key.add("MessageDate");
                    value.add(currentInstance.getChatRoombufferMessages().get(i).getTime());
                }
                currentInstance.getChatRoombufferMessages().clear();
            }
        }

        if (JSONHandler.createJSON(key, value, array).equals("{}")) {
            return JSONHandler.createJSON("MessagesList", "EmptyDatabase");
        }
        return JSONHandler.createJSON(key, value, array);
    }

    public void clearMessages(HttpServletRequest request) {
        String currentUser = request.getParameter("currentUser");
        String otherUser = request.getParameter("otherUser");
        
        User currentInstance = UserDatabase.getInstance().get(currentUser.toUpperCase());
        User otherInstance = UserDatabase.getInstance().get(otherUser.toUpperCase());

        if (!otherInstance.getIsGroup()) {
            for (int i = 0; i < MessageDatabase.getInstance().size(); i++) {
                
                Message messageIstance = MessageDatabase.getInstance().get(i);
                String userByIdFromIstance = MessageDatabase.getInstance().get(i).getUserByIdFrom().getUsername();
                String userByIdToIstance = MessageDatabase.getInstance().get(i).getUserByIdTo().getUsername();
                
                if (userByIdFromIstance.equalsIgnoreCase(currentUser) && userByIdToIstance.equalsIgnoreCase(otherUser)) {
                    messageIstance.setFromVisibility(Boolean.FALSE);
                }
                if (userByIdFromIstance.equalsIgnoreCase(otherUser) && userByIdToIstance.equalsIgnoreCase(currentUser)) {
                    messageIstance.setToVisibility(Boolean.FALSE);
                }
            }
        } else {
            for (int i = 0; i < currentInstance.getChatRoomMessages().size(); i++) {
                currentInstance.getChatRoomMessages().get(i).setFromVisibility(Boolean.FALSE);
            }
        }

        (new Thread(new ThreadHandler(3))).start();
    }
}
