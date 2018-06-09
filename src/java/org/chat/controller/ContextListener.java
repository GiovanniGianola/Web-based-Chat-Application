package org.chat.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.chat.model.handler.DatabaseHandler;
import org.chat.model.singleton.UserDatabase;

public class ContextListener implements ServletContextListener {

    private DatabaseHandler DH;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.DH = new DatabaseHandler();
        this.DH.restoreLocalDatabase();
        
        System.out.println("Server starting...");
        //System.out.println(this.DH.toString());
        //UserDatabase.printSingleton();
        
        //User ChatRoom = new User("ChatRoom", "P@ssw0rd");
        //ChatRoom.setIsGroup(Boolean.TRUE);
        //UserDatabase.getInstance().put("ChatRoom".toUpperCase(), ChatRoom);
        //UserFacade.addUser(ChatRoom);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server stopping...");
    }
    
}
