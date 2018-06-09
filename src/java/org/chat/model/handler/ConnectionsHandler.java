/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chat.model.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.chat.model.database.User;
import org.chat.model.singleton.UserDatabase;

/**
 *
 * @author giano
 */
public class ConnectionsHandler {

    public String checkConnections(HttpServletRequest request) {
        String user = request.getParameter("username");

        List<Object> key = new ArrayList<>();
        List<Object> value = new ArrayList<>();
        List<Object> array = new ArrayList<>();

        for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(user)) {
                if (entry.getValue().getIsGroup()){
                    array.add("ContactsList");
                    key.add("ContactName");
                    value.add(entry.getValue().getUsername());
                    key.add("UnreadMessages");
                    value.add(UserDatabase.getInstance().get(user.toUpperCase()).getChatRoombufferMessages().size());
                    key.add("IsOnline");
                    value.add(entry.getValue().isOnline());
                    key.add("IsGroup");
                    value.add(entry.getValue().getIsGroup());
                }
            }
        }
        
        for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(user)) {
                if (!entry.getValue().getIsGroup()){
                    array.add("ContactsList");
                    key.add("ContactName");
                    value.add(entry.getValue().getUsername());
                    key.add("UnreadMessages");
                    value.add(UserDatabase.getInstance().get(user.toUpperCase()).countSizeOfBuffer(entry.getValue().getUsername()));
                    key.add("IsOnline");
                    value.add(entry.getValue().isOnline());
                    key.add("IsGroup");
                    value.add(entry.getValue().getIsGroup());
                }
            }
        }

        if (JSONHandler.createJSON(key, value, array).equals("{}")) {
            return JSONHandler.createJSON("ContactsList", "EmptyDatabase");
        }
        return JSONHandler.createJSON(key, value, array);
    }

    public String searchContacts(HttpServletRequest request) {
        String query = request.getParameter("query");
        String user = request.getParameter("username");

        List<Object> key = new ArrayList<>();
        List<Object> value = new ArrayList<>();
        List<Object> array = new ArrayList<>();

        for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
            if (entry.getKey().contains(query.toUpperCase())) {
                if (entry.getValue().getIsGroup()){
                    array.add("SearchContact");
                    key.add("ContactName");
                    value.add(entry.getValue().getUsername());
                    key.add("UnreadMessages");
                    value.add(UserDatabase.getInstance().get(user.toUpperCase()).getChatRoombufferMessages().size());
                    key.add("IsOnline");
                    value.add(entry.getValue().isOnline());
                    key.add("IsGroup");
                    value.add(entry.getValue().getIsGroup());
                }
            }
        }
        
        for (Map.Entry<String, User> entry : UserDatabase.getInstance().entrySet()) {
            if (entry.getKey().contains(query.toUpperCase())) {
                if (!entry.getValue().getIsGroup()){
                    array.add("SearchContact");
                    key.add("ContactName");
                    value.add(entry.getValue().getUsername());
                    key.add("UnreadMessages");
                    value.add(UserDatabase.getInstance().get(user.toUpperCase()).countSizeOfBuffer(entry.getValue().getUsername()));
                    key.add("IsOnline");
                    value.add(entry.getValue().isOnline());
                    key.add("IsGroup");
                    value.add(entry.getValue().getIsGroup());
                }
            }
        }

        if (JSONHandler.createJSON(key, value, array)
                .equals("{}")) {
            return JSONHandler.createJSON("SearchContact", "EmptyDatabase");
        }

        return JSONHandler.createJSON(key, value, array);
    }

    private boolean checkChanges() {
        return true;
    }

    public void setProperties(HttpServletRequest request, String online) {
        String name = request.getParameter("username");

        if (online.equalsIgnoreCase("Online")) {
            UserDatabase.getInstance().get(name.toUpperCase()).setOnline(true);
        } else if (online.equalsIgnoreCase("Offline")) {
            UserDatabase.getInstance().get(name.toUpperCase()).setOnline(false);
        }
    }
}
